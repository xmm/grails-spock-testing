/**
 * This code taken from http://nimavat.me/blog/grails3-controller-integration-test-example
 */
package io.github.xmm.grails.spock.testing

import grails.util.GrailsWebMockUtil
import org.grails.plugins.testing.GrailsMockHttpServletRequest
import org.grails.plugins.testing.GrailsMockHttpServletResponse
import org.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import spock.lang.Ignore
import spock.lang.Specification

abstract class BaseControllerIntegrationSpec extends Specification {

	@Autowired
	WebApplicationContext ctx

	void setup() {
		MockHttpServletRequest request = new GrailsMockHttpServletRequest(ctx.servletContext)
		MockHttpServletResponse response = new GrailsMockHttpServletResponse()
		GrailsWebMockUtil.bindMockWebRequest(ctx, request, response)
		currentRequestAttributes.setControllerName(controllerName)
	}

	@Ignore
	abstract String getControllerName()

	@Ignore
	protected GrailsWebRequest getCurrentRequestAttributes() {
		return (GrailsWebRequest) RequestContextHolder.currentRequestAttributes()
	}

	void cleanup() {
		RequestContextHolder.resetRequestAttributes()
	}

	@Ignore
	def <T> T autowire(Class<T> clazz) {
		def bean = clazz.newInstance()
		ctx.autowireCapableBeanFactory.autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false)
		bean
	}


	@Ignore
	def <T> T autowire(T bean) {
		ctx.autowireCapableBeanFactory.autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false)
		bean
	}
}
