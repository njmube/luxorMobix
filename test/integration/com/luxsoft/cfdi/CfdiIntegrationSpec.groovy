package com.luxsoft.cfdi



import spock.lang.*

/**
 *
 */
class CfdiIntegrationSpec extends Specification {


    void "Salvar Cfdi"() {
		given:'Un Cfdi nuevo'
		def cfdi=Cfdi.build(comentario:'CFDI DE PRUEBA')
		
		when:'Salvamos el cfdi'
		cfdi.save()
		
		then:'El cfdi es persistido en la base de datos'
		cfdi.errors.errorCount==0
		cfdi.id
		Cfdi.get(cfdi.id).comentario=='CFDI DE PRUEBA'
		println Cfdi.get(cfdi.id)
    }
}
