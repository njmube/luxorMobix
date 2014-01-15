package com.luxsoft.cfdi


import mx.gob.sat.cfd.x3.ComprobanteDocument
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import com.luxsoft.mobix.*
 

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CfdiService)
@Build([Cfdi])
class CfdiServiceSpec extends Specification {
	
	

	def "Salvar un cfdi a archivo"(){
		
		given:'Un cfdi existente'
		File file=new File("test/unit/cfdiDePrueba.xml")
		ComprobanteDocument document=ComprobanteDocument.Factory.parse(file)
		assert document.getComprobante()
		
		grailsApplication.config.cfdi.xmlPath=System.properties['user.home']+"/pruebas/cfdi"
		println grailsApplication.config.cfdi.xmlPath
		and:'Un Cfdi existente'
		def cfdi=Cfdi.buildWithoutSave(emisor:'empresaDemo',xmlName:'cfdiDemo1.xml')
		
		when: 'Salvamos el archivo'
		def url=service.salvarArchivo(document,cfdi)
		
		then:'El Comprobante es salvado en el sistema de archivos'
		println url
		ComprobanteDocument target=ComprobanteDocument.Factory.parse(new File(url.toURI()))
		target.getComprobante()
		println target.getComprobante()
		
	}
   
    
}
