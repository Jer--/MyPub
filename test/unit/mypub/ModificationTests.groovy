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
@TestFor(Modification)
class ModificationTests {

	void testConstraints() {
		//setUp
		mockDomain(Pub)
		mockDomain(Modification)
		mockDomain(User)
		User user = new User(username:"test",password:"test",firstName:"test",lastName:"test",mail:"test@test.com")
		Pub pub = new Pub(name: 'pub', address: 'address', city: 'Toulouse', type: 'PUB')
		Pub pubWithModification = new Pub(name: 'pub', address: 'address', city: 'Ramonville', type: 'PUB')
		assert pub.validate()

		pub.addToModifications(new Modification("test",pubWithModification))

		def modif = pub.modifications.find {m ->
			m.username == "test"
		}
		assert modif.validate()


	}
}
