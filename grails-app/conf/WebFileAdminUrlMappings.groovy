class WebFileAdminUrlMappings {

	static mappings = {
        "/wfadmin/$path?/$action?" (controller:'Wfadmin')

        "/wfadmin/login" (controller:'Wfadmin', action:'login')
        "/wfadmin/logout" (controller:'Wfadmin', action:'logout')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
