/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub



import org.junit.*
import grails.test.mixin.*

@TestFor(UserController)
@Mock(User)
class UserControllerTests {

	void setUpSpringSecurity() {
		def mockSpringSecurityService = mockFor(grails.plugins.springsecurity.SpringSecurityService)
		mockSpringSecurityService.demand.getPrincipal() { -> ["username":"spiderman"] }
		controller.springSecurityService = mockSpringSecurityService.createMock()
	}

	def populateValidParams(params) {
		assert params != null
		params["username"] = 'superman'
		params["password"] = 'xxx'
		params["firstName"] = 'Kent'
		params["lastName"] = 'Clark'
		params["mail"] = 'superman@hero.galaxy'
		//params["birthday"]= new Date ('1800/01/01')
		//params["enabled"] = true
		//params["accountExpired"] = false
		//params["accountLocked"] = false
		//params["passwordExpired"] = false

	}

	void testIndex() {
		controller.index()
		assert "/user/list" == response.redirectedUrl
	}

	void testList() {
		def model = controller.list()
		
		assert model.userInstanceList.size() == 0
		assert model.userInstanceTotal == 0
	}

	void testCreate() {
		def model = controller.create()

		assert model.userInstance != null
	}
	
//	void testSave() {
//		setUpSpringSecurity()
//		controller.save()
//
//		assert model.userInstance != null
//		assert view == '/user/create'
//
//		response.reset()
//
//		populateValidParams(params)
//		controller.save()
//
//		assert response.redirectedUrl == '/user/show/1'
//		assert controller.flash.message != null
//		assert User.count() == 1
//	}
	
	//AVANCEE
	
//	void testVoir() {
//		response.reset()
//		
//		populateValidParams(params)
//		controller.voir()
//		
//		assert response.redirectedUrl == '/user/show/1'
//	}
	
	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null

		params.id = user.id

		def model = controller.show()

		assert model.userInstance == user
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null

		params.id = user.id

		def model = controller.edit()

		assert model.userInstance == user
	}

//	void testUpdate() {
//		controller.update()
//
//		assert flash.message != null
//		assert response.redirectedUrl == '/user/list'
//
//		response.reset()
//
//		populateValidParams(params)
//		def user = new User(params)
//
//		assert user.save() != null
//
//		// test invalid parameters in update
//		params.id = user.id
//		//TODO: add invalid values to params object
//		params["newContent"] = null
//		controller.update()
//
//		assert view == "/user/edit"
//		assert model.userInstance != null
//
//		user.clearErrors()
//
//		populateValidParams(params)
//		controller.update()
//
//		assert response.redirectedUrl == "/user/show/$user.id"
//		assert flash.message != null
//
//		//test outdated version number
//		response.reset()
//		user.clearErrors()
//
//		populateValidParams(params)
//		params.id = user.id
//		params.version = -1
//		controller.update()
//
//		assert view == "/modification/edit"
//		assert model.userInstance != null
//		assert model.userInstance.errors.getFieldError('version')
//		assert flash.message != null
//	}
//
//	void testDelete() {
//		controller.delete()
//		assert flash.message != null
//		assert response.redirectedUrl == '/user/list'
//
//		response.reset()
//
//		populateValidParams(params)
//		def user = new User(params)
//
//		assert user.save() != null
//		assert User.count() == 1
//
//		params.id = user.id
//
//		controller.delete()
//
//		assert User.count() == 0
//		assert User.get(user.id) == null
//		assert response.redirectedUrl == '/user/list'
//	}
}
