# Web File Admin
This is a Grails plugin. Edit your web files(eg. js/css/html) online. You can create new folder , new file or upload an exists file from your local disk.

## Install
To install the latest stable version of the plugin add this to your `BuildConfig.groovy` in the plugins section (be sure to use the latest version):

    compile ":webfileadmin:0.2"

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
    }

#### Add your custom path
Copy the default config section into your `Config.groovy`, then edit the `paths` section.

    js {
      name = 'Javascript'
      path = '/js'        // should be a folder path in `web-app` or in your WAR file.
    }

#### Add your custom local users
By default, you can open `/wfadmin` without login. It means you can use any other security plugin to control your web authority. Also we have simple local username and password, we do NOT recommend to use it in production. if you want to use it, edit Config.groovy, and add some config like below:

    wfadmin {
      // in 'wfadmin' section, add below lines
      auth.useLocalData = true
      users {
        user1 {
          username = 'USERNAME1'
          password = 'SHA1 of password' //  SHA1 of 'admin'
          name = 'USER1 NAME'
        }
        user2 {
          username = 'USERNAME2'
          password = 'SHA1 of password' //  SHA1 of 'admin'
          name = 'USER2 NAME'
        }
      }
    }

See docs at http://grails.org/plugin/webfileadmin
