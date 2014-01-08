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
<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.calle', 'error')} required">
	<label for="direccion.calle">
		<g:message code="empresa.direccion.calle.label" default="Calle" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="calle" maxlength="200" required="" value="${direccionInstance?.calle}"/>
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

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.numeroExterior', 'error')} required">
	<label for="direccion.numeroExterior">
		<g:message code="empresa.direccion.numeroExterior.label" default="Numero Exterior" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroExterior" maxlength="50" required="" value="${direccionInstance?.numeroExterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.numeroInterior', 'error')} ">
	<label for="direccion.numeroInterior">
		<g:message code="empresa.direccion.numeroInterior.label" default="Numero Interior" />
		
	</label>
	<g:textField name="numeroInterior" maxlength="50" value="${direccionInstance?.numeroInterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion.pais', 'error')} required">
	<label for="direccion.pais">
		<g:message code="empresa.direccion.pais.label" default="Pais" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pais" maxlength="100" required="" value="${direccionInstance?.pais}"/>
</div>
</fieldset>
<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'regimen', 'error')} ">
	<label for="regimen">
		<g:message code="empresa.regimen.label" default="Regimen" />
		
	</label>
	<g:textArea name="regimen" cols="40" rows="5" maxlength="255" value="${empresaInstance?.regimen}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'numeroDeCertificado', 'error')} ">
	<label for="numeroDeCertificado">
		<g:message code="empresa.numeroDeCertificado.label" default="Numero De Certificado" />
		
	</label>
	<g:textField name="numeroDeCertificado" maxlength="20" value="${empresaInstance?.numeroDeCertificado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'certificadoDigital', 'error')} ">
	<label for="certificadoDigital">
		<g:message code="empresa.certificadoDigital.label" default="Certificado Digital" />
		
	</label>
	<input type="file" id="certificadoDigital" name="certificadoDigital" />
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'certificadoDigitalPfx', 'error')} ">
	<label for="certificadoDigitalPfx">
		<g:message code="empresa.certificadoDigitalPfx.label" default="Certificado Digital Pfx" />
		
	</label>
	<input type="file" id="certificadoDigitalPfx" name="certificadoDigitalPfx" />
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'llavePrivada', 'error')} ">
	<label for="llavePrivada">
		<g:message code="empresa.llavePrivada.label" default="Llave Privada" />
		
	</label>
	<input type="file" id="llavePrivada" name="llavePrivada" />
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'passwordPfx', 'error')} ">
	<label for="passwordPfx">
		<g:message code="empresa.passwordPfx.label" default="Password Pfx" />
		
	</label>
	<g:textField name="passwordPfx" value="${empresaInstance?.passwordPfx}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'cfdiPath', 'error')} ">
	<label for="cfdiPath">
		<g:message code="empresa.cfdiPath.label" default="Cfdi Path" />
		
	</label>
	<g:textField name="cfdiPath" maxlength="250" value="${empresaInstance?.cfdiPath}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'folioDeVentas', 'error')} ">
	<label for="folioDeVentas">
		<g:message code="empresa.folioDeVentas.label" default="Folio De Ventas" />
		
	</label>
	<g:select id="folioDeVentas" name="folioDeVentas.id" from="${com.luxsoft.cfdi.CfdiFolio.list()}" optionKey="id" value="${empresaInstance?.folioDeVentas?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'folioNotasDeCredito', 'error')} ">
	<label for="folioNotasDeCredito">
		<g:message code="empresa.folioNotasDeCredito.label" default="Folio Notas De Credito" />
		
	</label>
	<g:select id="folioNotasDeCredito" name="folioNotasDeCredito.id" from="${com.luxsoft.cfdi.CfdiFolio.list()}" optionKey="id" value="${empresaInstance?.folioNotasDeCredito?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

