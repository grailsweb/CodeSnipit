package fontawasom

import grails.converters.JSON
import grails.converters.XML

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


@Transactional(readOnly = true)
class PersonController {
	def dataTableJsonService
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def testp(){

		respond Person.list(params), model:[personInstanceCount: Person.count()]
	}
	def dtjqui(){

	}
	def dcl(){
		respond Person.list(params), model:[personInstanceCount: Person.count()]
	}
	def yadcf(){
		respond Person.list(params), model:[personInstanceCount: Person.count()]
	}
	def datafilter(){
		respond Person.list(params), model:[personInstanceCount: Person.count()]
	}

	def listAjax() {
		def where =""
		def fieldFormat = []
		//println " in ajax" + params
		render dataTableJsonService.process(params, Person, where, fieldFormat)
		// params contains all fields from datatable request
		// Foo is the name of your domain class you want get data
		return
	}

	/**
	 * Lists all people.
	 *
	 * GET /people
	 * GET /people.json
	 * GET /people.xml
	 *
	 * @param callback For JSONP.
	 */
	def list1 = {
		def people = Person.list()
		if ( ! people ) {
			render(text: "No people found", status: 404)
		} else {
			withFormat {
				json {
					if ( params.callback ) {
						render(contentType: 'application/json',
						text: "${params.callback}(${people as JSON})")
					} else {
						render people as JSON
					}
				}
				xml { render people as XML }
			}
		}
	}

	/**
	 * Generates data for a jQuery DataTables table showing people.
	 *
	 * @param sEcho
	 * @param sSearch
	 * @param iSortCol_0
	 * @param iSortDir_0
	 * @param iDisplayStart
	 * @param iDisplayLength
	 */
	def dataTablesData = {
		def propertiesToRender = [
			'id',
			'firstName',
			'lastName',
			'dateOfBirth'
		]

		def dataToRender = [:]
		dataToRender.sEcho = params.sEcho
		dataToRender.aaData=[]                // Array of people.
		dataToRender.iTotalRecords = Person.count()
		dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords

		// Create the query, possibly with a search filters. We only search
		// String properties in this example.
		def filters = []
		filters << "p.firstName like :filter"
		filters << "p.lastName like :filter"

		def filter = filters.join(" OR ")
		def query = new StringBuilder("from Person as p")
		if ( params.sSearch ) {
			query.append(" where (${filter})")
		}

		def sortProperty = propertiesToRender[params.iSortCol_0 as int]
		def sortDir = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
		query.append(" order by p.${sortProperty} ${sortDir}")

		// Execute the query
		def people = []
		if ( params.sSearch ) {
			// Revise the number of total display records after applying the filter
			def countQuery = new StringBuilder("select count(*) from Person as p where (${filter})")
			def result = Person.executeQuery(countQuery.toString(),
					[filter: "%${params.sSearch}%"])
			if ( result ) {
				dataToRender.iTotalDisplayRecords = result[0]
			}
			people = Person.findAll(query.toString(),
					[filter: "%${params.sSearch}%"],
					[max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
		} else {
			people = Person.findAll(query.toString(),
					[max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
		}

		// Process the response
		people?.each { person ->
			def record = []
			propertiesToRender.each { record << person."${it}" }
			dataToRender.aaData << record
		}

		render dataToRender as JSON
	}
	def index(Integer max) {
		params.max = Math.min(max ?: 20, 100)
		respond Person.list(params), model:[personInstanceCount: Person.count()]
	}

	def show(Person personInstance) {
		respond personInstance
	}

	def create() {
		respond new Person(params)
	}

	@Transactional
	def save(Person personInstance) {
		if (personInstance == null) {
			notFound()
			return
		}

		if (personInstance.hasErrors()) {
			respond personInstance.errors, view:'create'
			return
		}

		personInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'personInstance.label', default: 'Person'),
					personInstance.id
				])
				redirect personInstance
			}
			'*' { respond personInstance, [status: CREATED] }
		}
	}

	def edit(Person personInstance) {
		respond personInstance
	}

	@Transactional
	def update(Person personInstance) {
		if (personInstance == null) {
			notFound()
			return
		}

		if (personInstance.hasErrors()) {
			respond personInstance.errors, view:'edit'
			return
		}

		personInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Person.label', default: 'Person'),
					personInstance.id
				])
				redirect personInstance
			}
			'*'{ respond personInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Person personInstance) {

		if (personInstance == null) {
			notFound()
			return
		}

		personInstance.delete flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Person.label', default: 'Person'),
					personInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'personInstance.label', default: 'Person'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

	def listAjax2(){
		println params
	params?.each{ println ("it: " + it) }
	println params?.start
	println params."order[0][dir]"
	println params."columns[1][data]"
	int len= params.findAll{ it.key ==~ /columns\[\d+\]\[data\]/ }.size()
	for(int i=0;  i<len; i++){
		def str="columns["+i+"][data]"
		println i + " ==> " + params."$str"
	}

	}
}
