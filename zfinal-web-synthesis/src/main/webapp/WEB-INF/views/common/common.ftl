<#macro page title="" subTitle="">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${title?if_exists}</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" href="${request.contextPath}/favicon.ico"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/pace/pace.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/animate.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/toastr/toastr.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/iCheck/flat/red.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/skins/skin-blue.min.css">
    <script>
        _main = this;
        _projectUrl = "${projectUrl?if_exists}";
        _contextPath = "${request.contextPath?if_exists}";
        _servletPath = "${servletPath?if_exists}";
        _isSupperAdmin = 0;
        <@shiro.hasRole name="ROLE_SUPPERADMIN">
            _isSupperAdmin = 1;
        </@shiro.hasRole>
    </script>
    <script src="${request.contextPath}/resources/lib/sockjs.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.base64.js"></script>
    <script src="${request.contextPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="${request.contextPath}/resources/lib/pace/pace.min.js"></script>
    <script src="${request.contextPath}/resources/lib/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${request.contextPath}/resources/lib/fastclick/fastclick.js"></script>
    <script src="${request.contextPath}/resources/lib/toastr/toastr.min.js"></script>
    <script src="${request.contextPath}/resources/lib/iCheck/icheck.min.js"></script>
    <script src="${request.contextPath}/resources/lib/layer/layer.js"></script>
    <script src="${request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script>
    <script src="${request.contextPath}/resources/lib/json2.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.form.min.js"></script>
    <script src="${request.contextPath}/resources/lib/adminlte/js/app.min.js"></script>
    <script src="${request.contextPath}/resources/js/utils.js"></script>
    <script src="${request.contextPath}/resources/js/common.js"></script>
    <script src="${request.contextPath}/resources/js/message.js"></script>
    <script type="text/javascript">
        $(document).ajaxStart(function () {
            Pace.restart();
        });
    </script>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <a class="logo">
            <span class="logo-mini">SMK</span>
            <span class="logo-lg">市民卡综合内网系统</span>
        </a>
        <nav class="navbar navbar-static-top">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"></a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown notifications-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bell-o animated swing"></i>
                            <span class="label label-warning" name="messageSize">0</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">你有条 <span name="messageSize">0</span> 未读消息</li>
                            <li>
                                <ul class="menu" id="messageUl"></ul>
                            </li>
                            <li class="footer"><a href="javascript:showMessageList();">显示全部</a></li>
                        </ul>
                    </li>
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <#if userInfo.headPortraitFilePath?exists && userInfo.headPortraitFilePath != "">
		                		<img src="${filesUrl?if_exists}${userInfo.headPortraitFilePath?if_exists}" class="user-image" >
		                		<#else>
		                		<img src="${request.contextPath}/resources/img/default_user.jpg" class="user-image" >
		                	</#if>
                            <span class="hidden-xs">${userInfo.name?if_exists}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="user-header">
                            	<#if userInfo.headPortraitFilePath?exists && userInfo.headPortraitFilePath != "">
                            		<img src="${filesUrl?if_exists}${userInfo.headPortraitFilePath?if_exists}" class="img-circle" >
                            		<#else>
                            		<img src="${request.contextPath}/resources/img/default_user.jpg" class="img-circle" >
                            	</#if>
                                <p>
                                    ${userInfo.name?if_exists} - <span title="${roleInfo?if_exists}">${roleInfo?split(',')[0]?if_exists}..</span>
                                </p>
                            </li>
                            <li class="user-footer">
                            	<div class="pull-left">
                                    <a href="javascript:alterPwd();" class="btn btn-default btn-flat">修改密码</a>
                                </div>
                                <div class="pull-right">
                                    <a href="${request.contextPath}/admin/logout" class="btn btn-default btn-flat">退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- =============================================== -->

    <aside class="main-sidebar">
        <section class="sidebar">
            <div class="user-panel">
                <div class="pull-left image">
                	<#if userInfo.headPortraitFilePath?exists && userInfo.headPortraitFilePath != "">
                		<img src="${filesUrl?if_exists}${userInfo.headPortraitFilePath?if_exists}" class="img-circle" style="height:45px;">
                		<#else>
                		<img src="${request.contextPath}/resources/img/default_user.jpg" class="img-circle" style="height:45px;">
                	</#if>
                </div>
                <div class="pull-left info">
                    <p>${userInfo.name?if_exists}</p>
                    <a title="${roleInfo?if_exists}">
                        ${roleInfo?split(',')[0]?if_exists}..
                    </a>
                </div>

            </div>
            <ul class="sidebar-menu" id="menuUl">
                <li class="header">菜单</li>
            </ul>
        </section>
    </aside>

    <!-- =============================================== -->

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                ${title?if_exists}
                <small>${subTitle?if_exists}</small>
            </h1>
            <ol class="breadcrumb" id="breadcrumbOl">
                <li><a><i class="fa fa-list"></i> 菜单</a></li>
            </ol>
        </section>

        <section class="content">
            <#nested/>
        </section>
    </div>

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
        </div>
        <strong>Copyright &copy; 2017 <a>Zhoubl</a>.</strong>
    </footer>

    <div class="control-sidebar-bg"></div>
</div>


</body>
</html>
</#macro>