package mypub

class Picture {

    String name
	byte[] data
	
	String toString() {return 'Pic name : ' + name}
	
	static constraints = {
		name blank : false
		data nullable : true, maxSize: 16777216 /* 16M */
	}
}
