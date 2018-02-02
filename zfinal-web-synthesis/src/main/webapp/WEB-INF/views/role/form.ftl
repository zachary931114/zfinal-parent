<#import "/WEB-INF/views/common/form.ftl" as form>
<#escape x as x?html>
<@form.page>
<script>
    $(function(){
        $(document.forms[0]).validate({
            rules:{
                "code":"required",
                "name":"required"
            },
            submitHandler: function(form) {
                $(form).ajaxSubmit(options);
            }
        });
        tips("角色编码必须ROLE_开头,并且为大写字母","#code");
    });
</script>
<form class="form-horizontal" method="post" action="save">
    <input type="hidden" name="id" value="${entity.id?if_exists}">
    <input type="hidden" name="fileUuid" value="${uuid?if_exists}">
    <div class="box-body">
        <div class="form-group">
            <label for="code" class="col-sm-3 control-label">编码<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="code" id="code" placeholder="请输入..." value="${entity.code?if_exists}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">名称<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="name" id="name" placeholder="请输入..." value="${entity.name?if_exists}"/>
            </div>
        </div>
        <#import "/WEB-INF/views/common/referM.ftl" as referM>
        <@referM.page title="菜单" name="menuIds" fileName="menuR" queryName="menuR" p="" e="_id_,_name_" fn="" idIndex=0 nameIndex=1 echoIds="${menuIds?if_exists}" echoNames="${menuNames?if_exists}"></@referM.page>
        <#import "/WEB-INF/views/common/referM.ftl" as referM>
        <@referM.page title="权限" name="permissionIds" fileName="permissionR" queryName="permissionR" p="" e="_id_,_name_" fn="" idIndex=0 nameIndex=1 echoIds="${permissionIds?if_exists}" echoNames="${permissionNames?if_exists}"></@referM.page>
    </div>
    <div class="box-footer" style="text-align: center;" >
        <button type="submit" class="btn btn-primary" style="width: 10%;">保存</button>
        <button type="button" class="btn btn-default" style="width: 10%;" onclick="closes();">关闭</button>
    </div>
</form>
</@form.page>
</#escape>