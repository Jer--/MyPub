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
			new User(username:'ben',  enabled: true, password:'admin', firstName:'Brion', lastName:'Benjamin', mail:'brion.benjamin@gmail.com').save(failOnError:true)
			new User(username:'jer',  enabled: true, password:'admin', firstName:'Coquin', lastName:'Jeremie', mail:'coquin.jeremie@gmail.com').save(failOnError:true)
			new User(username:'val',  enabled: true, password:'admin', firstName:'Heuillet', lastName:'Valerie', mail:'valerie.heuillet@gmail.com').save(failOnError:true)
			}
		UserRole.create User.findByUsername('admin'), adminRole, true
		UserRole.create User.findByUsername('ben'), adminRole, true
		UserRole.create User.findByUsername('jer'), adminRole, true
		UserRole.create User.findByUsername('val'), adminRole, true
		
    }
    def destroy = {
    }
}
