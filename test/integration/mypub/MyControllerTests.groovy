package mypub

import static org.junit.Assert.*
import grails.test.GrailsUnitTestCase

import org.junit.*

/**
 * Scenario integration tests:
 * This scenario allows us to test the different possible use cases do by users
 * of the web application MyPub.
 * Steps:
 * - Add three users (A, B and C).
 * - Delete User C.
 * - Creating a friendship bond between users A and B.
 * - Add a bar with user A edit.
 * - Add a comment with user B.
 * - Delete comment with user B.
 * - Change the description of the bar with user B.
 * - User B removes his friends the user A.
 */
class MyControllerTests extends GrailsUnitTestCase {

	@Before
	protected void setUp() {
	}

	@After
	void tearDown() {
		// Tear down logic here
	}

	@Test
	void testSomething() {
		fail "Implement me"
	}
}
