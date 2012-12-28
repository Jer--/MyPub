/*******************************************************************************
 * Author : Group BBHC
 * Licence : AGPL v3
 ******************************************************************************/
import mypub.*
 
class BootStrap {
	def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
	def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

    def init = { servletContext ->
		
		if(!User.count()) {
			new User(username:'admin',  enabled: true, password:'admin', firstName:'Admin', lastName:'Admin', mail:'johndu12@hotmail.fr').save(failOnError:true)
			}
		UserRole.create User.findByUsername('admin'), adminRole, true
		
    }
    def destroy = {
    }
}
