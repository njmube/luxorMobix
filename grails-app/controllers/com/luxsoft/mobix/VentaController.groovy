package com.luxsoft.mobix



import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured;

import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class VentaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

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
    def save(Venta ventaInstance) {
        if (ventaInstance == null) {
            notFound()
            return
        }
		println 'Salvando venta: '+ventaInstance.properties
		ventaIstance.importe=0.0
		ventaIstance.descuentos=0.0
		
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

    def edit(Venta ventaInstance) {
        respond ventaInstance
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
}
