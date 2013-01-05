/*******************************************************************************
 *  Author : Group BBHC
 *  Licence : AGPL v3
 ******************************************************************************/
package common

import static org.junit.Assert.*
import grails.test.mixin.*
import mypub.Pub
import mypub.User

import org.junit.*


class UtilsTests {

	void pubContextInit() {
		new Pub(new Pub(name: "pub", address: "address", city: "Toulouse", type: "PUB")).save()
	}

	void pubContextClose() {
		Pub.findByName("pub").delete
	}

	void userContextInit() {
		new User(username:"A", password:"passwordA", firstName:"brion", lastName:"benjamin", mail:"bjm.brion@gmail.com").save()
		new User(username:"B", password:"passwordB", firstName:"johanson", lastName:"scarlett", mail:"beauty@gmail.com").save()
		new User(username:"C", password:"passwordC", firstName:"filipo", lastName:"gonzalez", mail:"filipo.gonzalez@gmail.com").save()
		User.findByUsername("A").addTo(Pub.findByName("pub"))
		User.findByUsername("B").addTo(Pub.findByName("pub"))
		User.findByUsername("C").addTo(Pub.findByName("pub"))
	}

	void userContextClose() {
		User.findByUsername("A").delete
		User.findByUsername("B").delete
	}

	void deleteUserC() {
		User.findByUsername("C").delete
	}

	void friendShipAB() {
		User.findByUsername("A").addToFriends(User.findByUsername("B"))
		User.findByUsername("B").addToFriends(User.findByUsername("A"))
	}

	void createPubByA() {
		//def pub = new Pub(name: 'pub', address: 'address', city: 'Toulouse', type: 'PUB')
		params["name"] = "newPub"
		params["address"] = "new address"
		params["city"] = "Rocheford"
		params["type"] = "PUB"
		params["User"] = User.findByUsername("A")
		Pub.create()
	}

	
	void addCommentWithB() {
		// TODO
	}
	
	void deleteCommentWithB() {
		// TODO
	}
	
	void changeDescriptionPubWithB() {
		// TODO
	}
	
	void deleteFriendBA() {
		// TODO
	}
	

	private Object save(Object object ) {
		validateAndPrintErrors( object )
		Object result = object .save(flush: true )
		assertNotNull( "Object not created: " + object , result)
		return result
	}

	private void validateAndPrintErrors(Object object ) {
		if (! object.validate()) {
			object.errors.allErrors.each {error -> println error }
			fail( "failed to save object ${object}" )
		}
	}
}
