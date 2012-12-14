package mypub

class Pub {

    enum PubType {PUB, RESTO, CLUB}
	
	String name
	String address
	
	String latitude, longitude
	
	Picture presentationPicture
	
	static hasMany = [pictures : Picture, myTypes : PubType, coments : Coment, modifications : Modification]
	
	String toString () {return 'Pub : ' + name }
	
    static constraints = {
		address unique:true
		presentationPicture nullable: true
		
		pictures nullable: true
		coments nullable: true
		modifiactions nullable: true
    }
}
