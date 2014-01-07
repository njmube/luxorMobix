package com.luxsoft.mobix

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Empresa)
class EmpresaSpec extends Specification {
    

    void "Persistencia del certificado"() {
		setup:
		File certificado=new File("test/unit/00001000000202171318.cer")
		println 'Ruta del certificado: '+certificado.getAbsolutePath()
		
		
		expect:
		certificado.exists()
		certificado.readBytes()
    }
	
	void "Validar la lectura del certificado"(){
		
		given:'Una empresa y un certificado'
		File certificado=new File("test/unit/00001000000202171318.cer")
		byte[] data=certificado.getBytes()
		assert data
		def empresa=new Empresa()
		
		when:'Asignamos un certificado a la empresa'
		empresa.setCertificadoDigital(data)
		
		then:'El certificado en formato X509Certificate es accesible'
		empresa.getCertificado()
		println empresa.getCertificado().getSubjectX500Principal()
	}
	/*
	void "Validar la lectura del certificadoPfx"(){
		
		given:'Una empresa y un certificado'
		File certificado=new File("test/unit/PAPEL_CFDI_CERT.pfx")
		byte[] data=certificado.getBytes()
		assert data
		def empresa=new Empresa()
		
		when:'Asignamos un certificado a la empresa'
		empresa.setCertificadoDigitalPfx(data)
		
		then:'El certificado en formato X509Certificate es accesible'
		empresa.getCertificadoPfx()
		println empresa.getCertificadoPfx().getSubjectX500Principal()
	}*/
}
