package com.luxsoft.cfdi

import com.luxsoft.mobix.Empresa;
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class CfdiFolio {
	
	
	Long folio
	String serie
	Long folioInicial
	Long folioFinal
	Date asignacion
	Integer numeroDeAprobacion
	Integer anoAprobacion
	
	//static belongsTo = [empresa:Empresa]

    static constraints = {
		folio()
		serie(unique:true)
		folioInicial(minSize:0)
		folioFinal()
		asignacion()
		numeroDeAprobacion()
    }
	
	Long next(){
		assert folio<folioFinal,"Se llego al limite de folios"
		def next=folio++
		this.folio=next
		return next
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj.instanceOf(CfdiFolio)) )
			return false
		if(this.is(obj))
			return true
		def eb=new EqualsBuilder()
		eb.append(serie, obj.serie)
		return eb.isEquals()
	}
	
	@Override
	public int hashCode() {
		def hcb=new HashCodeBuilder(17,35)
		hcb.append(serie)
		return hcb.toHashCode()
	}
	
}
