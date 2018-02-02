$(function () {
    $.post(_contextPath + '/admin/menu/menuList', {}, function (datas) {
        if (datas.isSuccess) {

            var servletPath = _servletPath.substring(7);

            for (var i = 0; i < datas.data.length; i++) {
                var url = '';
                var code = '';
                var target = '';
                if (datas.data[i].url != null) {
                    url = datas.data[i].url;
                }
                if (datas.data[i].code != null) {
                    code = datas.data[i].code;
                }
                if (datas.data[i].target != null) {
                    target = datas.data[i].target;
                }

                if (datas.data[i].pId == null) {
                    var html = '<li class="treeview">';
                    if (datas.data[i].url == null) {
                        html += '<a href="" url="">';
                    } else {
                        html += '<a href="javascript:openUrl(\'' + url + '\',\'' + code + '\',\'' + target + '\');" url="' + url + '">';
                    }
                    html += '<i class="' + datas.data[i].icon + '"></i> <span>' + datas.data[i].name + '</span>';
                    if (datas.data[i].url == null) {
                        html += '<span class="pull-right-container">';
                        html += '<i class="fa fa-angle-left pull-right"></i>';
                        html += '</span>';
                    }
                    html += '</a>';
                    html += '<ul class="treeview-menu" id="subMenuUl' + datas.data[i].id + '">';
                    html += '</ul>';
                    html += '</li>';
                    $('#menuUl').append(html);
                } else {
                    var html = '<li class="">';
                    html += '<a href="javascript:openUrl(\'' + url + '\',\'' + code + '\',\'' + target + '\');" url="' + url + '"><i class="' + datas.data[i].icon + '"></i> ' + datas.data[i].name;
                    html += '<span class="pull-right-container">';
                    html += '<i class="fa fa-angle-left pull-right"></i>';
                    html += '</span>';
                    html += '</a>';
                    html += '<ul class="treeview-menu" id="subMenuUl' + datas.data[i].id + '">';
                    html += '</ul>';
                    html += '</li>';
                    $('#subMenuUl' + datas.data[i].pId).append(html);
                }
            }

            var breadcrumbArr = new Array();

            $('#menuUl li:gt(0)').each(function () {
                if ($(this).children('ul').children('li').length == 0) {
                    $(this).children('a').children('.pull-right-container').remove();
                    $(this).children('ul').remove();
                }

                var active = '';
                var url = $(this).children('a').attr('url');
                if (url != '') {
                    if (url.indexOf('?') == -1) {
                        if (servletPath == url) {
                            active = 'active';
                        }
                    } else {
                        var nUrl = url.substring(0, url.indexOf('?'));
                        if (servletPath == nUrl) {
                            active = 'active';
                        }
                    }
                }

                if (active != '') {
                    $(this).addClass('active');
                    breadcrumbArr.push('<li class="active"><a><i class="'+$(this).children('a').children('i').prop('class')+'"></i>'+$(this).children('a').text()+'</a></li>');
                    var flag = true;
                    var el = $(this).parent().parent();
                    while (flag) {
                        if (el.is('li')) {
                            el.addClass('active');
                            breadcrumbArr.push('<li><a><i class="'+el.children('a').children('i').prop('class')+'"></i>'+el.children('a').text()+'</a></li>');
                            el = el.parent().parent();
                        } else {
                            flag = false;
                        }
                    }
                }

            });

            for(var i=breadcrumbArr.length - 1;i>=0;i--){
                $('#breadcrumbOl').append(breadcrumbArr[i]);
            }

        } else {
            toastrerror('菜单数据加载错误');
        }
    }, 'json');
});

function openUrl(url, code, target) {
	if(target != null){
		if(target == "fn"){
			eval(url);
		}else{
			window.location.href = _contextPath + '/admin/' + url;
		}
	}else{
		window.location.href = _contextPath + '/admin/' + url;
	}
}

function alterPwd(){
	openWin(_contextPath + '/admin/user/alterPwd', '修改密码');
}