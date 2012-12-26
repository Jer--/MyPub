package mypub

import org.springframework.dao.DataIntegrityViolationException

class PictureController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pictureInstanceList: Picture.list(params), pictureInstanceTotal: Picture.count()]
    }

    def create() {
        [pictureInstance: new Picture(params)]
    }

    def save() {
        def pictureInstance = new Picture(params)
        if (!pictureInstance.save(flush: true)) {
            render(view: "create", model: [pictureInstance: pictureInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'picture.label', default: 'Picture'), pictureInstance.id])
        redirect(action: "show", id: pictureInstance.id)
    }

    def show(Long id) {
        def pictureInstance = Picture.get(id)
        if (!pictureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), id])
            redirect(action: "list")
            return
        }

        [pictureInstance: pictureInstance]
    }

    def edit(Long id) {
        def pictureInstance = Picture.get(id)
        if (!pictureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), id])
            redirect(action: "list")
            return
        }

        [pictureInstance: pictureInstance]
    }

    def update(Long id, Long version) {
        def pictureInstance = Picture.get(id)
        if (!pictureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pictureInstance.version > version) {
                pictureInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'picture.label', default: 'Picture')] as Object[],
                          "Another user has updated this Picture while you were editing")
                render(view: "edit", model: [pictureInstance: pictureInstance])
                return
            }
        }

        pictureInstance.properties = params

        if (!pictureInstance.save(flush: true)) {
            render(view: "edit", model: [pictureInstance: pictureInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'picture.label', default: 'Picture'), pictureInstance.id])
        redirect(action: "show", id: pictureInstance.id)
    }

    def delete(Long id) {
        def pictureInstance = Picture.get(id)
        if (!pictureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), id])
            redirect(action: "list")
            return
        }

        try {
            pictureInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'picture.label', default: 'Picture'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'picture.label', default: 'Picture'), id])
            redirect(action: "show", id: id)
        }
    }
}
