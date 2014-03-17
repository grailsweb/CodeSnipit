import groovy.sql.Sql
import fontawasom.*

class BootStrap {
	def grailsApplication
	def servletContext
	def init = { servletContext ->

		def appName = grailsApplication.metadata['app.name']

		println 'BootStrap running!'

		environments {
			test{
			}
			development {
				println "Development mode"
				testdata()
				def dbCreateProperty = grailsApplication.config.dataSource.dbCreate
				if(dbCreateProperty == 'create' || dbCreateProperty == 'create-drop'){
					println "developmnet creating table"
					String sqlString = servletContext.getResourceAsStream("/data/table_dump.sql").text
					def db = [url:grailsApplication.config.dataSource.url, user:grailsApplication.config.dataSource.username, password:grailsApplication.config.dataSource.password, driver:grailsApplication.config.dataSource.driverClassName]
					println db
					/*def cls = Class.forName(db.driver).newInstance();
					 Sql sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
					 sql.execute(sqlString)*/
				}
			}
			production {
			}
		}



		println 'BootStrap finished!'
	}
	def destroy = {
	}

	private def testdata(){
		// Create some test data
		new Book(author:"Stephen King",title:"The Shining").save()
		new Book(author:"James Patterson",title:"Along Came a Spider").save()
		new Book(author: 'myBook', title: 'this is my book').save()

		def johnDoe = new Person( firstName: "John", lastName: "Doe" ).save(failOnError: true)
		def joeReed = new Person( firstName: "Joe", lastName: "Reed" ).save(failOnError: true)
		def jimSmith = new Person( firstName: "Jim", lastName: "Smith" ).save(failOnError: true)
		def patrickHartwin = new Person( firstName: "Patrick", lastName: "Hartwin" ).save(failOnError: true)
		def steveGunther = new Person( firstName: "Steve", lastName: "Gunther" ).save(failOnError: true)
		def samWhiting = new Person( firstName: "Sam", lastName: "Whiting" ).save(failOnError: true)
		def sarahMathews = new Person( firstName: "Sarah", lastName: "Mathews" ).save(failOnError: true)
		def lisaPudock = new Person( firstName: "Lisa", lastName: "Pudock" ).save(failOnError: true)
		def karaWhiting = new Person( firstName: "Kara", lastName: "Whiting" ).save(failOnError: true)
		new Person( firstName: "Bill", lastName: "Ramasamy" ).save(failOnError: true)
		new Person( firstName: "Ranaga", lastName: "Suri" ).save(failOnError: true)
		new Person( firstName: "Anana", lastName: "Ravi" ).save(failOnError: true)
		new Person( firstName: "Zona", lastName: "Narayana" ).save(failOnError: true)
		new Person( firstName: "Bama", lastName: "Thiru" ).save(failOnError: true)
		new Person( firstName: "vijai", lastName: "Manikam" ).save(failOnError: true)
		new Person( firstName: "deva", lastName: "mani" ).save(failOnError: true)
	}
}
