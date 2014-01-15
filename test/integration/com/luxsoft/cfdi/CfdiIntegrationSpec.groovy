package com.luxsoft.cfdi



import com.luxsoft.mobix.Empresa;
import com.luxsoft.mobix.Producto;
import com.luxsoft.mobix.Venta;
import com.luxsoft.mobix.VentaDet;

import spock.lang.*

/**
 *
 */
class CfdiIntegrationSpec extends Specification {

	def cfdiService

    void "Generar CFDI a partir de una Venta"() {
		
		given:'Una venta nueva'
		def empresa=Empresa.build(nombre:'empresaDemo'
			,llavePrivada:new File("test/unit/testPrivateKey.key").getBytes()
			,numeroDeCertificado:'00001000000202171318'
			)
		
		assert empresa.privateKey,'No se ha definido la llave privada para la empresa: '+empresa
		def venta=Venta.build(empresa:empresa)
		def prod1=Producto.build(empresa:empresa,descripcion:'Producto 1')
		def prod2=Producto.build(empresa:empresa,descripcion:'Producto 2')
		def partida1=VentaDet.buildWithoutSave(venta:venta,cantidad:10,precio:10,comentario:'Partida 1',producto:prod1)
		def partida2=VentaDet.buildWithoutSave(venta:venta,cantidad:10,precio:20,comentario:'Partida 2',producto:prod2)
		venta.actualizarImportes()
		venta.save(failOnError:true)
		
		when:'Generamos el Cfdi'
		def cfdi=cfdiService.generarCfdi(venta)
		
		then:'El CFDI es persistido  exitosamente'
		def found=Cfdi.get(cfdi.id)
		found.xml
		found.xmlName=="$found.serie-$found.folio"+'.xml'
		found.getComprobante()
		println found.getComprobanteDocument()
		
    }
}
