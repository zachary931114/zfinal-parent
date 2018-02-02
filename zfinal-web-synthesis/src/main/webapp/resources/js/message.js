var websocket = null;

$(function() {
	initWebsocket()
	initMessageList();
});

function initWebsocket() {
	var urlPre = _projectUrl.replace("http:", "ws:");
	urlPre = urlPre.replace("https:", "wss:");
	if ('WebSocket' in window) {
		websocket = new WebSocket(urlPre + "/admin/websocket/message");
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket(urlPre + "/admin/websocket/message");
	} else {
		websocket = new SockJS(_projectUrl + "/admin/websocket/messageSockJs");
	}
	websocket.onopen = function() {
		console.log('message info: 连接打开.');
	};
	websocket.onerror = function() {
		console.log('message info: 连接错误.');
	};
	websocket.onmessage = function(event) {
		var message = $.parseJSON(event.data);
		if (message != null) {
			if (message.type == 0) {
				addMessage(message.id, message.title, message.url);
				top.toastrTitleInfo(message.title, '消息提醒', {
					timeOut : "0",
					onclick : function() {
						openMessage(message.id, message.url);
					}
				});
			} else if (message.type == 1) {
				websocket.close();
				top.alerts(message.title, function() {
					top.location.href = _contextPath + '/admin/logout';
				});
			}
		}
	};
	websocket.onclose = function(event) {
		console.log('message info: 连接关闭.');
		console.log(event);
	};

	window.onbeforeunload = function() {
		websocket.close();
	}

}

function initMessageList() {
	$.post(_contextPath + '/admin/message/getListByUser', {}, function(datas) {
		if (datas.isSuccess) {
			for (var i = 0; i < datas.data.length; i++) {
				addMessage(datas.data[i].id, datas.data[i].title,
						datas.data[i].url);
			}
			$.post(_contextPath + '/admin/message/getListSizeByUser', {},
					function(datas) {
						if (datas.isSuccess) {
							updateMessageSize(datas.data);
						}
					}, 'json');
		}
	}, 'json');
}

function addMessage(id, title, url) {
	var html = '<li title="' + title + '">';
	html += '<a href="javascript:openMessage(\'' + id + '\',\'' + url + '\')">';
	html += '<i class="fa fa-bell-o text-yellow"></i>' + title;
	html += '</a>';
	html += '</li>';
	if ($('#messageUl li').size() > 0) {
		$('#messageUl li:last').after(html);
	} else {
		$('#messageUl').append(html);
	}
	addMessageSize();
}

function openMessage(id, url) {
	$.post(_contextPath + '/admin/message/delete', {
		'ids' : id
	}, function() {
	}, 'json');
	window.location.href = _contextPath + '/admin/' + url;
}

function showMessageList() {
	openWin(_contextPath + '/admin/message/list', '未读消息');
}

function updateMessageSize(size) {
	$('span[name="messageSize"]').text(size);
}

function addMessageSize() {
	updateMessageSize(parseInt($('span[name="messageSize"]:eq(0)').text()) + 1);
}

function subMessageSize() {
	updateMessageSize(parseInt($('span[name="messageSize"]:eq(0)').text()) - 1);
}

function send(message) {
	websocket.send(message);
}

function closeWebSocket() {
	websocket.close();
}