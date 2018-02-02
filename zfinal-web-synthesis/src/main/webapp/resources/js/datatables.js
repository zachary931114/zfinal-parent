oTable = null;
dataTablesOption = null;
self_options = null;

rootUrl = "";
titleUrl = rootUrl + "getListTitleJson?listJsonFileName=" + _listJsonFileName;
dataUrl = rootUrl + "getListJson";

openTitle = "";
controller = "";

columnList = null;

$(function () {
    initTables();
});

function initTables() {
    $
        .post(
            titleUrl,
            {},
            function (datas) {
                if (datas.isSuccess) {
                    dataTablesOption = {
                        "sScrollY": "100%",
                        "sScrollX": "100%",
                        "sScrollXInner": datas.data.sumWidth,
                        "bAutoWidth": true,
                        "aoColumns": datas.data.columnList,
                        "fnDrawCallback": fnDrawCallback, // tbody回传重新绘制
                        "fnHeaderCallback": function (nHead, aasData,
                                                      iStart, iEnd, aiDisplay) {
                            $(nHead)
                                .find("th")
                                .eq(1)
                                .html(
                                    '<input type="checkbox" onclick="checkAll(this.checked)"/>');
                        }, // head回传重新绘制
                        "fnRowCallback": fnRowCallback_Ext, // row回传重新绘制
                        "fnInitComplete": fnInitComplete, // 数据加载表格构建完成之后执行
                        "bProcessing": true, // 加载数据时显示正在加载信息
                        "bServerSide": true, // 指定从服务器端获取数据
                        "bFilter": false, // 不使用过滤功能
                        "bLengthChange": false, // 用户不可改变每页显示数量
                        "iDisplayStart": parseInt(datas.data.iDisplayStart),// 初始化显示的首行数
                        "iDisplayLength": parseInt(datas.data.iDisplayLength),
                        "sAjaxSource": dataUrl, // 获取数据的url
                        "fnServerData": retrieveData, // 获取数据的处理函数
                        //"sDom" : 'T<"clear">lrtip', // <"top"ip>rt<"bottom"><"clear">lfrtip
                        "pagingType": "full_numbers",
                        "language": {
                            "sProcessing": "数据加载中，请稍等...",
                            "sLengthMenu": "每页显示 _MENU_条记录",
                            "sZeroRecords": "没有找到匹配的记录",
                            "sEmptyTable": "无数据内容",
                            "sInfo": "从第 _START_到第 _END_条数据;总共有 _TOTAL_条记录",
                            "sInfoEmpty": "从第 0 到第 0 条数据;总共有 0 条记录",
                            "oPaginate": {
                                "sFirst": "首页",
                                "sPrevious": "前一页",
                                "sNext": "后一页",
                                "sLast": "尾页",
                                "page": "页码",
                                "pageOf": "到"
                            }
                        },
                        "order": []
                    };

                    dataTablesOption = $.extend(true, {},
                        dataTablesOption, self_options);
                    oTable = $('#tables').DataTable(dataTablesOption);
                    columnList = datas.data.columnList;
                    initSearch();
                } else {
                    top.toastrerror(datas.message);
                }

            }, "json");
}

function initSearch() {
    $.each(columnList, function (index, value) {
        var i = index - 1;
        if (index > 1) {
            if (value.type == "Date") {
                var html = '<div class="col-xs-3" style="margin-bottom: 10px;" id="searchP'+value.name+'">';
                    html += '<div class="input-group">';
                    html += '<div class="input-group-btn"><button type="button" class="btn btn-primary">'+value.title+'</button></div>';
                    html += '<input type="text" name="'+ value.name + '" id="'+ value.name + '" onclick="WdatePicker({dateFmt:\''+ value.format+ '\'})" class="form-control Wdate" readonly>';
                    html += '</div>';
                    html += '</div>';
                    $("#searchPanel").append(html);
            }else if (value.type == "Select") {
                var html = '<div class="col-xs-3" style="margin-bottom: 10px;" id="searchP'+value.name+'">';
                html += '<div class="input-group">';
                html += '<div class="input-group-btn"><button type="button" class="btn btn-primary">'+value.title+'</button></div>';
                html += '<select class="form-control" name="'+ value.name + '" id="'+ value.name + '">';
                html += '<option value="">====请选择====</option>';
                if(value.keyValue != null && value.keyValue.length > 0){
                    for(var i=0; i<(value.keyValue.length)/2; i++){
                        html += '<option value="'+value.keyValue[i*2]+'">'+value.keyValue[i*2+1]+'</option>';
                    }
                }
                html += '</select>';
                html += '</div>';
                html += '</div>';
                $("#searchPanel").append(html);
            } else {
                var html = '<div class="col-xs-3" style="margin-bottom: 10px;" id="searchP'+value.name+'">';
                    html += '<div class="input-group">';
                    html += '<div class="input-group-btn"><button type="button" class="btn btn-primary">'+value.title+'</button></div>';
                    html += '<input type="text" name="'+ value.name + '" id="'+ value.name + '" class="form-control">';
                    html += '</div>';
                    html += '</div>';
                $("#searchPanel").append(html);
            }
        }
    });
    initSearchSuccess();
}

