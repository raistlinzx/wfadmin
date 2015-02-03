# Web File Admin
This is a Grails plugin. Edit your web files(eg. js/css/html) online. You can create new folder , new file or upload an exists file from your local disk.

## Install
To install the latest stable version of the plugin add this to your `BuildConfig.groovy` in the plugins section (be sure to use the latest version):

    compile ":webfileadmin:0.1"

## Using
     http://localhost:8080/YOUR_PROJECT_NAME/wfadmin


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
      users {
        admin {
          username = 'admin'
          password = '21232f297a57a5a743894a0e4a801fc3' //  MD5 of 'admin'
          name = 'Admin'
        }
      }
    }

#### Add your custom path
Copy the default config section into your `Config.groovy`, then edit the `paths` section .

    js {
      name = 'Javascript'
      path = '/js'        // should be a folder path in `web-app` or in your WAR file.
    }

#### Add your custom users
    user {
      username = 'USERNAME'
      password = '21232f297a57a5a743894a0e4a801fc3' //  MD5 of 'admin'
      name = 'NAME'
    }

See docs at http://grails.org/plugin/webfileadmin
