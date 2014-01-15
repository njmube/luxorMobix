package com.luxsoft.cfdi

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDateTime;

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor.RegimenFiscal;
import mx.gob.sat.cfd.x3.TUbicacion;
import mx.gob.sat.cfd.x3.TUbicacionFiscal;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Receptor;

import javax.xml.namespace.QName;

import com.luxsoft.mobix.Cliente;
import com.luxsoft.mobix.Direccion;
import com.luxsoft.mobix.Empresa;

class CFDIUtils {

	static XmlDateTime toXmlDate(Date fecha){
		Calendar c=Calendar.getInstance();
		c.setTime(fecha)
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
		XmlDateTime xmlDateTime = XmlDateTime.Factory.newInstance()
		xmlDateTime.setStringValue(df.format(c.getTime()))
		return xmlDateTime
	}
	
	static TUbicacionFiscal generarUbicacionFiscal(final Direccion direccion,final TUbicacionFiscal domicilio){
		assert direccion.validate()," La direccion es incorrecta"
		domicilio.setCalle(direccion.calle)
		domicilio.setCodigoPostal(direccion.codigoPostal)
		domicilio.setColonia(direccion.colonia)
		domicilio.setEstado(direccion.estado)
		domicilio.setMunicipio(direccion.municipio)
		domicilio.setNoExterior(direccion.numeroExterior)
		domicilio.setNoInterior(direccion.numeroInterior?:'_')
		domicilio.setPais(direccion.pais)
		return domicilio
	}
	
	static TUbicacion generarUbicacion(Direccion direccion,TUbicacion ubicacion){
		ubicacion.setCalle(direccion.calle)
		ubicacion.setCodigoPostal(direccion.codigoPostal)
		ubicacion.setColonia(direccion.colonia)
		ubicacion.setEstado(direccion.estado)
		ubicacion.setMunicipio(direccion.municipio)
		ubicacion.setNoExterior(direccion.numeroExterior)
		ubicacion.setNoInterior(direccion.numeroInterior?:'_')
		ubicacion.setPais(direccion.pais)
		return ubicacion
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
	
	static Emisor registrarEmisor(Comprobante comprobante,Empresa empresa){
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
	
	static Receptor registrarReceptor(Comprobante cfd,Cliente cliente){
		Receptor receptor=cfd.addNewReceptor()
		receptor.setNombre(cliente.nombre)
		receptor.setRfc(cliente.rfc)
		Direccion direccion=cliente.direccion
		TUbicacion ubicacion=receptor.addNewDomicilio()
		if(cliente.rfc!='')
			CFDIUtils.generarUbicacion(direccion,ubicacion)
		return receptor
	}
	
}
