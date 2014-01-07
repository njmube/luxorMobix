package com.luxsoft.mobix

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.lang.exception.ExceptionUtils;

import groovy.transform.ToString;


@ToString(includeNames=true,excludes="certificadoDigital")
class Empresa {
	
	String nombre
	String rfc
	Direccion direccion
	String regimen
	String numeroDeCertificado
	byte[] certificadoDigital
	byte[] certificadoDigitalPfx
	byte[] llavePrivada
	String passwordPfx
	
	X509Certificate certificado
	PrivateKey privateKey
	
	Date dateCreated
	Date lastUpdated
	
	
	static embedded = ['direccion']

    static constraints = {
		nombre(blank:false,maxSize:255,unique:true)
		rfc(blank:false,minSize:12,maxSize:13)
		direccion()
		regimen(maxSize:255)
		numeroDeCertificado(minSize:1,maxSize:20)
		certificadoDigital(nullable:true,maxSize:1024*1024*2) //2Mb para guardar el certificado digital
		certificadoDigitalPfx(nullable:true,maxSize:1024*1024*2) 
		llavePrivada(nullable:true,maxSize:1024*1024*2) 
		passwordPfx(nullable:true)
    }
	
	static transients = ['certificado','certificadoPfx']
	
	X509Certificate getCertificado(){
		if(!certificado && getCertificadoDigital()){
			try{
				//java.security.Security.addProvider(new BouncyCastleProvider());
				CertificateFactory fact= CertificateFactory.getInstance("X.509");
				InputStream is=new ByteArrayInputStream(getCertificadoDigital());
				certificado = (X509Certificate)fact.generateCertificate(is);
				certificado.checkValidity();
				//is.closeQuietly();
				is.close();
				return certificado;
			}catch (Exception e) {
				String msg=ExceptionUtils.getRootCauseMessage(e);
				throw new RuntimeException("Error tratando de leer Certificado: "+msg,e);
			}
		}
		return certificado;
	}
	
	
	
	String getCertificadoInfo(){
		//return getCertificado().getSubjectX500Principal()
		//getCertificado().get
		return "$certificado?.subjectX500Principal"
	}
	
	PrivateKey getPrivateKey(){
		if(privateKey==null){
			final byte[] encodedKey=llavePrivada
			PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(encodedKey);
			try {
				final KeyFactory keyFactory=KeyFactory.getInstance("RSA","BC");
				privateKey=keyFactory.generatePrivate(keySpec);
				//log.info("PrivateKey object successfully loaded...");
				
			} catch (Exception e) {
				throw new RuntimeException("Error generando la llave privada", ExceptionUtils.getRootCause(e));
			}
		}
		return privateKey;
	}
}
