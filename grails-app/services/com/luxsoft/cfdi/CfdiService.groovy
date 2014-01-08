package com.luxsoft.cfdi



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
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
		comprobante.setFecha(CFDIUtils.toXmlDate(new Date()).getCalendarValue())
		comprobante.setFormaDePago("PAGO EN UNA SOLA EXHIBICION")
		comprobante.setMetodoDePago("NO IDENTIFICADO")
		comprobante.setTipoCambio(source.tc.toString())
		comprobante.setMoneda(source.moneda.getCurrencyCode())
		
		//Especificos por el tipo de operacion
		registrarGenerales(comprobante, source)
		
		Empresa empresa=source.empresa
		
		//Emisor
		Emisor emisor=registrarEmisor(comprobante, empresa)
		
		// Expedido en
		TUbicacion ubicacion=comprobante.getEmisor().addNewExpedidoEn()
		CFDIUtils.generarUbicacion(empresa.direccion,ubicacion)
		
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
		
		def cfdi=build(comprobante, source)
		cfdi.cadenaOriginal=cadena
		return cfdi;
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
		CFDIUtils.generarUbicacionFiscal(empresa.direccion, domicilioFiscal)
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
			CFDIUtils.generarUbicacion(direccion,ubicacion)
		return receptor
	}
	
	Conceptos registrarConceptos(Comprobante comprobante,def source){
		Conceptos conceptos=comprobante.addNewConceptos()
		if(source.class==Venta){
			for(VentaDet det:source.partidas){
				Concepto c=conceptos.addNewConcepto()
				c.setCantidad(det.cantidad.abs())
				c.setUnidad(det.producto.unidad)
				c.setNoIdentificacion(det.id.toString())
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
		if(source.class==Venta){
			CfdiFolio folio=empresa.folioDeVentas
			assert folio,"No existe folio de ventas registrado para la empresa: $empresa.nombre"
			comprobante.setSerie(folio.serie.toString())
			comprobante.setFolio(folio.next().toString())
		}
		
	}
	
	void registrarGenerales(Comprobante comprobante,def source){
		if(source.class==Venta)
			comprobante.setTipoDeComprobante(TipoDeComprobante.INGRESO)
	}
	
	Cfdi build(Comprobante comprobante,def source){
		
		def cfdi=new Cfdi(
			serie:comprobante.getSerie()
			,folio:comprobante.getFolio()
			,tipoDeCfdi:comprobante.getTipoDeComprobante()
			,fecha:comprobante.fecha.getTime()
			,emisor:comprobante.getEmisor().getNombre()
			,receptor:comprobante.getReceptor().getNombre()
			,rfc:comprobante.getReceptor().getRfc()
			,subtotal:comprobante.getSubTotal()
			,impuesto:comprobante.getImpuestos().getTotalImpuestosTrasladados()
			,total:comprobante.getTotal()
			,numeroDeCertificado:comprobante.getNoCertificado()
			)
		if(source.class==Venta){
			cfdi.tipo='FACTURA'
			
		}
		cfdi.origen=source.id
		return cfdi
	}
	
	def salvar(ComprobanteDocument document,Cfdi cf,Empresa empresa) {
		
		validarDocumento(document)
		
		Comprobante comprobante=document.getComprobante();
		XmlOptions options = new XmlOptions();
		options.setCharacterEncoding("UTF-8");
		options.put( XmlOptions.SAVE_INNER );
		options.put( XmlOptions.SAVE_PRETTY_PRINT );
		options.put( XmlOptions.SAVE_AGGRESSIVE_NAMESPACES );
		options.put( XmlOptions.SAVE_USE_DEFAULT_NAMESPACE );
		options.put(XmlOptions.SAVE_NAMESPACES_FIRST);
		
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		document.save(os, options);
		cf.setXml(os.toByteArray());
		cf.setXmlPath(cf.getSerie()+"-"+cf.getFolio()+".xml");
		cf.save()
		
		File dir=new File(empresa.cfdiPath)
		if(!dir.exists())
			assert dir.mkdir(),"Imposible generar directorio: "+empresa.cfdiPath
		assert dir.exists(),"No existe el directorio para cfdi "+dir
		File xmlFile=new File(dir,cf.xmlPath);
		document.save(xmlFile,options);
		return cf;
	}
	
	void validarDocumento(ComprobanteDocument document) {
		List<XmlValidationError> errores=findErrors(document);
		if(errores.size()>0){
			StringBuffer buff=new StringBuffer();
			for(XmlValidationError e:errores){
				buff.append(e.getMessage()+"\n");
			}
			throw new RuntimeException("Datos para generar el comprobante fiscal (CFD) incorrectos "+buff.toString());
		}
	}
	
	List findErrors(final XmlObject node){
		final XmlOptions options=new XmlOptions();
		final List errors=new ArrayList();
		options.setErrorListener(errors);
		node.validate(options);
		return errors;
		
	}
	
}
