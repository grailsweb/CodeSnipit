import com.sfg.*

class BootStrap {

    def init = { servletContext ->
		new Book(title:"The Stand",isbn:1,numberOfPages:2345).save()
		new Book(title:"The Shining",isbn:2,numberOfPages:2345).save()
    }
    def destroy = {
    }
}
