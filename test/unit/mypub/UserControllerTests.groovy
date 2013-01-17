/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub

import org.junit.*
import grails.test.mixin.*

import grails.plugins.springsecurity.SpringSecurityService

@TestFor(UserController)
@Mock(User)
class UserControllerTests {

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
		params["username"] = 'superman'
		params["password"] = 'xxx'
		params["firstName"] = 'Kent'
		params["lastName"] = 'Clark'
		params["mail"] = 'superman@hero.galaxy'
		//params["birthday"]= new Date ('1800/01/01')
//		params["enabled"] = true
//		params["accountExpired"] = false
//		params["accountLocked"] = false
//		params["passwordExpired"] = false

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
	
	void testSave() {
		
		mockDomain(UserRole)
		mockDomain(Role)
		
		controller.springSecurityService = setUpSpringSecurity()
		
		controller.save()

		assert model.userInstance != null
		assert view == '/user/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/login/auth'
		assert controller.flash.message != null
		assert User.count() == 1
	}
	
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

	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		response.reset()

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null

		// test invalid parameters in update
		params.id = user.id
		//invalid values to params object
		params["username"] = null
		controller.update()

		assert view == "/user/edit"
		assert model.userInstance != null

		user.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/user/showProfile"
		assert flash.message != null

		//test outdated version number
		response.reset()
		user.clearErrors()

		populateValidParams(params)
		params.id = user.id
		params.version = -1
		controller.update()

		assert view == "/user/edit"
		assert model.userInstance != null
		assert model.userInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {

		mockDomain(UserRole)
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		response.reset()

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null
		assert User.count() == 1

		params.id = user.id

		controller.delete()

		assert User.count() == 0
		assert User.get(user.id) == null
		assert response.redirectedUrl == '/logout'
	}
	
	
	// Non - generated tests ///////////////////////////////////////////
	
	void testVoir() {
		
		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null
		
		controller.springSecurityService = setUpSpringSecurity(user)
		
		controller.voir()
		
		assert response.redirectedUrl == '/user/show/1'
	}
	
	void testShowProfile(){
		
		controller.springSecurityService = setUpSpringSecurity()
		
		controller.showProfile()
		
		assert flash.message != null
		assert response.redirectedUrl == '/'
		
		response.reset()

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null
		
		controller.springSecurityService = setUpSpringSecurity(user)

		params.id = user.id

		def model = controller.showProfile()

		assert model.userInstance == user

	}
	
	void testShowFriend() {
		controller.showFriend()
		
		assert flash.message != null
		assert response.redirectedUrl == '/user/listFriends'

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null

		params.id = user.id

		def model = controller.showFriend()

		assert model.userInstance == user
	}
	
	void testShowPublic() {
		controller.showPublic()
		
		assert flash.message != null
		assert response.redirectedUrl == '/user/listFriends'

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null

		params.id = user.id

		def model = controller.showPublic()

		assert model.userInstance == user
	}
	
	void testListFriends() {
		
		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null
		
		params["username"] = "user2"
		user.addToFriends(new User(params))
		
		controller.springSecurityService = setUpSpringSecurity(user)
		
		def model = controller.listFriends()
		
		assert model.userInstanceList.size() == 1
		assert model.userInstanceTotal == 1
	}
	
	void testListFriendsOfMyFriend() {
		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null
		
		params["username"] = "user2"
		user.addToFriends(new User(params))
		
		params["id"] = user.id
		def model = controller.listFriendsOfMyFriend()
		
		assert model.userInstanceList.size() == 1
		assert model.userInstanceTotal == 1
	}
	
	void testAddFriend() {
		
		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null
		
		controller.springSecurityService = setUpSpringSecurity(user)
		
		params["id"] = user.id
		controller.addFriend()
		
		assert flash.message != null
		assert response.redirectedUrl == '/user/listFriends'
		
		response.reset()
		
		params["username"] = "user2"
		def user2 = new User(params)
		assert user2.save() != null
		
		params["id"] = user2.id
		controller.addFriend()
		
		assert user.friends.size() == 1
		assert response.redirectedUrl == '/user/listFriends'
	}
	
//	void testRemoveFriend() {
//		populateValidParams(params)
//		def user = new User(params)
//		assert user.save() != null
//		
//		controller.springSecurityService = setUpSpringSecurity(user)
//		
//		params["username"] = "user2"
//		def user2 = new User(params)
//		assert user2.save() != null
//		
//		user.addToFriends(user2)
//		user2.addToFriends(user)
//		assert user.friends.size() == 1
//		assert user2.friends.size() == 1
//		
//		response.reset()
//		
//		params["id"] = user2.id
//		controller.removeFriend()
//		
//		assert user.friends.size() == 0
//		assert response.redirectedUrl == '/user/listFriends'
//		
//	}
	
	void testSearchUser() {
		params["username"] = "noUser"
		controller.searchUser()
		
		assert flash.message != null
		assert response.redirectedUrl == '/user/listFriends'
		
		response.reset()
		
		populateValidParams(params)
		def user = new User(params)
		assert user.save() != null
		
		params["username"] = user.username
		def model = controller.searchUser()
		
		assert model.userInstanceList.size() == 1
		assert model.userInstanceTotal == 1
	}
	
	void testDeleteClos3() {
		
		mockDomain(UserRole)
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		response.reset()

		populateValidParams(params)
		def user = new User(params)
		assert user.save() != null
		
		mockDomain(Picture)
		def picture = new Picture(name: 'picTest', data: [1,1,1,1])
		assert picture.save()
		
		user.addToPictures(picture)

		params.id = user.id

		controller.delete()

		assert User.count() == 0
		assert User.get(user.id) == null
		assert response.redirectedUrl == '/logout'
	}
	
	void testDeleteClos2() {
		
		mockDomain(UserRole)
		mockDomain(Role)
		
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		response.reset()

		populateValidParams(params)
		def user = new User(params)
		assert user.save() != null
		
		def userRole = new Role(authority: 'ROLE_USER').save()
		UserRole.create user, userRole, true

		params.id = user.id

		controller.delete()

		assert User.count() == 0
		assert User.get(user.id) == null
		assert response.redirectedUrl == '/logout'
	}
	
//	void testDeleteClos1() {
//		
//		populateValidParams(params)
//		def user = new User(params)
//		assert user.save() != null
//
//		params["username"] = 'user2'
//		def user2 = new User(params)
//		assert user2.save() != null
//
//		controller.springSecurityService = setUpSpringSecurity(user)
//		
//		params["id"] = user2.id
//		controller.addFriend()
//		
//		assert user.friends.size() == 1
//		assert response.redirectedUrl == '/user/listFriends'
//		
//		response.reset()
//
//		controller.delete()
//
//		assert User.count() == 0
//		assert User.get(user.id) == null
//		assert response.redirectedUrl == '/logout'
//	}
}
