<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Coment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'coment.label', default: 'Coment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-coment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller='User' action='showProfile'>My Profile</g:link></li>
				<li><g:link controller='Pub' action='list'>My Pubs</g:link></li>
				<li><g:link controller="coment" action="listForAPub" id="${comentInstance?.pub?.id}">${fieldValue(bean: comentInstance, field: "pub")} Coments List</g:link></li>
			</ul>
		</div>
		<div id="show-coment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list coment">
			
				<g:if test="${comentInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="coment.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${comentInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comentInstance?.postDate}">
				<li class="fieldcontain">
					<span id="postDate-label" class="property-label"><g:message code="coment.postDate.label" default="Post Date" /></span>
					
						<span class="property-value" aria-labelledby="postDate-label"><g:formatDate date="${comentInstance?.postDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${comentInstance?.pub}">
				<li class="fieldcontain">
					<span id="pub-label" class="property-label"><g:message code="coment.pub.label" default="Pub" /></span>
					
						<span class="property-value" aria-labelledby="pub-label"><g:link controller="pub" action="show" id="${comentInstance?.pub?.id}">${comentInstance?.pub?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${comentInstance?.text}">
				<li class="fieldcontain">
					<span id="text-label" class="property-label"><g:message code="coment.text.label" default="Text" /></span>
					
						<span class="property-value" aria-labelledby="text-label"><g:fieldValue bean="${comentInstance}" field="text"/></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
