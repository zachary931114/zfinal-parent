<#import "/WEB-INF/views/common/common.ftl" as common>
<#import "/WEB-INF/views/common/list.ftl" as list>
<#escape x as x?html>
    <@common.page title="权限管理" subTitle="权限信息综合管理">
        <@list.page title="权限管理">
        </@list.page>
        <script>
            hideDelete();
        </script>
    </@common.page>
</#escape>