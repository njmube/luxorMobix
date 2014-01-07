package com.luxsoft.mobix

import grails.validation.Validateable;
import groovy.transform.ToString;

@Validateable
@ToString(includeNames=true,includePackage=false,excludes="numeroExterior,pais")
class Direccion {
	
	String calle
	String numeroInterior
	String numeroExterior
	String colonia
	String municipio
	String codigoPostal
	String estado
	String pais

	
	static constraints = {
		calle(blank:false,minSize:1,maxSize:200)
		numeroExterior(blank:false,minSize:1,maxSize:50)
		numeroInterior(nullable:true,minSize:1,maxSize:50)
		colonia()
		municipio()
		codigoPostal()
		estado()
		pais(blank:false,minSize:1,maxSize:100)
	}
	
	/*
	String getLabel(){
		return "$calle ($numeroExterior) $colonia"
	}*/

}
