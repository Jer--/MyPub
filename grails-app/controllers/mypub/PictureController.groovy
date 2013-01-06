/*******************************************************************************
 * Author : Group BBHC
 * License : AGPL v3
 ******************************************************************************/
package mypub

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class PictureController {
	
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pictureInstanceList: Picture.list(params), pictureInstanceTotal: Picture.count()]
    }

    def create() {
        [pictureInstance: new Picture(params)]
    }

    def save() {
        def pictureInstance = new Picture(params)
		def courant = springSecurityService.currentUser
		String username = courant.username
		User.findByUsername(username).addToPictures(pictureInstance)
        if (!pictureInstance.save(flush: true)) {
            render(view: "create", model: [pictureInstance: pictureInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'picture.label', default: 'Picture'), pictureInstance.name])
        redirect(action: "showPerso", id: pictureInstance.id)
    }

	@Secured(['ROLE_ADMIN'])
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
	
	//// Non Generated methods /////////////////////////////////////////////////////////////////
	
	def showPerso() {
		def pictureInstance = Picture.get(params.id)
		if (!pictureInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])
			redirect(action: "listPerso")
			return
		}

		[pictureInstance: pictureInstance]
	}
	
	def showImgAmi() {
		def pictureInstance = Picture.get(params.id)
		if (!pictureInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])
			redirect(action: "listPerso")
			return
		}

		[pictureInstance: pictureInstance]
	}
	
	def listPerso() {
		def courant = springSecurityService.currentUser
		String username = courant.username
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[pictureInstanceList: User.findByUsername(username).pictures, pictureInstanceTotal: User.findByUsername(username).pictures.size()]
	}
	
	def listFriend(Long id) {
		def userInstance = User.get(id)
		[pictureInstanceList: userInstance.pictures, pictureInstanceTotal: userInstance.pictures.size()]
	}

	def enleverList() {
		def courant = springSecurityService.currentUser
		def pictureP = Picture.get(params.id)
		String username = courant.username
		if(User.findByUsername(username).avatar.equals(pictureP))
			User.findByUsername(username).avatar = null
		else {
			User.findByUsername(username).removeFromPictures(Picture.findById(pictureP.id))
			Picture.findById(pictureP.id).delete(flush: true)
		}
		redirect(action: "listPerso")
	}
	
	def showAPub() {
		def pictureInstance = Picture.get(params.id)
		if (!pictureInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])
			redirect(controller: "user", action: "showProfile")
			return
		}

		[pictureInstance: pictureInstance]
	}
	
	def listPub(Long id) {
		def pubInstance = Pub.get(id)
		[pictureInstanceList: pubInstance.pictures, pictureInstanceTotal: pubInstance.pictures.size()]
	}
	
	def viewImageId = {
		def picture = User.get(params.id).avatar
		byte[] pic = picture.data
		response.contentType = "image/jpeg"
		response.outputStream << pic
	}
	
	def viewImage = {
		def picture = Picture.get(params.id)
		byte[] pic = picture.data
		response.contentType = "image/jpeg"
		response.outputStream << pic
	}
}
