
<%@ page import="com.luxsoft.mobix.Empresa" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empresa.label', default: 'Empresa')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-empresa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-empresa" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'empresa.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="rfc" title="${message(code: 'empresa.rfc.label', default: 'Rfc')}" />
					
						<th><g:message code="empresa.direccion.label" default="Direccion" /></th>
					
						<g:sortableColumn property="regimen" title="${message(code: 'empresa.regimen.label', default: 'Regimen')}" />
					
						<g:sortableColumn property="numeroDeCertificado" title="${message(code: 'empresa.numeroDeCertificado.label', default: 'Numero De Certificado')}" />
					
						<g:sortableColumn property="certificadoDigital" title="${message(code: 'empresa.certificadoDigital.label', default: 'Certificado Digital')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${empresaInstanceList}" status="i" var="empresaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${empresaInstance.id}">${fieldValue(bean: empresaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: empresaInstance, field: "rfc")}</td>
					
						<td>${fieldValue(bean: empresaInstance, field: "direccion")}</td>
					
						<td>${fieldValue(bean: empresaInstance, field: "regimen")}</td>
					
						<td>${fieldValue(bean: empresaInstance, field: "numeroDeCertificado")}</td>
					
						<td>${fieldValue(bean: empresaInstance, field: "certificadoDigital")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${empresaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
