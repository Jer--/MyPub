package mypub

import static org.junit.Assert.*
import org.junit.*

/**
 * Run only integration tests : "grails test-app integration:". 
 */
class MyPubTests extends GroovyTestCase {

	def sessionFactory

	@Before
	void setUp() {
		sessionFactory.currentSession.flush()
		sessionFactory.currentSession.clear()
		drop database <myTestDatabase>
		create database <myTestDatabase>

		new User(username:'firefly',password:'benjamin', firstName:'brion', lastName:'benjamin', mail:'bjm.brion@gmail.com').save()
		new User(username:'unknown',password:'unknown', firstName:'johanson', lastName:'scarlett', mail:'beauty@gmail.com').save()
	}

	@After
	void tearDown() {
		// NOTHING TO DO AFTER
	}

	@Test
	void testSomething() {
		fail "Implement me"
	}
}
