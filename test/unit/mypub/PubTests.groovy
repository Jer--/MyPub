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
	

	void testSomething() {
		//setUp
		mockDomain(Pub)
		mockDomain(User)
		def user1 =  new User(username:'john', password: 'aaaa', firstName: 'john', lastName: 'doe', mail:'john@test.fr')
		assert user1.validate()
		
		/*
		 * to do : add pub to user1
		 * user1.addToPubs(new Coment(
			username: 'user1',
			postDate: new Date(),
			text : 'a coment'
			))
		*/
		
	}
}
