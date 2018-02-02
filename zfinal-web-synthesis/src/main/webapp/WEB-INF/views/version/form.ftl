<#import "/WEB-INF/views/common/form.ftl" as form>
<#escape x as x?html>
<@form.page>
<script>
    $(function(){
        $(document.forms[0]).validate({
            rules:{
                "code":"required"
            },
            submitHandler: function(form) {

                if("${entity.fileUuid?if_exists}" == ""){
                    if(fileQueueSizepackage() == 0){
                        top.alerts("请选择安装包");
                        return;
                    }
                }
                if(fileQueueSizepackage() > fileSuccessSizepackage()){
                    top.confirms("安装包文件未上传成功，是否继续？",{},function () {
                        $(form).ajaxSubmit(options);
                    });
                }else{
                    $(form).ajaxSubmit(options);
                }
            }
        });


    });
</script>
<form class="form-horizontal" method="post" action="save">
    <input type="hidden" name="id" value="${entity.id?if_exists}">
    <input type="hidden" name="fileUuid" value="${uuid?if_exists}">
    <div class="box-body">
        <#import "/WEB-INF/views/common/select.ftl" as select>
        <@select.page title="类型" name="type" value="${entity.type?if_exists}" required=true>
            <#list commonVersionTypeList as commonVersionType>
                <option value="${commonVersionType.val?if_exists}">${commonVersionType.text?if_exists}</option>
            </#list>
        </@select.page>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">版本号<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="code" id="code" placeholder="请输入..." value="${entity.code?if_exists}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">描述</label>
            <div class="col-sm-9">
                <textarea class="form-control formInput" rows="5" style="resize:none;" name="remark" id="remark" placeholder="请输入...">${entity.remark?if_exists}</textarea>
            </div>
        </div>
        <#import "/WEB-INF/views/common/file.ftl" as file><@file.page type="all" uuid="${uuid?if_exists}" filePathPrefix="${filePathPrefix?if_exists}" title="安装包" param="package" fileNumLimit=1 auto=true required=true></@file.page>
    </div>
    <div class="box-footer" style="text-align: center;" >
        <button type="submit" class="btn btn-primary" style="width: 10%;">保存</button>
        <button type="button" class="btn btn-default" style="width: 10%;" onclick="closes();">关闭</button>
    </div>
</form>
</@form.page>
</#escape>