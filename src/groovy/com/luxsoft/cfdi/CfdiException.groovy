package com.luxsoft.cfdi

class CfdiException extends RuntimeException{
	
	String message
	Cfdi cfdi
	
	
	
	String toString(){
		return message
	}

}
