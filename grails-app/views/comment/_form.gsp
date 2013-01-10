<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Comment" %>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="comment.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${commentInstance?.username}" readonly="true"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'pub', 'error')} required">
	<label for="pub">
		<g:message code="comment.pub.label" default="Pub" />
		<span class="required-indicator">*</span>
	</label>
	
	<g:select id="pub" name="pub.id" from="${mypub.Pub.findById(commentInstance?.pub?.id)}" optionKey="id" required="" value="${commentInstance?.pub?.id}" class="many-to-one" readonly="true"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="comment.text.label" default="Text" />
		
	</label>
	<g:textField name="text" maxlength="160" value="${commentInstance?.text}"/>
</div>

