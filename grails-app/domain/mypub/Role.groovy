/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
 ******************************************************************************/
package mypub

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
