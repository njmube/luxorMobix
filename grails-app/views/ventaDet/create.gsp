<%@ page import="com.luxsoft.mobix.VentaDet" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ventaDet.label', default: 'VentaDet')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-ventaDet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-ventaDet" class="content scaffold-create" role="main">
			<h1>Agregar partida venta: ${ventaDetInstance.venta.id }</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${ventaDetInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${ventaDetInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="agregarPartida" controller="venta">
				<fieldset class="form">
					<f:with bean="${ventaDetInstance}">
						<g:hiddenField name="venta" value="${ventaDetInstance.venta}"/>
						<f:field property="producto"/>
						<f:field property="cantidad"/>
						<f:field property="precio"/>
						<f:field property="impuestoTasa"/>
						<f:field property="comentario"/>
					</f:with>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.add.label', default: 'Agregar')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
