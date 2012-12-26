<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Modification" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'modification.label', default: 'Modification')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-modification" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-modification" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list modification">
			
				<g:if test="${modificationInstance?.usersOk}">
				<li class="fieldcontain">
					<span id="usersOk-label" class="property-label"><g:message code="modification.usersOk.label" default="Users Ok" /></span>
					
						<span class="property-value" aria-labelledby="usersOk-label"><g:fieldValue bean="${modificationInstance}" field="usersOk"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${modificationInstance?.proposalDate}">
				<li class="fieldcontain">
					<span id="proposalDate-label" class="property-label"><g:message code="modification.proposalDate.label" default="Proposal Date" /></span>
					
						<span class="property-value" aria-labelledby="proposalDate-label"><g:formatDate date="${modificationInstance?.proposalDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${modificationInstance?.pub}">
				<li class="fieldcontain">
					<span id="pub-label" class="property-label"><g:message code="modification.pub.label" default="Pub" /></span>
					
						<span class="property-value" aria-labelledby="pub-label"><g:link controller="pub" action="show" id="${modificationInstance?.pub?.id}">${modificationInstance?.pub?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${modificationInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="modification.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${modificationInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${modificationInstance?.id}" />
					<g:link class="edit" action="edit" id="${modificationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
