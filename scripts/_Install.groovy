//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//
ant.mkdir(dir:"${basedir}/web-app/js/webfileadmin")
ant.mkdir(dir:"${basedir}/web-app/js/webfileadmin/AdminLTE")

ant.copy(file: "${pluginBasedir}/web-app/js/webfileadmin/control.js", todir:"${basedir}/web-app/js/webfileadmin")
ant.copy(file: "${pluginBasedir}/web-app/js/webfileadmin/AdminLTE/app.js", todir:"${basedir}/web-app/js/webfileadmin/AdminLTE")

ant.mkdir(dir:"${basedir}/web-app/css/webfileadmin")
ant.copy(file: "${pluginBasedir}/web-app/css/webfileadmin/AdminLTE.css", todir:"${basedir}/web-app/css/webfileadmin")

ant.mkdir(dir:"${basedir}/web-app/images/webfileadmin")
ant.copy(file: "${pluginBasedir}/web-app/images/webfileadmin/avatar3.png", todir:"${basedir}/web-app/images/webfileadmin")