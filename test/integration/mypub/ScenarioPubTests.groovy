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
 */
class ScenarioPubTests extends GroovyTestCase {

	@Before
	void setUp() {
		log.info("[ScenarioPubTests] setUp(): Start integration test for MyPub App.")
	}

	@Test
	void testAddPub() {
	}

	@After
	void tearDown() {
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
