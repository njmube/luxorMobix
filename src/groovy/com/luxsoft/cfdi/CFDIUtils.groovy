package com.luxsoft.cfdi

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.xmlbeans.XmlDateTime;

import mx.gob.sat.cfd.x3.TUbicacion;
import mx.gob.sat.cfd.x3.TUbicacionFiscal;

import com.luxsoft.mobix.Direccion;

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
	
}
