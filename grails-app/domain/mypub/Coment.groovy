/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
 ******************************************************************************/
package mypub

class Coment {

	String username
	Date postDate
	String text
	// TODO generated with new attribute "idComment", and Comment with double "m"!
	int idComment
	static belongsTo = [pub : Pub]

	String toString () {
		if (text.length()>30){
			return username +
			' : ' + text.substring(0, 30)
		}
		else {
			return username +
			' : ' + text
		}
	}


	static constraints = { 
		text size: 1..160
		idComment unique:true
	}
}
