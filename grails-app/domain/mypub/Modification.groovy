/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub

import java.util.Date

class Modification {

	String username
	Date proposalDate = new Date()
	Pub newContent
	
	Modification(String username, Pub newContent) {
		this.username = username
		this.newContent = newContent
		usersOk = new ArrayList<User>()
		usersOk.add(User.findByUsername(username))

	}

	static belongsTo = [pub : Pub]
	static hasMany = [usersOk : User]

	String toString () {
		return 'Modification by : ' + username +
		' date :' + proposalDate
	}
}