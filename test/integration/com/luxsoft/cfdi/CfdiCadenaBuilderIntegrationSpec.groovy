package com.luxsoft.cfdi



import spock.lang.*

/**
 * Verifica que el Spring bean sea correctamnete armado
 * 
 */
class CfdiCadenaBuilderIntegrationSpec extends Specification {

	def grailsApplication
    
    void "El bean esta correctamente armado"() {
		given:'Grails application context'
		def cfdiCadenaBuilder=grailsApplication.mainContext.cfdiCadenaBuilder
		assert cfdiCadenaBuilder,"No se a configurado el bean cfdiCadenaBuilder"
		
		when:'Accesamos el archivo xslt de transofmraciones'
		File xslt=cfdiCadenaBuilder.xsltFile
		assert xslt,"No esta configurada el archov xslt para la genracion de la cadena original"
		
		then:'El archivo es el correcto'
		xslt.exists()
		xslt.name=='cadenaoriginal_3_2.xslt'
		println 'XSLT : '+xslt.absolutePath
    }
}
