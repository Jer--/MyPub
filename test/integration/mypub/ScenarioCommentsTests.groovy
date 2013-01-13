/*******************************************************************************
 *  Author : Group BBHC
 *  Licence : AGPL v3
 ******************************************************************************/
package mypub

import static org.junit.Assert.*

import org.junit.*

/**
 * Scenario for comments tests.
 */
class ScenarioCommentsTests extends GroovyTestCase {
	Pub pub
	User userA, userB, userC
	@Before
	void setUp() {
		log.info("[ScenarioCommentsTests] setUp(): Start integration test for MyPub App.")

		log.info("[ScenarioCommentsTests] setUp(): Flush the session (delete a pub and 3 Users if existed.")

		println "[setUp()] Nombre de pubs avant suppression du pub test : " + Pub.count()
		Pub.findByName("Pub des tests")?.delete()
		println "[setUp()] Nombre de pubs apres suppession du pub test : " + Pub.count()

		println "[setUp()] Nombre d'utilisateurs avant suppression des users tests : " + User.count()
		User.findByUsername("userA")?.delete()
		User.findByUsername("userB")?.delete()
		User.findByUsername("userC")?.delete()
		println "[setUp()] Nombre d'utilisateurs apres suppression des users tests : " + User.count()

		int nbUsers = User.count()
		int nbPubs = Pub.count()
		print "[setUp()] Nombre d'utilisateurs avant ajout des users tests : " + nbUsers
		println ", et de pubs : " + nbPubs

		log.info("[ScenarioCommentsTests] setUp(): Add a pub and 3 users.")
		pub = new Pub(name: "Pub des tests", address: "address", city: "Toulouse", zip: "97400", type: "PUB").save(failOnError:true)
		userA = new User(username:"userA",  enabled: true, password:"passwordA", firstName:"Hobitt", lastName:"bilbo", mail:"bjm.brion@gmail.com").save(failOnError:true)
		userB = new User(username:"userB",  enabled: true, password:"passwordB", firstName:"johanson", lastName:"scarlett", mail:"beauty@gmail.com").save(failOnError:true)
		userC = new User(username:"userC",  enabled: true, password:"passwordC", firstName:"filipo", lastName:"goz", mail:"filipo@gmail.com").save(failOnError:true)
		pub.setBelongsTo(userA)

		print "[setUp()] Nombre d'utilisateurs apres ajout des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		assert (nbUsers+3) == User.count()
		assert (nbPubs+1) == Pub.count()

		log.info("[ScenarioCommentsTests] Set up success.")
	}

	@Test
	void testSomething() {
		log.info("[ScenarioCommentsTests] Start integration test ScenarioCommentsTests.")
	}

	@After
	void tearDown() {
		log.info("[ScenarioCommentsTests] Tear Down begin.")

		log.info("[ScenarioCommentsTests] Tear Down: toString tests.")

		println "[tearDown()] Info pub test : " + Pub.findByName("pub").toString()
		println "[tearDown()] Info UserA test : " + User.findByUsername("userA").toString()
		println "[tearDown()] Info UserB test : " + User.findByUsername("userB").toString()
		println "[tearDown()] Info UserC test: " + User.findByUsername("userC").toString()

		print "[tearDown()] Nombre d'utilisateurs avant suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		int nbUsers = User.count()
		int nbPubs = Pub.count()

		log.info("[ScenarioCommentsTests] Tear Down: delete 3 users tests.")
		userA?.delete()
		userB?.delete()
		userC?.delete()

		log.info("[ScenarioCommentsTests] Tear Down: delete a pub test.")
		pub.delete()

		print "[tearDown()] Nombre d'utilisateurs apres suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		assert (nbUsers-3) == User.count()
		assert (nbPubs-1) == Pub.count()

		log.info("[ScenarioCommentsTests] Tear down end.")
	}
}
