<%---------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#---------------------------------------------------------------------------------%>
<%@ page import="mypub.Modification" %>



<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'about', 'error')} required">
	<label for="about">
		<g:message code="modification.about.label" default="About" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="about" from="${modificationInstance.constraints.about.inList}" required="" value="${modificationInstance?.about}" valueMessagePrefix="modification.about"/>
</div>

<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'newContent', 'error')} ">
	<label for="newContent">
		<g:message code="modification.newContent.label" default="New Content" />
		
	</label>
	<g:textField name="newContent" value="${modificationInstance?.newContent}"/>
</div>
