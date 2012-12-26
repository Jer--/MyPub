<%--#-------------------------------------------------------------------------------
# Author : Group BBHC
# Licence : AGPL v3
#-------------------------------------------------------------------------------
--%>

<%@ page import="mypub.User"%>
<%@ page
	import="org.springframework.dao.DataIntegrityViolationException"%>
<%@ page import="grails.plugins.springsecurity.Secured"%>
<%@ page
	import="org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils"%>
<%@ page
	import="org.springframework.security.authentication.AccountExpiredException"%>
<%@ page
	import="org.springframework.security.authentication.CredentialsExpiredException"%>
<%@ page
	import="org.springframework.security.authentication.DisabledException"%>
<%@ page
	import="org.springframework.security.authentication.LockedException"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder as SCH"%>
<%@ page import="org.springframework.security.web.WebAttributes"%>
<%@ page
	import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"%>


<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenue sur MyPub !</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
	
		<%--
si l'utilisateur n'est pas connecté
	--%>
	<sec:ifNotLoggedIn>
		<a href="#page-body" class="skip"><g:message
				code="default.link.skip.label" default="Skip to content&hellip;" /></a>
		<div align=left id="status" role="main">
			<h1 align=center>Menu</h1>
			<p>
				Pour vous connecter :<br />
				<%--
				redirection vers la connexion
				--%>
				<g:link controller='login' action='auth'>Connexion</g:link>
			</p>
			<p>
			<%--
				redirection vers l'inscription
				--%>
				Pour vous inscrire :<br />
				<g:link controller='User' action='create'>Inscription</g:link>
			</p>
		</div>


		<div id="page-body" role="explain">
			<h1>Bienvenue sur MyPub</h1>
			<br /> <br /> <br /> <br />
			<p align=justify>Hey ! Welcome to MyPub ! Your e-community which that you can exchange your favorites pubs with your friends! Recommend, note, comment and share !</p>
			<br /> <br /> <br /> <br />
		</div>
	</sec:ifNotLoggedIn>
	<%--
			si l'utilisateur est connecté
			--%>
	<sec:ifLoggedIn>
		<div class="clear"></div>
		<div id="controller-list" role="navigation">
			<div align=left id="status" role="main">
			<%--
				menu principal : diverses redirections
				--%>
				<h1 align=center>Menu</h1>
				<p align="center">
					Voir votre Profil :<br />
					<g:link controller='User' action='voir'>Mon Profil</g:link>
				</p>
				<p align="center">
					Voir les Membres de MyPub :<br />
					<g:link controller='User' action='list'>Membres</g:link>
				</p>
			
				<p align="center">
					Voir les Pubs :<br />
					<g:link controller='Pub' action='list'>Pubs</g:link>
				</p>
				<p align="center">
					Se Déconnecter :<br />
					<g:link controller='Logout'>Déconnexion</g:link>
				</p>
			</div>
			<div>
				<h1 align="center">
					Bienvenue sur votre page d'accueil
					<sec:username />
				</h1>
			</div>
		</div>
	</sec:ifLoggedIn>
	
		<!--<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			
			<h1>Menu :</h1>
			<ul>
				<li>bientot...</li>
				<li>bientot...</li>
				<li>bientot...</li>
				<li>bientot...</li>
				
			</ul>
			<%--<ul>
				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
					<li>${plugin.name} - ${plugin.version}</li>
				</g:each>
			</ul>--%>
		</div>
		<div id="page-body" role="main">
			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
		</div>-->
	</body>
</html>
