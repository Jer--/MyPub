/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
 ******************************************************************************/
package mypub

class User {
	// TODO Generated
	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String firstName, lastName, mail, sex
	Date birthday
	Picture avatar
	
	static hasMany = [friends : User, pictures : Picture, pubs : Pub]

	String toString() {return firstName + ' ' + lastName}
	
	
	static constraints = {
		username blank: false, unique: true
		password blank: false
		
		firstName blank: false
		lastName blank: false
		mail blank: false, email: true
		avatar nullable:true
		birthday  nullable: true, blank: false, max: new Date().minus(3650)
		sex  nullable: true, inList: ['M','F']
		
		friends nullable:true
		pictures nullable:true
		pubs nullable:true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		if (springSecurityService == null) {
			password = "passworld"
			return
		}
		password = springSecurityService.encodePassword(password)
	}
}
