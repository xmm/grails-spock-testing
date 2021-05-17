package io.github.xmm.grails.spock.testing

class ExampleService {

    DataProvider dataProvider

    Optional<Element> findElement(long id) {
        String value
        try {
            value = dataProvider.findById(id)
        } catch(RuntimeException ignored) {
            throw new ExternalException()
        }
        Optional.ofNullable(value)
                .map({ it -> new Element(id, it) })
    }
}
