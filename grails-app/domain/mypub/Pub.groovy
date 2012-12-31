/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
 ******************************************************************************/
package mypub

class Pub {
	
	String name
	String address
	String city
	String type
	
	float latitude, longitude
	
	Picture presentationPicture
	
	static belongsTo = User
	static hasMany =  [pictures : Picture, coments : Coment, modifications : Modification, users : User]
	
	String toString () {return name + ', ' + city }
	
    static constraints = {
		name nullable:false, blank:false
		address nullable:false, blank:false
		city nullable:false, blank:false
		type nullable:false, inList: ['PUB','RESTO','CLUB'], blank:false
		
		latitude nullable:true
		longitude nullable:true
		presentationPicture nullable: true
		
		pictures nullable: true
		modifications nullable:true
		coments nullable: true
		
    }
}
