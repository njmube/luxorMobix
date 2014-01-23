package com.luxsoft.mobix

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.CuentaPredial;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados.Traslado;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Receptor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.TipoDeComprobante;

import com.luxsoft.cfdi.CFDIUtils;
import com.luxsoft.cfdi.Cfdi;
import com.luxsoft.utils.MonedaUtils;
import com.luxsoft.utils.Rounding

class Venta {
	
	Empresa empresa
	Date fecha
	String tipo="VENTA"
	Cliente cliente
	Currency moneda=Currency.getInstance('MXN');
	BigDecimal tc
	BigDecimal importe=0
	BigDecimal descuentos=0
	BigDecimal subtotal=0
	BigDecimal impuestos=0
	BigDecimal total=0
	int plazo=0
	Date vencimiento
	String formaDePago
	String comentario
	List partidas=[]
	
	BigDecimal saldo=0
	BigDecimal pagosAplicados=0
	
	
	
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
	
	
	Object asType(Class type){
		if(type==Cfdi){
			def cfdi=new Cfdi(
				tipo:'FACTURA'
				,tipoDeCfdi:'I'
				,fecha:fecha
				,origen:id.toString()
				,emisor:empresa.clave
				,receptor:cliente.nombre
				,rfc:cliente.rfc
				,importe:importe
				,descuentos:descuentos
				,subtotal:subtotal
				,impuesto:impuestos
				,total:total
				)
			return cfdi
		}else if(type==ComprobanteDocument){
			final ComprobanteDocument document=ComprobanteDocument.Factory.newInstance()
			final Comprobante comprobante=document.addNewComprobante()
			CFDIUtils.depurar(document)
			comprobante.setVersion("3.2")
			comprobante.setFecha(CFDIUtils.toXmlDate(new Date()).getCalendarValue())
			comprobante.setFormaDePago("PAGO EN UNA SOLA EXHIBICION")
			comprobante.setMetodoDePago(this.formaDePago)
			comprobante.setMoneda(this.moneda.getCurrencyCode())
			comprobante.setTipoCambio(this.tc.toString())
			comprobante.setDescuento(this.descuentos)
			comprobante.setTipoDeComprobante(TipoDeComprobante.INGRESO)
			comprobante.setLugarExpedicion(this.empresa.direccion.pais)
			
			Emisor emisor=CFDIUtils.registrarEmisor(comprobante, empresa)
			Receptor receptor=CFDIUtils.registrarReceptor(comprobante, this.cliente)
			comprobante.setTotal(this.total)
			comprobante.setSubTotal(this.importe)			
			comprobante.setNoCertificado(this.empresa.numeroDeCertificado)
			comprobante.setNumCtaPago(this.cliente.cuentaDePago)
			Impuestos impuestos=comprobante.addNewImpuestos()
			if(this.cliente.rfc=='XAXX010101000'){
				comprobante.setSubTotal(this.total)
				comprobante.setDescuento(this.descuentos)
			}else{
				impuestos.setTotalImpuestosTrasladados(this.impuestos);
				Traslados traslados=impuestos.addNewTraslados();
				Traslado traslado=traslados.addNewTraslado();
				traslado.setImpuesto(Traslado.Impuesto.IVA);
				traslado.setImporte(this.impuestos);
				traslado.setTasa(MonedaUtils.IVA.multiply(BigDecimal.valueOf(100)));
				comprobante.setDescuento(this.descuentos);
			}
			Conceptos conceptos=comprobante.addNewConceptos()
			
			this.partidas.each {det->
				Concepto c=conceptos.addNewConcepto()
				c.setCantidad(det.cantidad.abs())
				c.setUnidad(det.producto.unidad)
				c.setNoIdentificacion(det.id.toString())
				c.setDescripcion(det.producto.descripcion)
				c.setValorUnitario(det.precio);
				c.setImporte(det.importe);
				if(cliente.rfc=='XAXX010101000'){
					c.setValorUnitario(det.calcularPercioConImpuesto())
					c.setImporte(det.calcularImporteConImpuesto())
				}
				if(det.producto.cuentaPredial){
					CuentaPredial cp=c.addNewCuentaPredial()
					cp.setNumero(det.producto.cuentaPredial)
				}
				
				
			}
			return document
		}else
			return super.asType(type)
	}
	
	
}
