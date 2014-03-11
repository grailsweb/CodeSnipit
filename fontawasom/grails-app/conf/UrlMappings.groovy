class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
		"/test"(view:"/test")
		"/font"(view:"/font")
		"/datatable"(view:"/datatable")
        "500"(view:'/error')
	}
}
