package com.luxsoft.mobix

import com.luxsoft.cfdi.CFDIUtils;


import groovy.transform.ToString;

@ToString(includeNames=true,includes=["nombre,rfc,direccion"])
class Cliente {
	
	String nombre
	String rfc
	
	Direccion direccion
	
	Date dateCreated
	Date lastUpdated
	
	static embedded = ['direccion']

    static constraints = {
		nombre(blank:false,maxSize:255,unique:true)
		rfc(blank:false,minSize:12,maxSize:13)
		direccion(nullable:false)
    }
	
	
}
