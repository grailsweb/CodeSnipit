package fontawasom
import java.util.Date;

class Person {
	String firstName, lastName
	Date dateOfBirth

	static mapping = {
		
		firstName index: 'person_name_idx'
		lastName index: 'person_name_idx'
	}
	static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		dateOfBirth(blank:true,nullable: true)
	}
}