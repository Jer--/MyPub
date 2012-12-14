package mypub

class Picture {

    String name
	byte[] data
	
	
	static constraints = {
		name blank : false
		data nullable : true, maxSize: 16777216 /* 16M */
	}
}
