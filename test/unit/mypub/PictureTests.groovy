/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
 ******************************************************************************/
package mypub


/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
  ******************************************************************************/
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Picture)
class PictureTests {

	void testConstraints() {
		def existingPicture = new Picture(name: 'picTest', data: [1,1,1,1])
		
		mockForConstraintsTests(Picture, [existingPicture])
		
		// validation pass
		def picture = new Picture(name: 'picTest')
		assert picture.validate()
		
		// name nullable: false
		picture = new Picture()
		assert !picture.validate()
		assert "nullable" == picture.errors["name"]
		
		// name blank: false
		picture = new Picture(name: '')
		assert !picture.validate()	
		assert "blank" == picture.errors["name"]
		
		// data: maxSize: 16777216
		byte[] dataTab = new byte[16777220]
		picture = new Picture(name: 'picTest', data: dataTab)
		assert !picture.validate()
		assert "maxSize" == picture.errors["data"]
		
		
	}
	
	void testToString() {
		def picture = new Picture(name: 'picTest', data: [1,1,1,1])
		assert picture.toString() == "picTest"
	}
}
