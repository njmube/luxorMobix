
<%@ page import="com.luxsoft.mobix.VentaDet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ventaDet.label', default: 'VentaDet')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ventaDet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ventaDet" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="ventaDet.producto.label" default="Producto" /></th>
					
						<g:sortableColumn property="cantidad" title="${message(code: 'ventaDet.cantidad.label', default: 'Cantidad')}" />
					
						<g:sortableColumn property="precio" title="${message(code: 'ventaDet.precio.label', default: 'Precio')}" />
					
						<g:sortableColumn property="importe" title="${message(code: 'ventaDet.importe.label', default: 'Importe')}" />
					
						<g:sortableColumn property="descuento" title="${message(code: 'ventaDet.descuento.label', default: 'Descuento')}" />
					
						<g:sortableColumn property="subtotal" title="${message(code: 'ventaDet.subtotal.label', default: 'Subtotal')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ventaDetInstanceList}" status="i" var="ventaDetInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ventaDetInstance.id}">${fieldValue(bean: ventaDetInstance, field: "producto")}</g:link></td>
					
						<td>${fieldValue(bean: ventaDetInstance, field: "cantidad")}</td>
					
						<td>${fieldValue(bean: ventaDetInstance, field: "precio")}</td>
					
						<td>${fieldValue(bean: ventaDetInstance, field: "importe")}</td>
					
						<td>${fieldValue(bean: ventaDetInstance, field: "descuento")}</td>
					
						<td>${fieldValue(bean: ventaDetInstance, field: "subtotal")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ventaDetInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
