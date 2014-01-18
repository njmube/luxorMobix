<%@ page import="com.luxsoft.cfdi.Cfdi" %>



<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'serie', 'error')} ">
	<label for="serie">
		<g:message code="cfdi.serie.label" default="Serie" />
		
	</label>
	<g:textField name="serie" maxlength="15" value="${cfdiInstance?.serie}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="cfdi.tipo.label" default="Tipo" />
		
	</label>
	<g:select name="tipo" from="${cfdiInstance.constraints.tipo.inList}" value="${cfdiInstance?.tipo}" valueMessagePrefix="cfdi.tipo" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="cfdi.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${cfdiInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="cfdi.folio.label" default="Folio" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="folio" maxlength="20" required="" value="${cfdiInstance?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'uuid', 'error')} ">
	<label for="uuid">
		<g:message code="cfdi.uuid.label" default="Uuid" />
		
	</label>
	<g:textArea name="uuid" cols="40" rows="5" maxlength="300" value="${cfdiInstance?.uuid}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'timbrado', 'error')} ">
	<label for="timbrado">
		<g:message code="cfdi.timbrado.label" default="Timbrado" />
		
	</label>
	<g:datePicker name="timbrado" precision="day"  value="${cfdiInstance?.timbrado}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'emisor', 'error')} required">
	<label for="emisor">
		<g:message code="cfdi.emisor.label" default="Emisor" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="emisor" cols="40" rows="5" maxlength="600" required="" value="${cfdiInstance?.emisor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'receptor', 'error')} required">
	<label for="receptor">
		<g:message code="cfdi.receptor.label" default="Receptor" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="receptor" cols="40" rows="5" maxlength="600" required="" value="${cfdiInstance?.receptor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'rfc', 'error')} required">
	<label for="rfc">
		<g:message code="cfdi.rfc.label" default="Rfc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rfc" maxlength="13" required="" value="${cfdiInstance?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'xmlName', 'error')} ">
	<label for="xmlName">
		<g:message code="cfdi.xmlName.label" default="Xml Name" />
		
	</label>
	<g:textField name="xmlName" maxlength="200" value="${cfdiInstance?.xmlName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'xml', 'error')} required">
	<label for="xml">
		<g:message code="cfdi.xml.label" default="Xml" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="xml" name="xml" />
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'cadenaOriginal', 'error')} ">
	<label for="cadenaOriginal">
		<g:message code="cfdi.cadenaOriginal.label" default="Cadena Original" />
		
	</label>
	<g:textArea name="cadenaOriginal" cols="40" rows="5" maxlength="65536" value="${cfdiInstance?.cadenaOriginal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'origen', 'error')} required">
	<label for="origen">
		<g:message code="cfdi.origen.label" default="Origen" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="origen" cols="40" rows="5" maxlength="255" required="" value="${cfdiInstance?.origen}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'tipoDeCfdi', 'error')} ">
	<label for="tipoDeCfdi">
		<g:message code="cfdi.tipoDeCfdi.label" default="Tipo De Cfdi" />
		
	</label>
	<g:select name="tipoDeCfdi" from="${cfdiInstance.constraints.tipoDeCfdi.inList}" value="${cfdiInstance?.tipoDeCfdi}" valueMessagePrefix="cfdi.tipoDeCfdi" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'comentario', 'error')} ">
	<label for="comentario">
		<g:message code="cfdi.comentario.label" default="Comentario" />
		
	</label>
	<g:textArea name="comentario" cols="40" rows="5" maxlength="355" value="${cfdiInstance?.comentario}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="cfdi.url.label" default="Url" />
		
	</label>
	<g:field type="url" name="url" value="${cfdiInstance?.url}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'descuentos', 'error')} required">
	<label for="descuentos">
		<g:message code="cfdi.descuentos.label" default="Descuentos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="descuentos" value="${fieldValue(bean: cfdiInstance, field: 'descuentos')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'importe', 'error')} required">
	<label for="importe">
		<g:message code="cfdi.importe.label" default="Importe" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="importe" value="${fieldValue(bean: cfdiInstance, field: 'importe')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'impuesto', 'error')} required">
	<label for="impuesto">
		<g:message code="cfdi.impuesto.label" default="Impuesto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impuesto" value="${fieldValue(bean: cfdiInstance, field: 'impuesto')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'subtotal', 'error')} required">
	<label for="subtotal">
		<g:message code="cfdi.subtotal.label" default="Subtotal" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="subtotal" value="${fieldValue(bean: cfdiInstance, field: 'subtotal')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cfdiInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="cfdi.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="total" value="${fieldValue(bean: cfdiInstance, field: 'total')}" required=""/>
</div>

