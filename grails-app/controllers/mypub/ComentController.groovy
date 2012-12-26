package mypub

import org.springframework.dao.DataIntegrityViolationException

class ComentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [comentInstanceList: Coment.list(params), comentInstanceTotal: Coment.count()]
    }

    def create() {
        [comentInstance: new Coment(params)]
    }

    def save() {
        def comentInstance = new Coment(params)
        if (!comentInstance.save(flush: true)) {
            render(view: "create", model: [comentInstance: comentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'coment.label', default: 'Coment'), comentInstance.id])
        redirect(action: "show", id: comentInstance.id)
    }

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
            redirect(action: "list")
            return
        }

        try {
            comentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'coment.label', default: 'Coment'), id])
            redirect(action: "show", id: id)
        }
    }
}
