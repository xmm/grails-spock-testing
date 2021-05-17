package io.github.xmm.grails.spock.testing

import grails.converters.JSON
import grails.test.mixin.integration.Integration
import org.grails.plugins.testing.GrailsMockHttpServletResponse

@Integration
class ExampleControllerIntegrationSpec extends BaseControllerIntegrationSpec {

    String controllerName = "Example"

    GrailsMockHttpServletResponse response

    ExampleController controller
    ExampleService exampleService
    DataProvider dataProvider

    def setup() {
        controller = autowire(ExampleController)
        dataProvider = Stub(DataProvider)
        exampleService.dataProvider = dataProvider
        response = controller.response
    }

    void "test something"() {
        setup:
            dataProvider.findById(1) >> "one"
        when:
            controller.get(1)
        then:
            response.status == 200
            response.json.toString() == new JSON([id: 1, value: "one"]).toString()
    }
}
