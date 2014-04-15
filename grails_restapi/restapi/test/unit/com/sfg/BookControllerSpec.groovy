package com.sfg

// test-app com.sfg.BookController
import static org.hamcrest.CoreMatchers.anything
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.TestFor

import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer

import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(BookController)
class BookControllerSpec extends Specification {

    def setup() {
		println "I am in setup"
    }

    def cleanup() {
    }

	void "test hello"() { 
		when: controller.hello()
		
		then: response.text == 'hello' 
				println "res ==> "+ response.text 
		}
	
    
	def "Test that a basic GET request returns a JSON result"(){
		def url ="http://192.168.2.23:8080/restapi/books/1"
		given:"A rest client instance"
			def rest = new RestBuilder()
			final mockServer = MockRestServiceServer.createServer(rest.restTemplate)
			mockServer.expect(requestTo(url))
					   .andExpect(method(HttpMethod.GET))
					   .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
						.andRespond(withSuccess('{"name":"acegi"}', MediaType.APPLICATION_JSON))


		when:"A get request is issued for a response that returns XML"
			def resp = rest.get(url) {
				accept "application/json"
			}

		then:"The response is a gpath result"
			mockServer.verify()
			println " output " + resp
			resp != null
			resp.json instanceof JSONObject
			resp.json.name == 'acegi'
			println resp?.status
			println resp?.text
	}


}
