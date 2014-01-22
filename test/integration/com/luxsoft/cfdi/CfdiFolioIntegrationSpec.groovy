package com.luxsoft.cfdi



import com.luxsoft.mobix.Empresa;

import spock.lang.*

/**
 *
 */
class CfdiFolioIntegrationSpec extends Specification {

    

    void "Persistencia de folios para un emisor"() {
		given:'A new folio'
		def folio=new CfdiFolio(serie:'FACTURA',emisor:'PAPEL',folio:0)
		when:'Salvamos el folio'
		folio.save()
		then:'El folio es salvado en la base de datos'
		folio.errors.errorCount==0
		folio.id
		CfdiFolio.get(folio.id)
		println folio
		
    }
	
	/*Modificar para utilizar @Rollup y where the spock*/
	void "Actualizar un folio"(){
		given:'Un folio existente'
		def folio=new CfdiFolio(serie:'FACTURA',emisor:'PAPEL',folio:0).save(failOnError:true)
		
		when:'Actualizamos el folio'
		def found=CfdiFolio.get(folio.id)
		long next=found.next()
		found.save(failOnError:true)
		
		then:'Los cambios son persistidos en la base de datos'
		CfdiFolio.get(found.id).folio==1
		
		
	}
}
