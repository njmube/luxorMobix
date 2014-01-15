package com.luxsoft.cfdi


import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider
import mx.gob.sat.cfd.x3.ComprobanteDocument
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification


/**
 * UnitTest para el correcto funcionamiento del sellador de Cfdi (CfdiSellador)
 * 
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)

class CfdiSelladorSpec extends Specification {
	
	def privateKey
	
	def setup(){
		def path="test/unit/testPrivateKey.key"
		File file=new File(path)
		assert  file.exists(),"Debe existir el archivo: "+path
		
		PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(file.getBytes())
		java.security.Security.addProvider(new BouncyCastleProvider())
		final  KeyFactory keyFactory=KeyFactory.getInstance("RSA","BC")
		this.privateKey=keyFactory.generatePrivate(keySpec)
		assert privateKey, "Se debe cargar la llave privada: "+file.absolutePath
	}
	
	
    void "Generacion del sello digital"() {
		
		given:'Un comprobante digigal'
		File file=new File("test/unit/cfdiDePrueba.xml")
		assert file.exists(),"No existe el CFDI: "+file
		ComprobanteDocument document=ComprobanteDocument.Factory.parse(file)
		assert document.getComprobante()
		
		and:'Un sellador '
		def sellador=new CfdiSellador()
		def mockCadenaBuilder=Mock(CfdiCadenaBuilder)
		String cadenaOriginal="||3.2|2013-12-31T02:03:57|ingreso|PAGO EN UNA SOLA EXHIBICION|71.77|1.0|MXN|83.2532|NO IDENTIFICADO|México|PAP830101CR3|PAPEL S.A. de C.V.|Biólogo Maximino Martínez|3902|San Salvador Xochimanca|DF|Distrito Federal|MEXICO|02870|Regimen General de Ley Personas Morales|FDE9310013X7|FAST DESING, S.A. DE C.V.|CONVENTO DE STA. MONICA|112|.|JARDINES DE STA. MONICA|TLALNEPANTLA|México|México|54050|1|No Aplica|Suc:TACUBA Docto:18975 (19/09/13)|8.59|8.59|1|No Aplica|Suc:TACUBA Docto:18936 (18/09/13)|45.60|45.60|1|No Aplica|Suc:TACUBA Docto:18966 (19/09/13)|17.58|17.58|IVA|16.00|11.4832|11.4832||"
		1 * mockCadenaBuilder.generarCadena(document) >> cadenaOriginal
		sellador.cadenaBuilder=mockCadenaBuilder
	
		when:'Generamos el sello digital'
		String sello=sellador.sellar(privateKey,document)
		
		then:'El sello es correcto'
		sello
		println 'Sello: '+sello
		//sello=='DPH/kbk5b58JJp90qMXf75xqGq9SuZ4apypuMXqr/v2fYItfBsWXLJpTfV3NeCS97A060XW/3cgxRSQ3VJQ/Tb8EzQKOZdvpnPhHcz9g606aaUIuxvsTr136znzSRJNDpdFbcEPJwJgu+7tYO3VjvkhymRhreG2H5zBRcFfP5KI='
    }
}
