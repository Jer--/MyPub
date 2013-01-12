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
@TestFor(Pub)
@TestMixin(User)
class PubTests {
	

	void testconstraint() {
		//setUp
		mockDomain(Pub)
		mockDomain(User)
        User user = new User(username:"test",password:"test",firstName:"test",lastName:"test",mail:"test@test.com")
		user.addToPubs(new Pub(name: 'pub', address: 'address', city: 'Toulouse', type: 'PUB'))
		
		def pub1 = user.pubs.find{p ->
			p.name == "pub"
			
		}
		
		
		// validation pass
		assert pub1.validate()
		
		
		// text nullable: false
		 pub1.name = null
	     assert !pub1.validate()
		
	}
	
	void testToString() {
		mockDomain(Pub)
		def pub = new Pub(name: 'pub', address: 'address', city: 'Toulouse', type: 'PUB')
		
		assert pub.toString() == "pub, Toulouse"
	}
}
