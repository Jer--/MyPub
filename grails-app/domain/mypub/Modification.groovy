package mypub

class Modification {
	
	String username
	Date proposalDate
	
	static hasMany = [usersOk : String]
	static belongsTo = [pub : Pub]

    static constraints = {
		userOk nullable: true
    }
}
