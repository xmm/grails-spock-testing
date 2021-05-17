package io.github.xmm.grails.spock.testing

import grails.converters.JSON
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ExampleController)
class ExampleControllerSpec extends Specification {

    def setup() {
        controller.exampleService = Stub(ExampleService)
    }

    void "test something"() {
        setup:
            controller.exampleService.findElement(1) >> { long id ->
                Optional.of(new Element(id, "one"))
            }
        when:
            controller.get(1)
        then:
            response.status == 200
            response.json.toString() == new JSON([id: 1, value: "one"]).toString()
    }
}
