<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>500</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" href="${request.contextPath}/favicon.ico"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/pace/themes/orange/pace-theme-loading-bar.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/skins/skin-blue.min.css">
    <script src="${request.contextPath}/resources/lib/jquery/jquery.min.js"></script>
    <script src="${request.contextPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="${request.contextPath}/resources/lib/pace/pace.min.js"></script>
    <script src="${request.contextPath}/resources/lib/adminlte/js/app.min.js"></script>
    <script>
        function back() {
            top.window.location.href = '${request.contextPath}';
        }
    </script>
</head>
<body style="background-color: #ecf0f5;">
<section class="content">
    <div class="error-page">
        <h2 class="headline text-red">500</h2>
        <div class="error-content">
            <h3><i class="fa fa-warning text-red"></i> 发生错误啦!</h3>
            <p style="margin-top: 30px;color: #dd4b39; font-size: 18px;font-weight: bold;">
                ${errMessage?if_exists}
            </p>
            <p style="margin-top: 50px;">
                <button type="button" class="btn btn-block btn-primary btn-flat" style="width: 50%;" onclick="back();">
                    返 回 主 页
                </button>
            </p>
        </div>
    </div>
</section>
</body>
</html>