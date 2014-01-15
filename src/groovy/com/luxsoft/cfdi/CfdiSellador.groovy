package com.luxsoft.cfdi

import java.security.Signature

import mx.gob.sat.cfd.x3.ComprobanteDocument;

import org.bouncycastle.util.encoders.Base64
import org.apache.commons.lang.exception.ExceptionUtils

class CfdiSellador {
	
	String algoritmo="SHA1withRSA"
	
	CfdiCadenaBuilder cadenaBuilder
	
	String sellar(def privateKey,ComprobanteDocument document){
		try {
			String cadenaOriginal=cadenaBuilder.generarCadena(document)
			final byte[] input=cadenaOriginal.getBytes("UTF-8")
			Signature signature=Signature.getInstance(algoritmo,"BC");
			signature.initSign(privateKey)
			signature.update(input)
			
			final byte[] signedData=signature.sign()
			final byte[] encoedeData=Base64.encode(signedData)
			return new String(encoedeData,"UTF-8")
		} catch (Exception e) {
			e.printStackTrace()
			String msg="Error generando sello digital: "+ExceptionUtils.getRootCauseMessage(e);
			log.error(msg,e);
			throw new CfdiException(message:msg)
			
		}
	}

}
