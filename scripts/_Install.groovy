ant.mkdir(dir:"$basedir/web-app/js/webfileadmin/AdminLTE")

ant.copy(file: "$pluginBasedir/web-app/js/webfileadmin/control.js", todir:"$basedir/web-app/js/webfileadmin")
ant.copy(file: "$pluginBasedir/web-app/js/webfileadmin/AdminLTE/app.js", todir:"$basedir/web-app/js/webfileadmin/AdminLTE")

ant.mkdir(dir:"$basedir/web-app/css/webfileadmin")
ant.copy(file: "$pluginBasedir/web-app/css/webfileadmin/AdminLTE.css", todir:"$basedir/web-app/css/webfileadmin")

ant.mkdir(dir:"$basedir/web-app/images/webfileadmin")
ant.copy(file: "$pluginBasedir/web-app/images/webfileadmin/avatar3.png", todir:"$basedir/web-app/images/webfileadmin")
