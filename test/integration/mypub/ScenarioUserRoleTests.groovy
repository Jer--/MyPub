package mypub

import static org.junit.Assert.*

import mypub.*

import org.junit.*

class ScenarioUserRoleTests {

	Role adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
	Role userRole = new Role(authority: 'ROLE_USER').save(flush: true)

	Pub pub
	User userA, userB, userC


	@Before
	void setUp() {
		log.info("[ScenarioUserRoleTests] setUp(): Start integration test for MyPub App.")

		log.info("[ScenarioUserRoleTests] setUp(): Flush the session (delete a pub and 3 Users if existed.")

		println "[setUp()] Nombre de pubs avant suppression du pub test : " + Pub.count()
		Pub.findByName("Pub des tests")?.delete()
		println "[setUp()] Nombre de pubs apres suppession du pub test : " + Pub.count()
		log.info("[ScenarioUserRoleTests] Set up success.")

		println "[setUp()] Nombre d'utilisateurs avant suppression des users tests : " + User.count()
		User.findByUsername("userA")?.delete()
		User.findByUsername("userB")?.delete()
		User.findByUsername("userC")?.delete()
		println "[setUp()] Nombre d'utilisateurs apres suppression des users tests : " + User.count()

		int nbPubs = Pub.count()
		println "[setUp()] Nombre de pub avant ajout d'un pub" + nbPubs
		pub = new Pub(name: "Pub des tests", address: "address", city: "Toulouse", zip: "97400", type: "PUB").save(failOnError:true)
		println "[setUp()] Nombre de pub après ajout d'un pub" + nbPubs
		assert (nbPubs+1) == Pub.count()
	}
	
	@Ignore
	@Test
	void testCreationAndDeletionUserWithUserRole() {
		log.info("[ScenarioPubTests] testCreationAndDeletionUserWithUserRole().")
		int nbUsers = User.count()
		int nbUserRoles = UserRole.count()
		print "[testCreationAndDeletionUserWithUserRole()] Nombre d'utilisateurs avant ajout des users tests : " + nbUsers
		log.info("[ScenarioPubTests] Ajout de 3 utilisateurs.")
		userA = new User(username:"userA",  enabled: true, password:"passwordA", firstName:"Hobitt", lastName:"bilbo", mail:"bjm.brion@gmail.com").save(failOnError:true)
		userB = new User(username:"userB",  enabled: true, password:"passwordB", firstName:"johanson", lastName:"scarlett", mail:"beauty@gmail.com").save(failOnError:true)
		userC = new User(username:"userC",  enabled: true, password:"passwordC", firstName:"filipo", lastName:"goz", mail:"filipo@gmail.com").save(failOnError:true)

		assert UserRole.create(User.findByUsername("userA"), Role.findByAuthority("ROLE_USER"), true)
		assert UserRole.create(User.findByUsername("userB"), Role.findByAuthority("ROLE_USER"), true)
		assert UserRole.create(User.findByUsername("userC"), Role.findByAuthority("ROLE_USER"), true)

		println "[testCreationAndDeletionUserWithUserRole()] La taille des UserRoles : " + nbUserRoles
		assert (nbUserRoles+3) == UserRole.count()


		// Le pub a été créé par l'utilisateur A.
		println "[testCreationAndDeletionUserWithUserRole()] Configuration : Le pub a été créé par l'utilisateur A"
		assert pub.addToUsers(userA)
		assert userA.addToPubs(pub)

		print "[testCreationAndDeletionUserWithUserRole()] Nombre d'utilisateurs apres ajout des users tests : " + User.count()
		assert (nbUsers+3) == User.count()

		print "[testCreationAndDeletionUserWithUserRole()] Nombre d'utilisateurs avant suppression des users tests : " + User.count()

		log.info("[ScenarioUserRoleTests] Delete 3 users tests.")

		println "[testCreationAndDeletionUserWithUserRole()] Info UserA test : " + User.findByUsername("userA").toString()
		println "[testCreationAndDeletionUserWithUserRole()] Info UserB test : " + User.findByUsername("userB").toString()
		println "[testCreationAndDeletionUserWithUserRole()] Info UserC test: " + User.findByUsername("userC").toString()

		println "[testCreationAndDeletionUserWithUserRole()] Suppression des rôles avant de supprimer les utilisateurs "
		assert UserRole.findByUser(User.findByUsername("userA")).delete()
		assert UserRole.findByUser(User.findByUsername("userB")).delete()
		assert UserRole.findByUser(User.findByUsername("userC")).delete()
		println "[testCreationAndDeletionUserWithUserRole()] Fin des suppressions des rôles "
		println "[testCreationAndDeletionUserWithUserRole()] Suppression des utilisateurs"
		assert userA.delete(failOnError:true)
		assert userB.delete(failOnError:true)
		assert userC.delete(failOnError:true)
		println "[testCreationAndDeletionUserWithUserRole()]Fin des suppressions des utilisateurs"
		println "[testCreationAndDeletionUserWithUserRole()] Nombre d'utilisateurs apres suppression des users tests : " + User.count()
		assert (nbUsers-3) == User.count()
	}

	@Test
	void testCreationAndDeletionUserWithAdminRole() {
		log.info("[ScenarioPubTests] testCreationAndDeletionUserWithAdminRole().")
		println "[testCreationAndDeletionUserWithAdminRole()]Debut des tests!"
	}


	@After
	void tearDown() {
		log.info("[ScenarioPubTests] Tear Down begin.")

		log.info("[ScenarioPubTests] Tear Down: toString pub tests.")
		println "[tearDown()] Info pub test : " + Pub.findByName("Pub des tests").toString()
		println "[tearDown()] Nombre de pubs avant suppression des pubs tests : " + Pub.count()

		int nbPubs = Pub.count()
		log.info("[ScenarioPubTests] Tear Down: delete a pub test.")
		pub.delete()

		print "[tearDown()] Nombre d'utilisateurs apres suppression des pubs tests " + Pub.count()
		assert (nbPubs-1) == Pub.count()

		log.info("[ScenarioPubTests] Tear down end.")
	}
}
