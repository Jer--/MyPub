/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
 ******************************************************************************/
package mypub

class Pub {
	
	String name
	String address
	String addressOptionnal
	String city
	String zip
	String website
	String type
	// TODO Generated with new attribute idPub
	int idPub
	
	Picture presentationPicture
	
	static belongsTo = User
	static hasMany =  [pictures : Picture, coments : Coment, modifications : Modification, users : User]
	
	String toString () {return name + ', ' + city }
	
    static constraints = {
		name nullable:false, blank:false
		address nullable:false, blank:false
		addressOptionnal nullable:true
		city nullable:false, blank:false
		idPub unique: true
		website nullable:true
		zip nullable:true
		
		type nullable:false, inList: ['PUB','RESTO','CLUB'], blank:false
		
		
		presentationPicture nullable: true
		
		pictures nullable: true
		modifications nullable:true
		coments nullable: true
		
    }
}