function initSearchSuccess() {
}

var fnRowCallback_Ext = function (nRow, aData, iRowCount, iDisplayIndex) {
    var id = aData[0];
    $('td:eq(1)', nRow).html(
        '<input type="checkbox" name="ids" value="' + id + '">');
    for (var i = 2; i < aData.length; i++) {
        $('td:eq(' + i + ')', nRow).html(
            '<a href="javascript:lookData(\'' + id + '\',\'' + iRowCount + '\');">' + aData[i]
            + '</a>');
    }
    return nRow;
}

function retrieveData(sSource, aoData, fnCallback) {
    $("#dataTableData").val($.param(aoData));
    $(document.forms[0]).ajaxSubmit({
        "type": "POST",
        "url": sSource,
        "dataType": "json",
        success: function (datas) {
            if (datas.isSuccess) {
                fnCallback(datas.data);
            } else {
                top.toastrerror(datas.message);
            }
        }
    });
}

function searchData() {
    var queryVal = '';
    $("#searchPanel input").each(
        function () {
            if ($(this).val() != '') {
                if ($(this).hasClass("Wdate")) {
                    var operator = "=";
                    if ($(this).attr("operator")) {
                        operator = $(this).attr("operator");
                    }
                    queryVal += " and str_to_date(date_format("
                        + $(this).prop("name")
                        + ",'%Y-%m-%d'),'%Y-%m-%d') " + operator + " str_to_date('"
                        + $(this).val() + "','%Y-%m-%d') ";
                } else {
                    if ($(this).attr("operator")) {
                        queryVal += " and " + $(this).prop("name") + " " + $(this).attr("operator") + " " + "'" + $(this).val() + "'";
                    } else {
                        queryVal += " and " + $(this).prop("name") + " like "
                            + "'%" + $(this).val() + "%'";
                    }
                }
            }
        });

    $("#searchPanel select").each(
        function () {
            if ($(this).val() != '') {
                queryVal += " and " + $(this).prop("name") + " like "
                    + "'%" + $(this).val() + "%'";
            }
        });

    if (queryVal != '') {
        queryVal = queryVal.substring(5);
    }

    $("#queryStatement").val($.base64.encode(queryVal));
    refresh(true);
}

function resetSearch() {
    $("#searchPanel input").each(function () {
        $(this).val("");
    });
    $("#searchPanel select").each(function () {
        $(this).val("");
    });
}

function updateSearch(name, html) {
    if (html != null) {
        document.getElementById("searchP" + name).innerHTML = html;
    }
}

function hideSearch(name) {
    if (name != null) {
        document.getElementById("searchP" + name).style.display = "none";
    }
}

function delSearch(name) {
    if (name != null) {
        var e = document.getElementById("searchP" + name);
        e.parentNode.removeChild(e);
    }
}

function cleanSearch() {
    $("#searchPanel div").remove();
}

function addSearch(name, html) {
    if (html != null) {
        $("#searchPanel").append('<div class="col-xs-3" style="margin-bottom: 10px;" id="searchP'+name+'"></div>');
        updateSearch(name, html);
    }
}

function hideColumn(index, isShow) {
    oTable.column(index).visible(isShow);
}

function fnInitComplete() {
    $('#tables tbody').on('dblclick', 'tr', function () {
        try {
            lookData(oTable.row(this).data()[0],oTable.row(this).index());
        } catch (e) {
        }
    });

    initDataSuccess();
}

function initDataSuccess() {
}

function refresh(flag) {
    oTable.draw(flag);
}

function fnDrawCallback(oSettings) {
    for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
        $(
            'td:eq(0)',
            oSettings.aoData[oSettings.aiDisplay[i]].nTr)
            .html(
                i
                + parseInt(oSettings._iDisplayStart)
                + 1);
    }
    refreshSuccess();
}

function refreshSuccess() {

}

function checkAll(flag) {
    $("input[name='ids']").prop('checked', flag);
}

function getIds() {
    return $('input[name="ids"]:checked', $('#tables')).serialize();
}

function hideAdd() {
    $("#addBtn").hide();
}
function hideEdit() {
    $("#editBtn").hide();
}
function hideDelete() {
    $("#deleteBtn").hide();
}
function hideStart() {
    $("#startBtn").hide();
}
function hideStop() {
    $("#stopBtn").hide();
}
function hideExport() {
    $("#exportBtn").hide();
}
function hideSearchBox() {
    $("#searchBox").hide();
}
function hideBtnDIV() {
    $("#btnDIV").hide();
}

