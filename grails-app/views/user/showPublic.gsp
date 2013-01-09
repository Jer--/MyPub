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
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller='User' action='showProfile'>My Profile</g:link></li>
				<li><g:link controller='User' action='listFriends'>My Friends</g:link></li>
				<li style="float : right"><g:link controller='Logout'>Logout</g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1>Public Profil</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.avatar}">
					<img id="showAvatar" class="Picture" src="${createLink(controller:'Picture', action:'viewImageId', id:userInstance?.id)}" width="150"/>
				</g:if>
				<g:else>
					<g:if test="${userInstance?.sex ==  'F'}">
					<img src="${resource(dir: 'images', file: '/pub/Avatar_woman.jpg')}" width="150"/>
					</g:if>
					<g:else>
					<img src="${resource(dir: 'images', file: '/pub/Avatar_man.jpg')}" width="150"/>
				</g:else>				</g:else>
			
				<g:if test="${userInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="user.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${userInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
				
			
				<g:if test="${userInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="user.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${userInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.mail}">
				<li class="fieldcontain">
					<span id="mail-label" class="property-label"><g:message code="user.mail.label" default="Mail" /></span>
					
						<span class="property-value" aria-labelledby="mail-label"><g:fieldValue bean="${userInstance}" field="mail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.birthday}">
				<li class="fieldcontain">
					<span id="birthday-label" class="property-label"><g:message code="user.birthday.label" default="Birthday" /></span>
					
						<span class="property-value" aria-labelledby="birthday-label"><g:formatDate date="${userInstance?.birthday}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.sex}">
				<li class="fieldcontain">
					<span id="sex-label" class="property-label"><g:message code="user.sex.label" default="Sex" /></span>
					
						<span class="property-value" aria-labelledby="sex-label"><g:fieldValue bean="${userInstance}" field="sex"/></span>
					
				</li>
				</g:if>
				
				<p align=right><g:link action="addFriend" id="${userInstance.id}">Add to my Friends</g:link></p>
				
			</ol>
		</div>
	</body>
</html>
