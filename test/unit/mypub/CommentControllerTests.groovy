/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub


import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.*

import org.junit.*
import org.springframework.dao.DataIntegrityViolationException

@TestFor(CommentController)
@TestMixin(Pub)
@Mock(Comment)
class CommentControllerTests {
	
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
		// Populate valid properties like...
		//params["name"] = 'someValidName'
		params["username"]='testName'
		//		params["postDate"]= new Date ('2007/01/01')
		params["text"]='comment test'
		params["pub"] = new Pub(name:'pub1', address:'123 av jj', city:'Toulouse')
	}

	void testIndex() {
		controller.index()
		assert "/comment/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.commentInstanceList.size() == 0
		assert model.commentInstanceTotal == 0
		
		params["max"] = 3
		model = controller.list()
		
		assert model.commentInstanceList.size() == 0
		assert model.commentInstanceTotal == 0
	}

	void testCreate() {
		controller.springSecurityService = setUpSpringSecurity()
		def model = controller.create()

		assert model.commentInstance != null
	}

	void testSave() {
		controller.save()

		assert model.commentInstance != null
		assert view == '/comment/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/comment/showComment/1'
		assert controller.flash.message != null
		assert Comment.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/comment/list'

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		params.id = comment.id

		def model = controller.show()

		assert model.commentInstance == comment
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/comment/list'

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		params.id = comment.id

		def model = controller.edit()

		assert model.commentInstance == comment
	}

	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/comment/list'

		response.reset()

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		// test invalid parameters in update
		params.id = comment.id
		//add invalid values to params object
		params["text"]='aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'

		controller.update()

		assert view == "/comment/edit"
		assert model.commentInstance != null

		comment.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/comment/show/$comment.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		comment.clearErrors()

		populateValidParams(params)
		params.id = comment.id
		params.version = -1
		controller.update()

		assert view == "/comment/edit"
		assert model.commentInstance != null
		assert model.commentInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/user/showProfile'

		response.reset()

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null
		assert Comment.count() == 1

		params.id = comment.id

		controller.delete()

		assert Comment.count() == 0
		assert Comment.get(comment.id) == null
		assert response.redirectedUrl == '/pub/show'
	}

	// Non - generated Test //////////////////////////////////////

	void testShowComment() {
		mockDomain(User)
		
		def user = new User(username: 'user1',
									password: 'pass1',
									firstName: 'alfred',
									lastName: 'alfredaussi',
									mail: 'alfred@john.fr')
	   assert user.save() != null
	   
	   controller.springSecurityService =setUpSpringSecurity(user)
	   
	   populateValidParams(params)
	   def comment = new Comment(params)
	   assert comment.save() != null
	   
	   params["id"] = comment.id
	   controller.showComment()
	   
	   assert response.redirectedUrl == '/comment/showAComment/1'
	   
	   response.reset()
	   
	   user.username = 'testName'
	   params["id"] = comment.id
	   controller.showComment()
	   
	   assert response.redirectedUrl == '/comment/showMyComment/1'
	}

	void testShowMyComment() {
		controller.showMyComment()

		assert flash.message != null
		assert response.redirectedUrl == '/user/showProfile'

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		params.id = comment.id

		def model = controller.showMyComment()

		assert model.commentInstance == comment
	}

	void testShowAComment() {
		controller.showAComment()

		assert flash.message != null
		assert response.redirectedUrl == '/user/showProfile'

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		params.id = comment.id

		def model = controller.showAComment()

		assert model.commentInstance == comment
	}

	void testListForAPub() {
		mockDomain(Pub)
		def pub = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB').save()
		pub.addToComments(new Comment(
				username: 'user1',
				postDate: new Date(),
				text : 'a comment'
				))
		assert pub.validate()

		def model = controller.listForAPub(pub.id)

		assert model.commentInstanceList.size() == 1
		assert model.commentInstanceTotal == 1
		
		pub.addToComments(new Comment(
			username: 'user2',
			postDate: new Date(),
			text : 'a comment'
			))
		
		model = controller.listForAPub(pub.id)
		
		assert model.commentInstanceList.size() == 2
		assert model.commentInstanceTotal == 2
	}
	
	void testDeleteCatch() {
		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null
		assert Comment.count() == 1

		params.id = comment.id
		
		Comment.metaClass.delete = { Map params -> 
		throw new DataIntegrityViolationException("...")
		}

		controller.delete()

		assert Comment.count() == 1
		assert response.redirectedUrl == '/comment/showComment/1'
	}
	
	void testUpdate2() {

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		params.id = comment.id
		params.version = 15
		controller.update()

		assert response.redirectedUrl == "/comment/show/$comment.id"
		assert flash.message != null
	}
}
