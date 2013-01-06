/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
 ******************************************************************************/
package mypub

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import org.springframework.dao.DataIntegrityViolationException

class UserController {
	/*To get id of user during connexion*/
	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }
		UserRole.create userInstance, Role.findByAuthority('ROLE_USER'), true
		
        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.username])
		redirect(controller: "login", action: "auth")
    }

	def voir(){
		def userInstance = springSecurityService.getCurrentUser().getId()
		redirect(controller: 'user', action: 'show', id: userInstance)
	}
	
    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.username])
        redirect(action: "showProfile")
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), userInstance.username])
            redirect(action: "list")
            return
        }

        try {
			userInstance.friends.each {
				it.removeFromFriends(userInstance)
			}
			def userRoleInstances = UserRole.findAllByUser(userInstance)
			userRoleInstances.each {
				it.delete()
			}
			userInstance.pictures.each {
				it.delete()
			}
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), userInstance.username])
            redirect(controller:"logout")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), userInstance.username])
            redirect(action: "show", id: id)
        }
    }
	
	// Non - Generated methos ////////////////////////////////////////
	
	def showProfile(){
		def courant = springSecurityService.currentUser
		String username = courant.username
		def userInstance = User.findByUsername(username)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(uri: '/')
			return
		}

		[userInstance: userInstance]
	}
	
	def showFriend(Long id) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(action: "listFriends")
			return
		}

		[userInstance: userInstance]
	}
	
	def showPublic(Long id) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), userInstance.username])
			redirect(action: "listFriends")
			return
		}

		[userInstance: userInstance]
	}
	
	def listFriends() {
		def courant = springSecurityService.currentUser
		String username = courant.username
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[userInstanceList: User.findByUsername(username).friends, userInstanceTotal: User.findByUsername(username).friends.size()]
	}
	
	def listFriendsOfMyFriend(Long id) {
		def userInstance = User.get(id)
		[userInstanceList: userInstance.friends, userInstanceTotal: userInstance.friends.size()]
	}
	
	def addFriend() {
		def courant = springSecurityService.currentUser
		String username = courant.username
		def userI = User.get(params.id)
		if (username.equals(userI.username)) {
			flash.message = "You can't add yourself as your friend"
			redirect(action: "listFriends")
			return
		}
		User.findByUsername(username).addToFriends(User.findByUsername(userI.username))
		User.findByUsername(userI.username).addToFriends(User.findByUsername(username))
		redirect(action: "listFriends")
	}
	
	def removeFriend() {
		def courant = springSecurityService.currentUser
		String username = courant.username
		def userI = User.get(params.id)
		User.findByUsername(username).removeFromFriends(User.findByUsername(userI.username))
		User.findByUsername(userI.username).removeFromFriends(User.findByUsername(username))
		redirect(action: "listFriends")
	}
	
	def searchUser() {
		//def userList = User.findByUsername(params.username)
		def param = params.username
		def userList = User.findAll {username == param || firstName == param || lastName == param}
		if(!userList) {
			flash.message = "No user found"
			redirect(action: "listFriends")
			return
		}
		[userInstanceList: userList, userInstanceTotal: userList.size()]
	}

}
