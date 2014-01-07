package com.luxsoft.mobix



import spock.lang.*

/**
 *
 */
class VentaDetIntegrationSpec extends Specification {

    

    void "Salvar partidas de ventas"() {
		
		given:'Una venta existente'
		def empresa=Empresa.build()
		def venta=Venta.build(empresa:empresa)
		
		when:'Agregamos partidas de venta'
		def prod1=Producto.build(empresa:empresa,descripcion:'Producto 1')
		def prod2=Producto.build(empresa:empresa,descripcion:'Producto 2')
		def partida1=VentaDet.buildWithoutSave(venta:venta,cantidad:10,precio:10,comentario:'Partida 1',producto:prod1)
		def partida2=VentaDet.buildWithoutSave(venta:venta,cantidad:10,precio:20,comentario:'Partida 2',producto:prod2)
		//venta.addToPartidas(partida1)
		//venta.addToPartidas(partida2)
		//venta.partidas[0].actualizarImportes()
		venta.actualizarImportes()
		venta.save(failOnError:true)
		
		then:'La venta con sus partidas es persistida correctamente'
		Venta.get(venta.id).partidas.size==2
		Venta.get(venta.id).partidas.sum{it.cantidad}==20
		Venta.get(venta.id).partidas.sum{it.importe}==300
		Venta.get(venta.id).partidas.each {
			println it
		}
		
    }
}
