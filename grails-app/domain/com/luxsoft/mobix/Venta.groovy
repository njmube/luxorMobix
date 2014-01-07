package com.luxsoft.mobix

import com.luxsoft.cfdi.Cfdi;
import com.luxsoft.utils.Rounding

class Venta {
	
	Empresa empresa
	Date fecha
	String tipo="VENTA"
	Cliente cliente
	Currency moneda=Currency.getInstance('MXN');
	BigDecimal tc
	BigDecimal importe
	BigDecimal descuentos
	BigDecimal subtotal
	BigDecimal impuestos
	BigDecimal total
	int plazo
	Date vencimiento
	String formaDePago
	String comentario
	List partidas=[]
	
	BigDecimal saldo
	BigDecimal pagosAplicados
	
	
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [partidas:VentaDet]
	
    static constraints = {
		empresa(nullable:false)
		cliente(nullable:false)
		fecha(nullable:false)
		importe(nullable:false,scale:2)
		descuentos(nullable:false,scale:2)
		subtotal(nullable:false,scale:2)
		impuestos(nullable:false,scale:2)
		total(nullable:false,scale:2)
		moneda(nullable:false)
		tc(nullable:false,scale:4)
		plazo(nullable:false)
		vencimiento(nullable:false)
		formaDePago(nullable:false,maxSize:20)
		comentario(blank:true,maxSize:300)
		saldo()
    }
	
	static mapping = {
		partidas lazy:false
		partidas cascade: "all-delete-orphan"
		cliente fetch:'join'
		sort "id"
		//pagosAplicados formula:'(select ifnull(sum(x.total),0) from CXCAplicacion x where x.factura_id=id)'
	}
	
	static transients = ['saldoActual']
	
	public BigDecimal getSaldoActual(){
		def pag=pagosAplicados?:0.0
		return total-pag
	}
	
	def actualizarImportes(){
		partidas.each {it.actualizarImportes()}
		importe=partidas.sum(0.0,{it.importe})
		importe=Rounding.round(importe, 2)
			
		subtotal=importe-descuentos
		impuestos=subtotal*0.16
		impuestos=Rounding.round(impuestos, 2)
		if(cliente.rfc=='XEXX010101000')
			impuestos=0.0
		total=subtotal+impuestos
	}
	
	
	
	
	String toString(){
		return "$id  $cliente : $total"
	}
	
	
	
}
