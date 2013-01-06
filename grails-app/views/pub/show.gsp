<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# License : AGPL v3
#-------------------------------------------------------------------------------
--%>
<%@ page import="mypub.Pub" %>
<!DOCTYPE html>
<html>
	<head>
		<g:set var="pubInst" value="${pubInstance}"/>

	<%
	//groovy code to get lat and longitude from the adress
	bar= pubInst.name
  	 ad = pubInst.address
	 adOp = pubInst.addressOptionnal
  	 city = pubInst.city
  	 base = 'http://maps.googleapis.com/maps/api/geocode/xml?'
  		if(adOp==null)
		  urla = base + 'address='+  ad + ',+'+city +'&sensor=true'
		  else
 	 			urla = base + 'address='+  ad +',+' + adOp + ',+'+city +'&sensor=true'
 	 urlb = urla.replaceAll(' ','+') //replace blank by +
 	 
 	 def url = new URL(urlb)
	  //google api
	 def geoCodeResult = new XmlParser().parseText(url.getText())
	 def lat = geoCodeResult.result.geometry.location.lat.text()
 	 def lng = geoCodeResult.result.geometry.location.lng.text()
	
  	%>
	<%--Script for Google Map --%>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["map"]});
      google.setOnLoadCallback(drawMap);
      var JSlat=<%=lat%>;
      var JSlng=<%=lng%>;
      
      function drawMap() {
        var data = google.visualization.arrayToDataTable([
          ['Lat', 'Lon', 'Name'],
          [JSlat, JSlng, 'bar']
        ]);

        var map = new google.visualization.Map(document.getElementById('map_div'));
        map.draw(data, {showTip: true});
      }
    </script>
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
			<%--insert the MAP --%>
			<div id="map_div" style="width: 400px; height: 300px"></div>
			</br>
				<g:if test="${pubInstance?.presentationPicture}">
					<img class="Picture" src="${createLink(controller:'Picture', action:'viewImage', id:pubInstance.presentationPicture.id)}" width="150"/>
				</g:if>
				<g:else>
					<img src="${resource(dir: 'images', file: 'pub/TemplatePubBasic.jpg')}" width="150"/>
				</g:else>
				
				<g:if test="${pubInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="pub.name.label" default="Name" /></span>
					
		            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${pubInstance}" field="name"/><g:link controller="modification" action="create" params="['pub.id': pubInstance?.id,about:'name']"><img style="vertical-align:middle" src="${resource(dir: 'images', file: 'modif.png')}"/></g:link></span>

				</li>
				</g:if>
			
				<g:if test="${pubInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="pub.address.label" default="Address" /></span>

	            <span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${pubInstance}" field="address"/><g:link controller="modification" action="create" params="['pub.id': pubInstance?.id,about:'address']"><img style="vertical-align:middle" src="${resource(dir: 'images', file: 'modif.png')}"/></g:link></span>
						
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.addressOptionnal}">
				<li class="fieldcontain">
					<span id="addressOptionnal-label" class="property-label"><g:message code="pub.addressOptionnal.label" default="Address Optionnal" /></span>
					
						<span class="property-value" aria-labelledby="addressOptionnal-label"><g:fieldValue bean="${pubInstance}" field="addressOptionnal"/></span>
					
				</li>
				
				
				<g:if test="${pubInstance?.zip}">
				<li class="fieldcontain">
					<span id="zip-label" class="property-label"><g:message code="pub.zip.label" default="Zip" /></span>
					
						<span class="property-value" aria-labelledby="zip-label"><g:fieldValue bean="${pubInstance}" field="zip"/></span>
					
				</li>
				</g:if>
			
				
				</g:if>
			
				<g:if test="${pubInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="pub.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${pubInstance}" field="city"/></span>
					
				</li>
				</g:if>
				
			
				<g:if test="${pubInstance?.website}">
				<li class="fieldcontain">
					<span id="website-label" class="property-label"><g:message code="pub.website.label" default="Website" /></span>
					
						<span class="property-value" aria-labelledby="website-label"><g:fieldValue bean="${pubInstance}" field="website"/></span>
					
				</li>
				</g:if>
			
			
				<g:if test="${pubInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="pub.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${pubInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.presentationPicture}">
				<li class="fieldcontain">
					<span id="presentationPicture-label" class="property-label"><g:message code="pub.presentationPicture.label" default="Presentation Picture" /></span>
					
						<span class="property-value" aria-labelledby="presentationPicture-label"><g:link controller="picture" action="show" id="${pubInstance?.presentationPicture?.id}">${pubInstance?.presentationPicture?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pubInstance?.pictures}">
				<li class="fieldcontain">
					<span id="pictures-label" class="property-label"><g:message code="pub.pictures.label" default="Pictures" /></span>
					<g:link controller="picture" action="listPub" id="${pubInstance.id}">See all Pictures</g:link>
						<g:each in="${pubInstance.pictures}" var="p">
						<span class="property-value" aria-labelledby="pictures-label"><g:link controller="picture" action="showPub" id="${p.id}" params="['pubId': pubInstance?.id]">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
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
			
				<g:if test="${pubInstance?.coments}">
				<li class="fieldcontain">
					<span id="coments-label" class="property-label"><g:message code="pub.coments.label" default="Coments" /></span>
						<span class="property-value" aria-labelledby="coments-label"><g:link controller="coment" action="listForAPub" id="${pubInstance.id}">See coments list</g:link></span>
						<g:each in="${pubInstance.coments.sort{a,b -> b.postDate <=> a.postDate}.take(10)}" var="c">
						<span class="property-value" aria-labelledby="coments-label"><g:link controller="coment" action="showComent" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
				<g:link controller="coment" action="create" params="['pub.id': pubInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'coment.label', default: 'Coment')])}</g:link>
			
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
