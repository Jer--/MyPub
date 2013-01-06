package mypub

import static org.junit.Assert.*

import org.junit.*

/**
 * Prepare controllers.
 */
class IntegrationTests extends GroovyTestCase {

	@Test
	void testSomething() {
		log.info("[IntegrationTests] Start integration test for controller.")
		log.info("[IntegrationTests] Launch method: testUpdatePub().")
		// testUpdatePub()
		log.info("[IntegrationTests] Launch method: testSomeRedirect().")
		// testSomeRedirect()
	}

	/*void testUpdatePub() {
	 def controller = new Pub(name: 'pub', address: 'address', city: 'Toulouse', type: 'PUB')
	 controller.params.address = "nouvelle address"
	 controller.params.city = "Saint Denis"
	 controller.update(controller.getIdPub(), controller.getIdPub())
	 }
	 void testSomeRedirect() {
	 def pub =  new Pub(name: 'pub', address: 'address', city: 'Toulouse', type: 'PUB')
	 pub.show()
	 assertEquals "/Pub/show", pub.response.redirectedUrl
	 }
	 */
}
