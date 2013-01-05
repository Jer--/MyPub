<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Pub" %>



<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="pub.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${pubInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="pub.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" required="" value="${pubInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'addressOptionnal', 'error')} ">
	<label for="addressOptionnal">
		<g:message code="pub.addressOptionnal.label" default="Address Optionnal" />
		
	</label>
	<g:textField name="addressOptionnal" value="${pubInstance?.addressOptionnal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'zip', 'error')} ">
	<label for="zip">
		<g:message code="pub.zip.label" default="Zip" />
		
	</label>
	<g:textField name="zip" value="${pubInstance?.zip}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="pub.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${pubInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'website', 'error')} ">
	<label for="website">
		<g:message code="pub.website.label" default="Website" />
		
	</label>
	<g:textField name="website" value="${pubInstance?.website}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="pub.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="type" from="${pubInstance.constraints.type.inList}" required="" value="${pubInstance?.type}" valueMessagePrefix="pub.type"/>
</div>

