package com.luxsoft.cfdi



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.namespace.QName;

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.TUbicacion;
import mx.gob.sat.cfd.x3.TUbicacionFiscal;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Receptor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.TipoDeComprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor.RegimenFiscal;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados.Traslado;

import org.apache.commons.lang.StringUtils;



import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDateTime;
import org.bouncycastle.util.encoders.Base64;

import com.luxsoft.mobix.Cliente;
import com.luxsoft.mobix.Direccion;
import com.luxsoft.mobix.Empresa;
import com.luxsoft.mobix.VentaDet
import com.luxsoft.mobix.Venta;
import com.luxsoft.utils.MonedaUtils;

import grails.transaction.Transactional

@Transactional
class CfdiService {
	
	def cadenaOriginalService 
	
	def selladorService

    def Cfdi generarCfdi(def source) {
		
		final ComprobanteDocument document=ComprobanteDocument.Factory.newInstance()
		Comprobante comprobante=document.addNewComprobante()
		depurar(document)
		comprobante.setVersion("3.2")
		comprobante.setFecha(getFecha())
		comprobante.setFormaDePago("PAGO EN UNA SOLA EXHIBICION")
		comprobante.setMetodoDePago("NO IDENTIFICADO")
		comprobante.setTipoCambio(source.tc)
		comprobante.setMoneda(source.moneda.getCurrencyCode())
		
		//Especificos por el tipo de operacion
		registrarGenerales(comprobante, source)
		
		Empresa empresa=source.empresa
		
		//Emisor
		Emisor emisor=registrarEmisor(comprobante, empresa)
		
		// Expedido en
		TUbicacion ubicacion=comprobante.getEmisor().addNewExpedidoEn()
		fillUbicacion(ubicacion, empresa.direccion)
		
		//Receptor
		Receptor receptor=registrarReceptor(comprobante, source.cliente)
		
		Conceptos conceptos=registrarConceptos(comprobante, source)
		
		//Totales
		comprobante.setTotal(source.total)
		comprobante.setSubTotal(source.importe)
		Impuestos impuestos=comprobante.addNewImpuestos()
		if(source.cliente.rfc=='XAXX010101000'){
			comprobante.setSubTotal(source.total)
			comprobante.setDescuento(source.descuentos)
		}else{
			impuestos.setTotalImpuestosTrasladados(source.impuestos);
			Traslados traslados=impuestos.addNewTraslados();
			Traslado traslado=traslados.addNewTraslado();
			traslado.setImpuesto(Traslado.Impuesto.IVA);
			traslado.setImporte(source.impuestos);
			traslado.setTasa(MonedaUtils.IVA.multiply(BigDecimal.valueOf(100)));
			comprobante.setDescuento(source.descuentos);
		}
		registrarSerieFolio(comprobante, empresa, source)
		
		String cadena=cadenaOriginalService.generarCadenaOriginal(document)
		String sello=selladorService.generarSello(empresa, cadena)
		comprobante.setSello(sello)
		byte[] encodedCert = Base64.encode(empresa.getCertificado().getEncoded());
		comprobante.setCertificado(new String(encodedCert))
		comprobante.setNoCertificado(empresa.numeroDeCertificado)
		
		def cfdi=new Cfdi(
			serie:comprobante.getSerie()
			,folio:comprobante.getFolio()
			,fecha: source.fecha,  
			)

    }
	
	Calendar getFecha(){
		Calendar c=Calendar.getInstance()
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
		XmlDateTime xmlDateTime = XmlDateTime.Factory.newInstance()
		xmlDateTime.setStringValue(df.format(c.getTime()))
		return xmlDateTime.getCalendarValue()
	}
	
	
	void depurar(ComprobanteDocument document){
		XmlCursor cursor=document.newCursor()
		if(cursor.toFirstChild()){
			QName qname=new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation","xsi")
			cursor.setAttributeText(qname,"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd" )
			cursor.toNextToken()
			cursor.insertNamespace("cfdi", "http://www.sat.gob.mx/cfd/3")
		}
	}
	
	TUbicacionFiscal fillDomicilioFiscal(final Direccion direccion,final TUbicacionFiscal domicilio){
		domicilio.setCalle(direccion.calle)
		domicilio.setCodigoPostal(direccion.codigoPostal)
		domicilio.setColonia(direccion.colonia)
		domicilio.setEstado(direccion.estado)
		domicilio.setMunicipio(direccion.municipio)
		domicilio.setNoExterior(direccion.numeroExterior)
		domicilio.setNoInterior(direccion.numeroInterior)
		domicilio.setPais(direccion.pais)
		return domicilio
	}
	
	TUbicacion fillUbicacion(TUbicacion ubicacion,Direccion direccion){
		ubicacion.setCalle(direccion.calle)
		ubicacion.setCodigoPostal(direccion.codigoPostal)
		ubicacion.setColonia(direccion.colonia)
		ubicacion.setEstado(direccion.estado)
		ubicacion.setMunicipio(direccion.municipio)
		ubicacion.setNoExterior(direccion.numeroExterior)
		ubicacion.setNoInterior(direccion.numeroInterior)
		ubicacion.setPais(direccion.pais)
		return ubicacion
	}
	
	
	
	Emisor registrarEmisor(Comprobante comprobante,Empresa empresa){
		Emisor emisor=comprobante.addNewEmisor()
		emisor.setNombre(empresa.nombre)
		emisor.setRfc(empresa.rfc)
		String regimen=empresa.regimen
		String[] regs=StringUtils.split(regimen, ';')
		for(String r:regs){
			RegimenFiscal rf=emisor.addNewRegimenFiscal()
			rf.setRegimen(r)
		}
		TUbicacionFiscal domicilioFiscal=emisor.addNewDomicilioFiscal()
		fillDomicilioFiscal(empresa.direccion, domicilioFiscal)
		comprobante.setLugarExpedicion(empresa.direccion.pais)
		return emisor
	}
	
	Receptor registrarReceptor(Comprobante cfd,Cliente cliente){
		Receptor receptor=cfd.addNewReceptor()
		receptor.setNombre(cliente.nombre)
		receptor.setRfc(cliente.rfc)
		Direccion direccion=cliente.direccion
		TUbicacion ubicacion=receptor.addNewDomicilio()
		if(cliente.rfc!='')
			fillUbicacion(direccion,receptor.addNewDomicilio())
		return receptor
	}
	
	Conceptos registrarConceptos(Comprobante comprobante,def source){
		Conceptos conceptos=cfdi.addNewConceptos()
		if(source.class==Venta){
			for(VentaDet det:source.partidas){
				Concepto c=conceptos.addNewConcepto()
				c.setCantidad(det.cantidad.abs())
				c.setUnidad(det.producto.unidad)
				c.setNoIdentificacion(det.id)
				c.setDescripcion(det.producto.descripcion)
				c.setValorUnitario(det.precio);
				c.setImporte(det.importe);
				if(source.cliente.rfc=='XAXX010101000'){
					c.setValorUnitario(det.calcularPercioConImpuesto())
					c.setImporte(det.calcularImporteConImpuesto())
				}
			}
		}
	}
	
	void registrarSerieFolio(Comprobante comprobante,Empresa empresa,def source){
		CfdiFolio folio=CfdiFolio.buscarPorRfcAndSerie(empresa.rfc, empresa.serieParaVenta)
		comprobante.setSerie(folio.serie)
		comprobante.setFolio(folio.next())
	}
	
	void registrarGenerales(Comprobante comprobante,def source){
		if(source.class==Venta)
			comprobante.setTipoDeComprobante(TipoDeComprobante.INGRESO)
	}
}
