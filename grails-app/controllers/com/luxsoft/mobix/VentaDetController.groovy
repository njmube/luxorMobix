package com.luxsoft.mobix



import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured;

import com.luxsoft.utils.MonedaUtils;

import grails.transaction.Transactional


@Secured(['ROLE_ADMIN'])
class VentaDetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]
	//static scaffold = true
	
	
	
	def create() {
		println 'Creando partida para venta: '+params['venta.id']
		//respond new VentaDet(params)
		def det=new VentaDet(params)
		det.cantidad=1
		[ventaDetInstance:det]
	}
	
	def agregarPartida(VentaDet det){
		def venta=det.venta
		println 'Agregando partida a: '+venta.partidas.size()
		
		det.actualizarImportes()
		det.descuento=0
		det.subtotal=0
		det.impuesto=MonedaUtils.calcularImpuesto(det.importe, det.impuestoTasa)
		det.costo=0
		det.validate()
		if(det.hasErrors()){
			println 'Hay errores la partida: '+det.properties 
			println 'Params: '+params 
			//redirect action:"create",params:params
			respond det.errors, view:'create'
		}else{
			det.venta=null
			venta.addToPartidas(det)
			venta.actualizarImportes()
			venta.save(flush:true)
			redirect controller:'venta', action:'edit',params:[id:venta.id]
		}
		
	}
	/*
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond VentaDet.list(params), model:[ventaDetInstanceCount: VentaDet.count()]
    }

    def show(VentaDet ventaDetInstance) {
        respond ventaDetInstance
    }

    

    @Transactional
    def save(VentaDet ventaDetInstance) {
		println 'Salvando partida: '+ventaDetInstance.properties
        if (ventaDetInstance == null) {
            notFound()
            return
        }

        if (ventaDetInstance.hasErrors()) {
            respond ventaDetInstance.errors, view:'create'
            return
        }

        ventaDetInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ventaDetInstance.label', default: 'VentaDet'), ventaDetInstance.id])
                redirect ventaDetInstance
            }
            '*' { respond ventaDetInstance, [status: CREATED] }
        }
    }

    def edit(VentaDet ventaDetInstance) {
        respond ventaDetInstance
    }

    @Transactional
    def update(VentaDet ventaDetInstance) {
        if (ventaDetInstance == null) {
            notFound()
            return
        }

        if (ventaDetInstance.hasErrors()) {
            respond ventaDetInstance.errors, view:'edit'
            return
        }

        ventaDetInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'VentaDet.label', default: 'VentaDet'), ventaDetInstance.id])
                redirect ventaDetInstance
            }
            '*'{ respond ventaDetInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(VentaDet ventaDetInstance) {

        if (ventaDetInstance == null) {
            notFound()
            return
        }

        ventaDetInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'VentaDet.label', default: 'VentaDet'), ventaDetInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ventaDetInstance.label', default: 'VentaDet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }*/
}
