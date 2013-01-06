/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
 ******************************************************************************/
package mypub



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {
	
	void testconstraint() {
		 def existingUser = new User(username:"userTest", password:"passwordTest", firstName:"Delon", lastName:"Alain", sex:'M', mail:"testmail@test.com")
		 
		 mockForConstraintsTests(User, [existingUser])
		 
		 /* 
		  * Validation success.
		  */
		 def user = new User(username:"userTest1", password:"passwordTest", firstName:"Ferrat", lastName:"Luc", mail:"testmail@test.com")
		 assert user.validate()
		 
		 /*
		  * Validation not success.
		  */
		 
		 /*
		  * The following attributes can be NULLABLE:
		  * - DDN
		  * - sex
		  * - friends
		  * - pictures
		  * - pubs
		  * Others attributes shall be indicated when User creation.
		  */
		 
		 // UserName is not NULLABLE.
		 user = new User(password:"passwordTest", firstName:"Patrick", lastName:"Sebastien", sex:'M', mail:"testmail@test.com")
		 assert !user.validate()
		 assert "nullable" == user.errors["username"]
		 
		 // Password is not NULLABLE.
		 user = new User(username:"userTest2", firstName:"Rizoli", lastName:"Phillipe", sex:'M', mail:"testmail@test.com")
		 assert !user.validate()
		 assert "nullable" == user.errors["password"]
		 
		 // FirstName is not NULLABLE.
		 user = new User(username:"userTest3", password:"passwordTest", lastName:"Mati", sex:'M', mail:"testmail@test.com")
		 assert !user.validate()
		 assert "nullable" == user.errors["firstName"]
		 
		 // LastName is not NULLABLE.
		 user = new User(username:"userTest4", password:"passwordTest", firstName:"Sabatier", sex:'M', mail:"testmail@test.com")
		 assert !user.validate()
		 assert "nullable" == user.errors["lastName"]
		 
		 // Mail is not NULLABLE.
		 user = new User(username:"userTest5", password:"passwordTest", firstName:"Vilard", lastName:"Herve")
		 assert !user.validate()
		 assert "nullable" == user.errors["mail"]
		 
		 /*
		  * The following attributes can not be blank:
		  * - userName
		  * - password
		  * - firstName
		  * - lastName
		  * - mail
		  * - DDN
		  */
		 
		 // UserName not be blank.
		 user = new User(username:"", password:"passwordTest", firstName:"Blanc", lastName:"Michel", mail:"testmail@test.com")
		 assert !user.validate()
		 assert "blank" == user.errors["username"]
		 
		 // Password not be blank.
		 user = new User(username:"userTest6", password:"", firstName:"Torr", lastName:"Michelle", mail:"testmail@test.com")
		 assert !user.validate()
		 assert "blank" == user.errors["password"]
		 
		 // FirstName not be blank.
		 user = new User(username:"userTest7", password:"passwordTest", firstName:"", lastName:"Gregory", mail:"testmail@test.com")
		 assert !user.validate()
		 assert "blank" == user.errors["firstName"]
		 
		 // LastName not be blank.
		 user = new User(username:"userTest8", password:"passwordTest", firstName:"Bardou", lastName:"", mail:"testmail@test.com")
		 assert !user.validate()
		 assert "blank" == user.errors["lastName"]
		 
		 // FirstName not be blank.
		 user = new User(username:"userTest9", password:"passwordTest", firstName:"Thuram", lastName:"Lilian", mail:"")
		 assert !user.validate()
		 assert "blank" == user.errors["mail"]
		 
		 // Mail must be written as email.
		 user = new User(username:"userTest10", password:"passwordTest", firstName:"Leta", lastName:"Nico", mail:"notemailatpointcom")
		 assert !user.validate()
		 assert "email" == user.errors["mail"]
		 
		 // Sex shall be in List ['M', 'F'].
		 user = new User(username:"userTest", password:"passwordTest", firstName:"Zemmour", lastName:"Eric", sex:'U', mail:"notemailatpointcom")
		 assert !user.validate()
		 //assert "inList" == user.errors["sex"]
		 
		 // Max DDN shall be new Date().minus(3650).
		 Date dateTest = new Date().minus(2650)
		 user = new User(username:"userTest10", password:"passwordTest", firstName:"Bayrou", lastName:"Francois", mail:"notemailatpointcom", birthday: dateTest)
		 assert !user.validate()
		 assert "max" == user.errors["birthday"]
	}
}