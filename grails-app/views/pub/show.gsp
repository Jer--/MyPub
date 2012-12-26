
<%@ page import="mypub.Pub" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pub.label', default: 'Pub')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pub" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pub" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pub">
			
				<g:if test="${pubInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="pub.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${pubInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.presentationPicture}">
				<li class="fieldcontain">
					<span id="presentationPicture-label" class="property-label"><g:message code="pub.presentationPicture.label" default="Presentation Picture" /></span>
					
						<span class="property-value" aria-labelledby="presentationPicture-label"><g:link controller="picture" action="show" id="${pubInstance?.presentationPicture?.id}">${pubInstance?.presentationPicture?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.modifications}">
				<li class="fieldcontain">
					<span id="modifications-label" class="property-label"><g:message code="pub.modifications.label" default="Modifications" /></span>
					
						<g:each in="${pubInstance.modifications}" var="m">
						<span class="property-value" aria-labelledby="modifications-label"><g:link controller="modification" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.pictures}">
				<li class="fieldcontain">
					<span id="pictures-label" class="property-label"><g:message code="pub.pictures.label" default="Pictures" /></span>
					
						<g:each in="${pubInstance.pictures}" var="p">
						<span class="property-value" aria-labelledby="pictures-label"><g:link controller="picture" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.coments}">
				<li class="fieldcontain">
					<span id="coments-label" class="property-label"><g:message code="pub.coments.label" default="Coments" /></span>
					
						<g:each in="${pubInstance.coments}" var="c">
						<span class="property-value" aria-labelledby="coments-label"><g:link controller="coment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.latitude}">
				<li class="fieldcontain">
					<span id="latitude-label" class="property-label"><g:message code="pub.latitude.label" default="Latitude" /></span>
					
						<span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${pubInstance}" field="latitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.longitude}">
				<li class="fieldcontain">
					<span id="longitude-label" class="property-label"><g:message code="pub.longitude.label" default="Longitude" /></span>
					
						<span class="property-value" aria-labelledby="longitude-label"><g:fieldValue bean="${pubInstance}" field="longitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.myTypes}">
				<li class="fieldcontain">
					<span id="myTypes-label" class="property-label"><g:message code="pub.myTypes.label" default="My Types" /></span>
					
						<span class="property-value" aria-labelledby="myTypes-label"><g:fieldValue bean="${pubInstance}" field="myTypes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="pub.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${pubInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.users}">
				<li class="fieldcontain">
					<span id="users-label" class="property-label"><g:message code="pub.users.label" default="Users" /></span>
					
						<g:each in="${pubInstance.users}" var="u">
						<span class="property-value" aria-labelledby="users-label"><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pubInstance?.id}" />
					<g:link class="edit" action="edit" id="${pubInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
