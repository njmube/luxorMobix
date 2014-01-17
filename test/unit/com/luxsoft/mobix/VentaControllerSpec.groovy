package com.luxsoft.mobix



import grails.test.mixin.*
import spock.lang.*

@TestFor(VentaController)
@Mock(Venta)
class VentaControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.ventaInstanceList
            model.ventaInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.ventaInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def venta = new Venta()
            venta.validate()
            controller.save(venta)

        then:"The create view is rendered again with the correct model"
            model.ventaInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            venta = new Venta(params)

            controller.save(venta)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/venta/show/1'
            controller.flash.message != null
            Venta.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def venta = new Venta(params)
            controller.show(venta)

        then:"A model is populated containing the domain instance"
            model.ventaInstance == venta
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def venta = new Venta(params)
            controller.edit(venta)

        then:"A model is populated containing the domain instance"
            model.ventaInstance == venta
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/venta/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def venta = new Venta()
            venta.validate()
            controller.update(venta)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.ventaInstance == venta

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            venta = new Venta(params).save(flush: true)
            controller.update(venta)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/venta/show/$venta.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/venta/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def venta = new Venta(params).save(flush: true)

        then:"It exists"
            Venta.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(venta)

        then:"The instance is deleted"
            Venta.count() == 0
            response.redirectedUrl == '/venta/index'
            flash.message != null
    }
}
