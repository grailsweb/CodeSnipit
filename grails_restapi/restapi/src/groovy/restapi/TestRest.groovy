package restapi

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

class TestRest {
	def host='http://localhost:8080'
	def upath ='/restapi/books/21'
	static main(args) {
		TestRest t = new TestRest()
		t.restclientPost()
		t.restclientGet()
		t.restclientPut()
		t.restclientGet()
		t.restclientDelete()
		t.restclientGet()
		return
		t.reset_put()
		t.rest_get()
		/*t.htrest()*/
	}

	def restclientGet() {
		def client = new RESTClient( host )
		client.auth.basic "me", "password"
		client.defaultRequestHeaders.'User-Agent' = "My basecamp application (myemail@domain.com)"
		try {
			def resp = client.get( path : upath  )

			assert resp.status == 200  // HTTP response code; 404 means not found, etc.
			println resp.getData()
			println "GET Success: ${resp.statusLine}"
		}catch( ex ) {
			println "GET Success:404"
			println ex.dump()
			assert ex.response.status == 404
		}
	}

	def restclientPost(){
		def client = new RESTClient( host )
		def resp = client.post( path :'/restapi/books',
		body :[isbn: '567', title: 'My Story',numberOfPages:"678"] ,requestContentType: URLENC)
		assert resp.status == 201
		println "POST Success: ${resp.statusLine}"
	}


	def restclientPut(){
		def client = new RESTClient( host )
		def resp = client.put( path : upath,
		body :[isbn: '567', title: 'My Story Put',numberOfPages:"678"] ,requestContentType: URLENC)
		assert resp.status == 204
		println "PUT Success: ${resp.statusLine}"
	}

	def restclientDelete(){
		def client = new RESTClient( host )
		def resp = client.delete( path : upath )
		println resp?.dump()
		assert resp.status == 204
		println "DELETE : ${upath}"
	}
	def htrest(){
		def path='/restapi/books'
		def http = new HTTPBuilder()

		http.request( host, GET, TEXT ) { req ->
			uri.path = path
			uri.query = [id:2 ]
			headers.'User-Agent' = "Mozilla/5.0 Firefox/3.0.4"
			headers.Accept = 'application/json'

			response.success = { resp, reader ->
				assert resp.statusLine.statusCode == 200
				println "Got response: ${resp.statusLine}"
				println "Content-Type: ${resp.headers.'Content-Type'}"
				println reader.text
			}

			response.'404' = { println 'Not found' }
		}
	}
	def rest_get(){

		def http = new HTTPBuilder('http://192.168.2.23:8080/restapi/books/2')
		def html = http.get( query : [q:'Groovy'])
		println html
	}

	def reset_put(){

		def http = new HTTPBuilder( host )
		def postBody = [isbn: '345', title: 'construction worker 6',numberOfPages:"678"] // will be url-encoded

		http.post( path: '/restapi/books', body: postBody,
		requestContentType: URLENC ) { resp ->

			println "POST Success: ${resp.statusLine}"
			assert resp.statusLine.statusCode == 201
		}
	}
}
