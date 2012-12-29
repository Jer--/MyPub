/*******************************************************************************
*  Author : Group BBHC
*  Licence : AGPL v3
 ******************************************************************************/
package mypub



import org.junit.*
import grails.test.mixin.*

@TestFor(PictureController)
@Mock(Picture)
class PictureControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["name"] = 'pictest'
		params["data"]= [1,1,1,1]
    }

    void testIndex() {
        controller.index()
        assert "/picture/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pictureInstanceList.size() == 0
        assert model.pictureInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pictureInstance != null
    }

   /* TODO : we can't test because there no current user
    * void testSave() {
        controller.save()

        assert model.pictureInstance != null
        assert view == '/picture/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/picture/show/1'
        assert controller.flash.message != null
        assert Picture.count() == 1
    }*/

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null

        params.id = picture.id

        def model = controller.show()

        assert model.pictureInstance == picture
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null

        params.id = picture.id

        def model = controller.edit()

        assert model.pictureInstance == picture
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        response.reset()

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null

        // test invalid parameters in update
        params.id = picture.id
        //TODO: add invalid values to params object
		params["name"] = null
		
        controller.update()

        assert view == "/picture/edit"
        assert model.pictureInstance != null

        picture.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/picture/show/$picture.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        picture.clearErrors()

        populateValidParams(params)
        params.id = picture.id
        params.version = -1
        controller.update()

        assert view == "/picture/edit"
        assert model.pictureInstance != null
        assert model.pictureInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/picture/list'

        response.reset()

        populateValidParams(params)
        def picture = new Picture(params)

        assert picture.save() != null
        assert Picture.count() == 1

        params.id = picture.id

        controller.delete()

        assert Picture.count() == 0
        assert Picture.get(picture.id) == null
        assert response.redirectedUrl == '/picture/list'
    }
}
