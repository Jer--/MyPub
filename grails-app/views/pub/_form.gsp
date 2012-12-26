<%@ page import="mypub.Pub" %>



<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="pub.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${pubInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'presentationPicture', 'error')} ">
	<label for="presentationPicture">
		<g:message code="pub.presentationPicture.label" default="Presentation Picture" />
		
	</label>
	<g:select id="presentationPicture" name="presentationPicture.id" from="${mypub.Picture.list()}" optionKey="id" value="${pubInstance?.presentationPicture?.id}" class="many-to-one" noSelection="['null': '']"/>
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

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'pictures', 'error')} ">
	<label for="pictures">
		<g:message code="pub.pictures.label" default="Pictures" />
		
	</label>
	<g:select name="pictures" from="${mypub.Picture.list()}" multiple="multiple" optionKey="id" size="5" value="${pubInstance?.pictures*.id}" class="many-to-many"/>
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

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'latitude', 'error')} ">
	<label for="latitude">
		<g:message code="pub.latitude.label" default="Latitude" />
		
	</label>
	<g:textField name="latitude" value="${pubInstance?.latitude}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'longitude', 'error')} ">
	<label for="longitude">
		<g:message code="pub.longitude.label" default="Longitude" />
		
	</label>
	<g:textField name="longitude" value="${pubInstance?.longitude}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'myTypes', 'error')} ">
	<label for="myTypes">
		<g:message code="pub.myTypes.label" default="My Types" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="pub.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${pubInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pubInstance, field: 'users', 'error')} ">
	<label for="users">
		<g:message code="pub.users.label" default="Users" />
		
	</label>
	
</div>

