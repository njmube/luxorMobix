package com.luxsoft.mobix

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(DateTagLib)
class DateTagLibSpec extends Specification {

    

    void "fecha de modificacion"() {
		given:'Una fecha '
		Date now=new Date()
		def expectedFormat=now.format("dd/MM/yyyy hh:mm")
		expect:
		applyTemplate('<lux:modificado date="${date}" />',[date:now])==expectedFormat
    }
}
