
<%--
	def sucursales=['CHEQUE','TRANSFERENCIA','EFECTIVO']
	def attrs = [name: property, value: value,from:sucursales]
	if (required) 
		attrs.required = ''
	out << g.select(attrs) 

<span class="property-label" aria-labelledby="certificadoDigital-label">	</span>

<span class="property-label" aria-labelledby="certificadoDigital-label">	</span>
<span class="property-value" aria-labelledby="certificadoDigital-label">
	${bean.certificado?.subjectX500Principal}
</span>
--%>
<div class="fieldcontain">
	<label for="${label}"><g:message code="empresa.certificadoDigital.label" default="Certificado"/></label>
	<span class="property-value">
		${bean.certificado?.subjectX500Principal}
	</span>
	
	<%--
	<dl class="property-value">
		<dt>Subject</dt>
		<dd>Principal</dd>
	</dl>
	 --%>
</div>


 

 
