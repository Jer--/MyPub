package mypub

import grails.plugin.spock.UnitSpec
import grails.plugins.Spock.lang.*
import grails.test.mixin.*

@TestFor(PictureController)
class PictureControllerSpec extends UnitSpec {
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
}
