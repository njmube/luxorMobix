package com.luxsoft.cfdi

import groovy.transform.ToString;

import java.io.ByteArrayInputStream

import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante

@ToString(includeNames=true,includes="id,serie,folio,tipo,fecha,uuid")
class Cfdi {
	
	
	String serie
	String tipo
	String tipoDeCfdi
	Date fecha
	String folio
	String uuid
	Date timbrado
	String emisor
	String receptor
	String rfc
	BigDecimal importe
	BigDecimal descuentos
	BigDecimal subtotal
	BigDecimal impuesto
	BigDecimal total
	String comentario
	
	String cadenaOriginal
	String origen
	String xmlName
	byte[] xml
	String url
	
		

	
	
	ComprobanteDocument comprobanteDocument
	
	TimbreFiscal timbreFiscal
	
	Date dateCreated
	Date lastUpdated

    static constraints = {
		serie blannk:false,maxSize:15 
		tipo inList:['FACTURA','NOTA_CREDITO','NOTA_CARGO']
		fecha nullable:false
		folio blank:false,maxSize:20
		uuid nullable:true,maxSize:300
		timbrado(nullable:true)
		emisor blank:false,maxSize:600
		receptor blank:false,maxSize:600
		rfc blank:false,maxSize:13
		xmlName nullable:true,maxSize:200
		xml maxSize:(1024 * 512)  // 50kb para almacenar el xml
		cadenaOriginal maxSize:1024*64, nullable:true //@Column(name="CADENA_ORIGINAL",length=1048576,nullable=true)
		origen blank:false,maxSize:255
		tipoDeCfdi inList:['I','E']
		comentario nullable:true,maxSize:355
		url nullable:true,url:true
    }
	
	static transients = ['comprobanteDocument','timbreFiscal']
	
	Comprobante getComprobante(){
		getComprobanteDocument().getComprobante()
	}
	
	public ComprobanteDocument getComprobanteDocument(){
		if(this.comprobanteDocument==null){
			loadComprobante()
		}
		return this.comprobanteDocument
	}
	
	void loadComprobante(){
		ByteArrayInputStream is=new ByteArrayInputStream(getXml())
		this.comprobanteDocument=ComprobanteDocument.Factory.parse(is)
		
		
	}
	
	public static Cfdi generateFromXmlFile(File file){
		return new RuntimeException('Metodo por implementar');
	}
	
	String toString(){
		return "($emisor) Id:$id  Tipo:$tipo Serie:$serie Folio:$folio  UUID:$uuid xmlName:$xmlName"
	}
	
}
