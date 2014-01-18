package com.luxsoft.mobix



import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured;

import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class EmpresaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

	
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Empresa.list(params), model:[empresaInstanceCount: Empresa.count()]
    }

    def show(Empresa empresaInstance) {
        respond empresaInstance
    }

    def create() {
        respond new Empresa(params)
    }

    @Transactional
    def save(Empresa empresaInstance) {
        if (empresaInstance == null) {
            notFound()
            return
        }

        if (empresaInstance.hasErrors()) {
            respond empresaInstance.errors, view:'create'
            return
        }
        
		empresaInstance.save flush:true
		flash.message = message(code: 'default.updated.message', args: [message(code: 'Empresa.label', default: 'Empresa'), empresaInstance.id])
		redirect view:'show',model:[empresaInstance:empresaInstance]

        
    }

    def edit(Empresa empresaInstance) {
        respond empresaInstance
    }

    @Transactional
    def update(Empresa empresaInstance) {
        if (empresaInstance == null) {
            notFound()
            return
        }

        if (empresaInstance.hasErrors()) {
            respond empresaInstance.errors, view:'edit'
            return
        }

        empresaInstance.save flush:true
		flash.message = message(code: 'default.updated.message', args: [message(code: 'Empresa.label', default: 'Empresa'), empresaInstance.id])
		redirect view:'show',model:[empresaInstance:empresaInstance]
		/*
        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Empresa.label', default: 'Empresa'), empresaInstance.id])
                redirect view:'show',model:[empresaInstance:empresaInstance]
            }
            '*'{ respond empresaInstance, [status: OK] }
        }*/
    }

    @Transactional
    def delete(Empresa empresaInstance) {

        if (empresaInstance == null) {
            notFound()
            return
        }

        empresaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Empresa.label', default: 'Empresa'), empresaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresaInstance.label', default: 'Empresa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
