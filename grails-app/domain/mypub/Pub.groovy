/*******************************************************************************
 * Author : Group BBHC
 * Licence : AGPL v3
 ******************************************************************************/
package mypub

class Pub {

    enum PubType {PUB, RESTO, CLUB}
	
	String name
	String address
	
	String latitude, longitude
	
	Picture presentationPicture
	
	static belongsTo = User
	static hasMany = [pictures : Picture, myTypes : PubType, coments : Coment, modifications : Modification, users : User]
	
	String toString () {return 'Pub : ' + name +
								' address ' + address 
	}
	
    static constraints = {
		address unique:true
		presentationPicture nullable: true
		
		modifications nullable:true
		pictures nullable: true
		coments nullable: true
    }
}
