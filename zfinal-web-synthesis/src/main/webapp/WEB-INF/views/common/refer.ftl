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
        .center{
            text-align: center;
        }
        .odd{
            background-color:#E8E8E8;
        }
    </style>
    <script type="text/javascript">

        titleUrl = rootUrl + "getReferTitleJson?listJsonFileName=${listJsonFileName?if_exists}";
        dataUrl = rootUrl + "getReferJson";

        $(function () {
        });

        function initDataSuccess() {
            tips("勾选数据后点击确认","#submitBtn");
            tips("点击清空已选数据","#clearBtn");
        }

        function tips(content,e) {
            layer.tips(content, e,{
                tips: [2, '#3c8dbc'],
                time: 0,
                tipsMore: true,
                zIndex : 55555
            });
        }

        function lookData(id,index) {
            submit(index);
        }

        function submit(index) {
            var eIds = [];
            var arr = new Array();
            if("${eIds?if_exists}".indexOf(',') != -1){
                eIds = "${eIds?if_exists}".split(',');
            }else{
                eIds.push( "${eIds?if_exists}");
            }
            var nTr = oTable.row(index);
            for(var i=0; i<eIds.length; i++){
                if(eIds[i]!=null && eIds[i]!=''){
                    var vals = "," + nTr.data()[i+1];
                    vals = vals.substring(1);
                    try{
                        parent.document.getElementById(eIds[i]).value = vals;
                    }catch(e){}
                    arr[i] = vals;
                }else{
                    arr[i] = null;
                }
            }

            <#if fn?exists && fn != "">
                parent.${fn?if_exists}(arr);
            </#if>

            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        function submitM() {
            var eIds = [];
            var arr = new Array();
            if("${eIds?if_exists}".indexOf(',') != -1){
                eIds = "${eIds?if_exists}".split(',');
            }else{
                eIds.push( "${eIds?if_exists}");
            }
            var nTrs = oTable.rows().nodes();
            for(var i=0; i<eIds.length; i++){
                if(eIds[i]!=null && eIds[i]!=''){
                    var vals = '';
                    for(var j = 0; j < nTrs.length; j++){
                        if($(nTrs[j]).find('input[name="ids"]:checked').length > 0){
                            vals += "," + oTable.row(nTrs[j]).data()[i+1];
                        }
                    }
                    vals = vals.substring(1);
                    try{
                        parent.document.getElementById(eIds[i]).value = vals;
                    }catch(e){}
                    arr[i] = vals;
                }else{
                    arr[i] = null;
                }
            }

            <#if fn?exists && fn != "">
                parent.${fn?if_exists}(arr);
            </#if>

            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        function clearData(){
            var eIds = [];
            var arr = new Array();
            if("${eIds?if_exists}".indexOf(',') != -1){
                eIds = "${eIds?if_exists}".split(',');
            }else{
                eIds.push("${eIds?if_exists}");
            }
            for(var i=0; i<eIds.length; i++){
                if(eIds[i]!=null && eIds[i]!=''){
                    try{
                        parent.document.getElementById(eIds[i]).value = '';
                    }catch(e){}
                    arr[i] = '';
                }else{
                    arr[i] = null;
                }
            }

            <#if fn?exists && fn != "">
                parent.${fn?if_exists}(arr);
            </#if>

            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

    </script>
</head>
<body style="background-color: #ecf0f5;">
    <section class="content">
        <div class="box box-success ">
            <div class="box-header with-border">
                <h3 class="box-title" style="font-weight: bold;"><i class="fa fa-search-plus" style="margin-right: 5px;"></i>查询</h3>

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
                <h3 class="box-title" style="font-weight: bold;"><i class="fa fa-list" style="margin-right: 5px;"></i>列表</h3>
                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                            title="">
                        <i class="fa fa-minus"></i></button>
                </div>
            </div>
            <div class="box-body">
                <#if multiple?exists && multiple!="0">
                    <button id="submitBtn" type="button" class="btn btn-primary btn-sm"  onclick="submitM();">
                        <i class="fa fa-check"></i>
                        确认选择
                    </button>
                    <#else>
                    <button id="clearBtn" type="button" class="btn btn-danger btn-sm" onclick="clearData();">
                        <i class="fa fa-remove"></i>
                        清空选择
                    </button>
                </#if>
                <form method="post">
                    <table class="table table-striped table-bordered" style="height:100%;" id="tables"></table>
                    <input type="hidden" name="dataTableData" id="dataTableData"/>
                    <input type="hidden" name="queryStatement" id="queryStatement"/>
                    <input type="hidden" name="listJsonFileName" value="${listJsonFileName?if_exists}"/>
                    <input type="hidden" name="listJsonQueryName" value="${listJsonQueryName?if_exists}"/>
                    <input type="hidden" name="p" value="${p?if_exists}"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>