function addData() {
    top.openWin("add", _title + "新增");
}

function lookData(id,index) {
    editData(id);
}

function editData(id) {
    top.openWin("edit?ids=" + id, _title + "编辑");
}

function lookDatas() {
    var ids = getIds();
    if (ids == "") {
        top.toastrinfo("至少选择一条数据！");
        return;
    }
    var idArr = ids.split("&");
    if (idArr.length > 1) {
        top.toastrinfo("只能选择一条数据！")
    } else {
        var id = ids.split("=")[1];
        lookData(id);
    }
}

function deleteDatas() {
    var ids = getIds();
    if (ids == "") {
        top.toastrinfo("至少选择一条数据！")
    } else {
        top.confirms("确认删除选中的数据吗？", {
            btn: ["确认", "取消"]
        }, function () {
            $.post(rootUrl + "delete?" + ids, {}, function (datas) {
                if (datas.isSuccess) {
                    top.toastrsuccess("删除成功！");
                    refresh(false);
                } else {
                    top.toastrerror(datas.message);
                    refresh(false);
                }
            }, "json");
        });
    }
}

function start(){
    var ids = getIds();
    if (ids == "") {
        top.toastrinfo("至少选择一条数据！")
    } else {
        top.confirms("确认启用选中的数据吗？", {
            btn : [ "确认", "取消" ]
        }, function() {
            $.post(rootUrl + "start?" + ids, {}, function(datas) {
                if (datas.isSuccess) {
                    top.toastrsuccess("启用成功！");
                    refresh(false);
                } else {
                    top.toastrerror(datas.message);
                    refresh(false);
                }
            }, "json");
        });
    }
}

function stop(){
    var ids = getIds();
    if (ids == "") {
        top.toastrinfo("至少选择一条数据！")
    } else {
        top.confirms("确认停用选中的数据吗？", {
            btn : [ "确认", "取消" ]
        }, function() {
            $.post(rootUrl + "stop?" + ids, {}, function(datas) {
                if (datas.isSuccess) {
                    top.toastrsuccess("停用成功！");
                    refresh(false);
                } else {
                    top.toastrerror(datas.message);
                    refresh(false);
                }
            }, "json");
        });
    }
}

function showExport(){
	var html = '<form id="exportF" target="_blank" method="post" action="export">';
		html += '<input type="hidden" name="title" id="titleExport"/>';
		html += '<input type="hidden" name="dataTableData" id="dataTableDataExport"/>';
		html += '<input type="hidden" name="queryStatement" id="queryStatementExport"/>';
		html += '<input type="hidden" name="listJsonFileName" id="listJsonFileNameExport"/>';
		html += '<input type="hidden" name="listJsonQueryName" id="listJsonQueryNameExport"/>';
		html += '<input type="hidden" name="listJsonSqlCondition" id="listJsonSqlConditionExport"/>';
		html += '<div style="text-align:center;margin-top:10px;">';
		html += '<p>';
		html += '<label for="exportStartNum" class="control-label" style="width:20%;text-align:right;">起始行数：</label>';
		html += '<input type="text" class="form-control" style="display:inline-block;width:40%;" name="exportStartNum" id="exportStartNum" placeholder="请输入..." value="0"/>';
		html += '</p>';
		html += '<p>';
		html += '<label for="exportEndNum" class="control-label" style="width:20%;text-align:right;">截止行数：</label>';
		html += '<input type="text" class="form-control" style="display:inline-block;width:40%;" name="exportEndNum" id="exportEndNum" placeholder="请输入..." value="20"/>';
		html += '</p>';
		html += '<hr/>';
		html += '<button type="button" class="btn btn-primary" style="width: 50%" onclick="exportDatas();"><i class="fa fa-download"></i>导出</button>';
		html += '</div>';
		html += '</form>';
	top.openWinByHtml("导出",html,"30%","35%");
}

function exportDatas(){
	if($('#exportStartNum').val() == ""){
		top.toastrinfo('请输入起始行数');
		return;
	}
	if($('#exportEndNum').val() == ""){
		top.toastrinfo('请输入截止行数');
		return;
	}
	$('#titleExport').val(_title);
	$('#dataTableDataExport').val($('#dataTableData').val());
	$('#queryStatementExport').val($('#queryStatement').val());
	$('#listJsonFileNameExport').val($('#listJsonFileName').val());
	$('#listJsonQueryNameExport').val($('#listJsonQueryName').val());
	$('#listJsonSqlConditionExport').val($('#listJsonSqlCondition').val());
	$('#exportF').submit();
}