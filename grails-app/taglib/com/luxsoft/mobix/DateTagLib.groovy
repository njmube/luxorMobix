package com.luxsoft.mobix

class DateTagLib {
    static defaultEncodeAs = 'html'
    //static encodeAsForTags = [tagName: 'raw']
	static namespace="lux"
	
	def dateFromNow={ attrs->
		
	}
	/**
	 * Etiqueta para mostrar la ultima fecha de modificacion en un formato adecuado
	 * Wrapper de grails g.formatDate
	 * 
	 * @attr date REQUIRED La fecha a formatear
	 * @attr format El formato a aplicar por default: 'dd/MM/yyyy hh:mm'
	 * 
	 */
	def modificado={ attrs->
		def date=attrs.date
		def format=attrs.format
		if(!format)
			format='dd/MM/yyyy hh:mm'//g.message(code:'modificado.date.format',default:'dd/MM/yyyy hh:mm')
		out << g.formatDate(format:format,date:date)
		
	}
}
