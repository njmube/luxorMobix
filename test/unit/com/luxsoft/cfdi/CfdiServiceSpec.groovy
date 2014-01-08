package com.luxsoft.cfdi

import java.util.List;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Receptor;
import grails.buildtestdata.mixin.Build;
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

import com.luxsoft.mobix.*;


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CfdiService)
@Mock([Empresa,CfdiFolio,Cliente,Venta,VentaDet,Producto])
@Build([Empresa,CfdiFolio,Cliente,Venta,VentaDet,Producto])
class CfdiServiceSpec extends Specification {

	def "Generacion valida del emisor a partir de una empresa"(){
		given:'Una empresa existente'
		def empresa=Empresa.build().save(failOnError:true)
		and:'Un Comprobante'
		Comprobante comprobante=ComprobanteDocument.Factory.newInstance().addNewComprobante()
		
		when:'Generamos el emisor'
		Emisor emisor=service.registrarEmisor(comprobante,empresa)
		
		then:'El emisor es valido'
		List errors=findErrors(emisor)
		if(errors){
			errors.each {
				println it.getMessage()
			}
		}
		errors.isEmpty()
		
	}
	
	def "Generacion valida del receptor"(){
		given:'Un cliente existente'
		def cliente=Cliente.build().save(failOnError:true)
		and:'Un Comprobante'
		Comprobante comprobante=ComprobanteDocument.Factory.newInstance().addNewComprobante()
		
		when:'Generamos el receptor'
		Receptor receptor=service.registrarReceptor(comprobante,cliente)
		
		then:'El receptor es valido'
		List errors=findErrors(receptor)
		if(errors){
			errors.each {
				println it.getMessage()
			}
		}
		errors.isEmpty()
		println 'Receptor: '+receptor
	}
	
	def "Generar cfdi para venta"(){
		
		setup: 'Preparamos una venta'
		def folio=CfdiFolio.buildWithoutSave(serie:'SERIE1',folioInicial:1,folioFinal:100)
		def empresa=Empresa.build(folioDeVentas:folio).save(failOnError:true)
		
		assert empresa.folioDeVentas, 'No se registro el folio de ventas'	
		
		def cliente=Cliente.build().save(failOnError:true,rfc:'UNI981130D84')
		def venta=Venta.build(empresa:empresa,cliente:cliente)
		
		def prod1=Producto.build(empresa:empresa,descripcion:'Producto 1')
		def prod2=Producto.build(empresa:empresa,descripcion:'Producto 2')
		def partida1=VentaDet.buildWithoutSave(venta:venta,cantidad:10,precio:10,comentario:'Partida 1',producto:prod1)
		def partida2=VentaDet.buildWithoutSave(venta:venta,cantidad:10,precio:20,comentario:'Partida 2',producto:prod2)
		venta.actualizarImportes()
		venta.save(failOnError:true)
		
		def mockCadenaOriginalService=Mock(CadenaOriginalService)
		1 * mockCadenaOriginalService.generarCadenaOriginal(_)>>"CADENA DE PRUEBA"
		service.cadenaOriginalService=mockCadenaOriginalService
		
		def mockSelladorService=Mock(SelladorService)
		1 * mockSelladorService.generarSello(_,_)>>"CADENA SELLADA"
		service.selladorService=mockSelladorService
		java.security.Security.addProvider(new BouncyCastleProvider());
		when:'Generamos un cfdi'
		Cfdi cfdi=service.generarCfdi(venta)
		
		then:'El cfdi es generado exitosamente'
		cfdi.id
		
	}
	
	List findErrors(final XmlObject node){
		final XmlOptions options=new XmlOptions();
		final List errors=new ArrayList();
		options.setErrorListener(errors);
		node.validate(options);
		return errors;
		
	}
   
    
}
