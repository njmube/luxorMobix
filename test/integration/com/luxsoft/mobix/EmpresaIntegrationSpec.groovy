package com.luxsoft.mobix



import spock.lang.*

/**
 *
 */
class EmpresaIntegrationSpec extends Specification {

	

    void "Salvar una empresa"() {
		given:'Una empresa nueva'
		def empresa=Empresa.buildWithoutSave(nombre:'Empresa de prueba',rfc:'CARR700317575')
		
		when:'Salvamos la empresa'
		empresa.save()
		//println empresa.direccion
		println empresa
		then:'La empresa es persistida en la base de datos'
		empresa.errors.errorCount==0
		empresa.id
		Empresa.get(empresa.id).rfc=='CARR700317575'	
			
    }
	
	void "Actualizar una empresa"(){
		given:'Una empresa existente'
		def empresa=Empresa.build().save(failOnError:true)
		
		
		when:'Cambiamos una propiedad'
		def foundEmpresa=Empresa.get(empresa.id)
		foundEmpresa.rfc='CARR700317575'
		foundEmpresa.save(failOnError:true)
		
		then:'El cambio es registrado en la base de datos'
		Empresa.get(foundEmpresa.id).rfc=='CARR700317575'
		
	}
	
	void "Salvar persistencia de certificado"(){
		
		given:'Una empresa existente y un certificado digital'
		def empresa=Empresa.build().save(failOnError:true)
		
		File certificado=new File("test/integration/00001000000202466134.cer")
		assert certificado.exists()
		byte[] data=certificado.getBytes()
		assert data
		
		when: 'Cuando actualizamos la empresa'
		empresa.certificadoDigital=data
		empresa.save(failOnError:true)
		
		then:' El certificado es persistido en la base de datos'
		Empresa.get(empresa.id).certificadoDigital==data
		
		
		
	}
}
