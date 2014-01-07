package com.luxsoft.mobix



import spock.lang.*

/**
 *
 */
class VentaIntegrationSpec extends Specification {

    

    void "Salvar una venta nueva"() {
		given:'Una venta nueva'
		def venta=Venta.build(fecha:new Date(),comentario:'Venta de prueba')
		
		when:'La venta es salvada'
		venta.save()
		
		then:'Le venta es persistida en la base de datos'
		venta.errors.errorCount==0
		venta.id
		Venta.get(venta.id).comentario=='Venta de prueba'
    }
	
	void 'Actualizar una venta existente'(){
		given:'Una venta existente'
		def venta=Venta.build().save(failOnError:true)
		
		when:'La actualizamos una propiedad'
		def found=Venta.get(venta.id)
		found.comentario='VENTA MODIFICADA'
		found.save(failOnError:true)
		
		then:'La venta es actualizada en la base de datos'
		Venta.get(found.id).comentario=='VENTA MODIFICADA'
	}
}
