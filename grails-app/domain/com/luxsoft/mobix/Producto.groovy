package com.luxsoft.mobix

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class Producto {
	
	Empresa empresa
	String descripcion
	String unidad
	String cuentaPredial
	
	Date dateCreated
	Date lastUpdated

    static constraints = {
		empresa nullable:false
		descripcion blank:false,maxSize:300
		unidad blank:false,maxSize:30
		cuentaPredial nullable:true,maxSize:200
    }
	
	String toString(){
		return "$descripcion"
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj.instanceOf(Producto)) )
			return false
		if(this.is(obj))
			return true
		def eb=new EqualsBuilder()
		eb.append(descripcion, obj.descripcion)
		return eb.isEquals()
	}
	
	@Override
	public int hashCode() {
		def hcb=new HashCodeBuilder(17,35)
		hcb.append(descripcion)
		return hcb.toHashCode()
	}
}
