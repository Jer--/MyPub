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
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
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