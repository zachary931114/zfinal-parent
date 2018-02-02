<#import "/WEB-INF/views/common/form.ftl" as form>
<#escape x as x?html>
<@form.page>
<script>
    $(function(){
        $(document.forms[0]).validate({
            rules:{
                "code":"required",
                "name":"required",
                "address":"required"
            },
            submitHandler: function(form) {
                $(form).ajaxSubmit(options);
            }
        });
        tips("地址必须为百度地图中的地址","#address");
    });
</script>
<form class="form-horizontal" method="post" action="save">
    <input type="hidden" name="id" value="${entity.id?if_exists}">
    <input type="hidden" name="fileUuid" value="${uuid?if_exists}">
    <div class="box-body">
        <#import "/WEB-INF/views/common/select.ftl" as select>
        <@select.page title="类型" name="type" value="${entity.type?if_exists}" required=true>
            <#list commonAddressTypeList as commonAddressType>
                <option value="${commonAddressType.val?if_exists}">${commonAddressType.text?if_exists}</option>
            </#list>
        </@select.page>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">名称<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="name" id="name" placeholder="请输入..." value="${entity.name?if_exists}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-3 control-label">地址<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="address" id="address" placeholder="请输入..."  value="${entity.address?if_exists}"/>
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