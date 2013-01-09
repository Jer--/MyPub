<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Picture" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'picture.label', default: 'Picture')}" />
		<title>Friends's Pictures</title>
	</head>
	<body>
		<a href="#list-picture" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller='User' action='showProfile'>My Profile</g:link></li>
				<li><g:link controller='User' action='listFriends'>My Friends</g:link></li>
				<li><g:link action="listPerso"><g:message code="My Pictures" /></g:link></li>
				<li><g:link controller='Logout'>Logout</g:link></li>
			</ul>
		</div>
		<div id="list-picture" class="content scaffold-list" role="main">
			<h1>Friend's Pictures</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'picture.name.label', default: 'Name')}" />
						<g:sortableColumn property="picture" title="Picture" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pictureInstanceList}" status="i" var="pictureInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="showImgAmi" id="${pictureInstance.id}">${fieldValue(bean: pictureInstance, field: "name")}</g:link></td>
						<td><img class="Picture" src="${createLink(controller:'Picture', action:'viewImage', id:pictureInstance.id)}" width="100"/></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pictureInstanceTotal}" />
			</div>
		</div>
	</body>
</html>