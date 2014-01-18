package com.luxsoft.mobix



import static org.springframework.http.HttpStatus.*

import java.util.regex.Pattern.Curly;

import org.springframework.security.access.annotation.Secured;

import com.luxsoft.utils.MonedaUtils;

import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class VentaController {
	
	def cfdiService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]
	
	def list(){
		redirect action:'index'
	}

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Venta.list(params), model:[ventaInstanceCount: Venta.count()]
    }

    def show(Venta ventaInstance) {
        respond ventaInstance
    }

    def create() {
		params.tc=1.0
		println 'Generando venta..'+params
        respond new Venta(params)
    }

    @Transactional
    def save() {
		println 'Salvando ventas Parametros: '+params.moneda
		def ventaInstance=new Venta()
		bindData(ventaInstance,params,[exclude: 'moneda'])
		ventaInstance.importe=0.0
		ventaInstance.descuentos=0.0
		ventaInstance.vencimiento=new Date()
		ventaInstance.moneda=Currency.getInstance(params.moneda)
		//ventaInstance.moneda=MonedaUtils.PESOS
		ventaInstance.validate()
		println 'Venta por salvar: '+ventaInstance.properties
        if (ventaInstance.hasErrors()) {
            respond ventaInstance.errors, view:'create'
            return
        }

        ventaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ventaInstance.label', default: 'Venta'), ventaInstance.id])
                redirect ventaInstance
            }
            '*' { respond ventaInstance, [status: CREATED] }
        }
    }

    def edit(long id) {
		def ventaInstance=Venta.findById(id)
		if(ventaInstance==null){
			notFound()
			return
		}
		[ventaInstance:ventaInstance]
        //respond ventaInstance
    }

    @Transactional
    def update(Venta ventaInstance) {
        if (ventaInstance == null) {
            notFound()
            return
        }

        if (ventaInstance.hasErrors()) {
            respond ventaInstance.errors, view:'edit'
            return
        }

        ventaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Venta.label', default: 'Venta'), ventaInstance.id])
                redirect ventaInstance
            }
            '*'{ respond ventaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Venta ventaInstance) {

        if (ventaInstance == null) {
            notFound()
            return
        }

        ventaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Venta.label', default: 'Venta'), ventaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ventaInstance.label', default: 'Venta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	
	@Transactional
	def facturar(long id){
		def venta=Venta.findById(id)
		if(venta==null){
			notFound()
			return
		}
		println 'Mandando facturar venta: '+venta.id
		println 'CfdiService: '+cfdiService.class
		def cfdi=cfdiService.generarCfdi(venta)
		
		render view:'/cfdi/show',model:[cfdi:cfdi]
	}
}
