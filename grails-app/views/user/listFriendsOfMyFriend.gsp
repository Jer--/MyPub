<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title>Friends of my Friend</title>
	</head>
	<body>
		<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller='User' action='showProfile'>My Profile</g:link></li>
				<li><g:link controller='User' action='listFriends'>My Friends</g:link></li>
				<li>
					<g:form action="searchUser" >
						<g:submitButton name="Recherche" class="recherche" value="${message(code: 'default.button.recherche.label', default: 'Find User')}" />
						<g:textField name="username" paramName="username" required="" value="${userInstance?.username}"/>
					</g:form>
				</li>
			</ul>
		</div>
		<div id="list-user" class="content scaffold-list" role="main">
			<h1>Friends of my Friend</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="user.avatar.label" default="Avatar" /></th>

						<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />

						<g:sortableColumn property="firstName" title="${message(code: 'user.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'user.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="mail" title="${message(code: 'user.mail.label', default: 'Mail')}" />
					
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<g:if test="${userInstance?.avatar}">
							<td><img class="Picture" src="${createLink(controller:'Picture', action:'viewImageId', id:userInstance?.id)}" width="100"/></td>
						</g:if>
						<g:else>
							<td><img src="${resource(dir: 'images', file: 'defaultAvatar.jpg')}" width="100"/></td>
						</g:else>
						<td><g:link action="showPublic" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>

						<td>${fieldValue(bean: userInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "mail")}</td>
						
						<td><g:link action="addFriend" id="${userInstance.id}">Add</g:link></td>
										
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
