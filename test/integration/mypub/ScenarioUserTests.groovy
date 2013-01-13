/*******************************************************************************
 *  Author : Group BBHC
 *  Licence : AGPL v3
 ******************************************************************************/
package mypub

import static org.junit.Assert.*
import groovy.mock.interceptor.*

import org.junit.*

/**
 * Scenario for users tests.
 * This scenario allows us to test the different possible use cases do by users
 * of the web application MyPub.
 * -> Steps:
 * - Add three users (A, B and C),
 * - User A creates a pub,
 * - Add users B and C to the pub list.
 * - Creating friendship bond between users A and B and between users A and C,
 * - Delete friendship bond between users A and B and between users A and C,
 * - Delete the pub,
 * - Delete User A, B, C.
 */
class ScenarioUserTests extends GroovyTestCase {
	Pub pub
	User userA, userB, userC
	@Before
	void setUp() {
		log.info("[ScenarioUserTests] setUp(): Start integration test for MyPub App.")

		log.info("[ScenarioUserTests] setUp(): Flush the session (delete a pub and 3 Users if existed.")

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
		log.info("[ScenarioUserTests] setUp(): Add a pub and 3 users.")
		pub = new Pub(name: "Pub des tests", address: "address", city: "Toulouse", zip: "97400", type: "PUB").save(failOnError:true)
		userA = new User(username:"userA",  enabled: true, password:"passwordA", firstName:"Hobitt", lastName:"bilbo", mail:"bjm.brion@gmail.com").save(failOnError:true)
		userB = new User(username:"userB",  enabled: true, password:"passwordB", firstName:"johanson", lastName:"scarlett", mail:"beauty@gmail.com").save(failOnError:true)
		userC = new User(username:"userC",  enabled: true, password:"passwordC", firstName:"filipo", lastName:"goz", mail:"filipo@gmail.com").save(failOnError:true)
		// Le pub a été créé par l'utilisateur A.
		pub.addToUsers(userA)
		pub.setBelongsTo(userA)
		userA.addToPubs(pub)
		
		print "[setUp()] Nombre d'utilisateurs apres ajout des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		assert (nbUsers+3) == User.count()
		assert (nbPubs+1) == Pub.count()

		log.info("[TestAppTests] Set up success.")
	}

	@Test
	void testAddsUsersToPub() {
		log.info("[ScenarioUserTests] Add users to the pub.")
		println "[testAddsUsersToPub()] Adds the users B and C to the pub."
		assert userB.addToPubs(pub)
		assert userC.addToPubs(pub)
		assert pub.addToUsers(userB)
		assert pub.addToUsers(userC)
		
		print "[testAddsUsersToPub()] Number users in pub : " + pub.getUsers().size()
		print ", Nb pub for userA : " + userA.getPubs().size()
		print ", Nb pub for userB : " + userB.getPubs().size()
		println ", Nb pub for userC : " + userC.getPubs().size()
		assert 1 == userB.getPubs().size()
		assert 1 == userC.getPubs().size()
		assert 3 == pub.getUsers().size()
		assert pub.getUsers().size() == 3

		log.info("[ScenarioUserTests] Add users to a pub end.")
	}

	@Test
	void testAddsFriends() {
		log.info("[testAddsFriends] Add friends to user A.")
		println "[testAddsFriends]  Aucun ami avant ajout des liens d'amities "
		assert userA.addToFriends(User.findByUsername("userB"))
		assert userA.addToFriends(User.findByUsername("userC"))
		assert userB.addToFriends(User.findByUsername("userA"))
		assert userC.addToFriends(User.findByUsername("userA"))
		
		println "[testAddsFriends] Nombre d'ami apres ajout des liens d'amities pour A : " + userA.getFriends().size()
		assert 2 == userA.getFriends().size()
		
		println "[testAddsFriends] Nombre d'ami apres ajout des liens d'amities pour B : " + userB.getFriends().size()
		assert 1 == userB.getFriends().size()
		
		println "[testAddsFriends] Nombre d'ami apres ajout des liens d'amities pour C : " + userC.getFriends().size()
		assert 1 == userC.getFriends().size()
		
		
		log.info("[testAddsFriends] Add friends to user A end.")
	}

	@Test
	void testRemovesFriends() {
		log.info("[ScenarioUserTests] Add friends to user A.")
		println "[testRemovesFriends] Aucun ami avant ajout des liens d'amities "
		assert userA.addToFriends(User.findByUsername("userB"))
		assert userA.addToFriends(User.findByUsername("userC"))
		assert userB.addToFriends(User.findByUsername("userA"))
		assert userC.addToFriends(User.findByUsername("userA"))
		
		println "[testRemovesFriends] Nombre d'ami apres ajout des liens d'amities pour A : " + userA.getFriends().size()
		assert 2 == userA.getFriends().size()
		println "[testRemovesFriends] Nombre d'ami apres ajout des liens d'amities pour B : " + userB.getFriends().size()
		assert 1 == userB.getFriends().size()
		println "[testRemovesFriends] Nombre d'ami apres ajout des liens d'amities pour C : " + userC.getFriends().size()
		assert 1 == userC.getFriends().size()
		
		log.info("[ScenarioUserTests] Add friends to user A end.")
		
		println "[testRemovesFriends] Nombre d'ami avant suppression des liens d'amities : " + userA.getFriends().size()
		assert 2 == userA.getFriends().size()
		assert userA.getFriends().remove(User.findByUsername("userB"))
		assert userA.getFriends().remove(User.findByUsername("userC"))
		assert userB.getFriends().remove(User.findByUsername("userA"))
		assert userC.getFriends().remove(User.findByUsername("userA"))
		
		println "[testRemovesFriends] Nombre d'ami apres suppression des liens d'amities pour A : " + userA.getFriends().size()
		assert 2 != userA.getFriends().size()
		println "[testRemovesFriends] Nombre d'ami apres suppression des liens d'amities pour B : " + userB.getFriends().size()
		assert 1 != userB.getFriends().size()
		println "[testRemovesFriends] Nombre d'ami apres suppression des liens d'amities pour C : " + userC.getFriends().size()
		assert 1 != userC.getFriends().size()
	}

	@After
	void tearDown() {
		log.info("[ScenarioUserTests] Tear Down begin.")

		log.info("[ScenarioUserTests] Tear Down: toString tests.")

		println "[tearDown()] Info pub test : " + Pub.findByName("Pub des tests").toString()
		println "[tearDown()] Info UserA test : " + User.findByUsername("userA").toString()
		println "[tearDown()] Info UserB test : " + User.findByUsername("userB").toString()
		println "[tearDown()] Info UserC test: " + User.findByUsername("userC").toString()

		print "[tearDown()] Nombre d'utilisateurs avant suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		int nbUsers = User.count()
		int nbPubs = Pub.count()

		log.info("[ScenarioUserTests] Tear Down: delete 3 users tests.")
		userA?.delete()
		userB?.delete()
		userC?.delete()

		log.info("[ScenarioUserTests] Tear Down: delete a pub test.")
		pub.delete()

		print "[tearDown()] Nombre d'utilisateurs apres suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		assert (nbUsers-3) == User.count()
		assert (nbPubs-1) == Pub.count()

		log.info("[ScenarioUserTests] Tear down end.")
	}
}
