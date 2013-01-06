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

<%--<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="modification.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="author" name="author.id" from="${mypub.User.list()}" optionKey="id" required="" value="${modificationInstance?.author?.id}" class="many-to-one"/>
</div>

--%><div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'newContent', 'error')} ">
	<label for="newContent">
		<g:message code="modification.newContent.label" default="New Content" />
		
	</label>
	<g:textField name="newContent" value="${modificationInstance?.newContent}"/>
</div>

<%--<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'proposalDate', 'error')} required">
	<label for="proposalDate">
		<g:message code="modification.proposalDate.label" default="Proposal Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="proposalDate" precision="day"  value="${modificationInstance?.proposalDate}"  />
</div>

--%><%--<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'pub', 'error')} required">
	<label for="pub">
		<g:message code="modification.pub.label" default="Pub" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pub" name="pub.id" from="${mypub.Pub.list()}" optionKey="id" required="" value="${modificationInstance?.pub?.id}" class="many-to-one"/>
</div>

--%><%--<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'usersOk', 'error')} ">
	<label for="usersOk">
		<g:message code="modification.usersOk.label" default="Users Ok" />
		
	</label>
	<g:select name="usersOk" from="${mypub.User.list()}" multiple="multiple" optionKey="id" size="5" value="${modificationInstance?.usersOk*.id}" class="many-to-many"/>
</div>

--%>