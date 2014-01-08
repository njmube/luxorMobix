package com.luxsoft.cfdi

import java.util.List;

import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import mx.gob.sat.cfd.x3.TUbicacionFiscal;

import com.luxsoft.mobix.Direccion;

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CFDIUtilsSpec extends Specification {

   

    void "Generar fecha en el formato adecuado"() {
		given:'Una fecha en espcifico'
		def now=new Date()
		
		when:'Obtenemos la fecha en el estandar requerido'
		XmlDateTime xmlDate=CFDIUtils.toXmlDate(now)
		
		then:'La fecha es generada en el formato adecuado'
		//xmlDate.getCalendarValue().getTime()==now
		xmlDate.getStringValue()==now.format("yyyy-MM-dd'T'HH:mm:ss")
		println 'Now: '+xmlDate
		
    }
	
	void "Generar una TUbicacionFiscal"(){
		given:'Una direccion'
		Direccion direccion=new Direccion(calle:'Paseo del potrero'
			,numeroExterior:'109'
			//,numeroInterior:'0'
			,colonia:'Pedregal del Gigante'
			,municipio:'Leon'
			,estado:'Guanajuato'
			,pais:'Mexico'
			,codigoPostal:'37296')
		and:'Una ubicacion fiscal'
		
		TUbicacionFiscal ubicacion=TUbicacionFiscal.Factory.newInstance()
		
		when:'Transformamos la direccion en TUbicacionFiscal'
		TUbicacionFiscal res=CFDIUtils.generarUbicacionFiscal(direccion,ubicacion)
		
		List errors=findErrors(res)
		then:'Obtenemos una ubicacion valida'
		if(errors){
			errors.each {
				println it.getMessage()
			}
		}
		errors.isEmpty()
		/*
		errors.each {
			println it.getMessage()
		}
		errors.isEmpty()
		*/
	}
	
	List findErrors(final XmlObject node){
		final XmlOptions options=new XmlOptions();
		final List errors=new ArrayList();
		options.setErrorListener(errors);
		node.validate(options);
		return errors;
		
	}
}
