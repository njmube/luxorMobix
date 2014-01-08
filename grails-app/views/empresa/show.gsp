
<%@ page import="com.luxsoft.mobix.Empresa" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empresa.label', default: 'Empresa')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-empresa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-empresa" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list empresa">
			
				<g:if test="${empresaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="empresa.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${empresaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.rfc}">
				<li class="fieldcontain">
					<span id="rfc-label" class="property-label"><g:message code="empresa.rfc.label" default="Rfc" /></span>
					
						<span class="property-value" aria-labelledby="rfc-label"><g:fieldValue bean="${empresaInstance}" field="rfc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.direccion}">
				<li class="fieldcontain">
					<span id="direccion-label" class="property-label"><g:message code="empresa.direccion.label" default="Direccion" /></span>
					
						<span class="property-value" aria-labelledby="direccion-label"><g:fieldValue bean="${empresaInstance}" field="direccion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.regimen}">
				<li class="fieldcontain">
					<span id="regimen-label" class="property-label"><g:message code="empresa.regimen.label" default="Regimen" /></span>
					
						<span class="property-value" aria-labelledby="regimen-label"><g:fieldValue bean="${empresaInstance}" field="regimen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.numeroDeCertificado}">
				<li class="fieldcontain">
					<span id="numeroDeCertificado-label" class="property-label"><g:message code="empresa.numeroDeCertificado.label" default="Numero De Certificado" /></span>
					
						<span class="property-value" aria-labelledby="numeroDeCertificado-label"><g:fieldValue bean="${empresaInstance}" field="numeroDeCertificado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.certificadoDigital}">
				<li class="fieldcontain">
					<span id="certificadoDigital-label" class="property-label"><g:message code="empresa.certificadoDigital.label" default="Certificado Digital" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.certificadoDigitalPfx}">
				<li class="fieldcontain">
					<span id="certificadoDigitalPfx-label" class="property-label"><g:message code="empresa.certificadoDigitalPfx.label" default="Certificado Digital Pfx" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.llavePrivada}">
				<li class="fieldcontain">
					<span id="llavePrivada-label" class="property-label"><g:message code="empresa.llavePrivada.label" default="Llave Privada" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.passwordPfx}">
				<li class="fieldcontain">
					<span id="passwordPfx-label" class="property-label"><g:message code="empresa.passwordPfx.label" default="Password Pfx" /></span>
					
						<span class="property-value" aria-labelledby="passwordPfx-label"><g:fieldValue bean="${empresaInstance}" field="passwordPfx"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.cfdiPath}">
				<li class="fieldcontain">
					<span id="cfdiPath-label" class="property-label"><g:message code="empresa.cfdiPath.label" default="Cfdi Path" /></span>
					
						<span class="property-value" aria-labelledby="cfdiPath-label"><g:fieldValue bean="${empresaInstance}" field="cfdiPath"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.folioDeVentas}">
				<li class="fieldcontain">
					<span id="folioDeVentas-label" class="property-label"><g:message code="empresa.folioDeVentas.label" default="Folio De Ventas" /></span>
					
						<span class="property-value" aria-labelledby="folioDeVentas-label"><g:link controller="cfdiFolio" action="show" id="${empresaInstance?.folioDeVentas?.id}">${empresaInstance?.folioDeVentas?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.folioNotasDeCredito}">
				<li class="fieldcontain">
					<span id="folioNotasDeCredito-label" class="property-label"><g:message code="empresa.folioNotasDeCredito.label" default="Folio Notas De Credito" /></span>
					
						<span class="property-value" aria-labelledby="folioNotasDeCredito-label"><g:link controller="cfdiFolio" action="show" id="${empresaInstance?.folioNotasDeCredito?.id}">${empresaInstance?.folioNotasDeCredito?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="empresa.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${empresaInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${empresaInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="empresa.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${empresaInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:empresaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${empresaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
