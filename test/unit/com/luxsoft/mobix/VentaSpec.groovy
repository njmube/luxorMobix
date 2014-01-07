package com.luxsoft.mobix

import com.luxsoft.cfdi.Cfdi;

//import grails.buildtestdata.mixin.Build;
//import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(Venta)
//@Build(Venta)
class VentaSpec extends Specification {

    

    void "Generacion de Cfdi"() {
		given: 'Una venta existene'
		def venta=Venta.build()
		
		when: 'Cuando convertimos en un Cfdi'
		def cfdi=venta as Cfdi
		
		then: 'El cfdi is valido'
		cfdi.getComprobante().validate()
    }
}
