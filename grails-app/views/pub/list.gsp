<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
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
					
						<g:sortableColumn property="name" title="${message(code: 'pub.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'pub.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'pub.city.label', default: 'City')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'pub.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="latitude" title="${message(code: 'pub.latitude.label', default: 'Latitude')}" />
					
						<g:sortableColumn property="longitude" title="${message(code: 'pub.longitude.label', default: 'Longitude')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pubInstanceList}" status="i" var="pubInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pubInstance.id}">${fieldValue(bean: pubInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: pubInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "latitude")}</td>
					
						<td>${fieldValue(bean: pubInstance, field: "longitude")}</td>
					
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
