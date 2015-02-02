# Web File Admin
This is a Grails plugin. Edit your web files(eg. js/css/html) online.

## Install
To install the latest stable version of the plugin add this to your `BuildConfig.groovy` in the plugins section (be sure to use the latest version):

    compile ":webfileadmin:0.1"

## Config
By default, this plugin will help you to edit css/js/images/views. (The views can be edited, only with command `run-war` or running in Tomcat after packaging your war file.)

#### Default Config
No need change your Config.groovy. This plugin will work using default config like below:

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
    }

#### Add your custom path
Copy the default config section into your `Config.groovy`, then edit the `paths` section .

    js {
      name = 'Javascript' 
      path = '/js'        // should be a folder path in `web-app` or in your WAR file.
    }


## Features in Next Version
- Login and Edit your files with a simple security filter.

See docs at http://grails.org/plugin/webfileadmin
