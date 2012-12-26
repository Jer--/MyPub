
<%@ page import="mypub.Coment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'coment.label', default: 'Coment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-coment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-coment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="text" title="${message(code: 'coment.text.label', default: 'Text')}" />
					
						<g:sortableColumn property="postDate" title="${message(code: 'coment.postDate.label', default: 'Post Date')}" />
					
						<th><g:message code="coment.pub.label" default="Pub" /></th>
					
						<g:sortableColumn property="username" title="${message(code: 'coment.username.label', default: 'Username')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${comentInstanceList}" status="i" var="comentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${comentInstance.id}">${fieldValue(bean: comentInstance, field: "text")}</g:link></td>
					
						<td><g:formatDate date="${comentInstance.postDate}" /></td>
					
						<td>${fieldValue(bean: comentInstance, field: "pub")}</td>
					
						<td>${fieldValue(bean: comentInstance, field: "username")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${comentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
