package mypub

class Modification {
	
	String username
	Date proposalDate
	
	static belongsTo = [pub : Pub]
	static hasMany = [usersOk : String]

    static constraints = {
		usersOk nullable:true
    }
}
