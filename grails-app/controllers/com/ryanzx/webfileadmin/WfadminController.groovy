package com.ryanzx.webfileadmin

class WfadminController {

	private static final Map DEFAULT_PATHS = [
		css:    [name: 'CSS',        path: '/css'],
		js:     [name: 'Javascript', path: '/js'],
		images: [name: 'Images',     path: '/images'],
		views:  [name: 'Views',      path: '/WEB-INF/grails-app/views']
	]
	private static final Map DEFAULT_USERS = [
		admin: [username: 'admin', password: '21232f297a57a5a743894a0e4a801fc3', name: 'Admin1']
	]

	def beforeInterceptor = [action: this.&auth, except: 'login']

	// defined with private scope, so it's not considered an action
	private auth() {
		if (!session.wfadmin_user) {
			redirect(action: 'login')
			return false
		}
	}

	def afterInterceptor = { model ->
		model.paths = getPath()
		model.root = servletContext.getRealPath('')
	}

	private getPath(path) {
		def paths = grailsApplication.config.wfadmin.paths ?: DEFAULT_PATHS

		if(path) {
			return paths.containsKey(path) ? paths."$path" : null
		}
		return paths
	}

	private getUser(username, password) {
		def users = grailsApplication.config.wfadmin.users ?: DEFAULT_USERS
		//println users
		//println password.encodeAsMD5()
		def user
		if(users) {
			user = users.find { it.value.username == username && it.value.password == password.encodeAsMD5() }.value
		}
		return user
	}

	def login(String username, String password) {
		if(!username || !password) {
			return
		}

		def user = getUser(username, password)
		if(user) {
			//println user
			session.wfadmin_user = [username: user.username, name: user.name]
			redirect uri: "/wfadmin"
		}
	}

	def logout() {
		session.removeAttribute('wfadmin_user')
		redirect action: 'login'
	}

	def index(String path, String file) {
		def _path = getPath(path)
		if(!_path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		if(!path) {
			return
		}

		def _root = servletContext.getRealPath('')
		def _file = new File("$_root$_path.path", file ?: '')

		if(!_file.exists()) {
			render view:'showfiles', model: [error: "The file \"${file ?: '/'}\" does not exist", path: _path]
		} else if(_file.isDirectory()) {
			render view:'showfiles', model: [path: _path, file: _file, files: _file.listFiles()]
		} else {
			render view:'editfile', model: [path: _path, file: _file]
		}
	}

	def delete(String path, String file) {
		if(!path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _path = getPath(path)
		if(!_path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _root = servletContext.getRealPath('')

		if(!file) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.', path: _path]
			return
		}

		def _file = new File("$_root$_path.path", file)
		if(!_file.exists()) {
			render view: 'showfiles', model: [error: "The file \"$file\" does not exist", path: _path]
			return
		}

		// println 'delete'
		_file.delete()
		render 'success'
	}

	def create(String path, String file) {

		if(!path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _path = getPath(path)
		if(!_path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _root = servletContext.getRealPath('')
		def _file = new File("$_root$_path.path", file)
		if(!_file.exists()) {
			render view:'showfiles', model: [error: "The file \"$file\" does not exist", path: _path]
			return
		}

		render view:'editfile', model: [path: _path]
	}

	def save(String path, String file, String filename) {
		if(!path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _path = getPath(path)
		if(!_path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _root = servletContext.getRealPath('')
		def _file = new File("$_root$_path.path", file)
		if(!_file.exists()) {
			render view:'showfiles', model: [error: "The file \"$file\" does not exist", path: _path]
			return
		}

		if(_file.isDirectory()) {
			_file = new File(_file.path, filename)
		}

		_file.withWriter { it.write(params.content) }

		render 'Save Completed.'
	}

	def newfolder(String path, String file, String dirname) {
		if(!path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _path = getPath(path)
		if(!_path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _root = servletContext.getRealPath('')
		def _file = new File("$_root$_path.path/$file/$dirname")
		if(!_file.exists()) {
			_file.mkdirs()
		}

		redirect uri: "/wfadmin/$path", params: [file: file]
	}

	def upload(String path, String file) {
		if(!path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _path = getPath(path)
		if(!_path) {
			render view:'showfiles', model: [error: 'This path does not exist, or is not a directory.']
			return
		}

		def _root = servletContext.getRealPath('')

		try {
			def uploadFile = request.multipartFiles.uploadfile[0]
			if( uploadFile && !uploadFile.empty ) {
				//println uploadFile.originalFilename
				uploadFile.transferTo(new File("$_root$_path.path$file", uploadFile.originalFilename))
			}
			//println request.requestUri
			redirect uri: "/wfadmin/$path", params: [file: file]
		}
		catch(e) {
			render view:'showfiles', model: [error: "Upload error. $e.message", path: _path]
		}
	}
}
