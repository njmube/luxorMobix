
<%@ page import="com.luxsoft.cfdi.Cfdi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cfdi.label', default: 'Cfdi')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cfdi" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
			</ul>
		</div>
		<div id="list-cfdi" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="serie" title="${message(code: 'cfdi.serie.label', default: 'Serie')}" />
						<g:sortableColumn property="folio" title="${message(code: 'cfdi.folio.label', default: 'Folio')}" />
						<g:sortableColumn property="emisor" title="${message(code: 'cfdi.serie.label', default: 'Emisor')}" />
						
						<g:sortableColumn property="fecha" title="${message(code: 'cfdi.fecha.label', default: 'Fecha')}" />
						<g:sortableColumn property="receptor" title="${message(code: 'cfdi.serie.label', default: 'Receptor')}" />
						<g:sortableColumn property="uuid" title="${message(code: 'cfdi.uuid.label', default: 'Uuid')}" />
						<g:sortableColumn property="timbrado" title="${message(code: 'cfdi.timbrado.label', default: 'Timbrado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cfdiInstanceList}" status="i" var="cfdiInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: cfdiInstance, field: "serie")}</td>
						<td><g:link action="show" id="${cfdiInstance.id}">${fieldValue(bean: cfdiInstance, field: "folio")}</g:link></td>
						<td>${fieldValue(bean: cfdiInstance, field: "emisor")}</td>
						<td><g:formatDate date="${cfdiInstance.fecha}" /></td>
						<td>${fieldValue(bean: cfdiInstance, field: "receptor")}</td>
						<td>${fieldValue(bean: cfdiInstance, field: "uuid")}</td>
						<td><g:formatDate date="${cfdiInstance.timbrado}" /></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cfdiInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
