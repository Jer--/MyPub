<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Pub" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pub.label', default: 'Pub')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pub" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link controller='Pub' action='listPubs'>My Pubs</g:link></li>
				<li>
					<g:form action="searchPub" >
						<g:submitButton name="Recherche" class="recherche" value="${message(code: 'default.button.recherche.label', default: 'Find Pub')}" />
						<g:textField name="pubname" paramName="pubname" required="" placeholder="Name OR City OR Zip"/>
					</g:form>
				</li>	
				<li style="float : right"><g:link controller='Logout'><img src="${resource(dir: 'images', file: 'skin/logout.png')}" width="15"/> Logout</g:link></li>
				
			</ul>
		</div>
		<div id="list-pub" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="presentationPicture" title="${message(code: 'pub.presentationPicture.label', default: 'Presentation Picture')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'pub.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'pub.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="addressOptionnal" title="${message(code: 'pub.addressOptionnal.label', default: 'Address Optionnal')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'pub.city.label', default: 'City')}" />
					
						<g:sortableColumn property="website" title="${message(code: 'pub.website.label', default: 'Website')}" />
					
						<g:sortableColumn property="zip" title="${message(code: 'pub.zip.label', default: 'Zip')}" />
					
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${pubInstanceList}" status="i" var="pubInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<g:if test="${pubInstance?.presentationPicture}">
							<td><img class="Picture" src="${createLink(controller:'Picture', action:'viewImage', id:pubInstance.presentationPicture.id)}" width="100"/></td>
						</g:if>
						<g:else>
							<td><img src="${resource(dir: 'images', file: 'pub/TemplatePubBasic.jpg')}" width="100"/></td>
						</g:else>
					
						<td><g:link action="show" id="${pubInstance.id}">${fieldValue(bean: pubInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: pubInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "addressOptionnal")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "website")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "zip")}</td>
						
						<td><g:link action="addPub" id="${pubInstance.id}">Add</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pubInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
