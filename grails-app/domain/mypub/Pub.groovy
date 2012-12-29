/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
 ******************************************************************************/
package mypub

class Pub {
	
	String name
	String address
	String type
	
	String latitude, longitude
	
	Picture presentationPicture
	
	static belongsTo = User
	static hasMany = [pictures : Picture, coments : Coment, modifications : Modification, users : User]
	
	String toString () {return 'Pub : ' + name +
								' address ' + address 
	}
	
    static constraints = {
		address unique:true
		presentationPicture nullable: true
		//type in ['PUB','RESTO','CLUB']
		modifications nullable:true
		pictures nullable: true
		coments nullable: true
    }
}
