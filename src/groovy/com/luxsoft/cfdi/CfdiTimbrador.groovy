package com.luxsoft.cfdi


import java.text.SimpleDateFormat;

import org.apache.commons.lang.exception.ExceptionUtils

import com.edicom.ediwinws.cfdi.client.CfdiClient
import com.edicom.ediwinws.cfdi.utils.ZipUtils


class CfdiTimbrador {
	
	ZipUtils zipUtils=new ZipUtils()
	CfdiClient cfdiClient=new CfdiClient()
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
	
	def timbradoDePrueba
	
	Cfdi timbrar(Cfdi cfdi,String user,String password){
		try {
			String nombre=cfdi.xmlName
			byte[] xml=cfdi.xml
			assert xml,'El cfdi esta mal generado no contiene datos xml'
			assert nombre,"El cfdi esta mal generado no define nombre de archivo xmlName"
			byte[] zipFile=zipUtils.comprimeArchivo(nombre, xml)
			//byte[] res=cfdiClient.getCfdiTest(user, password, zipFile)
			byte[] res
			if(timbradoDePrueba){
				println 'Timbrando de prueba: '+cfdi
				res=cfdiClient.getCfdiTest(user, password, zipFile)
			}else{
				println 'Timbrando real de: '+cfdi
				res=cfdiClient.getCfdiTest(user, password, zipFile)
			}
			Map<String, byte[]> map =zipUtils.descomprimeArchivo(res)
			Map.Entry<String, byte[]> entry=map.entrySet().iterator().next()
			
			
			cfdi.xmlName=entry.getKey()
			cfdi.xml=entry.getValue()
			cfdi.loadComprobante()
			cfdi.timbreFiscal=new TimbreFiscal(cfdi.getComprobante())
			cfdi.uuid=cfdi.timbreFiscal.UUID
			cfdi.timbrado=df.parse(cfdi.timbreFiscal.FechaTimbrado)
			return cfdi
		} catch (Exception e) {
			e.printStackTrace()
			String msg="Imposible timbrar cfdi $cfdi.id Error: "+ExceptionUtils.getRootCauseMessage(e)
			throw new CfdiException(message:msg,cfdi:cfdi)
		}
	}

}
