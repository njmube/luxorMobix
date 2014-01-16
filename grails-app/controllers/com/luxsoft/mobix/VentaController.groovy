package com.luxsoft.mobix

import org.springframework.security.access.annotation.Secured;
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class VentaController {
    static scaffold = true
	
	def create(){
		println 'Generando venta nueva'
		params.importe=0
		params.descuentos=0
		respond new Venta(params)
	}
}
