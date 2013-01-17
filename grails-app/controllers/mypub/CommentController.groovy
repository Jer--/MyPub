/*******************************************************************************
 * Author : Group BBHC
 * License : AGPL v3
 ******************************************************************************/
package mypub

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class CommentController {
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [commentInstanceList: Comment.list(params), commentInstanceTotal: Comment.count()]
    }

    def create() {
		def courant = springSecurityService.currentUser
		params["username"] = courant.username
        [commentInstance: new Comment(params)]
    }

    def save() {
		params["postDate"] = new Date()
        def commentInstance = new Comment(params)
        if (!commentInstance.save(flush: true)) {
            render(view: "create", model: [commentInstance: commentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'comment.label', default: 'comment'), commentInstance.id])
        redirect(action: "showComment", id: commentInstance.id)
    }

	@Secured(['ROLE_ADMIN'])
    def show(Long id) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'comment'), id])
            redirect(action: "list")
            return
        }

        [commentInstance: commentInstance]
    }

    def edit(Long id) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'comment'), id])
            redirect(action: "list")
            return
        }

        [commentInstance: commentInstance]
    }

    def update(Long id, Long version) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'comment'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (commentInstance.version > version) {
                commentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'comment.label', default: 'comment')] as Object[],
                          "Another user has updated this comment while you were editing")
                render(view: "edit", model: [commentInstance: commentInstance])
                return
            }
        }

        commentInstance.properties = params

        if (!commentInstance.save(flush: true)) {
            render(view: "edit", model: [commentInstance: commentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'comment.label', default: 'comment'), commentInstance.id])
        redirect(action: "show", id: commentInstance.id)
    }

    def delete(Long id) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'comment'), id])
            redirect(controller: "user", action: "showProfile")
            return
        }

        try {
			def pubId = commentInstance.pub.id
            commentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'comment.label', default: 'comment'), id])
            redirect(controller: "pub", action: "show", id:pubId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'comment.label', default: 'comment'), id])
            redirect(action: "showcomment", id: id)
        }
    }
	
	// Non- generated methods //////////////////////////////////////////////////
	
	def showComment(Long id) {
		def courrent = springSecurityService.currentUser
		String username = courrent.username
		def commentInstance = Comment.get(id)
		//String commentUsername = commentInstance.username
		if(commentInstance.username.equalsIgnoreCase(username)) {
			redirect(action: 'showMyComment', id: id)
		} else {
			redirect(action: "showAComment", id: id)
		}
	}
	
	def showMyComment(Long id) {
		def commentInstance = Comment.get(id)
		if (!commentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'comment'), id])
			redirect(controller: "user", action: "showProfile")
			return
		}

		[commentInstance: commentInstance]
	}
	
	def showAComment(Long id) {
		def commentInstance = Comment.get(id)
		if (!commentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'comment'), id])
			redirect(controller: "user", action: "showProfile")
			return
		}

		[commentInstance: commentInstance]
	}
	
	def listForAPub(Long id) {
		def pubInstance = Pub.get(id)
		[commentInstanceList: pubInstance.comments.sort{a,b -> b.postDate <=> a.postDate}, commentInstanceTotal: pubInstance.comments.size()]
	}
}
