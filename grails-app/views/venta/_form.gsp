<%@ page import="com.luxsoft.mobix.Venta" %>



<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="venta.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${com.luxsoft.mobix.Empresa.list()}" optionKey="id" required="" value="${ventaInstance?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="venta.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${com.luxsoft.mobix.Cliente.list()}" optionKey="id" required="" value="${ventaInstance?.cliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="venta.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${ventaInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'importe', 'error')} required">
	<label for="importe">
		<g:message code="venta.importe.label" default="Importe" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="importe" value="${fieldValue(bean: ventaInstance, field: 'importe')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'descuentos', 'error')} required">
	<label for="descuentos">
		<g:message code="venta.descuentos.label" default="Descuentos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="descuentos" value="${fieldValue(bean: ventaInstance, field: 'descuentos')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'subtotal', 'error')} required">
	<label for="subtotal">
		<g:message code="venta.subtotal.label" default="Subtotal" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="subtotal" value="${fieldValue(bean: ventaInstance, field: 'subtotal')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'impuestos', 'error')} required">
	<label for="impuestos">
		<g:message code="venta.impuestos.label" default="Impuestos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impuestos" value="${fieldValue(bean: ventaInstance, field: 'impuestos')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="venta.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="total" value="${fieldValue(bean: ventaInstance, field: 'total')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'moneda', 'error')} required">
	<label for="moneda">
		<g:message code="venta.moneda.label" default="Moneda" />
		<span class="required-indicator">*</span>
	</label>
	<g:currencySelect name="moneda" value="${ventaInstance?.moneda}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'tc', 'error')} required">
	<label for="tc">
		<g:message code="venta.tc.label" default="Tc" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tc" value="${fieldValue(bean: ventaInstance, field: 'tc')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'plazo', 'error')} required">
	<label for="plazo">
		<g:message code="venta.plazo.label" default="Plazo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="plazo" type="number" value="${ventaInstance.plazo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'vencimiento', 'error')} required">
	<label for="vencimiento">
		<g:message code="venta.vencimiento.label" default="Vencimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="vencimiento" precision="day"  value="${ventaInstance?.vencimiento}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'formaDePago', 'error')} ">
	<label for="formaDePago">
		<g:message code="venta.formaDePago.label" default="Forma De Pago" />
		
	</label>
	<g:textField name="formaDePago" maxlength="20" value="${ventaInstance?.formaDePago}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'comentario', 'error')} ">
	<label for="comentario">
		<g:message code="venta.comentario.label" default="Comentario" />
		
	</label>
	<g:textArea name="comentario" cols="40" rows="5" maxlength="300" value="${ventaInstance?.comentario}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'saldo', 'error')} required">
	<label for="saldo">
		<g:message code="venta.saldo.label" default="Saldo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="saldo" value="${fieldValue(bean: ventaInstance, field: 'saldo')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'pagosAplicados', 'error')} required">
	<label for="pagosAplicados">
		<g:message code="venta.pagosAplicados.label" default="Pagos Aplicados" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pagosAplicados" value="${fieldValue(bean: ventaInstance, field: 'pagosAplicados')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'partidas', 'error')} ">
	<label for="partidas">
		<g:message code="venta.partidas.label" default="Partidas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${ventaInstance?.partidas?}" var="p">
    <li><g:link controller="ventaDet" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="ventaDet" action="create" params="['venta.id': ventaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'ventaDet.label', default: 'VentaDet')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="venta.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" value="${ventaInstance?.tipo}"/>
</div>

