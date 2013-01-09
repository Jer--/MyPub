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
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-picture" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller='User' action='showProfile'>My Profile</g:link></li>
				<li><g:link controller='User' action='listFriends'>My Friends</g:link></li>
				<li><g:link action="listPerso">My Pictures</g:link></li>
				<li><g:link controller='Logout'>Logout</g:link></li>
			</ul>
		</div>
		<div id="show-picture" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list picture">
			
				<g:if test="${pictureInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="picture.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${pictureInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pictureInstance?.data}">
				<li class="fieldcontain">
				<div id="imgperso">
					<span id="data-label" class="property-label" style="display:none; visibility:hidden"><g:message code="picture.data.label" default="Data" /></span>
					<img class="Picture" src="${createLink(controller:'Picture', action:'viewImage', id:pictureInstance.id)}"/>
				</div>
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
