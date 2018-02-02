<#macro page>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/pace/themes/orange/pace-theme-loading-bar.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/animate.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/iCheck/flat/red.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/select2/dist/css/select2.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/webuploader/webuploader.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/skins/skin-blue.min.css">
    <script src="${request.contextPath}/resources/lib/jquery/jquery.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.base64.js"></script>
    <script src="${request.contextPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="${request.contextPath}/resources/lib/pace/pace.min.js"></script>
    <script src="${request.contextPath}/resources/lib/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${request.contextPath}/resources/lib/fastclick/fastclick.js"></script>
    <script src="${request.contextPath}/resources/lib/iCheck/icheck.min.js"></script>
    <script src="${request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script>
    <script src="${request.contextPath}/resources/lib/json2.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.form.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jqueryValidate/jquery.validate.js"></script>
    <script src="${request.contextPath}/resources/lib/jqueryValidate/additional-methods.js"></script>
    <script src="${request.contextPath}/resources/lib/jqueryValidate/localization/messages_zh.js"></script>
    <script src="${request.contextPath}/resources/lib/select2/dist/js/select2.min.js"></script>
    <script src="${request.contextPath}/resources/lib/select2/dist/js/i18n/zh-CN.js"></script>
    <script src="${request.contextPath}/resources/lib/webuploader/webuploader.min.js"></script>
    <script src="${request.contextPath}/resources/lib/layer/layer.js"></script>
    <script src="${request.contextPath}/resources/lib/aes/aes_1.js"></script>
    <script src="${request.contextPath}/resources/js/codec.js"></script>
    <script src="${request.contextPath}/resources/js/refer.js"></script>
    <script src="${request.contextPath}/resources/lib/adminlte/js/app.min.js"></script>
    <style>
        .errorColor{color:red;}
        .formInput{width: 70%;}
        .required{color:red;}
        .form-horizontal .form-group {
            margin-right: -15px;
            margin-left: -15px;
            min-height: 60px;
        }
        .refer {
            float: left;
        }
        .formText {
        	padding-top:7px;
        }
        .formRow {
        	width: 50%;
        	float:left;
        }
    </style>
    <script type="text/javascript">

        contextPath = "${request.contextPath}";

        $(document).ajaxStart(function () {
            Pace.restart();
        });

        $(function(){
        });
        
        function tips(content,e) {
            layer.tips(content, e,{
                tips: [2, '#3c8dbc'],
                time: 0,
                tipsMore: true,
                zIndex : 55555
            });
        }

        function closes(){
            if(parent._main != null){
                top.closesWin(window.name);
            }else{
                parent.closesWin(window.name);
            }

        }

        function refresh() {
            if(parent.searchData != null){
                parent.searchData();
            }
        }

        function closesAndRefresh(){
            refresh();
            closes();
        }

        var options = {
            beforeSerialize: beforeRequest,
            beforeSubmit:  showRequest,
            success: showResponse,
            type: "post",
            dataType:"json"
        };
        function beforeRequest($form, options) { return true; }
        function showRequest(arr, $form, options) {
            return true;
        }
        function showResponse(data, statusText,$form) {
            if(data.isSuccess){
                if(data.message == null || data.message == ""){
                    top.toastrsuccess("保存成功");
                }else{
                    top.toastrsuccess(data.message);
                }
                closesAndRefresh();
            }else{
                top.toastrerror(data.message);
            }
        }
        $.validator.setDefaults({
            meta: "validate",
            errorClass: "errorColor",
            submitHandler: function(form) {
                $(form).ajaxSubmit(options);
            }
        });

        Date.prototype.format = function(fmt)
        {
            var o = {
                "M+" : this.getMonth()+1,                 //月份
                "d+" : this.getDate(),                    //日
                "h+" : this.getHours(),                   //小时
                "m+" : this.getMinutes(),                 //分
                "s+" : this.getSeconds(),                 //秒
                "q+" : Math.floor((this.getMonth()+3)/3), //季度
                "S"  : this.getMilliseconds()             //毫秒
            };
            if(/(y+)/.test(fmt))
                fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            for(var k in o)
                if(new RegExp("("+ k +")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            return fmt;
        }

    </script>
</head>
<body style="background-color: #ecf0f5;">
    <section class="content">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title" style="font-weight: bold;"><i class="fa fa-table" style="margin-right: 5px;"></i></h3>
                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                            title="">
                        <i class="fa fa-minus"></i></button>
                </div>
            </div>
            <#nested/>
        </div>
    </section>
</body>
</html>
</#macro>