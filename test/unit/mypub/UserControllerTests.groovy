package mypub



import org.junit.*
import grails.test.mixin.*

@TestFor(UserController)
@Mock(User)
class UserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["username"] = 'john'
		params["password"] = 'azerty'
		params["firstName"] = 'alfred'
		params["lastName"] = 'alfredaussi'
		params["mail"] = 'alfred@john.fr'
		//params["ddn"]= new Date ('2007/01/01')
		//params["enabled"] = true
		//params["accountExpired"] = false
		//params["accountLocked"] = false
		//params["passwordExpired"] = false
		
    }

    void testIndex() {
        controller.index()
        assert "/user/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userInstanceList.size() == 0
        assert model.userInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userInstance != null
    }

    /*TODO problem with password encode
     * void testSave() {
        controller.save()

        assert model.userInstance != null
        assert view == '/user/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/user/show/1'
        assert controller.flash.message != null
        assert User.count() == 1
    }
*/
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        params.id = user.id

        def model = controller.show()

        assert model.userInstance == user
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        params.id = user.id

        def model = controller.edit()

        assert model.userInstance == user
    }
}
