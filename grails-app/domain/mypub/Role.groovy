/*******************************************************************************
*  Author : Group BBHC
*  License : AGPL v3
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
	@Override
	public String toString() {
		return super.toString();
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
