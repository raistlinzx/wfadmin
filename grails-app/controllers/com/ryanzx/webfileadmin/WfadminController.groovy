package com.ryanzx.webfileadmin
import java.security.MessageDigest

class WfadminController {
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
	def grailsApplication

	private def getPath(def path) {
		def paths = grailsApplication.config.wfadmin.paths?:[css:[name:'CSS', path:'/css'], js:[name:'Javascript', path:'/js'], images:[name:'Images', path:'/images'], views:[name:'Views', path:'/WEB-INF/grails-app/views']]
		
		if(path) {
			return paths.containsKey(path)?paths."${path}":null
		}
		return paths
	}

	private def getUser(def username, def password) {
		def users = grailsApplication.config.wfadmin.users?:[admin:[username:'admin',password:'d033e22ae348aeb5660fc2140aec35850c4da997',name:'Admin1']]
		//println users
		//println password.encodeAsMD5()
		def messageDigest = MessageDigest.getInstance('SHA1')
		messageDigest.update(password.getBytes())
		def passwordSha1Hex = new BigInteger(1, messageDigest.digest()).toString(16).padLeft( 40, '0' )

		def user = null
		if(users) {
			user = users.find { it.value.username == username && it.value.password == passwordSha1Hex }?.value
		}
		return user
	}

	def login = {
		if(params.username && params.password) {
			def user = getUser(params.username, params.password)
			if(user) {
				//println user
				session.wfadmin_user=[useranme: user.username, name: user.name]
				redirect uri: "/wfadmin"
			}

		}
	}

	def logout = {
		session.wfadmin_user = null
		redirect action: 'login'
	}

	def index = {
		def _path = getPath(params.path)
		if(!_path) {
			render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
			return
		}
		if(params.path) {
			def _root = servletContext.getRealPath('')
			def _file = new File("${_root}${_path.path}/${params.file?:''}")

			if(!_file.exists()) {
				render view:'showfiles', model: [error: "The file \"${params.file?:'/'}\" is not exists", path: _path]
			} else if(_file.isDirectory()) {
				render view:'showfiles', model: [path: _path, file: _file, files: _file.listFiles()]
			} else {
				render view:'editfile', model: [path: _path, file: _file]
			}
		}
	}


	def delete = {
		if(params.path) {
			def _path = getPath(params.path)
			if(!_path) {
				render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
				return
			}
			def _root = servletContext.getRealPath('')
			
			if(params.file) {
				def _file = new File("${_root}${_path.path}/${params.file}")
				if(!_file.exists()) {
					render view:'showfiles', model: [error: "The file \"${params.file}\" is not exists", path: _path]
				} else {
					// println 'delete'
					_file.delete()
					render 'success'
				} 
			} else {
				render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.', path: _path]
			}
		} else {
			render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
		}
	}

	def create = {
		
		if(params.path) {
			def _path = getPath(params.path)
			if(!_path) {
				render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
				return
			}

			def _root = servletContext.getRealPath('')
			def _file = new File("${_root}${_path.path}/${params.file}")
			if(!_file.exists()) {
				render view:'showfiles', model: [error: "The file \"${params.file}\" is not exists", path: _path]
			} else {
				render view:'editfile', model: [path: _path]
			} 
		
		} else {
			render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
		}
	}

	def save = {
		if(params.path) {
			def _path = getPath(params.path)
			if(!_path) {
				render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
				return
			}

			def _root = servletContext.getRealPath('')
			def _file = new File("${_root}${_path.path}/${params.file}")
			if(!_file.exists()) {
				render view:'showfiles', model: [error: "The file \"${params.file}\" is not exists", path: _path]
			} else {
			
				if(_file.isDirectory()) {
					_file = new File("${_file.getPath()}/${params.filename}")
				}
				
				def printWriter = _file.newPrintWriter()
				printWriter.write(params.content)
				printWriter.flush()
				printWriter.close()
				
				render 'Save Completed.'
			
			} 
		
		} else {
			render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
		}
	}


	def newfolder = {
		if(params.path) {
			def _path = getPath(params.path)
			if(!_path) {
				render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
				return
			}

			def _root = servletContext.getRealPath('')
			def _file = new File("${_root}${_path.path}/${params.file}/${params.dirname}")
			if(!_file.exists()) {
				_file.mkdir()
			} 
			
			redirect uri: "/wfadmin/${params.path}", params: [file: params.file]
		} else {
			render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
		}
	}


	def upload = {
		if(params.path) {
			def _path = getPath(params.path)
			if(!_path) {
				render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
				return
			}

			def _root = servletContext.getRealPath('')

			try {
				def file = request.multipartFiles.uploadfile[0]
                if( file && !file.empty )  
                {
                	//println file.originalFilename
                    file.transferTo(new File("${_root}${_path.path}${params.file}/${file.originalFilename}"))
                }
                //println request.requestUri
                redirect uri: "/wfadmin/${params.path}", params: [file: params.file]
			}
			catch(Exception e) {
				render view:'showfiles', model: [error: "Upload error. ${e.message}", path: _path]
			}
		} else {
			render view:'showfiles', model: [error: 'This path is not exists, or is not a directory.']
		}
	}
}