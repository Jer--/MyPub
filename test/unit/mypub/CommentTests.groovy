/*******************************************************************************
 *  Author : Group BBHC
 *  License : AGPL v3
 ******************************************************************************/
package mypub



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
@TestMixin(Pub)
class CommentTests {

	void testConstraints() {

		//setUp
		mockDomain(Pub)
		mockDomain(Comment)
		Pub pub1 = new Pub(name: 'pub1', address: 'address', city: 'city', type: 'PUB')
		assert pub1.validate()

		pub1.addTocomments(new Comment(
				username: 'user1',
				text : 'a comment'
				))

		def comment = pub1.comments.find {c ->
			c.username == "user1"
		}

		// validation pass
		assert comment.validate()

		// text nullable: false
		comment.text = null
		assert !comment.validate()

		// username nullable: false
		comment.text = 'some text'
		comment.username = null
		assert !comment.validate()

		// postDate nullable: false
		comment.username = 'user1'
		comment.postDate = null
		assert !comment.validate()

		// text size ..160
		comment.postDate = new Date()
		comment.text = 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'
		assert !comment.validate()

	}
}
