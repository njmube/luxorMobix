<%@ page import="com.luxsoft.mobix.Empresa" %>
<g:select 
	name="${property}" 
	from="${Empresa.list()}" 
	value="${value}" 
	optionKey="clave"
	optionValue="nombre"
	/>