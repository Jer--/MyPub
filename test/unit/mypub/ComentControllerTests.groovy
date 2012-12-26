package mypub



import org.junit.*
import grails.test.mixin.*

@TestFor(ComentController)
@Mock(Coment)
class ComentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/coment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.comentInstanceList.size() == 0
        assert model.comentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.comentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.comentInstance != null
        assert view == '/coment/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/coment/show/1'
        assert controller.flash.message != null
        assert Coment.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/coment/list'

        populateValidParams(params)
        def coment = new Coment(params)

        assert coment.save() != null

        params.id = coment.id

        def model = controller.show()

        assert model.comentInstance == coment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/coment/list'

        populateValidParams(params)
        def coment = new Coment(params)

        assert coment.save() != null

        params.id = coment.id

        def model = controller.edit()

        assert model.comentInstance == coment
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/coment/list'

        response.reset()

        populateValidParams(params)
        def coment = new Coment(params)

        assert coment.save() != null

        // test invalid parameters in update
        params.id = coment.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/coment/edit"
        assert model.comentInstance != null

        coment.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/coment/show/$coment.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        coment.clearErrors()

        populateValidParams(params)
        params.id = coment.id
        params.version = -1
        controller.update()

        assert view == "/coment/edit"
        assert model.comentInstance != null
        assert model.comentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/coment/list'

        response.reset()

        populateValidParams(params)
        def coment = new Coment(params)

        assert coment.save() != null
        assert Coment.count() == 1

        params.id = coment.id

        controller.delete()

        assert Coment.count() == 0
        assert Coment.get(coment.id) == null
        assert response.redirectedUrl == '/coment/list'
    }
}
