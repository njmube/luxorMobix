<%@ page import="com.luxsoft.mobix.Empresa" %>



<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="empresa.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="nombre" cols="40" rows="5" maxlength="255" required="" value="${empresaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'rfc', 'error')} required">
	<label for="rfc">
		<g:message code="empresa.rfc.label" default="Rfc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rfc" maxlength="13" required="" value="${empresaInstance?.rfc}"/>
</div>
<fieldset class="embedded"><legend><g:message code="empresa.direccion.label" default="Direccion" /></legend>
<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.calle', 'error')} ">
	<label for="direccion.calle">
		<g:message code="empresa.direccion.calle.label" default="Calle" />
		
	</label>
	<g:textField name="calle" maxlength="200" value="${direccionInstance?.calle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.codigoPostal', 'error')} ">
	<label for="direccion.codigoPostal">
		<g:message code="empresa.direccion.codigoPostal.label" default="Codigo Postal" />
		
	</label>
	<g:textField name="codigoPostal" value="${direccionInstance?.codigoPostal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.colonia', 'error')} ">
	<label for="direccion.colonia">
		<g:message code="empresa.direccion.colonia.label" default="Colonia" />
		
	</label>
	<g:textField name="colonia" value="${direccionInstance?.colonia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.estado', 'error')} ">
	<label for="direccion.estado">
		<g:message code="empresa.direccion.estado.label" default="Estado" />
		
	</label>
	<g:textField name="estado" value="${direccionInstance?.estado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.municipio', 'error')} ">
	<label for="direccion.municipio">
		<g:message code="empresa.direccion.municipio.label" default="Municipio" />
		
	</label>
	<g:textField name="municipio" value="${direccionInstance?.municipio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.numeroExterior', 'error')} ">
	<label for="direccion.numeroExterior">
		<g:message code="empresa.direccion.numeroExterior.label" default="Numero Exterior" />
		
	</label>
	<g:textField name="numeroExterior" maxlength="50" value="${direccionInstance?.numeroExterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.numeroInterior', 'error')} ">
	<label for="direccion.numeroInterior">
		<g:message code="empresa.direccion.numeroInterior.label" default="Numero Interior" />
		
	</label>
	<g:textField name="numeroInterior" maxlength="50" value="${direccionInstance?.numeroInterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.pais', 'error')} ">
	<label for="direccion.pais">
		<g:message code="empresa.direccion.pais.label" default="Pais" />
		
	</label>
	<g:textField name="pais" maxlength="100" value="${direccionInstance?.pais}"/>
</div>
</fieldset>
<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'certificadoDigital', 'error')} ">
	<label for="certificadoDigital">
		<g:message code="empresa.certificadoDigital.label" default="Certificado Digital" />
		
	</label>
	<input type="file" id="certificadoDigital" name="certificadoDigital" />
</div>

