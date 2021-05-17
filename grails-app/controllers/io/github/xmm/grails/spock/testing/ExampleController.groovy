package io.github.xmm.grails.spock.testing

import grails.converters.JSON

class ExampleController {

    ExampleService exampleService

    def get(long id) {
        def result = Optional.ofNullable(exampleService.findElement(id)).orElseThrow()
        render(new JSON(result.get()))
    }
}
