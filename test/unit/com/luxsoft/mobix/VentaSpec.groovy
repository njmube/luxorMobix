package com.luxsoft.mobix

import grails.buildtestdata.mixin.Build;
import grails.test.mixin.TestFor;

import com.luxsoft.cfdi.Cfdi;

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Venta)
@Build([Venta])
class VentaSpec extends Specification {

    

    void "Convertir a Cfdi"() {
		
		setup: 'Preparamos una venta'
		def venta=Venta.build(privateKey:null)
		assert venta.id
		
		when: 'Cuando convertimos en un Cfdi'
		def cfdi=venta as Cfdi
		
		then: 'El cfdi es correocto'
		cfdi.origen==venta.id
		cfdi.tipo=='FACTURA'
		cfdi.tipoDeCfdi=='I'
		cfdi.emisor=='CLIENTE NOMBRE'
		
    }
}
