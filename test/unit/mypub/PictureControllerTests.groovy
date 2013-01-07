/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
 ******************************************************************************/
package mypub



import org.junit.*
import grails.test.mixin.*

@TestFor(PictureController)
@TestMixin(Pub)
@Mock(Picture)
class PictureControllerTests {
	
	void setUpSpringSecurity() {
		def mockSpringSecurityService = mockFor(grails.plugins.springsecurity.SpringSecurityService)
		mockSpringSecurityService.demand.getPrincipal() { -> ["username":"Test"] }
		controller.springSecurityService = mockSpringSecurityService.createMock()
	}

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["name"] = 'pictest'
		params["data"]= [1,1,1,1]
    }

    void testIndex() {
        controller.index()
        assert "/picture/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pictureInstanceList.size() == 0
        assert model.pictureInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pictureInstance != null
    }

   // TODO : we can't test because there no current user
//     void testSave() {
//		 mockDomain(User)
//		 
//		 def userInstance = new User(username: 'user1',
// 									password: 'pass1',
// 									firstName: 'alfred',
// 									lastName: 'alfredaussi',
// 									mail: 'alfred@john.fr')
//		assert userInstance.validate()
//		 
//		controller.springSecurityService = [currentUser:[id:userInstance.id]]
//		//setUpSpringSecurity()
//        controller.save()
//
//        assert model.pictureInstance != null
//        assert view == '/picture/create'
//
//        response.reset()
//
//        populateValidParams(params)
//        controller.save()
//
//        assert response.redirectedUrl == '/picture/show/1'
//        assert controller.flash.message != null
//        assert Picture.count() == 1
//    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null

        params.id = picture.id

        def model = controller.show()

        assert model.pictureInstance == picture
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null

        params.id = picture.id

        def model = controller.edit()

        assert model.pictureInstance == picture
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        response.reset()

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null

        // test invalid parameters in update
        params.id = picture.id
        //TODO: add invalid values to params object
		params["name"] = null
		
        controller.update()

        assert view == "/picture/edit"
        assert model.pictureInstance != null

        picture.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/picture/show/$picture.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        picture.clearErrors()

        populateValidParams(params)
        params.id = picture.id
        params.version = -1
        controller.update()

        assert view == "/picture/edit"
        assert model.pictureInstance != null
        assert model.pictureInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        response.reset()

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null
        assert Picture.count() == 1

        params.id = picture.id

        controller.delete()

        assert Picture.count() == 0
        assert Picture.get(picture.id) == null
        assert response.redirectedUrl == '/picture/list'
    }
	
	// Non - generated Tests ///////////////////////////////////////
		
	void testShowPerso() {
		controller.showPerso()

		assert flash.message != null
		assert response.redirectedUrl == '/picture/listPerso'

		populateValidParams(params)
		def picture = new Picture(params)

		assert picture.save() != null

		params.id = picture.id

		def model = controller.showPerso()

		assert model.pictureInstance == picture
	}
	
	void testShowImgAmi() {

		controller.showImgAmi()

		assert flash.message != null
		assert response.redirectedUrl == '/picture/listPerso'

		populateValidParams(params)
		def picture = new Picture(params)

		assert picture.save() != null

		params.id = picture.id

		def model = controller.showImgAmi()

		assert model.pictureInstance == picture
	}
	
//	TODO : we can't test because there no current user
//	void testListPerso() {
//
//		def model = controller.listPerso()
//
//		assert model.pictureInstanceList.size() == 0
//		assert model.pictureInstanceTotal == 0
//	}

//	void testListFriend() {
//
//		mockDomain(User)
//
//		def userInstance = new User(username: 'user1',
//									password: 'pass1',
//									firstName: 'alfred',
//									lastName: 'alfredaussi',
//									mail: 'alfred@john.fr')
//		userInstance.addToPictures(new Picture(params))
//		assert userInstance.validate()
//
//		def model = controller.listFriend(userInstance.id)
//
//
//		//assert model.pictureInstanceTotal == 0
//		assert model.pictureInstanceList == null
//	}
	
//	void testEnleverList() {
//		//no curent user
//	}
	
	void testCreateForAPub() {
		
		mockDomain(Pub)
		def pub = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB').save()
		assert pub.validate()
		
		def model = controller.createForAPub(pub.id)

        assert model.pictureInstance != null
	}
	
     void testSaveForAPub() {
		 mockDomain(Pub)
		def pub = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB').save()
		assert pub.validate()
		
        controller.saveForAPub(pub.id)

        assert model.pictureInstance != null
        assert view == '/picture/createForAPub'

        response.reset()

        populateValidParams(params)
        controller.saveForAPub(pub.id)

        assert response.redirectedUrl == '/picture/showPub/1'
        assert controller.flash.message != null
        assert Picture.count() == 1
    }

//	 void testEnleverListForAPub() {
//		 mockDomain(Pub)
//		 def pub = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB').save()
//		 assert pub.validate()
//		 
//		 populateValidParams(params)
//		 def picture = new Picture(params)
//		 
//		 pub.addToPictures(picture)
//		 assert pub.pictures.size() == 1
//		 
//		 params["pubId"] = pub.id
//		 picture.enleverListForAPub()
//		 
//		 assert pub.pictures.size() == 0
//	 }	
	 
	void testShowAPub() {
		controller.showAPub()

		assert flash.message != null
		assert response.redirectedUrl == '/pub/show'

		populateValidParams(params)
		def picture = new Picture(params)

		assert picture.save() != null

		params.id = picture.id

		def model = controller.showAPub()

		assert model.pictureInstance == picture
	}
	
	void testShowMyPub() {
		controller.showMyPub()

		assert flash.message != null
		assert response.redirectedUrl == '/pub/show'

		populateValidParams(params)
		def picture = new Picture(params)

		assert picture.save() != null

		params.id = picture.id

		def model = controller.showMyPub()

		assert model.pictureInstance == picture
	}
	
	void testListPub() {
		mockDomain(Pub)
		def pub = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB').save()
		assert pub.validate()
		
		populateValidParams(params)
		def picture = new Picture(params)
		
		pub.addToPictures(picture)
		assert pub.pictures.size() == 1
		
		def model = controller.listPub(pub.id)
		assert model.pictureInstanceList.size() == 1
		assert model.pictureInstanceTotal == 1
	}
	
}
