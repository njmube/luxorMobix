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
import org.apache.commons.lang.exception.ExceptionUtils;
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
	
	def grailsApplication
	
	def cfdiSellador

	@Transactional
    def Cfdi generarCfdi(def source) {
		
		def cfdi=source as Cfdi
		
		def ComprobanteDocument document=source as ComprobanteDocument
		
		Comprobante comprobante=document.getComprobante()
		comprobante.setSello(cfdiSellador.sellar(source.empresa.privateKey,document))
		byte[] encodedCert=Base64.encode(source.empresa.certificado.getEncoded())
		comprobante.setCertificado(new String(encodedCert))
		
		XmlOptions options = new XmlOptions()
		options.setCharacterEncoding("UTF-8")
		options.put( XmlOptions.SAVE_INNER )
		options.put( XmlOptions.SAVE_PRETTY_PRINT )
		options.put( XmlOptions.SAVE_AGGRESSIVE_NAMESPACES )
		options.put( XmlOptions.SAVE_USE_DEFAULT_NAMESPACE )
		options.put(XmlOptions.SAVE_NAMESPACES_FIRST)
		ByteArrayOutputStream os=new ByteArrayOutputStream()
		document.save(os, options)
		
		cfdi.setXml(os.toByteArray())
		cfdi.setXmlName("$cfdi.rfc-$cfdi.serie-$cfdi.folio"+".xml")
		
		validarDocumento(document)
		cfdi.save()
		return cfdi
    }
	
	
	
	def salvarEnArchivo(Cfdi cfdi,Empresa empresa) {
		try {
			ComprobanteDocument document=cfdi.getComprobanteDocument()
			File dir=new File(empresa.xmlDirectory?:System.properties['user.home'])
			assert dir.exists() && dir.isDirectory(),"Debe existir el directorio: $dir.absolutePath"
			File xmlFile=new File(dir,cfdi.xmlName);
			document.save(xmlFile);
			cfdi.setUrl(xmlFile.toURI().toURL())
			return cfdi
		} catch (Exception e) {
			e.printStackTrace()
			log.error(e)
			cfdi.comentario="No fue posible salvar el xml al sistema de archivos Error: "+ExceptionUtils.getRootCauseMessage(e)
			//cfdi.comentario=cfdi.comentario.padLeft(355)
		}
	}
	
	void validarDocumento(ComprobanteDocument document) {
		List<XmlValidationError> errores=findErrors(document);
		if(errores.size()>0){
			StringBuffer buff=new StringBuffer();
			for(XmlValidationError e:errores){
				buff.append(e.getMessage()+"\n");
			}
			throw new CfdiException(message:"Datos para generar el comprobante fiscal (CFD) incorrectos "+buff.toString());
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
