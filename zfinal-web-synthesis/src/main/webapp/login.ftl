<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" href="${request.contextPath}/favicon.ico"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/toastr/toastr.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/animate.min.css">
    <script src="${request.contextPath}/resources/lib/jquery/jquery.min.js"></script>
    <script src="${request.contextPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="${request.contextPath}/resources/lib/aes/aes_1.js"></script>
    <script src="${request.contextPath}/resources/lib/toastr/toastr.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.particleground.min.js"></script>
    <script src="${request.contextPath}/resources/js/utils.js"></script>
    <script src="${request.contextPath}/resources/js/codec.js"></script>
    <script>
        if (top._main != null) {
            top.location.href = "${projectUrl?if_exists}";
        }
        
        function login() {
			if ($('#username').val() == '') {
				top.toastrinfo('用户名不能为空');
				return;
			}
			if ($('#password').val() == '') {
				top.toastrinfo('密码不能为空');
				return;
			}
			if ($('#yzCode').val() == '') {
				top.toastrinfo('验证码不能为空');
				return;
			}
			$('#password').val(aes($('#password').val()));
			$('#f').submit();
		}
        
        $(function(){
        	<#--
        		$("body").particleground({
	        		dotColor: '#367fa9',
	    			lineColor: '#3c8dbc'
	        	});
        	-->
        	
        	reYzCode();
        	
        	$(document).keydown(function(e){
				if(e.keyCode==13){
				   login();
				}
			});
        });
        
        function reYzCode(){
        	$("#yzCodeImg").attr('src','${request.contextPath}/admin/kaptcha?d=' + new Date());
        }
        
    </script>
</head>
<body class="hold-transition login-page">
<div class="login-box" <#--style="margin-top:-35%"-->>
    <div class="login-logo animated bounceInDown">
        <a>市民卡综合内网系统</a>
    </div>
    <div class="login-box-body">
        <p class="login-box-msg">请登录你的账户</p>
    <#if errorInfo?exists>
        <div class="callout callout-danger ">
            <p>
            ${errorInfo?if_exists}
            </p>
        </div>
    </#if>
        <form id="f" action="${request.contextPath}/admin/goLogin" method="post">
            <div class="form-group has-feedback">
                <input type="text" id="username" name="username" class="form-control" placeholder="用户名">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="password" name="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
		        <div class="col-xs-8">
		          <input type="text" id="yzCode" name="yzCode" class="form-control" placeholder="验证码">
		        </div>
		        <div class="col-xs-4">
		          <img id="yzCodeImg"  width="100%" height="34" onclick="reYzCode();" style="cursor:pointer;"/>
		        </div>
		    </div>
            <div class="row" style="margin-top:10px;">
                <div class="col-xs-8">
                </div>
                <div class="col-xs-4">
                    <button type="button" id="loginBtn" class="btn btn-primary btn-block btn-flat" onclick="login()">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
