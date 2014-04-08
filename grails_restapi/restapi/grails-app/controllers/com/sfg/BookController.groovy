package com.sfg
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.GET
import grails.rest.RestfulController
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient

class BookController extends RestfulController<Book> {

	// We support both JSON and XML.
	static responseFormats = ['json', 'html', 'xml']
	static respondsWith = [update:['json', 'xml', 'html']]

	BookController() {
		super(Book)
	}

	@Override
	def show() {
		// We pass which fields to be rendered with the includes attributes,
		// we exclude the class property for all responses.

		respond Book.get(params.id), formats:['xml', 'json', 'html']

		//respond queryForResource(params.id), [includes: includeFields, excludes: ['class']]
	}

	@Override
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond listAllResources(params), [includes: includeFields, excludes: ['class']]
	}

	private getIncludeFields() {
		params.fields?.tokenize(',')
	}

	@Override
	def update(Book updatedBook) {
		if (updatedBook.hasErrors()) {
			respond updatedBook.errors
			return
		}
		Book.withTransaction {
			updatedBook.save(flush: true)
			respond updatedBook
		}
	}

	@Override
	def save(Book book){
		println book.dump()
		book.save(flush: true)
		respond book
	}

	def test(){

		def host='http://192.168.2.23:8080'
		def path='/restapi/books/'+params.id
		def http = new HTTPBuilder(host)

		def html = http.get( path : path )
		println html
		render('test' + html)
	}
	def rtest1(){
		println params
		def host='http://192.168.2.23:8080'
		def path='/restapi/books/'+params.id
		def output= " rest"
		def twitter = new RESTClient( "${host}" )
		try { // expect an exception from a 404 response:
			def resp = twitter.get(path: path)
			println resp.dump()
			println resp.status
			output = resp.responseData
		}
		// The exception is used for flow control but has access to the response as well:
		catch( ex ) { println ex
			if ( ex?.response.status == 404 ){
				output="Record not found"
			}

		}

		render('rtest :' + output )
	}

	def rtest(){
		println "I am in rtest"
		withRest(uri: "http://localhost:8080/restapi/books") {
			def bodyContent = [
				pano: jsonText
			]
	
			def json = put(body: bodyContent, requestContentType: JSON)
	
			if (json.status == 200) {
			  wsr.success = true
			}
	}
		render("done")
	}
}
