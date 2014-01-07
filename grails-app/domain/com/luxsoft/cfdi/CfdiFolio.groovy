package com.luxsoft.cfdi

class CfdiFolio {
	
	String rfc
	Long folio
	String serie
	Long folioInicial
	Long folioFinal
	Date asignacion
	Integer numeroDeAprobacion
	Integer anoAprobacion

    static constraints = {
		rfc(minSize:12,maxSize:13)
		folio()
		serie(unique:true)
		folioInicial(minSize:0)
		folioFinal()
		asignacion()
		numeroDeAprobacion()
    }
	
	Long next(){
		assert folio<folioFinal,"Se llego al limite de folios"
		def next=folio++
		this.folio=next
		return next
	}
	
	static buscarPorRfcAndSerie(String rfc,String serie){
		return CfdiFolio.findByRfcAndSerie(rfc,serie)
	}
}
