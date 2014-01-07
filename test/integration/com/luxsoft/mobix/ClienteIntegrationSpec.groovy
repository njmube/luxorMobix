package com.luxsoft.mobix



import spock.lang.*


/**
 * Verifica la correcta persistencia de clientes
 * 
 */
class ClienteIntegrationSpec extends Specification {

   

    void "Salvar un Cliente"() {
		given:"Un cliente nuevo"
		def cliente=Cliente.buildWithoutSave(nombre:'Cliente 1')
		
		when:'Salvamos el cliente'
		cliente.save()
		
		then:'El cliente es persistido en la base de datos'
		cliente.errors.errorCount==0
		cliente.id
		Cliente.get(cliente.id).nombre=='Cliente 1'
		
    }
	
	void "Actualizar un cliente"(){
		given:'Un cliente existente'
		def cliente=Cliente.build().save(failOnError:true)
		
		when:'Actualizamos una propiedad'
		def found=Cliente.get(cliente.id)
		found.nombre="Cliente Actualizado"
		found.save(failOnError:true)
		
		then:'Los cambios se reflejan en la base de datos'
		Cliente.get(found.id).nombre=='Cliente Actualizado'
		
	}
	
	void "Eliminar un cliente"(){
		given:'Un cliente existente'
		def cliente=Cliente.build().save(failOnError:true)
		
		when:'Eliminamos el cliente'
		def found=Cliente.get(cliente.id)
		found.delete(flush:true)
		
		then:'El cliente ya no existe en la base de datos'
		!Cliente.exists(found.id)
	}
}
