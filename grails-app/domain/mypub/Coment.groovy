/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
 ******************************************************************************/
package mypub

class Coment {

	String username
	Date postDate
	String text

	static belongsTo = [pub : Pub]

	String toString () {
		return 'Comment by : ' + username +
		' date :' + postDate +
		' / ' + text
	}


	static constraints = { text size: 1..160 }
}
