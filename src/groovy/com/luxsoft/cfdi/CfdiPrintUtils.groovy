package com.luxsoft.cfdi


import java.security.Policy.Parameters;
import java.text.MessageFormat;

import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x3.TUbicacion;

import org.apache.commons.lang.StringUtils;

import com.luxsoft.utils.ImporteALetra;




class CfdiPrintUtils {
	
	static resolverParametros(Cfdi cfdi){
		Comprobante comprobante=cfdi.getComprobante()
		def parametros=[:]
		// Datos tomados del Comprobante fiscal digital XML
		
		parametros.put("SERIE", 			comprobante.getSerie());
		parametros.put("FOLIO", 			comprobante.getFolio());
		parametros.put("NUM_CERTIFICADO", 	comprobante.getNoCertificado());
		parametros.put("SELLO_DIGITAL", 	comprobante.getSello());
		parametros.put("CADENA_ORIGINAL", 	cfdi.getCadenaOriginal());
		parametros.put("RECEPTOR_NOMBRE", 			comprobante.getReceptor().getNombre()); //Recibir como Parametro
		parametros.put("RECEPTOR_RFC", 				comprobante.getReceptor().getRfc());
		parametros.put("FECHA", 			comprobante.getFecha().getTime());
		parametros.put("NFISCAL", 			comprobante.getSerie()+" - "+comprobante.getFolio());
		parametros.put("IMPORTE", 			comprobante.getSubTotal());
		parametros.put("IVA", 			comprobante.getImpuestos().getTotalImpuestosTrasladados());
		parametros.put("TOTAL", 			comprobante.getTotal());
		parametros.put("RECEPTOR_DIRECCION", 		getDireccionEnFormatoEstandar(comprobante.getReceptor().getDomicilio()) );
		parametros.put("NUM_CTA_PAGO", 		comprobante.getNumCtaPago());
		parametros.put("METODO_PAGO", 		comprobante.getMetodoDePago());
		parametros.put("FORMA_PAGO", 		comprobante.getFormaDePago());
		//Datos tomado de la aplicacion
		parametros.put("IMP_CON_LETRA", 	ImporteALetra.aLetra(comprobante.getTotal()));
		parametros['FORMA_DE_PAGO']=comprobante.formaDePago
		parametros['PINT_IVA']='16 '
		
		parametros.put("DESCUENTOS", 	comprobante.getDescuento());
		
		
		if(comprobante.getReceptor().rfc=='XAXX010101000'){
			parametros.put("IMPORTE", 			comprobante.getTotal());
		}
		
		Emisor emisor=comprobante.getEmisor();
		parametros.put("EMISOR_NOMBRE", 	emisor.getNombre());
		parametros.put("EMISOR_RFC", 		emisor.getRfc());
		String pattern="{0} {1}  {2}  {3}" +
				"\n{4}  {5}  {6}";
		String direccionEmisor=MessageFormat.format(pattern
				,emisor.getDomicilioFiscal().getCalle()
				,emisor.getDomicilioFiscal().getNoExterior()
				,StringUtils.defaultIfEmpty(emisor.getDomicilioFiscal().getNoInterior(),"")
				
				,emisor.getDomicilioFiscal().getColonia()
				
				,emisor.getDomicilioFiscal().getMunicipio()
				
				,emisor.getDomicilioFiscal().getCodigoPostal()
				,emisor.getDomicilioFiscal().getEstado()
				);
		parametros.put("EMISOR_DIRECCION", direccionEmisor);
		parametros.put("EXPEDIDO_DIRECCION", direccionEmisor);
		parametros.put("REGIMEN",comprobante.getEmisor().getRegimenFiscalArray(0).regimen);
	
		
		if (emisor.getExpedidoEn() != null){
			TUbicacion expedido=emisor.getExpedidoEn();
		
			String pattern2="{0} {1}  {2}  {3}" +
				"\n{4}  {5}  {6}";
			String expedidoDir=MessageFormat.format(pattern2
				,expedido.getCalle()
				,expedido.getNoExterior()
				,StringUtils.defaultIfEmpty(expedido.getNoInterior(),"")
				,expedido.getColonia()
				,expedido.getMunicipio()
				,expedido.getCodigoPostal()
				,expedido.getEstado()
				);
			parametros.put("EXPEDIDO_DIRECCION", expedidoDir);
			
			
		}
			
		
		//Especiales para CFDI
			
		if(cfdi.uuid!=null){
			
			//println 'Imagen generada: '+img
			def img=QRCodeUtils.generarQR(cfdi.getComprobante())
			println 'Imagen generada: '+img
			parametros.put("QR_CODE",img);
			//parametros.put("QR_CODE",QRCodeUtils.getQCode(cfdi.getComprobante()))
			TimbreFiscal timbre=new TimbreFiscal(cfdi.getComprobante())
			parametros.put("FECHA_TIMBRADO", timbre.FechaTimbrado);
			parametros.put("FOLIO_FISCAL", timbre.UUID);
			parametros.put("SELLO_DIGITAL_SAT", timbre.selloSAT);
			parametros.put("CERTIFICADO_SAT", timbre.noCertificadoSAT);
			parametros.put("CADENA_ORIGINAL_SAT", timbre.cadenaOriginal());
		}
		
		
		
		return parametros;
	}
	
	static String getDireccionEnFormatoEstandar(TUbicacion u){
		String pattern="{0} {1} {2} {3}" +
				" {4} {5} {6}" +
				" {7} {8}";
		//StringUtils.
		return MessageFormat.format(pattern
				,u.getCalle() !=null?u.getCalle():""
				,(u.getNoExterior()!=null && !u.getNoExterior().equals(".") )?"NO."+u.getNoExterior():""
				,(u.getNoInterior()!=null && !u.getNoInterior().equals(".") )?"INT."+u.getNoInterior():""
				,u.getColonia()!=null?","+u.getColonia():""
				,u.getCodigoPostal() !=null?","+u.getCodigoPostal():""
				,u.getMunicipio()!=null?","+u.getMunicipio():""
				,u.getLocalidad()!=null?","+u.getLocalidad():""
				,u.getEstado()!=null?","+u.getEstado()+",":""
				,u.getPais()!=null?u.getPais():""
				);
	}

}
