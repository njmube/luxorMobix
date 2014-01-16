
<%@ page import="com.luxsoft.mobix.Venta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'venta.label', default: 'Venta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-venta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-venta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="venta.empresa.label" default="Empresa" /></th>
					
						<th><g:message code="venta.cliente.label" default="Cliente" /></th>
					
						<g:sortableColumn property="fecha" title="${message(code: 'venta.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="importe" title="${message(code: 'venta.importe.label', default: 'Importe')}" />
					
						<g:sortableColumn property="descuentos" title="${message(code: 'venta.descuentos.label', default: 'Descuentos')}" />
					
						<g:sortableColumn property="subtotal" title="${message(code: 'venta.subtotal.label', default: 'Subtotal')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ventaInstanceList}" status="i" var="ventaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ventaInstance.id}">${fieldValue(bean: ventaInstance, field: "empresa")}</g:link></td>
					
						<td>${fieldValue(bean: ventaInstance, field: "cliente")}</td>
					
						<td><g:formatDate date="${ventaInstance.fecha}" /></td>
					
						<td>${fieldValue(bean: ventaInstance, field: "importe")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "descuentos")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "subtotal")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ventaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
