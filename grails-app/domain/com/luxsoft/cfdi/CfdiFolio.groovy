package com.luxsoft.cfdi

class CfdiFolio {
	
	String emisor
	String serie
	Long folio
	
	static constraints = {
		emisor blank:false,unique:true,maxSize:13
		serie inList:['FACTURA','NOTA_DE_CREDITO','NOTA_DE_CARGO']
		folio nullable:false,unique:true
	}
	
	Long next(){
		folio++
		return folio
	}
	
	String toString(){
		return "$emisor - $serie - $folio"
	}
	
}
