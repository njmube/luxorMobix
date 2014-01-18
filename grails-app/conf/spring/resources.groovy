import com.luxsoft.cfdi.CfdiCadenaBuilder;
import com.luxsoft.cfdi.CfdiSellador;
import com.luxsoft.cfdi.CfdiTimbrador;

// Place your Spring DSL code here
beans = {
	
	cfdiCadenaBuilder(CfdiCadenaBuilder){
		xsltFile="web-app/sat/cadenaoriginal_3_2.xslt"
	}
	cfdiSellador(CfdiSellador){
		cadenaBuilder=ref("cfdiCadenaBuilder")
	}
	cfdiTimbrador(CfdiTimbrador){
		timbradoDePrueba=false
	}
}
