/*******************************************************************************
 *  Author : Group BBHC
 *  Licence : AGPL v3
 ******************************************************************************/
package mypub
package common

import static org.junit.*

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
class TestAppTests extends GroovyTestCase {

	def sessionFactory

	@Before
	void setUp() {
		/*
		 * Enhancement of integration test:
		 *
		 * sessionFactory.currentSession.flush()
		 * sessionFactory.currentSession.clear()
		 * drop database <myTestDatabase>
		 * create database <myTestDatabase>
		 */
		log.info("[TestAppTests] Start integration test for MyPub App.")
		log.info("[TestAppTests] Launch method: pubContextInit.")
		// pubContextInit()
		log.info("[TestAppTests] Launch method: userContextInit.")
		// userContextInit()
		log.info("[TestAppTests] Set up success.")
	}
	
	@Test
	void testSomething() {
		log.info("[TestAppTests] Launch method: deleteUserC().")
		// deleteUserC()
		log.info("[TestAppTests] Launch method: friendShipAB().")
		// friendShipAB()
		log.info("[TestAppTests] Launch method: createPubByA().")
		// createPubByA()
		log.info("[TestAppTests] Launch method: addCommentWithB().")
		// addCommentWithB()
		log.info("[TestAppTests] Launch method: deleteCommentWithB().")
		// deleteCommentWithB()
		log.info("[TestAppTests] Launch method: changeDescriptionPubWithB().")
		// changeDescriptionPubWithB()
		log.info("[TestAppTests] Launch method: deleteFriendBA().")
		// deleteFriendBA()
	}
	
	@After
	void tearDown() {
		// userContextClose()
		// pubContextInit()
		log.info("[TestAppTests] Finish!.")
	}
}
