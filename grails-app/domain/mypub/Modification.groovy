package mypub

class Modification {
	
	String username
	Date proposalDate
	
	static belongsTo = [pub : Pub]
	static hasMany = [usersOk : String]

	String toString () {return 'Modification by : ' + username +
		' date :' + proposalDate 
		}

	
    static constraints = {
		usersOk nullable:true
    }
}
