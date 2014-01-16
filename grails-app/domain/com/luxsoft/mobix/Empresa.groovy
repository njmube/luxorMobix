package com.luxsoft.mobix

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.luxsoft.cfdi.CFDIUtils;




class Empresa {
	
	String clave
	String nombre
	String rfc
	Direccion direccion
	String regimen
	String numeroDeCertificado
	byte[] certificadoDigital
	byte[] certificadoDigitalPfx
	byte[] llavePrivada
	String passwordPfx
	String xmlDirectory
	X509Certificate certificado
	PrivateKey privateKey
	
	
	Date dateCreated
	Date lastUpdated
	
	
	static embedded = ['direccion']

    static constraints = {
		clave(blank:false,minSize:5,maxSize:15,unique:true)
		nombre(blank:false,maxSize:255,unique:true)
		rfc(blank:false,minSize:12,maxSize:13)
		direccion(nullable:false)
		regimen(blank:false,maxSize:255)
		numeroDeCertificado(blank:false,minSize:1,maxSize:20)
		certificadoDigital(nullable:false,maxSize:1024*1024*2) 
		certificadoDigitalPfx(nullable:true,maxSize:1024*1024*2) 
		llavePrivada(nullable:false,maxSize:1024*1024*2) 
		passwordPfx(nullable:true)
		xmlDirectory(nullable:true,maxSize:250,validator:{dir->
			
			if(dir){
				def file=new File(dir)
				if( !file.exists())
					return 'empresa.xmlDirectory.invalid'
			}
			
		})
    }
	
	static mapping = {
		
	}
	
	static transients = ['certificado','certificadoPfx','privateKey']
	
	X509Certificate getCertificado(){
		if(!certificado){
			assert certificadoDigital,'Debe cargar el binario del certificado '
			log.info('Cargando certificado digital en formato X509')
			CertificateFactory fact= CertificateFactory.getInstance("X.509","BC")
			InputStream is=new ByteArrayInputStream(certificadoDigital)
			certificado = (X509Certificate)fact.generateCertificate(is)
			certificado.checkValidity()
				//is.closeQuietly();
			is.close();
			this.certificado=certificado
		}
		
		return certificado;
	}
	
	String getCertificadoInfo(){
		return "$certificado?.subjectX500Principal"
	}
	
	PrivateKey getPrivateKey(){
		if(!privateKey && llavePrivada){
			final byte[] encodedKey=llavePrivada
			PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(encodedKey)
			final  KeyFactory keyFactory=KeyFactory.getInstance("RSA","BC")
			this.privateKey=keyFactory.generatePrivate(keySpec)
		}
		return privateKey;
	}
	
	String toString(){
		return "$nombre ($rfc)"
	}
	
	
}
