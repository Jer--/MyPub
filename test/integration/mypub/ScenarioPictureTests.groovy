/*******************************************************************************
 *  Author : Group BBHC
 *  Licence : AGPL v3
 ******************************************************************************/
package mypub

import org.junit.*

/**
 * Run only integration tests : "grails test-app integration:". 
 * 
 * Scenario integration tests:
 * This scenario allows us to test the different possible use cases do by users
 * of the web application MyPub.
 * -> Steps:
 * - Add a pub (pub1),
 * - Add three users (A, B and C),
 * - Delete User C,
 * - Creating a friendship bond between users A and B,
 * - Add a bar with user A edit,
 * - Add a comment with user B,
 * - Delete comment with user B,
 * - Change the description of the bar with user B,
 * - User B removes his friend (the user A).
 */
class ScenarioPictureTests extends GroovyTestCase {

	def sessionFactory
	Pub pub
	User userA, userB, userC

	@Before
	void setUp() {
		log.info("[ScenarioPictureTests] setUp(): Start integration test for MyPub App.")

		log.info("[ScenarioPictureTests] setUp(): Flush the session (delete a pub and 3 Users if existed.")

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
		log.info("[ScenarioPictureTests] setUp(): Add a pub and 3 users.")
		pub = new Pub(name: "Pub des tests", address: "address", city: "Toulouse", zip: "97400", type: "PUB").save(failOnError:true)
		userA = new User(username:"userA",  enabled: true, password:"passwordA", firstName:"Hobitt", lastName:"bilbo", mail:"bjm.brion@gmail.com").save(failOnError:true)
		userB = new User(username:"userB",  enabled: true, password:"passwordB", firstName:"johanson", lastName:"scarlett", mail:"beauty@gmail.com").save(failOnError:true)
		userC = new User(username:"userC",  enabled: true, password:"passwordC", firstName:"filipo", lastName:"goz", mail:"filipo@gmail.com").save(failOnError:true)
		pub.setBelongsTo(userA)
		print "[setUp()] Nombre d'utilisateurs apres ajout des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		assert (nbUsers+3) == User.count()
		assert (nbPubs+1) == Pub.count()

		log.info("[ScenarioPictureTests] Set up success.")

		/*
		 * Enhancement of integration test:
		 *
		 * sessionFactory.currentSession.flush()
		 * sessionFactory.currentSession.clear()
		 * drop database <myTestDatabase>
		 * create database <myTestDatabase>
		 */
		log.info("[ScenarioPictureTests] Set up end.")
	}

	@Test
	void testSomething() {
		log.info("[ScenarioPictureTests] Launch method: deleteUserC().")
	}

	@After
	void tearDown() {
		log.info("[ScenarioPictureTests] Tear Down begin.")

		log.info("[ScenarioPictureTests] Tear Down: toString tests.")

		println "[tearDown()] Info pub test : " + Pub.findByName("pub").toString()
		println "[tearDown()] Info UserA test : " + User.findByUsername("userA").toString()
		println "[tearDown()] Info UserB test : " + User.findByUsername("userB").toString()
		println "[tearDown()] Info UserC test: " + User.findByUsername("userC").toString()

		print "[tearDown()] Nombre d'utilisateurs avant suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		int nbUsers = User.count()
		int nbPubs = Pub.count()

		log.info("[ScenarioPictureTests] Tear Down: delete 3 users tests.")
		userA?.delete()
		userB?.delete()
		userC?.delete()

		log.info("[ScenarioPictureTests] Tear Down: delete a pub test.")
		pub.delete()

		print "[tearDown()] Nombre d'utilisateurs apres suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		assert (nbUsers-3) == User.count()
		assert (nbPubs-1) == Pub.count()

		log.info("[ScenarioPictureTests] Tear down end.")
		log.info("[ScenarioPictureTests] Finish!.")
	}
}
