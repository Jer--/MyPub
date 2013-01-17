/*******************************************************************************
 *  Author : Group BBHC
 *  Licence : AGPL v3
 ******************************************************************************/
package mypub

import static org.junit.Assert.*
import grails.test.mixin.*

import org.junit.*

/**
 * Scenario for pub tests.
 * This scenario allows us to test the different possible use cases do by users
 * of the web application MyPub.
 * -> Steps:
 * - Add three users (A, B and C),
 * - User A creates a pub,
 * - Users B and C add the pub to their pub list,
 * - Users A and B add comments to the pub,
 * - Users B deletes his comment,
 * - Users A then B modify the pub description, 
 * - Delete the pub,
 * - Delete User A, B, C.
 */
class ScenarioPubTests extends GroovyTestCase {

	Pub pub
	User userA, userB, userC
	@Before
	void setUp() {
		log.info("[ScenarioPubTests] setUp(): Start integration test for MyPub App.")

		log.info("[ScenarioPubTests] setUp(): Flush the session (delete a pub and 3 Users if existed.")
		
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

		log.info("[ScenarioPubTests] setUp(): Add a pub and 3 users.")
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

		log.info("[ScenarioPubTests] Set up success.")
	}

	@Test
	void testPubToUsersWithComments() {
		log.info("[ScenarioPubTests] testPubDescription start.")
		
		println "[testPubToUsersWithComments()] Le pub doit posseder " + pub.getUsers().size() + " == 1 utilisateur (A qui l'a créé)."
		assert 1 == pub.getUsers().size()
		println "[testPubToUsersWithComments()] L'utilisateur A doit posseder " + userA.getPubs().size() + " pub (celui qu'il a créé."
		println "[testPubToUsersWithComments()] L'utilisateur B ne possede pas de pub avant de lui en ajouter un."
		println "[testPubToUsersWithComments()] L'utilisateur C ne possede pas de pub avant de lui en ajouter un."
		println "[testPubToUsersWithComments()] Ajout des pubs pour les utilisateurs B et C"
		assert userB.addToPubs(pub)
		assert userC.addToPubs(pub)
		
		println "[testPubToUsersWithComments()] L'utilisateur B possede " + userB.getPubs().size() + " pub(s)."
		assert 1 == userB.getPubs().size()
		println "[testPubToUsersWithComments()] L'utilisateur C possede " + userC.getPubs().size() + " pub(s)."
		assert 1 == userC.getPubs().size()
		println "[testPubToUsersWithComments()] Le pub possede " + pub.getUsers().size() + " utilisateur(s)."
		assert 3 == pub.getUsers().size()
		
		println "[testPubToUsersWithComments()] Les utilisateurs A et B ajoutent chacun un commentaire dans le pub."
		Comment commentUserA = new Comment(username:"userA", postDate:new Date(), text:"Commentaire de test fait par l'utilisateur A")
		Comment commentUserB = new Comment(username:"userB", postDate:new Date(), text:"Commentaire de test fait par l'utilisateur B")
		assert pub.addToComments(commentUserA)
		assert pub.addToComments(commentUserB)
		println "[testPubToUsersWithComments()] L'utilisateur A et B ont ajoute un commentaire chacun au pub, nb commentaires = " + pub.getComments().size()
		assert 2 == pub.getComments().size()
		
		println "[testPubToUsersWithComments()] L'utilisateur B supprime son commentaire fait, avant nb commentaires = " + pub.getComments().size()
		assert pub.getComments().remove(commentUserB)
		println "[testPubToUsersWithComments()] L'utilisateur B supprime son commentaire fait, apres nb commentaires = " + pub.getComments().size()
		assert 1 == pub.getComments().size()
		log.info("[ScenarioPubTests] testPubDescription end.")
	}
	
