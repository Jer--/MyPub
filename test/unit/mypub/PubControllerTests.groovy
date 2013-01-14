/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
  ******************************************************************************/
package mypub

import grails.plugins.springsecurity.SpringSecurityService
import org.junit.*
import grails.test.mixin.*

@TestFor(PubController)
@Mock(Pub)
class PubControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'someValidName'
		params["address"] = '123 av jj'
		params["type"] = 'PUB'
		params["city"] = 'albi'
		params["zip"] = '31000'
    }
	
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

    void testIndex() {
        controller.index()
        assert "/pub/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pubInstanceList.size() == 0
        assert model.pubInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pubInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pubInstance != null
        assert view == '/pub/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pub/show/1'
        assert controller.flash.message != null
        assert Pub.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null

        params.id = pub.id

        def model = controller.show()

        assert model.pubInstance == pub
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null

        params.id = pub.id

        def model = controller.edit()

        assert model.pubInstance == pub
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        response.reset()

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null

        // test invalid parameters in update
        params.id = pub.id
        // add invalid values to params object
		params["address"] = null
        controller.update()

        assert view == "/pub/edit"
        assert model.pubInstance != null

        pub.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pub/show/$pub.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pub.clearErrors()

        populateValidParams(params)
        params.id = pub.id
        params.version = -1
        controller.update()

        assert view == "/pub/edit"
        assert model.pubInstance != null
        assert model.pubInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        response.reset()

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null
        assert Pub.count() == 1

        params.id = pub.id

        controller.delete()

        assert Pub.count() == 0
        assert Pub.get(pub.id) == null
        assert response.redirectedUrl == '/pub/list'
    }
	
	// Non - generated Test //////////////////////////////////////////
	
	void testSearchPub() {
		
		params.pubname = "  "
		controller.searchPub()
		assert response.redirectedUrl == '/pub/list'
		
		response.reset()
		
		populateValidParams(params)
		def pub = new Pub(params)
		
		assert pub.save() != null
		assert Pub.count() == 1
		
		params.pubname = "someValidName"
		def model = controller.searchPub()
		assert model.pubInstanceList.size() == 1
		assert model.pubInstanceTotal == 1
		
		params.pubname = "albi"
		model = controller.searchPub()
		assert model.pubInstanceList.size() == 1
		assert model.pubInstanceTotal == 1
		
		params.pubname = "31000"
		model = controller.searchPub()
		assert model.pubInstanceList.size() == 1
		assert model.pubInstanceTotal == 1
		
	}
	
	void testDeleteClos1() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/pub/list'

		response.reset()

		populateValidParams(params)
		def pub = new Pub(params)

		assert pub.save() != null
		assert Pub.count() == 1
		
		mockDomain(User)
		
		def user = new User(username: 'user1',
									password: 'pass1',
									firstName: 'alfred',
									lastName: 'alfredaussi',
									mail: 'alfred@john.fr')
	   assert user.save() != null
	   
	   controller.springSecurityService =setUpSpringSecurity(user)

		params.id = pub.id
		
		controller.addPub()
		
		response.reset()

		controller.delete()

		assert Pub.count() == 0
		assert Pub.get(pub.id) == null
		assert response.redirectedUrl == '/pub/list'
	}
	
	void testAddPub() {
		mockDomain(User)
		
		def user = new User(username: 'user1',
									password: 'pass1',
									firstName: 'alfred',
									lastName: 'alfredaussi',
									mail: 'alfred@john.fr')
	   assert user.save() != null
	   
	   controller.springSecurityService =setUpSpringSecurity(user)
	   
	   populateValidParams(params)
	   def pub = new Pub(params)
	   assert pub.save() != null
	   
	   params.id = pub.id
	   controller.addPub()
	   
	   assert flash.message == null
	   assert response.redirectedUrl == '/pub/listPubs'
	   assert user.pubs.size() == 1
	   assert pub.users.size() == 1
	   
	   response.reset()
	   
	   controller.addPub()
	   
	   assert flash.message != null
	   assert response.redirectedUrl == '/pub/listPubs'
	   assert user.pubs.size() == 1
	   assert pub.users.size() == 1
	   
	}
	
//	void testRemovePub() {
//		
//		mockDomain(User)
//		
//		def user = new User(username: 'user1',
//									password: 'pass1',
//									firstName: 'alfred',
//									lastName: 'alfredaussi',
//									mail: 'alfred@john.fr')
//	   assert user.save() != null
//	   
//	   controller.springSecurityService =setUpSpringSecurity(user)
//	   
//	   populateValidParams(params)
//	   def pub = new Pub(params)
//	   assert pub.save() != null
//	   
//	   params.id = pub.id
//	   controller.addPub()
//	   
//	   assert flash.message == null
//	   assert response.redirectedUrl == '/pub/listPubs'
//	   assert user.pubs.size() == 1
//	   assert pub.users.size() == 1
//	   
//	   response.reset()
//	   
//	   controller.removePub()
//	   assert response.redirectedUrl == '/pub/listPubs'
//	   assert user.pubs.size() == 0
//	   assert pub.users.size() == 0
//	}
	
	void testList2() {
		params["max"] = 3
		def model = controller.list()
		
		assert model.pubInstanceList.size() == 0
		assert model.pubInstanceTotal == 0
	}
	
	//void testListPub(){
		//controller.springSecurityService = setUpSpringSecurity()
		//
		//controller.listPubs()
		//assert response.redirectedUrl == '/pub/listPubs'
	//}
}
