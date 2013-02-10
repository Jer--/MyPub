package mypub

import grails.plugin.spock.UnitSpec
import grails.plugins.Spock.lang.*
import grails.test.mixin.*
import grails.plugins.springsecurity.SpringSecurityService

@TestFor(PictureController)
@Mock(Picture)
class PictureControllerSpec extends UnitSpec {
	def setUpSpringSecurity(User u) {
		
				def security = mockFor(SpringSecurityService)
				security.metaClass.getCurrentUser = { -> return u }
				return security
	}
	
	def 'Index action should redirect to list page'() {
		when:
		controller.index()

		then:
		response.redirectedUrl == "/picture/list"
	}
	
	def 'listPub action return a list of 1 picture'() {
		setup:
		mockDomain(Pub)
		def pub = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB').save()
		assert pub.validate()
		
		params["name"] = 'pictest'
		params["data"]= [1,1,1,1]
		def picture = new Picture(params)
		
		pub.addToPictures(picture)
		assert pub.pictures.size() == 1
		
		when:
		def model = controller.listPub(pub.id)
		
		then:
		assert model.pictureInstanceList.size() == 1
		assert model.pictureInstanceTotal == 1
	}
	
	def 'action save should add the picture to the current user pictures list'() {
		setup:
		mockDomain(User)
		
		def userInstance = new User(username: 'user1',
									password: 'pass1',
									firstName: 'alfred',
									lastName: 'alfredaussi',
									mail: 'alfred@john.fr')
	   assert userInstance.validate()
	   assert userInstance.save() != null
	
	   //SpringSecurityService.metaClass.getCurrentUser = { -> return userInstance }
	   controller.springSecurityService =setUpSpringSecurity(userInstance)
	   
	   when:
	   params["name"] = 'pictest'
	   params["data"]= [1,1,1,1]
	   controller.save()
	   
	   then:
	   assert response.redirectedUrl == '/picture/showPerso/1'
	   assert controller.flash.message != null
	   assert Picture.count() == 1
	}
}
