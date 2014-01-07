package com.luxsoft.mobix



import spock.lang.*

/**
 *
 */
class ProductoIntegrationSpec extends Specification {

    

    void "Salvar un producto"() {
		given:'Un producto nuevo'
		def empresa=Empresa.build()
		def producto=Producto.buildWithoutSave(descripcion:'Producto de prueba',empresa:empresa)
		
		when:'Salvamos el producto'
		producto.save()
		
		then:'El producto es persistido en la base de datos'
		producto.errors.errorCount==0
		producto.id
		producto.descripcion=='Producto de prueba'
    }
	
	void "Actualizar un producto"(){
		given:'Un producto existente'
		def producto=Producto.build().save(failOnError:true)
		
		when:'Actualizamos una propiedad'
		def found=Producto.get(producto.id)
		found.cuentaPredial='CUENTA_PREDIAL'
		found.save(failOnError:true)
		
		then:'Los cambios se reflejan en la base de datos'
		Producto.get(found.id).cuentaPredial=='CUENTA_PREDIAL'
	}
	
	void "Eliminar un producto"(){
		given:'Un producto existente'
		def producto=Producto.build().save(failOnError:true)
		
		when:'Eliminamos el producto'
		def found=Producto.get(producto.id)
		found.delete(flush:true)
		
		then:'El producto ya no existe en la base de datos'
		!Producto.exists(found.id)
	}
}