	@Test
	void testAddPubToUsersWithModifications() {
		log.info("[ScenarioPubTests] testAddPubToUsersWithModifications start.")
		
		println "[testAddPubToUsersWithModifications()] Le pub doit posseder " + pub.getUsers().size() + " == 1 utilisateur (A qui l'a créé)."
		assert 1 == pub.getUsers().size()
		println "[testAddPubToUsersWithModifications()] L'utilisateur A doit posseder " + userA.getPubs().size() + " pub (celui qu'il a créé."
		println "[testAddPubToUsersWithModifications()] L'utilisateur B ne possede pas de pub avant de lui en ajouter un."
		println "[testAddPubToUsersWithModifications()] L'utilisateur C ne possede pas de pub avant de lui en ajouter un."
		println "[testAddPubToUsersWithModifications()] Ajout des pubs pour les utilisateurs B et C"
		userB.addToPubs(pub)
		userC.addToPubs(pub)
		
		println "[testAddPubToUsersWithModifications()] L'utilisateur B possede " + userB.getPubs().size() + " pub(s)."
		assert 1 == userB.getPubs().size()
		println "[testAddPubToUsersWithModifications()] L'utilisateur C possede " + userC.getPubs().size() + " pub(s)."
		assert 1 == userC.getPubs().size()
		println "[testAddPubToUsersWithModifications()] Le pub possede " + pub.getUsers().size() + " utilisateur(s)."
		assert 3 == pub.getUsers().size()
		
		println "[testAddPubToUsersWithModifications()] Ajout modification sur le pub par l'utilisateur A."
		Modification modificationA = new Modification(about:"type", author:"userA", newContent:"CLUB")
		// assert modificationA.addToUsersOk(userA)
		// assert modificationA.setBelongsTo(pub)
		
		println "[testAddPubToUsersWithModifications()] Configuration de la modification sur le pub par l'utilisateur A."
		// assert 1 == modificationA.getUsersOk()
		// assert modificationA.getBelongsTo()
		
		assert pub.addToModifications(modificationA)
		assert 1 == pub.getModifications().size()
		
		// TODO setBelongsTo failed!
		// assert modificationA.save()
		
		println "[testAddPubToUsersWithModifications()] Ajout modification sur le pub par l'utilisateur B."
		Modification modificationB = new Modification(about:"type", author:"userB", newContent:"RESTO")
		// assert modificationB.addToUsersOk(userA)
		// assert modificationB.addToUsersOk(userB)
		assert pub.addToModifications(modificationB)
		
		println "[testAddPubToUsersWithModifications()] Ajout des modifications des utilisateurs A et B sur le pub."
		assert 2 == pub.getModifications().size()
		
		println "[testAddPubToUsersWithModifications()] Suppression des modifications des utilisateurs A et B sur le pub."
		// assert modificationA.getUsersOk().remove(userA)
		// assert modificationB.getUsersOk().remove(userA)
		// assert modificationB.getUsersOk().remove(userB)
		
		// assert modificationA.delete()
		// assert modificationB.delete()
		
		assert pub.getModifications().remove(modificationA)
		assert pub.getModifications().remove(modificationB)
		
		log.info("[ScenarioPubTests] testAddPubToUsersWithModifications end.")
	}

	@After
	void tearDown() {
		log.info("[ScenarioPubTests] Tear Down begin.")

		log.info("[ScenarioPubTests] Tear Down: toString tests.")

		println "[tearDown()] Info pub test : " + Pub.findByName("Pub des tests").toString()
		println "[tearDown()] Info UserA test : " + User.findByUsername("userA").toString()
		println "[tearDown()] Info UserB test : " + User.findByUsername("userB").toString()
		println "[tearDown()] Info UserC test: " + User.findByUsername("userC").toString()

		print "[tearDown()] Nombre d'utilisateurs avant suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		int nbUsers = User.count()
		int nbPubs = Pub.count()

		log.info("[ScenarioPubTests] Tear Down: delete 3 users tests.")
		userA?.delete()
		userB?.delete()
		userC?.delete()

		log.info("[ScenarioPubTests] Tear Down: delete a pub test.")
		pub.delete()

		print "[tearDown()] Nombre d'utilisateurs apres suppression des users tests : " + User.count()
		println ", et de pubs : " + Pub.count()

		assert (nbUsers-3) == User.count()
		assert (nbPubs-1) == Pub.count()

		log.info("[ScenarioPubTests] Tear down end.")
	}

	private Object save(Object object ) {
		validateAndPrintErrors( object )
		Object result = object .save(flush: true )
		assertNotNull( "Object not created: " + object , result)
		return result
	}

	private void validateAndPrintErrors(Object object ) {
		if (! object.validate()) {
			object.errors.allErrors.each { error -> println error }
			fail( "failed to save object ${object}" )
		}
	}
}
