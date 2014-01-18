
<%@ page import="com.luxsoft.mobix.Venta" %>
<%@ page import="com.luxsoft.cfdi.Cfdi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'venta.label', default: 'Venta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-venta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-venta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list venta">
			
				<g:if test="${ventaInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="venta.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${ventaInstance?.empresa?.id}">${ventaInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="venta.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="cliente" action="show" id="${ventaInstance?.cliente?.id}">${ventaInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="venta.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${ventaInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="venta.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${ventaInstance}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.descuentos}">
				<li class="fieldcontain">
					<span id="descuentos-label" class="property-label"><g:message code="venta.descuentos.label" default="Descuentos" /></span>
					
						<span class="property-value" aria-labelledby="descuentos-label"><g:fieldValue bean="${ventaInstance}" field="descuentos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.subtotal}">
				<li class="fieldcontain">
					<span id="subtotal-label" class="property-label"><g:message code="venta.subtotal.label" default="Subtotal" /></span>
					
						<span class="property-value" aria-labelledby="subtotal-label"><g:fieldValue bean="${ventaInstance}" field="subtotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.impuestos}">
				<li class="fieldcontain">
					<span id="impuestos-label" class="property-label"><g:message code="venta.impuestos.label" default="Impuestos" /></span>
					
						<span class="property-value" aria-labelledby="impuestos-label"><g:fieldValue bean="${ventaInstance}" field="impuestos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="venta.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:fieldValue bean="${ventaInstance}" field="total"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.moneda}">
				<li class="fieldcontain">
					<span id="moneda-label" class="property-label"><g:message code="venta.moneda.label" default="Moneda" /></span>
					
						<span class="property-value" aria-labelledby="moneda-label"><g:fieldValue bean="${ventaInstance}" field="moneda"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.tc}">
				<li class="fieldcontain">
					<span id="tc-label" class="property-label"><g:message code="venta.tc.label" default="Tc" /></span>
					
						<span class="property-value" aria-labelledby="tc-label"><g:fieldValue bean="${ventaInstance}" field="tc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.plazo}">
				<li class="fieldcontain">
					<span id="plazo-label" class="property-label"><g:message code="venta.plazo.label" default="Plazo" /></span>
					
						<span class="property-value" aria-labelledby="plazo-label"><g:fieldValue bean="${ventaInstance}" field="plazo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.vencimiento}">
				<li class="fieldcontain">
					<span id="vencimiento-label" class="property-label"><g:message code="venta.vencimiento.label" default="Vencimiento" /></span>
					
						<span class="property-value" aria-labelledby="vencimiento-label"><g:formatDate date="${ventaInstance?.vencimiento}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.formaDePago}">
				<li class="fieldcontain">
					<span id="formaDePago-label" class="property-label"><g:message code="venta.formaDePago.label" default="Forma De Pago" /></span>
					
						<span class="property-value" aria-labelledby="formaDePago-label"><g:fieldValue bean="${ventaInstance}" field="formaDePago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="venta.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${ventaInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.saldo}">
				<li class="fieldcontain">
					<span id="saldo-label" class="property-label"><g:message code="venta.saldo.label" default="Saldo" /></span>
					
						<span class="property-value" aria-labelledby="saldo-label"><g:fieldValue bean="${ventaInstance}" field="saldo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="venta.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${ventaInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="venta.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${ventaInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.pagosAplicados}">
				<li class="fieldcontain">
					<span id="pagosAplicados-label" class="property-label"><g:message code="venta.pagosAplicados.label" default="Pagos Aplicados" /></span>
					
						<span class="property-value" aria-labelledby="pagosAplicados-label"><g:fieldValue bean="${ventaInstance}" field="pagosAplicados"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.partidas}">
				<li class="fieldcontain">
					<span id="partidas-label" class="property-label"><g:message code="venta.partidas.label" default="Partidas" /></span>
					
						<g:each in="${ventaInstance.partidas}" var="p">
						<span class="property-value" aria-labelledby="partidas-label"><g:link controller="ventaDet" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="venta.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${ventaInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:ventaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${ventaInstance}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<g:if test="${!Cfdi.findByOrigen(ventaInstance?.id) }">
						<g:link class="message" action="facturar" value="Facturar" id="${ventaInstance.id}">
							<g:message code="venta.facturar.label" default="Facturar"/>	
						</g:link>
					</g:if>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
