log4j = {
	error 'org.codehaus.groovy.grails',
	      'org.springframework',
	      'org.hibernate',
	      'net.sf.ehcache.hibernate'
}

wfadmin {
  paths {
    css {
      name = 'CSS'
      path = '/css'
    }

    images {
      name = 'Images'
      path = '/images'
    }

    js {
      name = 'Javascript'
      path = '/js'
    }

    views {
      name = 'Views'
      path = '/WEB-INF/grails-app/views'
    }
  }
  users {
    admin {
      username = 'admin'
      password = 'd033e22ae348aeb5660fc2140aec35850c4da997' //  SHA1 of 'admin'
      name = 'Admin'
    }
  }
}
