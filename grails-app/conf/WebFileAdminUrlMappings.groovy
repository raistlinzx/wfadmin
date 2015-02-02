class WebFileAdminUrlMappings {

	static mappings = {
        "/wfadmin/$path?/$action?" (controller:'Wfadmin')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
