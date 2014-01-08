package com.luxsoft.cfdi



import com.luxsoft.mobix.Empresa;

import spock.lang.*

/**
 *
 */
class CfdiFolioIntegrationSpec extends Specification {

    

    void "Persistencia de folios para una empresa"() {
		
		given:'Una empresa existente'
		def empresa=Empresa.build()
		
		when:'Agregamos folios a la empresa'
		//def folio1=CfdiFolio.buildWithoutSave(serie:'SERIE1',empresa:empresa)
		//def folio2=CfdiFolio.buildWithoutSave(serie:'SERIE2',empresa:empresa)
		empresa.folioDeVentas=CfdiFolio.buildWithoutSave(serie:'SERIE1')
		empresa.folioNotasDeCredito=CfdiFolio.buildWithoutSave(serie:'SERIE2')
		empresa.save(failOnError:true)
		
		then:'Los folios se persisten en la base de datos'
		Empresa.get(empresa.id).folioDeVentas.serie=='SERIE1'
		Empresa.get(empresa.id).folioNotasDeCredito.serie=='SERIE2'
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
}
