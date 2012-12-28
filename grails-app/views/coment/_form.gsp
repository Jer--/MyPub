<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Coment" %>



<div class="fieldcontain ${hasErrors(bean: comentInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="coment.text.label" default="Text" />
		
	</label>
	<g:textField name="text" maxlength="160" value="${comentInstance?.text}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: comentInstance, field: 'postDate', 'error')} required">
	<label for="postDate">
		<g:message code="coment.postDate.label" default="Post Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="postDate" precision="day"  value="${comentInstance?.postDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: comentInstance, field: 'pub', 'error')} required">
	<label for="pub">
		<g:message code="coment.pub.label" default="Pub" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pub" name="pub.id" from="${mypub.Pub.list()}" optionKey="id" required="" value="${comentInstance?.pub?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: comentInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="coment.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${comentInstance?.username}"/>
</div>

