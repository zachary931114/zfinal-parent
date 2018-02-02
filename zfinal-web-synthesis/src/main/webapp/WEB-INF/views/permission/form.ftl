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
        tips("权限编码必须PERMISSION_开头,并且为大写字母(格式:PERMISSION_控制器映射名称_方法映射名称)","#code");
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
    </div>
    <div class="box-footer" style="text-align: center;" >
        <button type="submit" class="btn btn-primary" style="width: 10%;">保存</button>
        <button type="button" class="btn btn-default" style="width: 10%;" onclick="closes();">关闭</button>
    </div>
</form>
</@form.page>
</#escape>