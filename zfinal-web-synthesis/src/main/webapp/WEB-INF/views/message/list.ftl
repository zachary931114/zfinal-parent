<#import "/WEB-INF/views/common/sublist.ftl" as list>
<#escape x as x?html>
<@list.page title="消息管理">
</@list.page>
<script>
    function initDataSuccess() {
        hideColumn(1,false);
    }
    function editData(id) {
        $.post('getUrl', {'id':id}, function (datas) {
            if(datas.isSuccess){
                $.post('delete', {'ids':id}, function () {}, 'json');
                top.window.location.href = '${request.contextPath}/admin/' + datas.data;
            }
        }, 'json');
    }
    hideSearchBox();
    hideBtnDIV();
</script>
</#escape>