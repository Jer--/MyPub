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
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1>Friend Profile</h1>
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
			
				<g:if test="${userInstance?.friends}">
				<li class="fieldcontain">
					<span id="friends-label" class="property-label"><g:message code="user.friends.label" default="Friends" /></span>
					
						<span class="property-value" aria-labelledby="friends-label"><g:link controller="user" action="listFriendsOfMyFriend" id="${userInstance.id}">See all friends</g:link></span>
						<g:each in="${userInstance.friends.take(10)}" var="f">
						<span class="property-value" aria-labelledby="friends-label"><g:link controller="user" action="showPublic"  id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.pictures}">
				<li class="fieldcontain">
					<span id="pictures-label" class="property-label"><g:message code="user.pictures.label" default="Pictures" /></span>
					
						<span class="property-value" aria-labelledby="pictures-label"><g:link controller="picture" action="listFriend" id="${userInstance.id}">See all pictures</g:link></span>
						<g:each in="${userInstance.pictures.take(10)}" var="p">
						<span class="property-value" aria-labelledby="pictures-label"><g:link controller="picture" action="showImgAmi" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.pubs}">
				<li class="fieldcontain">
					<span id="pubs-label" class="property-label"><g:message code="user.pubs.label" default="Pubs" /></span>
					
						<g:each in="${userInstance.pubs}" var="p">
						<span class="property-value" aria-labelledby="pubs-label"><g:link controller="pub" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
				
				<p align=right><g:link action="removeFriend" id="${userInstance.id}">Remove from my Friends</g:link></p>
			
			</ol>
		</div>
	</body>
</html>
