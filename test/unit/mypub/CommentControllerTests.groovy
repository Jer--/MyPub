/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub



import grails.test.mixin.*

import org.junit.*

@TestFor(CommentController)
@TestMixin(Pub)
@Mock(Comment)
class CommentControllerTests {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
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
	}

	//	No Current User
	//	void testCreate() {
	//		def model = controller.create()
	//
	//		assert model.commentInstance != null
	//	}

	void testSave() {
		controller.save()

		assert model.commentInstance != null
		assert view == '/comment/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/comment/showcomment/1'
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
		//TODO: add invalid values to params object
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

	//	No Current User
	//	void testShowcomment() {
	//
	//	}

	void testShowMycomment() {
		controller.showMycomment()

		assert flash.message != null
		assert response.redirectedUrl == '/user/showProfile'

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		params.id = comment.id

		def model = controller.showMycomment()

		assert model.commentInstance == comment
	}

	void testShowAcomment() {
		controller.showAcomment()

		assert flash.message != null
		assert response.redirectedUrl == '/user/showProfile'

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null

		params.id = comment.id

		def model = controller.showAcomment()

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
	}
}
