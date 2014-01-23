package com.luxsoft.cfdi

class CfdiFolio {
	
	String emisor
	String serie
	Long folio
	
	static constraints = {
		emisor blank:false,maxSize:20
		serie blank:false,maxSize:10
		folio nullable:false
	}
	
	Long next(){
		folio++
		return folio
	}
	
	String toString(){
		return "$emisor - $serie - $folio"
	}
	
}
