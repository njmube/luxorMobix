package com.luxsoft.mobix

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.TipoDeComprobante;
import grails.buildtestdata.mixin.Build;
import grails.test.mixin.TestFor;

import com.luxsoft.cfdi.CFDIUtils;
import com.luxsoft.cfdi.Cfdi;

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Venta)
@Build([Venta,Empresa,Cliente])
class VentaSpec extends Specification {

    

    void "Convertir a Cfdi"() {
		
		setup: 'Preparamos una venta'
		def empresa=Empresa.build(nombre:'PAPEL')
		def cliente=Cliente.build(nombre:'CLIENTE_1')
		def venta=Venta.build(empresa:empresa,cliente:cliente)
		assert venta.id
		
		when: 'Cuando convertimos en un Cfdi'
		def cfdi=venta as Cfdi
		
		then: 'El cfdi es correocto'
		cfdi.origen==venta.id.toString()
		cfdi.tipo=='FACTURA'
		cfdi.tipoDeCfdi=='I'
		cfdi.emisor==cliente.nombre
		
    }
	
	void "Convertir a ComprobanteDocument"(){
		setup:'Preparamos una venta'
		def empresa=Empresa.build(nombre:'PAPEL')
		def cliente=Cliente.build(nombre:'CLIENTE_1',rfc:'CARR700317575')
		def venta=Venta.build(empresa:empresa,cliente:cliente)
		assert venta.id
		
		when: 'Convertimos a ComprobnteDocument'
		ComprobanteDocument docto=venta as ComprobanteDocument
		def comprobante=docto.getComprobante()
		
		then: 'El Comprobante se genera correctamente'
		
		comprobante.version=="3.2"
		comprobante.fecha==CFDIUtils.toXmlDate(venta.fecha).getCalendarValue()
		comprobante.formaDePago=="PAGO EN UNA SOLA EXHIBICION"
		comprobante.metodoDePago=="NO IDENTIFICADO"
		comprobante.tipoCambio==venta.tc.toString()
		comprobante.moneda==venta.moneda.getCurrencyCode()
		comprobante.descuento==venta.descuentos
		comprobante.tipoDeComprobante==TipoDeComprobante.INGRESO
		comprobante.lugarExpedicion==empresa.direccion.pais
		comprobante.emisor
		comprobante.receptor
		comprobante.total==venta.total
		comprobante.subTotal==venta.importe
		comprobante.getImpuestos().getTotalImpuestosTrasladados()==venta.impuestos
		comprobante.descuento==venta.descuentos
		comprobante.getConceptos()
		
		
	}
}
