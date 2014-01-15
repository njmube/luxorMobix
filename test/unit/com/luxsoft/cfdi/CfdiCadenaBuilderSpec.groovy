package com.luxsoft.cfdi

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * Unit Test para CadenaBuilder
 * 
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CfdiCadenaBuilderSpec extends Specification {
	
	CfdiCadenaBuilder cadenaBuilder

	def setup() {
		cadenaBuilder=new CfdiCadenaBuilder()
		cadenaBuilder.xsltFile=new File("web-app/sat/cadenaoriginal_3_2.xslt")
		assert cadenaBuilder.xsltFile.exists()
	}

    void "Probar la generacion de la cadena original"() {
		given:"Un cfdi valido"
		File file=new File("test/unit/cfdiDePrueba.xml")
		assert file.exists(),"No existe el CFDI: "+file
		ComprobanteDocument document=ComprobanteDocument.Factory.parse(file)
		assert document.getComprobante()
		
		when:'Generamos la cadena original'
		String cadena=cadenaBuilder.generarCadena(document)
		
		then:'La cadena es generada'
		cadena
		println cadena
    }
}
