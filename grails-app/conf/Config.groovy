// configuration for plugin testing - will not be included in the plugin zip

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
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
      password = '21232f297a57a5a743894a0e4a801fc3' //  MD5 of 'admin'
      name = 'Admin'
    }
    user {
      username = 'user'
      password = '21232f297a57a5a743894a0e4a801fc3' //  MD5 of 'admin'
      name = 'User1'
    }
  }
}