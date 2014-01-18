package com.luxsoft.cfdi



import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured;

import com.luxsoft.mobix.Empresa;
import com.luxsoft.mobix.Venta;

import grails.transaction.Transactional


@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class CfdiController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def cfdiTimbrador

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cfdi.list(params), model:[cfdiInstanceCount: Cfdi.count()]
    }

    def show(Cfdi cfdiInstance) {
        respond cfdiInstance
    }
    
	@Transactional
	def timbrar(long id){
		def cfdi=Cfdi.findById(id)
		if(cfdi==null){
			notFound()
			return
		}
		
		cfdi=cfdiTimbrador.timbrar(cfdi,"PAP830101CR3", "yqjvqfofb")
		redirect action:'index' 
		
	}
    
    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cfdiInstance.label', default: 'Cfdi'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	def mostrarXml(long id){
		def cfdi=Cfdi.findById(id)
		if(cfdi==null){
			notFound()
			return
		}
		
		//CFDIUtils.depurar(cfdi.getComprobanteDocument())
		//def data[] =cfdi.xml
		render view:'cfdiXml',model:[cfdi:cfdi,xml:cfdi.getComprobanteDocument().xmlText()]
	}
	
	@Transactional
	def delete(Cfdi cfdiInstance) {

		if (cfdiInstance == null) {
			notFound()
			return
		}

		cfdiInstance.delete flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cfdi.label', default: 'Cfdi'), cfdiInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}
}
