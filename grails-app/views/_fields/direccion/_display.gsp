<div class="fieldcontain">
	<label for="${label}"><g:message code="direccion.label" default="Dirección"/></label>
	<div>
		<ul>
			<li class="property-value">
				<g:fieldValue bean="${bean}" field="${property+'.calle'}"/> 
				#<g:fieldValue bean="${bean}" field="${property+'.numeroExterior'}"/>
			</li>
			<li class="property-value">
				<g:fieldValue bean="${bean}" field="${property+'.colonia'}"/> 
				,<g:fieldValue bean="${bean}" field="${property+'.municipio'}"/>
			</li>
			<li class="property-value">
				<g:fieldValue bean="${bean}" field="${property+'.estado'}"/> 
				,<g:fieldValue bean="${bean}" field="${property+'.pais'}"/>
				, C.P: <g:fieldValue bean="${bean}" field="${property+'.codigoPostal'}"/>
			</li>
		</ul>
	</div>
	
	
</div>


 

 
