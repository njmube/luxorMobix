import com.luxsoft.cfdi.CfdiCadenaBuilder;
import com.luxsoft.cfdi.CfdiSellador;

// Place your Spring DSL code here
beans = {
	
	cfdiCadenaBuilder(CfdiCadenaBuilder){
		xsltFile="sat/cadenaoriginal_3_2.xslt"
	}
	cfdiSellador(CfdiSellador){
		cadenaBuilder=ref("cfdiCadenaBuilder")
	}
}
