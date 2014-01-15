package com.luxsoft.cfdi

import java.text.MessageFormat;

import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Complemento;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.xmlbeans.XmlObject;

class TimbreFiscal {
	
	String version
	String UUID
	String FechaTimbrado
	String selloCFD
	String selloSAT
	String noCertificadoSAT
	
	public TimbreFiscal(Comprobante cfdi){
		
		Complemento complemento=cfdi.getComplemento();
		if(complemento!=null){
			//println complemento
			String queryExpression =
					"declare namespace tfd='http://www.sat.gob.mx/TimbreFiscalDigital';" +
				    "\$this/tfd:TimbreFiscalDigital"
			XmlObject[] res=complemento.selectPath(queryExpression);
			if(res.length>0){
				XmlObject timbre=res[0];
				version=timbre.getDomNode().getAttributes().getNamedItem("version").getNodeValue();
				UUID=timbre.getDomNode().getAttributes().getNamedItem("UUID").getNodeValue();
				FechaTimbrado=timbre.getDomNode().getAttributes().getNamedItem("FechaTimbrado").getNodeValue();
				selloCFD=timbre.getDomNode().getAttributes().getNamedItem("selloCFD").getNodeValue();
				selloSAT=timbre.getDomNode().getAttributes().getNamedItem("selloSAT").getNodeValue();
				noCertificadoSAT=timbre.getDomNode().getAttributes().getNamedItem("noCertificadoSAT").getNodeValue();
			}
		}
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String cadenaOriginal(){
		String pattern="||{0}|{1}|{2}|{3}|{4}||";
		return MessageFormat.format(pattern, version,UUID,FechaTimbrado,selloCFD,noCertificadoSAT);
	}

}
