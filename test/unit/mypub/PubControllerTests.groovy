package mypub



import org.junit.*
import grails.test.mixin.*

@TestFor(PubController)
@Mock(Pub)
class PubControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["address"] = 'someValidName'
		params["name"] = 'blabla'
		params["latitude"] = 'blabla'
		params["longitude"] = 'blabla'
		params["type"] = 'PUB'
    }

    void testIndex() {
        controller.index()
        assert "/pub/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pubInstanceList.size() == 0
        assert model.pubInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pubInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pubInstance != null
        assert view == '/pub/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pub/show/1'
        assert controller.flash.message != null
        assert Pub.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null

        params.id = pub.id

        def model = controller.show()

        assert model.pubInstance == pub
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null

        params.id = pub.id

        def model = controller.edit()

        assert model.pubInstance == pub
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        response.reset()

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null

        // test invalid parameters in update
        params.id = pub.id
        //TODO: add invalid values to params object
		params["name"] = null

        controller.update()

        assert view == "/pub/edit"
        assert model.pubInstance != null

        pub.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pub/show/$pub.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pub.clearErrors()

        populateValidParams(params)
        params.id = pub.id
        params.version = -1
        controller.update()

        assert view == "/pub/edit"
        assert model.pubInstance != null
        assert model.pubInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pub/list'

        response.reset()

        populateValidParams(params)
        def pub = new Pub(params)

        assert pub.save() != null
        assert Pub.count() == 1

        params.id = pub.id

        controller.delete()

        assert Pub.count() == 0
        assert Pub.get(pub.id) == null
        assert response.redirectedUrl == '/pub/list'
    }
}
