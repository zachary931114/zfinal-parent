toastr.options = {
    "closeButton": true,
    "debug": false,
    "positionClass": "toast-bottom-right",
    "onclick": null,
    "showDuration": "1000",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "0",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

function toastrinfo(message, option) {
    toastr.info(message, option);
}

function toastrsuccess(message, option) {
    toastr.success(message, option);
}

function toastrerror(message, option) {
    toastr.error(message, option);
}

function toastrTitleInfo(message, title, option) {
    toastr.info(message, title, option);
}

function toastrTitleSuccess(message, title, option) {
    toastr.success(message, title, option);
}

function toastrTitleError(message, title, option) {
    toastr.error(message, title, option);
}

function alerts(message, func) {
    layer.alert(message, {
        title: '提示',
        closeBtn: 0
    }, func);
}

function closeLayer(index) {
    layer.close(index);
}

function closesWin(name) {
    var index = layer.getFrameIndex(name); // 先得到当前iframe层的索引
    closeLayer(index); // 再执行关闭
}

function confirms(title, option, func1, func2) {
    layer.confirm(title, option, function (index) {
        if (func1) {
            func1();
        }
        closeLayer(index);
    }, function (index) {
        if (func2) {
            func2();
        }
        closeLayer(index);
    });
}

function openPrompt(title, defaultVal, func) {
    if (defaultVal == null) {
        defaultVal = '';
    }
    layer.prompt({
        formType: 2,
        value: defaultVal,
        title: title
    }, function (value, index, elem) {
        if (func) {
            func(value);
        }
        closeLayer(index);
    });
}

function openWinByHtml(title,html,width,height){
	top.layer.open({
		type: 1,
        title: title,
        shadeClose: false,
        shade: [0.5, '#393D49'],
        zIndex: 666666,
        maxmin: false, // 开启最大化最小化按钮
        area: [width, height],
        content: html
	});
}

openIframeList = [];
function openWin(url, title) {
    var index = layer.open({
        type: 2,
        title: title,
        shadeClose: true,
        shade: false,
        zIndex: 666666,
        maxmin: true, // 开启最大化最小化按钮
        area: ['95%', '95%'],
        content: url
    });

    for (var i = 0; i < openIframeList.length; i++) {
        if (openIframeList[i].url == url) {
            layer.close(openIframeList[i].index);
            openIframeList.splice(i, 1);
        }
    }
    openIframeList.push({
        "index": index,
        "url": url
    });
}

function bytesToSize(bytes) {
    if (bytes === 0) return '0 B';
    var k = 1024;
    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    i = Math.floor(Math.log(bytes) / Math.log(k));
    return (bytes / Math.pow(k, i)).toFixed(2) + ' ' + sizes[i];
}