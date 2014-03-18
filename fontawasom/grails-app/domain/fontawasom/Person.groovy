package fontawasom
import java.util.Date;
import org.grails.databinding.BindingFormat

class Person {
	String firstName, lastName
	
	@BindingFormat('dd-MM-yyyy')
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