package com.luxsoft.mobix

import groovy.transform.ToString;

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

import com.luxsoft.utils.MonedaUtils;


class VentaDet {
	
	Producto producto
    BigDecimal cantidad
	BigDecimal precio
	BigDecimal importe
	BigDecimal descuento
	BigDecimal subtotal
	BigDecimal impuesto
	BigDecimal impuestoTasa=16
	BigDecimal costo
	String comentario
	Date dateCreated
	Date lastUpdated

	static belongsTo = [venta:Venta]
	

    static constraints = {
		producto(nullable:false)
		cantidad(nullable:false,scale:3)
		precio(nullable:false,scale:2)
		importe(nullable:false,scale:2)
		descuento(nullable:false,scale:2)
		subtotal(nullable:false,scale:2)
		impuesto(nullable:false,scale:2)
		impuestoTasa(nullable:false,minSize:0,maxSize:100)
		costo(nullable:false,scale:2)
		comentario(nullable:true,maxSize:300)
    }
	
	static mapping = {
		producto fetch:'join'
	}
	
	def actualizarImportes(){
		importe=precio*cantidad
		/*
		println 'Actualizando importes...'
		importe=precio*cantidad
		println 'Importe actualizado a:'+importe
		subtotal=importe-descuento
		impuesto=MonedaUtils.calcularImpuesto(subtotal, impuestoTasa)
		*/
	}
	
	def afterInsert(){
		importe=precio*cantidad
	}
	
	String toString(){
		return "$producto Cantidad: $cantidad  Precio: $precio  Importe: $importe"
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj.instanceOf(VentaDet)) )
			return false
		if(this.is(obj))
			return true
		def eb=new EqualsBuilder()
		eb.append(producto, obj.producto)
		eb.append(cantidad, obj.cantidad)
		eb.append(cantidad, obj.precio)
		eb.append(comentario, obj.comentario)
		return eb.isEquals()
	}
	
	@Override
	public int hashCode() {
		def hcb=new HashCodeBuilder(17,35)
		hcb.append(producto)
		hcb.append(cantidad)
		hcb.append(precio)
		hcb.append(comentario)
		return hcb.toHashCode()
	}
	
	BigDecimal calcularPercioConImpuesto(){
		return precio+MonedaUtils.calcularImpuesto(precio, impuestoTasa)
	}
	
	BigDecimal calcularImporteConImpuesto(){
		return precio+MonedaUtils.calcularImpuesto(importe, impuestoTasa)
	}
}
