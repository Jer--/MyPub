/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub

import org.springframework.dao.DataIntegrityViolationException

class ModificationController {
	def springSecurityService
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[modificationInstanceList: Modification.list(params), modificationInstanceTotal: Modification.count()]
	}

	def create() {
		params.pub = Pub.findById(params.pubId)
		System.out.println("++++++++++ param : "+params.pub);
		[modificationInstance: new Modification(params)]
	}

	def save() {
		def modificationInstance = new Modification(params)
		modificationInstance.author = springSecurityService.currentUser
		if (!modificationInstance.save(flush: true)) {
			render(view: "create", model: [modificationInstance: modificationInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'modification.label', default: 'Modification'),
			modificationInstance.id
		])
		def username = modificationInstance.author.username
		redirect(action: "show", id: modificationInstance.id)
	}

	def show(Long id) {
		def modificationInstance = Modification.get(id)
		if (!modificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'modification.label', default: 'Modification'),
				id
			])
			redirect(action: "list")
			return
		}

		[modificationInstance: modificationInstance]
	}

	def edit(Long id) {
		def modificationInstance = Modification.get(id)
		if (!modificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'modification.label', default: 'Modification'),
				id
			])
			redirect(action: "list")
			return
		}

		[modificationInstance: modificationInstance]
	}

	def update(Long id, Long version) {
		def modificationInstance = Modification.get(id)
		if (!modificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'modification.label', default: 'Modification'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (modificationInstance.version > version) {
				modificationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'modification.label', default: 'Modification')] as Object[],
						"Another user has updated this Modification while you were editing")
				render(view: "edit", model: [modificationInstance: modificationInstance])
				return
			}
		}

		modificationInstance.properties = params

		if (!modificationInstance.save(flush: true)) {
			render(view: "edit", model: [modificationInstance: modificationInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'modification.label', default: 'Modification'),
			modificationInstance.id
		])
		redirect(action: "show", id: modificationInstance.id)
	}

	def delete(Long id) {
		def modificationInstance = Modification.get(id)
		if (!modificationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'modification.label', default: 'Modification'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			modificationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'modification.label', default: 'Modification'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'modification.label', default: 'Modification'),
				id
			])
			redirect(action: "show", id: id)
		}
	}
}
