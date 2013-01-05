/*******************************************************************************
 * Author : Group BBHC
 * License : AGPL v3
 ******************************************************************************/
package mypub

import org.springframework.dao.DataIntegrityViolationException

class PubController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pubInstanceList: Pub.list(params), pubInstanceTotal: Pub.count()]
    }

    def create() {
        [pubInstance: new Pub(params)]
    }

    def save() {
        def pubInstance = new Pub(params)
        if (!pubInstance.save(flush: true)) {
            render(view: "create", model: [pubInstance: pubInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pub.label', default: 'Pub'), pubInstance.id])
        redirect(action: "show", id: pubInstance.id)
    }

    def show(Long id) {
        def pubInstance = Pub.get(id)
        if (!pubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pub.label', default: 'Pub'), id])
            redirect(action: "list")
            return
        }

        [pubInstance: pubInstance]
    }

    def edit(Long id) {
        def pubInstance = Pub.get(id)
        if (!pubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pub.label', default: 'Pub'), id])
            redirect(action: "list")
            return
        }

        [pubInstance: pubInstance]
    }

    def update(Long id, Long version) {
        def pubInstance = Pub.get(id)
        if (!pubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pub.label', default: 'Pub'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pubInstance.version > version) {
                pubInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pub.label', default: 'Pub')] as Object[],
                          "Another user has updated this Pub while you were editing")
                render(view: "edit", model: [pubInstance: pubInstance])
                return
            }
        }

        pubInstance.properties = params

        if (!pubInstance.save(flush: true)) {
            render(view: "edit", model: [pubInstance: pubInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pub.label', default: 'Pub'), pubInstance.id])
        redirect(action: "show", id: pubInstance.id)
    }

    def delete(Long id) {
        def pubInstance = Pub.get(id)
        if (!pubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pub.label', default: 'Pub'), id])
            redirect(action: "list")
            return
        }

        try {
            pubInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pub.label', default: 'Pub'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pub.label', default: 'Pub'), id])
            redirect(action: "show", id: id)
        }
    }
	
	// Non - generated methods ////////////////////////////////////////////
	
	def searchPub() {
		def param = params.pubname
		def pubList = Pub.findAll {name == param || city == param || zip == param}
		if(!pubList) {
			flash.message = "No pub found"
			redirect(action: "list")
			return
		}
		[pubInstanceList: pubList, pubInstanceTotal: pubList.size()]
	}
}
