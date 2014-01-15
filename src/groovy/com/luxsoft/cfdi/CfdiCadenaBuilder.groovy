package com.luxsoft.cfdi

import mx.gob.sat.cfd.x3.ComprobanteDocument;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Generador de cadena original
 * 
 * @author Ruben Cancino 
 *
 */
class CfdiCadenaBuilder {
	
	File xsltFile
	
	/**
	 * Genera la cadean original de un comprobante fiscal digital
	 * 
	 * @return La cadena original
	 */
	String generarCadena(ComprobanteDocument document){
		TransformerFactory factory=TransformerFactory.newInstance()
		StreamSource source=new StreamSource(xsltFile);
		Transformer transformer=factory.newTransformer(source);
		Writer writer=new StringWriter();
		StreamResult out=new StreamResult(writer);
		Source so=new DOMSource(document.getDomNode());
		transformer.transform(so, out);
		return writer.toString();
		
	}

}
