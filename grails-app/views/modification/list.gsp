<%---------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#---------------------------------------------------------------------------------%>
<%@ page import="mypub.Modification" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'modification.label', default: 'Modification')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-modification" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-modification" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="about" title="${message(code: 'modification.about.label', default: 'About')}" />
					
						<th><g:message code="modification.author.label" default="Author" /></th>
					
						<g:sortableColumn property="newContent" title="${message(code: 'modification.newContent.label', default: 'New Content')}" />
					
						<g:sortableColumn property="proposalDate" title="${message(code: 'modification.proposalDate.label', default: 'Proposal Date')}" />
					
						<th><g:message code="modification.pub.label" default="Pub" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${modificationInstanceList}" status="i" var="modificationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${modificationInstance.id}">${fieldValue(bean: modificationInstance, field: "about")}</g:link></td>
					
						<td>${fieldValue(bean: modificationInstance, field: "author")}</td>
					
						<td>${fieldValue(bean: modificationInstance, field: "newContent")}</td>
					
						<td><g:formatDate date="${modificationInstance.proposalDate}" /></td>
					
						<td>${fieldValue(bean: modificationInstance, field: "pub")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${modificationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
