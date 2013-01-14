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
	
	Picture presentationPicture
	
	static belongsTo = User
	static hasMany =  [pictures : Picture, comments : Comment, modifications : Modification, users : User]
	
	String toString () {return name + ', ' + city }
	
    static constraints = {
		name nullable:false, blank:false, unique: true
		address nullable:false, blank:false
		addressOptionnal nullable:true
		city nullable:false, blank:false
		website nullable:true
		zip nullable:true
		type nullable:false, inList: ['PUB','RESTO','CLUB'], blank:false
		
		
		presentationPicture nullable: true
		
		pictures nullable: true
		modifications nullable:true
		comments nullable: true
    }
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
