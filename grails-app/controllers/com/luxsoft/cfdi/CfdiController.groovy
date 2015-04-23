package com.luxsoft.cfdi



import static org.springframework.http.HttpStatus.*

import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;

import org.springframework.security.access.annotation.Secured;

import com.luxsoft.mobix.Empresa;
import com.luxsoft.mobix.Venta;

import grails.transaction.Transactional
import com.luxsoft.utils.ImporteALetra;

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class CfdiController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def cfdiTimbrador
	def cfdiService

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
	
	def imprimirCfdi(long id){
		
		def cfdi=Cfdi.findById(id)
		if(cfdi==null){
			notFound()
			return
		}
		Comprobante cfd=cfdi.getComprobante()
		def conceptos=cfd.getConceptos().getConceptoArray()
		
		def modelData=conceptos.collect { cc ->
			
			def res=[
			'cantidad':cc.getCantidad()
			 ,'NoIdentificacion':cc.getNoIdentificacion()
			 ,'descripcion':cc.getDescripcion()
			 ,'unidad':cc.getUnidad()
			 ,'ValorUnitario':cc.getValorUnitario()
			 ,'Importe':cc.getImporte()
			 ]
			if(cc.informacionAduaneraArray){
				res.PEDIMENTO_FECHA=cc.informacionAduaneraArray[0]?.fecha.getTime()
				res.PEDIMENTO=cc.informacionAduaneraArray[0]?.numero
				res.ADUANA=cc.informacionAduaneraArray[0]?.aduana
			}
			if(cc.cuentaPredial){
				res.CUENTA_PREDIAL=cc.cuentaPredial.numero
			}
			return res
		}
		def repParams=CfdiPrintUtils.resolverParametros(cfdi)
		params<<repParams
		params.FECHA=cfd.fecha.getTime().format("yyyy-MM-dd'T'HH:mm:ss")
		chain(controller:'jasper',action:'index',model:[data:modelData],params:params)

		
	}
	
	def importeALetra(){
		println "Convirtiendo a Letra"
	 ImporteALetra.aLetra(new BigDecimal(999999999.00) )
	
}
	
	def descargarXml(long id){
		println "Descargando xml"
		Cfdi cfdi=Cfdi.findById(id)
		if(cfdi==null){
			notFound()
			return
		}
		response.setContentType("application/octet-stream")
		response.setHeader("Content-disposition", "attachment; filename=\"$cfdi.xmlName\"")
		response.outputStream << cfdi.getComprobanteDocument().newInputStream()
		
	}
	
	@Transactional
	def cancelar(long id){
		Cfdi cfdi=Cfdi.findById(id)
		if(cfdi==null){
			notFound()
			return
		}
		println 'Cancelando cfdi: '+id
		cfdi=cfdiService.cancelar(cfdi)
		render view:'showCancelado',model:[cfdiInstance:cfdi]
	}
}
