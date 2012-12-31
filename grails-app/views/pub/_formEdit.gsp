<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Pub" %>



<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="pub.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${pubInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="pub.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" required="" value="${pubInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="pub.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${pubInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="pub.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="type" from="${pubInstance.constraints.type.inList}" required="" value="${pubInstance?.type}" valueMessagePrefix="pub.type"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'latitude', 'error')} required">
	<label for="latitude">
		<g:message code="pub.latitude.label" default="Latitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="latitude" value="${fieldValue(bean: pubInstance, field: 'latitude')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'longitude', 'error')} required">
	<label for="longitude">
		<g:message code="pub.longitude.label" default="Longitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="longitude" value="${fieldValue(bean: pubInstance, field: 'longitude')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'presentationPicture', 'error')} ">
	<label for="presentationPicture">
		<g:message code="pub.presentationPicture.label" default="Presentation Picture" />
		
	</label>
	<g:select id="presentationPicture" name="presentationPicture.id" from="${mypub.Picture.list()}" optionKey="id" value="${pubInstance?.presentationPicture?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'pictures', 'error')} ">
	<label for="pictures">
		<g:message code="pub.pictures.label" default="Pictures" />
		
	</label>
	<g:select name="pictures" from="${mypub.Picture.list()}" multiple="multiple" optionKey="id" size="5" value="${pubInstance?.pictures*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'modifications', 'error')} ">
	<label for="modifications">
		<g:message code="pub.modifications.label" default="Modifications" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${pubInstance?.modifications?}" var="m">
    <li><g:link controller="modification" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="modification" action="create" params="['pub.id': pubInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'modification.label', default: 'Modification')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'coments', 'error')} ">
	<label for="coments">
		<g:message code="pub.coments.label" default="Coments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${pubInstance?.coments?}" var="c">
    <li><g:link controller="coment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="coment" action="create" params="['pub.id': pubInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'coment.label', default: 'Coment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'users', 'error')} ">
	<label for="users">
		<g:message code="pub.users.label" default="Users" />
		
	</label>
<ul class="one-to-many">
	<g:each in="${pubInstance?.users?}" var="u">
    	<li><g:link controller="user" action="showPublic" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
	</g:each>
</ul>
	
</div>