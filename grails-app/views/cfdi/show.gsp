
<%@ page import="com.luxsoft.cfdi.Cfdi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cfdi.label', default: 'Cfdi')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cfdi" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				
			</ul>
		</div>
		<div id="show-cfdi" class="content scaffold-show" role="main">
			<h1>Comprobante fiscal digital CFDI id: ${cfdiInstance.id }</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cfdi">
			
				<g:if test="${cfdiInstance?.serie}">
				<li class="fieldcontain">
					<span id="serie-label" class="property-label"><g:message code="cfdi.serie.label" default="Serie" /></span>
					
						<span class="property-value" aria-labelledby="serie-label"><g:fieldValue bean="${cfdiInstance}" field="serie"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="cfdi.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${cfdiInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="cfdi.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${cfdiInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="cfdi.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${cfdiInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.uuid}">
				<li class="fieldcontain">
					<span id="uuid-label" class="property-label"><g:message code="cfdi.uuid.label" default="Uuid" /></span>
					
						<span class="property-value" aria-labelledby="uuid-label"><g:fieldValue bean="${cfdiInstance}" field="uuid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.timbrado}">
				<li class="fieldcontain">
					<span id="timbrado-label" class="property-label"><g:message code="cfdi.timbrado.label" default="Timbrado" /></span>
					
						<span class="property-value" aria-labelledby="timbrado-label"><g:formatDate date="${cfdiInstance?.timbrado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.emisor}">
				<li class="fieldcontain">
					<span id="emisor-label" class="property-label"><g:message code="cfdi.emisor.label" default="Emisor" /></span>
					
						<span class="property-value" aria-labelledby="emisor-label"><g:fieldValue bean="${cfdiInstance}" field="emisor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.receptor}">
				<li class="fieldcontain">
					<span id="receptor-label" class="property-label"><g:message code="cfdi.receptor.label" default="Receptor" /></span>
					
						<span class="property-value" aria-labelledby="receptor-label"><g:fieldValue bean="${cfdiInstance}" field="receptor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.rfc}">
				<li class="fieldcontain">
					<span id="rfc-label" class="property-label"><g:message code="cfdi.rfc.label" default="Rfc" /></span>
					
						<span class="property-value" aria-labelledby="rfc-label"><g:fieldValue bean="${cfdiInstance}" field="rfc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.xmlName}">
				<li class="fieldcontain">
					<span id="xmlName-label" class="property-label"><g:message code="cfdi.xmlName.label" default="Xml Name" /></span>
					
						<span class="property-value" aria-labelledby="xmlName-label"><g:fieldValue bean="${cfdiInstance}" field="xmlName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.xml}">
				<li class="fieldcontain">
					<span id="xml-label" class="property-label"><g:message code="cfdi.xml.label" default="Xml" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.cadenaOriginal}">
				<li class="fieldcontain">
					<span id="cadenaOriginal-label" class="property-label"><g:message code="cfdi.cadenaOriginal.label" default="Cadena Original" /></span>
					
						<span class="property-value" aria-labelledby="cadenaOriginal-label"><g:fieldValue bean="${cfdiInstance}" field="cadenaOriginal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.origen}">
				<li class="fieldcontain">
					<span id="origen-label" class="property-label"><g:message code="cfdi.origen.label" default="Origen" /></span>
					
						<span class="property-value" aria-labelledby="origen-label"><g:fieldValue bean="${cfdiInstance}" field="origen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.tipoDeCfdi}">
				<li class="fieldcontain">
					<span id="tipoDeCfdi-label" class="property-label"><g:message code="cfdi.tipoDeCfdi.label" default="Tipo De Cfdi" /></span>
					
						<span class="property-value" aria-labelledby="tipoDeCfdi-label"><g:fieldValue bean="${cfdiInstance}" field="tipoDeCfdi"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="cfdi.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${cfdiInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.url}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="cfdi.url.label" default="Url" /></span>
					
						<span class="property-value" aria-labelledby="url-label"><g:fieldValue bean="${cfdiInstance}" field="url"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="cfdi.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${cfdiInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.descuentos}">
				<li class="fieldcontain">
					<span id="descuentos-label" class="property-label"><g:message code="cfdi.descuentos.label" default="Descuentos" /></span>
					
						<span class="property-value" aria-labelledby="descuentos-label"><g:fieldValue bean="${cfdiInstance}" field="descuentos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="cfdi.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${cfdiInstance}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.impuesto}">
				<li class="fieldcontain">
					<span id="impuesto-label" class="property-label"><g:message code="cfdi.impuesto.label" default="Impuesto" /></span>
					
						<span class="property-value" aria-labelledby="impuesto-label"><g:fieldValue bean="${cfdiInstance}" field="impuesto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="cfdi.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${cfdiInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.subtotal}">
				<li class="fieldcontain">
					<span id="subtotal-label" class="property-label"><g:message code="cfdi.subtotal.label" default="Subtotal" /></span>
					
						<span class="property-value" aria-labelledby="subtotal-label"><g:fieldValue bean="${cfdiInstance}" field="subtotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cfdiInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="cfdi.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:fieldValue bean="${cfdiInstance}" field="total"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<fieldset class="buttons">
				<g:jasperReport
					controller="cfdi"
					action="imprimirCfdi"
					jasper="CFDI" 
					format="PDF" 
					name="Imprimir CFDI">
							<g:hiddenField name="id" value="${cfdiInstance.id}"/>
				</g:jasperReport>
			</fieldset>
			<g:form url="[resource:cfdiInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="timbrar" resource="${cfdiInstance}">Timbrar</g:link>
					<g:link class="edit" action="mostrarXml" resource="${cfdiInstance}">XML</g:link>
					<g:link class="create" action="descargarXml" resource="${cfdiInstance}">Descargar XML</g:link>
					<g:if test="${cfdiInstance.uuid }">
						<g:actionSubmit class="delete" action="cancelar" value="${message(code: 'default.button.cancel.label', default: 'Cancelar')}" 
							onclick="return confirm('${message(code: 'default.button.cancel.confirm.message', default: 'Seguro que desa cancelar?')}');" />
					</g:if>
					<g:else>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</g:else>
					
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
