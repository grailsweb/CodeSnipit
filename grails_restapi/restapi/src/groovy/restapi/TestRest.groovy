package restapi

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

class TestRest {

	static main(args) {
		TestRest t = new TestRest()
		t.rtest()
	}

	def rtest(){
		
		def host='http://192.168.2.23:8080'
		def path='/restapi/books/2'
		
		def twitter = new RESTClient( "${host}/${path}" )
		// twitter auth omitted
		 
		try { // expect an exception from a 404 response:
			twitter.head path : 'public_timeline'
			assert false, 'Expected exception'
		}
		// The exception is used for flow control but has access to the response as well:
		catch( ex ) { assert ex.response.status == 404 }
		 
		assert twitter.head( path : 'home_timeline.json' ).status == 200
		
	}
	def test(){

		def host='http://192.168.2.23:8080'
		def path='/restapi/books/2'
		def http = new HTTPBuilder(host)

		def html = http.get( path : path )
		println html
		 
	}
}
