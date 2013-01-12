/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub



import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import grails.plugins.springsecurity.SpringSecurityService
import org.junit.*
import grails.test.mixin.*

@TestFor(ModificationController)
@TestMixin(Pub)
@Mock(Modification)
class ModificationControllerTests {

	def setUpSpringSecurity() {
		def user1 = new User(username: 'user1',
			password: 'pass1',
			firstName: 'alfred',
			lastName: 'alfredaussi',
			mail: 'alfred@john.fr')

		def security = mockFor(SpringSecurityService)
		security.metaClass.getCurrentUser = { -> return user1 }
		
		return security
	}
	
	def setUpSpringSecurity(User u) {
		
		def security = mockFor(SpringSecurityService)
		security.metaClass.getCurrentUser = { -> return u }
		return security
	}

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		params["author"] = new User(username:"userTest", password:"passwordTest", firstName:"Delon", lastName:"Alain", sex:'M', mail:"testmail@test.com")
		params["about"] = "city"
		params["newContent"] = "Paris"
		Pub pubInstance = new Pub(name:'pub1', address:'123 av jj', city:'Toulouse')
		params["pub"] = pubInstance
		params["pubId"] = pubInstance.id
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
		controller.springSecurityService = setUpSpringSecurity()
		mockDomain(Pub)
		def model = controller.create()

		assert model.modificationInstance != null
	}

	void testSave() {
		controller.springSecurityService = setUpSpringSecurity()
		mockDomain(Pub)
		controller.save()

		assert model.modificationInstance != null
		assert view == '/modification/create'

		response.reset()
		populateValidParams(params)
//		controller.save()
//
//		assert response.redirectedUrl == '/modification/show/1'
//		assert controller.flash.message != null
//		assert Modification.count() == 1
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
		params["newContent"] = null
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
