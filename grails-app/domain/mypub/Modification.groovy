/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub

import java.util.Date

class Modification {

	User author
	Date proposalDate = new Date()
	String about
	String newContent

	static belongsTo = [pub : Pub]
	static hasMany = [usersOk : User]

	String toString () {
		return 'Modification by : ' + author.getUsername() +
		' date :' + proposalDate
	}

	static constraints = {
		about nullable:false, inList: [
			'name',
			'address',
			'addressOptionnal',
			'city',
			'zip',
			'website',
			'type'
		], blank:false
		author nullable:false
		newContent nullable:false
	}
}