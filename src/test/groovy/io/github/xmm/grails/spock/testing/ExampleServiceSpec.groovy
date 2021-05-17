package io.github.xmm.grails.spock.testing

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ExampleService)
class ExampleServiceSpec extends Specification {

    def setup() {
        service.dataProvider = Stub(DataProvider)
    }

    void "service findElement must return existent id"() {
        setup:
            service.dataProvider.findById(1) >> "one"
        when:
            def element = service.findElement(1)
        then:
            element.isPresent()
            with(element.get()) {
                id == 1
                value == "one"
            }
    }

    void "service findElement must return Option.empty() if id not exists"() {
        setup:
            service.dataProvider.findById(1) >> null
        when:
            def element = service.findElement(1)
        then:
            !element.isPresent()
            element == Optional.empty()
    }
}
