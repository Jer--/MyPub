<%---------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#---------------------------------------------------------------------------------%>
<%@ page import="mypub.Modification" %>
<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'newContent', 'error')} ">
	<label for="newContent">
		<g:message code="modification.newContent.label" default="New Content" />
	</label>
	<g:textField name="newContent" value="${actualContent}"/>
</div>
<g:hiddenField name="about" value="${modificationInstance?.about}" />
<g:hiddenField name="pubId" value="${modificationInstance?.pub.id}" />
