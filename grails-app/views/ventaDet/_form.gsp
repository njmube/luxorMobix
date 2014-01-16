<%@ page import="com.luxsoft.mobix.VentaDet" %>



<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'producto', 'error')} required">
	<label for="producto">
		<g:message code="ventaDet.producto.label" default="Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="producto" name="producto.id" from="${com.luxsoft.mobix.Producto.list()}" optionKey="id" required="" value="${ventaDetInstance?.producto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="ventaDet.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cantidad" value="${fieldValue(bean: ventaDetInstance, field: 'cantidad')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'precio', 'error')} required">
	<label for="precio">
		<g:message code="ventaDet.precio.label" default="Precio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precio" value="${fieldValue(bean: ventaDetInstance, field: 'precio')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'importe', 'error')} required">
	<label for="importe">
		<g:message code="ventaDet.importe.label" default="Importe" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="importe" value="${fieldValue(bean: ventaDetInstance, field: 'importe')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'descuento', 'error')} required">
	<label for="descuento">
		<g:message code="ventaDet.descuento.label" default="Descuento" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="descuento" value="${fieldValue(bean: ventaDetInstance, field: 'descuento')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'subtotal', 'error')} required">
	<label for="subtotal">
		<g:message code="ventaDet.subtotal.label" default="Subtotal" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="subtotal" value="${fieldValue(bean: ventaDetInstance, field: 'subtotal')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'impuesto', 'error')} required">
	<label for="impuesto">
		<g:message code="ventaDet.impuesto.label" default="Impuesto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impuesto" value="${fieldValue(bean: ventaDetInstance, field: 'impuesto')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'impuestoTasa', 'error')} required">
	<label for="impuestoTasa">
		<g:message code="ventaDet.impuestoTasa.label" default="Impuesto Tasa" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impuestoTasa" value="${fieldValue(bean: ventaDetInstance, field: 'impuestoTasa')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'costo', 'error')} required">
	<label for="costo">
		<g:message code="ventaDet.costo.label" default="Costo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="costo" value="${fieldValue(bean: ventaDetInstance, field: 'costo')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'comentario', 'error')} ">
	<label for="comentario">
		<g:message code="ventaDet.comentario.label" default="Comentario" />
		
	</label>
	<g:textArea name="comentario" cols="40" rows="5" maxlength="300" value="${ventaDetInstance?.comentario}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ventaDetInstance, field: 'venta', 'error')} required">
	<label for="venta">
		<g:message code="ventaDet.venta.label" default="Venta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="venta" name="venta.id" from="${com.luxsoft.mobix.Venta.list()}" optionKey="id" required="" value="${ventaDetInstance?.venta?.id}" class="many-to-one"/>
</div>

