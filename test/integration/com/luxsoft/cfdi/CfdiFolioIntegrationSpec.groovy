package com.luxsoft.cfdi



import spock.lang.*

/**
 *
 */
class CfdiFolioIntegrationSpec extends Specification {

    

    void "Salvar un folio nuevo"() {
		given:'Un folio nuevo'
		def folio=CfdiFolio.build(serie:'SERIE1')
		
		when:'Salvamos el folio'
		folio.save()
		
		then:'El nuevo folio es persistido en la base de datos'
		folio.errors.errorCount==0
		folio.id
		CfdiFolio.get(folio.id).serie=='SERIE1'
    }
	
	void "Actualizar un folio"(){
		given:'Un folio existente'
		def folio=CfdiFolio.build(serie:'SERIE1').save(failOnError:true)
		
		when:'Actualizamos una propiedad'
		def found=CfdiFolio.get(folio.id)
		found.folioInicial=1
		found.folioFinal=100
		found.folio=10
		found.save(failOnError:true)
		
		then:'Los cambios son persistidos en la base de datos'
		CfdiFolio.get(found.id).folio==10
		
		
	}
	
	void 'Localizar folio por serie'(){
		given:'Un folio existente'
		def folio=CfdiFolio.build(serie:'SERIE1',rfc:'CARR700317575').save(failOnError:true)
		
		when:'Buscamos el folio por serie'
		def found=CfdiFolio.buscarPorRfcAndSerie("CARR700317575","SERIE1")
		
		then:'El folio es localizado'
		found
		CfdiFolio.get(found.id).serie=='SERIE1'
	}
}
