package com.luxsoft.mobix

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class DireccionSpec extends Specification {

    

    void "To string apropiado"() {
		given:'Una direccion nueva'
		def direccion=new Direccion(
			calle:'calle1'
			,numeroInterior:'10'
			,colonia:'colonia'
			,municipio:'municipio'
			,codigoPostal:'codigoPostal'
			,estado:'estado'
			,pais:'pais'
			)
		
		when:'Generamos generamos un toString'
		def toString=direccion.toString()
		
		then:'El formato es el esperado'
		toString=='Direccion(calle:calle1, numeroInterior:10, colonia:colonia, municipio:municipio, codigoPostal:codigoPostal, estado:estado)'
    }
}
