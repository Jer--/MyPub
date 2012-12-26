<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Picture" %>



<div class="fieldcontain ${hasErrors(bean: pictureInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="picture.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${pictureInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pictureInstance, field: 'data', 'error')} ">
	<label for="data">
		<g:message code="picture.data.label" default="Data" />
		
	</label>
	<input type="file" id="data" name="data" />
</div>

