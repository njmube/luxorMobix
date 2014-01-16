
package com.luxsoft.mobix

import grails.test.mixin.TestFor

import java.security.PrivateKey

import org.bouncycastle.jce.provider.BouncyCastleProvider

import spock.lang.Specification
import spock.lang.Unroll;

/**
 * Unit test para la entidad empresa
 * 
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Empresa)
class EmpresaSpec extends Specification {
	
	def setup(){
		java.security.Security.addProvider(new BouncyCastleProvider())
	}
	
	void "Validar la lectura del certificado en formato X509"(){
		
		given:'Un certificado'
		File certificado=new File("test/unit/00001000000202171318.cer")
		byte[] data=certificado.getBytes()
		assert data
		
		and:'Una empresa '
		def empresa=new Empresa(certificadoDigital:data)
		
		when:'Asignamos un certificado a la empresa'
		empresa.certificadoDigital=data
		
		then:'El certificado en formato X509Certificate es accesible'
		empresa.certificado
		println empresa.certificado.getSubjectX500Principal()
	}
	
	void "Validar lectura de la llave privada en formato PKCS8EncodedKeySpec "(){
		given:'Un archivo de llave privada'
		File pk=new File("test/unit/testPrivateKey.key")
		assert pk.exists()
		assert pk.getBytes()
		
		and:'Una empresa'
		def empresa=new Empresa(llavePrivada:pk.getBytes())
		
		when:'Se accesa la propiedad privateKey'
		PrivateKey key=empresa.privateKey
		
		then:'La llave es valida'
		key
		println key
		
	}
	
	@Unroll
	void "Validacion de la propiedad #property: #propertyValue valida:#anticipatedValid"(){
		given:'Una empresa nueva'
		def empresa=new Empresa()
		empresa.properties[property]=propertyValue
		//println "Propiedad: $property Valor:$propertyValue"
		when:'Invocamos la validacion'
		boolean valid=empresa.validate([property])
		println empresa.errors.getFieldError(property)
		
		println "Propiedad: $property Valor:$propertyValue $valid"
		then:'Las propiedades son validas'
		valid==anticipatedValid
		if(!valid){
			println empresa.errors.getFieldError(property)
			empresa.errors.getFieldError(property)?.code==errorCode
		}
		
		where:
		property|propertyValue|anticipatedValid|errorCode
		"xmlDirectory"|'user/noexiste'|false|"empresa.xmlDirectory.invalid"
		"xmlDirectory"|System.properties['user.home']|true|"empresa.xmlDirectory.invalid"
	}
	
}
