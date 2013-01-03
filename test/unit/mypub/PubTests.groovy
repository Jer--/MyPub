/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
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
		//def user1 =  new User(username:'john', password: 'aaaa', firstName: 'john', lastName: 'doe', mail:'john@test.fr').save()
		//assert user1.validate()
		
		//user1.addToPubs(new Pub(name:'pub1', address:'123 av jj'))
		
		/*def pub = user1.pubs.find {p ->
			p.name == "pub1"
		}*/
		
		// validation pass
		//assert pub.validate()
		
		
		// text nullable: false
		//pub.name = null
		//assert !pub.validate()
			true
		
	}
}
