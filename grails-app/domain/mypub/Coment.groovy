package mypub

class Coment {
	
	String username
	Date postDate
	String text
	
	static belongsTo = [pub : Pub]

    static constraints = {
		text size: 1..160
    }
}
