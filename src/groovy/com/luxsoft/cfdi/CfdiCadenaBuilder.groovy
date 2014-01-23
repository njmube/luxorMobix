package com.luxsoft.cfdi

import mx.gob.sat.cfd.x3.ComprobanteDocument;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * Generador de cadena original
 * 
 * @author Ruben Cancino 
 *
 */
class CfdiCadenaBuilder implements ResourceLoaderAware{
	
	ResourceLoader loader
	String xsltPath="sat/cadenaoriginal_3_2.xslt"
	
	/**
	 * Genera la cadean original de un comprobante fiscal digital
	 * 
	 * @return La cadena original
	 */
	String generarCadena(ComprobanteDocument document){
		TransformerFactory factory=TransformerFactory.newInstance()
		File xsltFile=loader.getResource(xsltPath).getFile()
		assert xsltFile.exists(),"Debe instalar el xslt para la cadena original :"+xsltPath
		StreamSource source=new StreamSource(xsltFile);
		Transformer transformer=factory.newTransformer(source);
		Writer writer=new StringWriter();
		StreamResult out=new StreamResult(writer);
		Source so=new DOMSource(document.getDomNode());
		transformer.transform(so, out);
		return writer.toString();
		
	}

	@Override
	public void setResourceLoader(ResourceLoader arg0) {
		this.loader=arg0
		
	}

}
