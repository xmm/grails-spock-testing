package io.github.xmm.grails.spock.testing

import groovy.transform.CompileStatic

@CompileStatic
//@Immutable
//@ToString(includeNames = true)
class Element {
    long id
    String value

    Element(long id, String value) {
        this.id = id
        this.value = value
    }
}
