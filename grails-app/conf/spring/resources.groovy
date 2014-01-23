import grails.util.Environment;

import com.luxsoft.cfdi.CfdiCadenaBuilder;
import com.luxsoft.cfdi.CfdiSellador;
import com.luxsoft.cfdi.CfdiTimbrador;

// Place your Spring DSL code here
beans = {
	
	cfdiCadenaBuilder(CfdiCadenaBuilder){
	}
	
	cfdiSellador(CfdiSellador){
		cadenaBuilder=ref("cfdiCadenaBuilder")
	}
	
	Environment.executeForCurrentEnvironment {
		development{
			cfdiTimbrador(CfdiTimbrador){
				timbradoDePrueba=true
			}
		}
		
		production{
			cfdiTimbrador(CfdiTimbrador){
				timbradoDePrueba=false
			}
		}
	}
	
}
