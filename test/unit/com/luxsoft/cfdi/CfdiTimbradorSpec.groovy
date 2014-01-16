package com.luxsoft.cfdi

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * Unit Test para el CfdiTimbrador
 * Verifica el correcto timbrado de los Cfdi's
 *
 */
@TestMixin(GrailsUnitTestMixin)
@Build([Cfdi])
class CfdiTimbradorSpec extends Specification {

    def cfdiTimbrador
	
	
	def setup(){
		
		cfdiTimbrador=new CfdiTimbrador()
		cfdiTimbrador.timbradoDePrueba=true
		
	}

    void "timbrar un comprobante valido"() {
		given:'Un comprobante nuevo'
		
		def cfdi=Cfdi.build(
			xmlName:'cfdiDePrueba.xml'
			,xml:new File('test/unit/cfdiDePrueba.xml').getBytes())
		
		
		when:'Mandamos timbrar'
		cfdiTimbrador.timbradoDePrueba=true
		cfdi=cfdiTimbrador.timbrar(cfdi,"PAP830101CR3", "yqjvqfofb")
		
		then:'El Comprobante es timbrado exitosamente'
		cfdi.uuid
		cfdi.timbreFiscal
		cfdi.timbreFiscal.UUID
		println cfdi
		println cfdi.timbreFiscal
		
    }
}
