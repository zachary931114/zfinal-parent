<#macro page title="">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" href="${request.contextPath}/favicon.ico"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/pace/themes/orange/pace-theme-loading-bar.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/datatables/media/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${request.contextPath}/resources/lib/adminlte/css/skins/skin-blue.min.css">
    <script>
        _title = "${title?if_exists}";
        _listJsonFileName = "${listJsonFileName?if_exists}";
        _contextPath = "${request.contextPath?if_exists}";
        _servletPath = "${servletPath?if_exists}";
    </script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.min.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.base64.js"></script>
    <script src="${request.contextPath}/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="${request.contextPath}/resources/lib/pace/pace.min.js"></script>
    <script src="${request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script>
    <script src="${request.contextPath}/resources/lib/jquery/jquery.form.min.js"></script>
    <script src="${request.contextPath}/resources/lib/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="${request.contextPath}/resources/lib/datatables/media/js/dataTables.bootstrap.min.js"></script>
    <script src="${request.contextPath}/resources/lib/layer/layer.js"></script>
    <script src="${request.contextPath}/resources/js/datatables.js"></script>
    <script src="${request.contextPath}/resources/lib/adminlte/js/app.min.js"></script>
    <style>
        .center {
            text-align: center;
        }

        .odd {
            background-color: #E8E8E8;
        }
    </style>
    <script type="text/javascript">
        $(function () {
        });

        function addData() {
            openWin("add", _title + "新增");
        }

        function editData(id) {
            openWin("edit?ids=" + id, _title + "编辑");
        }

        function openWin(url, title) {
            var index = layer.open({
                type: 2,
                title: title,
                shadeClose: true,
                shade: false,
                zIndex: 666666,
                maxmin: false, // 开启最大化最小化按钮
                area: ['95%', '95%'],
                content: url
            });
        }

        function closeLayer(index) {
            layer.close(index);
        }

        function closesWin(name) {
            var index = layer.getFrameIndex(name); // 先得到当前iframe层的索引
            closeLayer(index); // 再执行关闭
        }
    </script>
</head>
<body style="background-color: #ecf0f5;">
<form method="post">
    <section class="content">
        <div class="box box-success " id="searchBox">
            <div class="box-header with-border">
                <h3 class="box-title" style="font-weight: bold;"><i class="fa fa-search-plus"
                                                                    style="margin-right: 5px;"></i>查询</h3>

                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                            title="">
                        <i class="fa fa-minus"></i></button>
                </div>
            </div>
            <div class="box-body" style="">
                <div id="searchPanel" class="row" style="padding: 5px;text-align:right;">
                </div>
            </div>
            <div class="box-footer" style="">
                <div class="row" style="text-align:center;">
                    <button type="button" class="btn btn-primary" style="width: 10%;" onclick="searchData();">
                        <i class="fa fa-search-plus"></i>
                        查询
                    </button>
                    <button type="button" class="btn btn-warning" style="width: 10%;" onclick="resetSearch();">
                        <i class="fa fa-refresh"></i>
                        重置
                    </button>
                </div>
            </div>
        </div>
        <div class="box box-primary box-solid">
            <div class="box-header with-border">
                <h3 class="box-title" style="font-weight: bold;"><i class="fa fa-list" style="margin-right: 5px;"></i>列表
                </h3>
                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                            title="">
                        <i class="fa fa-minus"></i></button>
                </div>
            </div>
            <div class="box-body">
                <div style="margin-bottom: 15px;" id="btnDIV">
                    <button id="addBtn" type="button" class="btn btn-primary" style="width: 8%" onclick="addData();">
                        <i class="fa fa-plus"></i>
						新建
                    </button>
                    <button id="deleteBtn" type="button" class="btn btn-danger" style="width: 8%"
                            onclick="deleteDatas();">
                        <i class="fa fa-remove"></i>
						删除
                    </button>
                    <button id="startBtn" type="button" class="btn btn-success" style="width: 8%" onclick="start();">
                        <i class="fa fa-check"></i>
						启用
                    </button>
                    <button id="stopBtn" type="button" class="btn btn-danger" style="width: 8%" onclick="stop();">
                        <i class="fa fa-close"></i>
						停用
                    </button>
                    <button id="exportBtn" type="button" class="btn btn-primary" style="width: 8%" onclick="showExport();">
	                    <i class="fa fa-download"></i>
	                  	 导出
	                </button>
                    <#nested/>
                </div>
                <table class="table table-striped table-bordered" style="height:100%;" id="tables"></table>
                <input type="hidden" name="dataTableData" id="dataTableData"/>
	            <input type="hidden" name="queryStatement" id="queryStatement"/>
	            <input type="hidden" name="listJsonFileName" id="listJsonFileName" value="${listJsonFileName?if_exists}"/>
	            <input type="hidden" name="listJsonQueryName" id="listJsonQueryName" value="${listJsonQueryName?if_exists}"/>
	            <input type="hidden" name="listJsonSqlCondition" id="listJsonSqlCondition" value="${listJsonSqlCondition?if_exists}"/>
            </div>
        </div>
    </section>
</form>
</body>
</html>
</#macro>