<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Sistema de administración inmobiliaria</title>
	</head>
	<body>
		<a href="#list-empresa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a>
				</li>
				<li><g:link class="list" controller="empresa" action="index">
					<g:message code="empresa.list.label" default="Empresas" /></g:link>
				</li>
				<li><g:link class="list" controller="cliente" action="index">
					<g:message code="cliente.list.label" default="Clientes" /></g:link>
				</li>
				<li><g:link class="list" controller="producto" action="index">
					<g:message code="producto.list.label" default="Productos" /></g:link>
				</li>
				<li><g:link class="list" controller="venta" action="index">
					<g:message code="venta.list.label" default="Ventas" /></g:link>
				</li>
				<li><g:link class="list" controller="cfdi" action="index">
					<g:message code="cfdi.list.label" default="CFDI's" /></g:link>
				</li>
			</ul>
		</div>
		<div id="home-page" class="content scaffold-list" role="main">
			<h1>Header</h1>
		</div>
		
	</body>
</html>
