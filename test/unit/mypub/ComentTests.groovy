/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
 ******************************************************************************/
package mypub



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Coment)
@TestMixin(Pub)
class ComentTests {

	void testConstraints() {

		//setUp
		mockDomain(Pub)
		mockDomain(Coment)
		Pub pub1 = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB')
		assert pub1.validate()
		
		pub1.addToComents(new Coment(
			username: 'user1',
			postDate: new Date(),
			text : 'a coment'
			))
		
		def coment = pub1.coments.find {c ->
			c.username == "user1"
		}
		
		// validation pass
		assert coment.validate()
		
		// text nullable: false
		coment.text = null
		assert !coment.validate()	

		// username nullable: false
		coment.text = 'some text'
		coment.username = null
		assert !coment.validate()
		
		// postDate nullable: false
		coment.username = 'user1'
		coment.postDate = null
		assert !coment.validate()
		
		// text size ..160
		coment.postDate = new Date()
		coment.text = 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'
		assert !coment.validate()
		
	}
}
