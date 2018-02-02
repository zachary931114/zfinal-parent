$(function() {
	initRefer();
});

function initRefer() {
	$(".refer").each(function() {
		var enabled = "1";
		if ($(this).attr("data-enabled")) {
			enabled = $(this).attr("data-enabled");
		}
		if (enabled == "1") {
			if ($(this).prop("type") == "text") {
				$(this).dblclick(function() {
					_openRefer(this);
				});
			} else {
				$(this).click(function() {
					_openRefer(this);
				});
			}
		}
	});
}

function _openRefer(referObj) {

	var fileName = null, queryName = null, e = null, p = null, fn = null, multiple = 0, type = 1;
	if ($(referObj).attr("data-multiple")) {
		multiple = $(referObj).attr("data-multiple");	//是否多选 0.单选 1.多选
	}
	if ($(referObj).attr("data-type")) {
		type = $(referObj).attr("data-type");		//类型
	}
	if ($(referObj).attr("data-fileName")) {
        fileName = $(referObj).attr("data-fileName");	//文件名称
	}
	if ($(referObj).attr("data-queryName")) {
        queryName = $(referObj).attr("data-queryName");	//查询名称
	}
	if ($(referObj).attr("data-e")) {
		e = $(referObj).attr("data-e");		//元素 多个用逗号分隔
	}
	if ($(referObj).attr("data-p")) {
		p = $(referObj).attr("data-p");		//参数	多个用逗号分隔
	}
	if ($(referObj).attr("data-fn")) {
		fn = $(referObj).attr("data-fn");	//回调方法
	}

	var url = contextPath + "/admin/refer/list?multiple=" + multiple;
	if (fileName != null) {
		url += "&listJsonFileName=" + fileName;
	}
	if (queryName != null) {
		url += "&listJsonQueryName=" + queryName;
	}
	if (e != null) {
		url += "&eIds=" + e;
	}
	if (p != null) {
		url += "&p=" + p;
	}
	if (fn != null) {
		url += "&fn=" + fn;
	}

	if (type == 1) {
		var index = layer.open({
			type : 2,
			title : '',
			shadeClose : true,
			shade : false,
			maxmin : false,
			zIndex : 666666,
			area : [ '90%', '90%' ],
			content : url
		});
	} else {
		var index = layer.open({
			type : 2,
			title : '',
			shadeClose : true,
			shade : false,
			maxmin : false,
			zIndex : 666666,
			area : [ '40%', '65%' ],
			content : url
		});
	}

}