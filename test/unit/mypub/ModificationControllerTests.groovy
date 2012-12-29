/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
 ******************************************************************************/
package mypub



import org.junit.*
import grails.test.mixin.*

@TestFor(ModificationController)
@Mock(Modification)
class ModificationControllerTests {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		//params["name"] = 'someValidName'
		params["username"]='toto'
		params["proposalDate"] = new Date('2007/01/01')
		params["pub"] = new Pub(name:'pub1', address:'123 av jj', latitude:'aze', longitude:'azeza')


	}

	void testIndex() {
		controller.index()
		assert "/modification/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.modificationInstanceList.size() == 0
		assert model.modificationInstanceTotal == 0
	}

	void testCreate() {
		def model = controller.create()

		assert model.modificationInstance != null
	}

	void testSave() {
		controller.save()

		assert model.modificationInstance != null
		assert view == '/modification/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/modification/show/1'
		assert controller.flash.message != null
		assert Modification.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/modification/list'

		populateValidParams(params)
		def modification = new Modification(params)

		assert modification.save() != null

		params.id = modification.id

		def model = controller.show()

		assert model.modificationInstance == modification
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/modification/list'

		populateValidParams(params)
		def modification = new Modification(params)

		assert modification.save() != null

		params.id = modification.id

		def model = controller.edit()

		assert model.modificationInstance == modification
	}

	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/modification/list'

		response.reset()

		populateValidParams(params)
		def modification = new Modification(params)

		assert modification.save() != null

		// test invalid parameters in update
		params.id = modification.id
		//TODO: add invalid values to params object
		params["proposalDate"]='text'

		controller.update()

		assert view == "/modification/edit"
		assert model.modificationInstance != null

		modification.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/modification/show/$modification.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		modification.clearErrors()

		populateValidParams(params)
		params.id = modification.id
		params.version = -1
		controller.update()

		assert view == "/modification/edit"
		assert model.modificationInstance != null
		assert model.modificationInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/modification/list'

		response.reset()

		populateValidParams(params)
		def modification = new Modification(params)

		assert modification.save() != null
		assert Modification.count() == 1

		params.id = modification.id

		controller.delete()

		assert Modification.count() == 0
		assert Modification.get(modification.id) == null
		assert response.redirectedUrl == '/modification/list'
	}
}
