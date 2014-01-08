package com.luxsoft.cfdi

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import grails.transaction.Transactional



class CadenaOriginalService {
	
	def grailsApplication

    def String generarCadenaOriginal(ComprobanteDocument document) {
		String xsltPath='sat/cadenaoriginal_3_2.xslt'
		File xslt = grailsApplication.mainContext.getResource(xsltPath).file
		assert xslt.exists(),"No existe el archivo de transformacion XSLT: "+xsltPath
		TransformerFactory factory=TransformerFactory.newInstance()
		StreamSource source=new StreamSource(xslt.getInputStream());
		Transformer transformer=factory.newTransformer(source);
		Writer writer=new StringWriter();
		StreamResult out=new StreamResult(writer);
		Source so=new DOMSource(document.getDomNode());
		transformer.transform(so, out);
		return writer.toString();
    }
}
