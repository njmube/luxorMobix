package com.luxsoft.cfdi

import java.security.Signature;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bouncycastle.util.encoders.Base64;

import com.luxsoft.mobix.Empresa;

import grails.transaction.Transactional

@Transactional
class SelladorService {

	def String generarSello(Empresa empresa,final String cadenaOrignal){
		
		try {
			final byte[] cadena=cadenaOrignal.getBytes("UTF-8");
			String algoritmo="SHA1withRSA";
			Signature signature=Signature.getInstance(algoritmo,"BC");
			signature.initSign(empresa.getPrivateKey());
			signature.update(cadena);
			
			final byte[] signedData=signature.sign();
			final byte[] encoedeData=Base64.encode(signedData);
			return new String(encoedeData,"UTF-8");
		} catch (Exception  e) {
			e.printStackTrace();
			String msg="Error generando sello digital: "+ExceptionUtils.getRootCauseMessage(e);
			log.error(msg,e);
			throw new RuntimeException(msg,ExceptionUtils.getCause(e));
		}
	}
}
