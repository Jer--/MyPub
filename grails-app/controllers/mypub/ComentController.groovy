/*******************************************************************************
 * Author : Group BBHC
 * Licence : AGPL v3
 ******************************************************************************/
package mypub

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class ComentController {
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [comentInstanceList: Coment.list(params), comentInstanceTotal: Coment.count()]
    }

    def create() {
		def courant = springSecurityService.currentUser
		params["username"] = courant.username
        [comentInstance: new Coment(params)]
    }

    def save() {
		params["postDate"] = new Date()
        def comentInstance = new Coment(params)
        if (!comentInstance.save(flush: true)) {
            render(view: "create", model: [comentInstance: comentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'coment.label', default: 'Coment'), comentInstance.id])
        redirect(action: "showComent", id: comentInstance.id)
    }

	@Secured(['ROLE_ADMIN'])
    def show(Long id) {
        def comentInstance = Coment.get(id)
        if (!comentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(action: "list")
            return
        }

        [comentInstance: comentInstance]
    }

    def edit(Long id) {
        def comentInstance = Coment.get(id)
        if (!comentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(action: "list")
            return
        }

        [comentInstance: comentInstance]
    }

    def update(Long id, Long version) {
        def comentInstance = Coment.get(id)
        if (!comentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (comentInstance.version > version) {
                comentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'coment.label', default: 'Coment')] as Object[],
                          "Another user has updated this Coment while you were editing")
                render(view: "edit", model: [comentInstance: comentInstance])
                return
            }
        }

        comentInstance.properties = params

        if (!comentInstance.save(flush: true)) {
            render(view: "edit", model: [comentInstance: comentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'coment.label', default: 'Coment'), comentInstance.id])
        redirect(action: "show", id: comentInstance.id)
    }

    def delete(Long id) {
        def comentInstance = Coment.get(id)
        if (!comentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(controller: "user", action: "showProfile")
            return
        }

        try {
			def pubId = comentInstance.pub.id
            comentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(controller: "pub", action: "show", id:pubId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(action: "showComent", id: id)
        }
    }
	
	// Non- generated methods //////////////////////////////////////////////////
	
	def showComent(Long id) {
		def courrent = springSecurityService.currentUser
		String username = courrent.username
		def comentInstance = Coment.get(id)
		//String comentUsername = comentInstance.username
		if(comentInstance.username.equalsIgnoreCase(username)) {
			redirect(action: 'showMyComent', id: id)
		} else {
			redirect(action: "showAComent", id: id)
		}
	}
	
	def showMyComent(Long id) {
		def comentInstance = Coment.get(id)
		if (!comentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'coment.label', default: 'Coment'), id])
			redirect(controller: "user", action: "showProfile")
			return
		}

		[comentInstance: comentInstance]
	}
	
	def showAComent(Long id) {
		def comentInstance = Coment.get(id)
		if (!comentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'coment.label', default: 'Coment'), id])
			redirect(controller: "user", action: "showProfile")
			return
		}

		[comentInstance: comentInstance]
	}
	
	def listForAPub(Long id) {
		def pubInstance = Pub.get(id)
		[comentInstanceList: pubInstance.coments.sort{a,b -> b.postDate <=> a.postDate}, comentInstanceTotal: pubInstance.coments.size()]
	}
}
