<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><g:layoutTitle default="Web File Admin"/></title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <link href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="http://cdn.bootcss.com/ionicons/1.5.2/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${request.contextPath}/css/webfileadmin/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="${request.contextPath}/wfadmin/" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Web File Admin
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">

                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>${session.wfadmin_user.name?:'Admin'} <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                    <img src="${request.contextPath}/images/webfileadmin/avatar3.png" class="img-circle" alt="User Image" />
                                    <p>
                                        ${session.wfadmin_user.name?:'Admin'}
                                        <small>Member since Nov. 2012</small>
                                    </p>
                                </li>

                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-right">
                                        <a href="${request.contextPath}/wfadmin/logout" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="${request.contextPath}/images/webfileadmin/avatar3.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, ${session.wfadmin_user.name?:'Admin'}</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li>
                            <a href="${request.contextPath}/wfadmin/">
                                <i class="fa fa-dashboard"></i> <span>Welcome</span>
                            </a>
                        </li>
                        <g:each var='_path' in="${paths}">
                        <li>
                            <a href="${request.contextPath}/wfadmin/${_path.key}">
                                <i class="fa fa-folder"></i> <span>${_path.value.name}</span>
                                <i class="fa fa-angle-right pull-right"></i>
                            </a>
                        </li>
                        </g:each>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <g:layoutBody/>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        <script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.bootcss.com/bootstrap/3.3.1/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="http://cdn.bootcss.com/jqueryui/1.11.1/jquery-ui.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${request.contextPath}/js/webfileadmin/AdminLTE/app.js" type="text/javascript"></script>

        <script src="${request.contextPath}/js/webfileadmin/control.js" type="text/javascript"></script>

    </body>
</html>
