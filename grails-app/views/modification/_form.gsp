<%@ page import="mypub.Modification" %>



<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'usersOk', 'error')} ">
	<label for="usersOk">
		<g:message code="modification.usersOk.label" default="Users Ok" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'proposalDate', 'error')} required">
	<label for="proposalDate">
		<g:message code="modification.proposalDate.label" default="Proposal Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="proposalDate" precision="day"  value="${modificationInstance?.proposalDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'pub', 'error')} required">
	<label for="pub">
		<g:message code="modification.pub.label" default="Pub" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pub" name="pub.id" from="${mypub.Pub.list()}" optionKey="id" required="" value="${modificationInstance?.pub?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: modificationInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="modification.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${modificationInstance?.username}"/>
</div>

