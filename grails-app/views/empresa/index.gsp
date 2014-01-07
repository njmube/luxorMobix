
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
						
						<th><g:message code="empresa.certificadoDigital.label" default="Certificado" />
						<th><g:message code="empresa.certificadoDigitalPfx.label" default="Certificado Pfx" />
						<th><g:message code="empresa.llavePrivada.label" default="Pk" />
						<g:sortableColumn property="lastUpdated" title="${message(code: 'empresa.lastUpdated.label', default: 'Modificado')}" />
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${empresaInstanceList}" status="i" var="empresaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${empresaInstance.id}">${fieldValue(bean: empresaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: empresaInstance, field: "rfc")}</td>
					
						<td>${fieldValue(bean: empresaInstance, field: "direccion.calle")}</td>
					
						<td>
							<g:checkBox name="certificadoDigital" checked="${empresaInstance.certificadoDigital }" disabled="true"/>
						</td>
						<td>
							<g:checkBox name="certificadoDigitalPfx" checked="${empresaInstance.certificadoDigitalPfx }" disabled="true"/>
						</td>
						<td>
							<g:checkBox name="llavePrivada" checked="${empresaInstance.llavePrivada }" disabled="true"/>
						</td>
						<td>
							<lux:modificado date="${empresaInstance.lastUpdated }"/>
							
						</td>
					
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
