<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Comment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comment.label', default: 'comment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-comment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller='User' action='showProfile'>My Profile</g:link></li>
				<li><g:link controller='Pub' action='list'>My Pubs</g:link></li>
				<li style="float : right"><g:link controller='Logout'><img src="${resource(dir: 'images', file: 'skin/logout.png')}" width="15"/> Logout</g:link></li>
			</ul>
		</div>
		<div id="list-comment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'comment.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="postDate" title="${message(code: 'comment.postDate.label', default: 'Post Date')}" />
					
						<th><g:message code="comment.pub.label" default="Pub" /></th>
						
						<g:sortableColumn property="text" title="${message(code: 'comment.text.label', default: 'Text')}" />					
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${commentInstanceList}" status="i" var="commentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: commentInstance, field: "username")}</td>
					
						<td><g:formatDate date="${commentInstance.postDate}" /></td>
					
						<td><g:link controller="pub" action="show" id="${commentInstance?.pub?.id}">${fieldValue(bean: commentInstance, field: "pub")}</g:link></td>
					
						<td><g:link action="showComment" id="${commentInstance.id}">${fieldValue(bean: commentInstance, field: "text")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${commentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
