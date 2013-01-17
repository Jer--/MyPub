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
 * - Add three users (A, B and C),
 * - User A creates a pub,
 * - Create one fake picture,
 * - Create one real picture,
 * - Add 2 pictures to user A,
 * - Add the real picture as avatar to A,
 * - Change the user A avatar, fake picture take place of real picture,
 * - User A delete a picture.
 * - Delete the pub,
 * - Delete User A, B, C.
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
		// Le pub a été créé par l'utilisateur A.
		pub.addToUsers(userA)
		pub.setBelongsTo(userA)
		userA.addToPubs(pub)
		
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
	void testAddPictureToPubAndUser() {
		log.info("[ScenarioPictureTests] Launch method: deleteUserC().")
		println "[testAddPictureToPubAndUser()] Start."
		
        def imagesFolder = 'C:\\Users\\BenJ\\git\\MyPub\\web-app\\images\\pub\\TemplatePubBasic.jpg'
        println "Chemin de l'image : " + imagesFolder
        def imageList = new File(imagesFolder).list()
		println "[testAddPictureToPubAndUser()] Convertion image en liste : " + imageList.toString()
		println "[testAddPictureToPubAndUser()] Creation des images 'realPicture' et 'testPicture'."
		Picture picture1 = new Picture(name:'realPicture', data: [1,2,3,4,5,6,7,8])
		Picture picture2 = new Picture(name:'testPicture', data: [1,0,0,1, 1,0,0,1])
		
		println "[testAddPictureToPubAndUser()] Data de picture 1 : " + picture1.getData()
		println "[testAddPictureToPubAndUser()] Data de picture 2 : " + picture2.getData()
		
		println "[testAddPictureToPubAndUser()] Sauvegarde de l'image dans la base de données du site."
		assert picture1.save()
		assert picture2.save()
		println picture2.toString()
		println "[testAddPictureToPubAndUser()] L'utilisateur A ajoute l'image 'realPicture' et 'testPicture' à sa galerie de photo."
		assert userA.addToPictures(picture1)
		assert userA.addToPictures(picture2)
		assert 2 == userA.getPictures().size()
		
		println "[testAddPictureToPubAndUser()] L'utilisateur A ajoute l'image 'realPicture' comme avatar."
		println "[testAddPictureToPubAndUser()] L'image reel : " + picture1.toString()
		userA.setAvatar(picture1)
		
		println "[testAddPictureToPubAndUser()] L'utilisateur A change d'image d'avatar 'realPicture' -> 'testPicture'."
		userA.setAvatar(picture2)
		
		println "[testAddPictureToPubAndUser()] L'utilisateur A supprime l'image 'realPicture'."
		assert userA.getPictures().remove(picture1)
		assert 1 == userA.getPictures().size()
		
		println "[testAddPictureToPubAndUser()] L'utilisateur A ajoute l'image ."
		assert userA.getPictures().remove(picture2)
	}

	@After
	void tearDown() {
		log.info("[ScenarioPictureTests] Tear Down begin.")

		log.info("[ScenarioPictureTests] Tear Down: toString tests.")

		println "[tearDown()] Info pub test : " + Pub.findByName("Pub des tests").toString()
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
