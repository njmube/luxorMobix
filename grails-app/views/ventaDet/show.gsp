
<%@ page import="com.luxsoft.mobix.VentaDet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ventaDet.label', default: 'VentaDet')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ventaDet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ventaDet" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ventaDet">
			
				<g:if test="${ventaDetInstance?.producto}">
				<li class="fieldcontain">
					<span id="producto-label" class="property-label"><g:message code="ventaDet.producto.label" default="Producto" /></span>
					
						<span class="property-value" aria-labelledby="producto-label"><g:link controller="producto" action="show" id="${ventaDetInstance?.producto?.id}">${ventaDetInstance?.producto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.cantidad}">
				<li class="fieldcontain">
					<span id="cantidad-label" class="property-label"><g:message code="ventaDet.cantidad.label" default="Cantidad" /></span>
					
						<span class="property-value" aria-labelledby="cantidad-label"><g:fieldValue bean="${ventaDetInstance}" field="cantidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.precio}">
				<li class="fieldcontain">
					<span id="precio-label" class="property-label"><g:message code="ventaDet.precio.label" default="Precio" /></span>
					
						<span class="property-value" aria-labelledby="precio-label"><g:fieldValue bean="${ventaDetInstance}" field="precio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="ventaDet.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${ventaDetInstance}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.descuento}">
				<li class="fieldcontain">
					<span id="descuento-label" class="property-label"><g:message code="ventaDet.descuento.label" default="Descuento" /></span>
					
						<span class="property-value" aria-labelledby="descuento-label"><g:fieldValue bean="${ventaDetInstance}" field="descuento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.subtotal}">
				<li class="fieldcontain">
					<span id="subtotal-label" class="property-label"><g:message code="ventaDet.subtotal.label" default="Subtotal" /></span>
					
						<span class="property-value" aria-labelledby="subtotal-label"><g:fieldValue bean="${ventaDetInstance}" field="subtotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.impuesto}">
				<li class="fieldcontain">
					<span id="impuesto-label" class="property-label"><g:message code="ventaDet.impuesto.label" default="Impuesto" /></span>
					
						<span class="property-value" aria-labelledby="impuesto-label"><g:fieldValue bean="${ventaDetInstance}" field="impuesto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.impuestoTasa}">
				<li class="fieldcontain">
					<span id="impuestoTasa-label" class="property-label"><g:message code="ventaDet.impuestoTasa.label" default="Impuesto Tasa" /></span>
					
						<span class="property-value" aria-labelledby="impuestoTasa-label"><g:fieldValue bean="${ventaDetInstance}" field="impuestoTasa"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.costo}">
				<li class="fieldcontain">
					<span id="costo-label" class="property-label"><g:message code="ventaDet.costo.label" default="Costo" /></span>
					
						<span class="property-value" aria-labelledby="costo-label"><g:fieldValue bean="${ventaDetInstance}" field="costo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="ventaDet.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${ventaDetInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="ventaDet.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${ventaDetInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="ventaDet.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${ventaDetInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaDetInstance?.venta}">
				<li class="fieldcontain">
					<span id="venta-label" class="property-label"><g:message code="ventaDet.venta.label" default="Venta" /></span>
					
						<span class="property-value" aria-labelledby="venta-label"><g:link controller="venta" action="show" id="${ventaDetInstance?.venta?.id}">${ventaDetInstance?.venta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:ventaDetInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${ventaDetInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
