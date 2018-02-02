<#import "/WEB-INF/views/common/form.ftl" as form>
<#escape x as x?html>
<@form.page>
<script>
    $(function(){
        $(document.forms[0]).validate({
            rules:{
                "code":"required",
                <#if !entity.id?exists>
                    "pwd":"required",
                </#if>
                "name":"required"
            },
            submitHandler: function(form) {

                if($('#roleIds').val() == ""){
                    top.alerts("请选择角色");
                    return;
                }

                if(fileQueueSizeheadPortrait() > fileSuccessSizeheadPortrait()){
                    top.confirms("头像文件未上传成功，是否继续？",{},function () {
                    	if($('#pwd').val() != ''){
							$('#pwd').val(aes($('#pwd').val()));                		
	                	}
                        $(form).ajaxSubmit(options);
                    });
                }else{
                	if($('#pwd').val() != ''){
						$('#pwd').val(aes($('#pwd').val()));                		
                	}
                    $(form).ajaxSubmit(options);
                }
            }
        });

        <#if entity.id?exists>
            tips("密码为空则不更新密码","#pwd");
        </#if>

    });
</script>
<form class="form-horizontal" method="post" action="save">
    <input type="hidden" name="id" value="${entity.id?if_exists}">
    <input type="hidden" name="fileUuid" value="${uuid?if_exists}">
    <div class="box-body">
        <div class="form-group">
            <label for="code" class="col-sm-3 control-label">用户名<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="code" id="code" placeholder="请输入..." value="${entity.code?if_exists}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd" class="col-sm-3 control-label">密码<#if !entity.id?exists><i class="required">*</i></#if></label>
            <div class="col-sm-9">
                <input type="password" class="form-control formInput" name="pwd" id="pwd" placeholder="请输入..." />
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">名称<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="name" id="name" placeholder="请输入..." value="${entity.name?if_exists}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">邮箱</label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="email" id="email" placeholder="请输入..." value="${entity.email?if_exists}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-3 control-label">手机</label>
            <div class="col-sm-9">
                <input type="text" class="form-control formInput" name="phone" id="phone" placeholder="请输入..." value="${entity.phone?if_exists}"/>
            </div>
        </div>
        <#import "/WEB-INF/views/common/select.ftl" as select>
        <@select.page title="地市" name="city" value="${entity.city?if_exists}" required=true>
            <option value="金华市">金华市</option>
        	<option value="兰溪市">兰溪市</option>
        	<option value="永康市">永康市</option>
        	<option value="东阳市">东阳市</option>
        	<option value="义乌市">义乌市</option>
        	<option value="武义县">武义县</option>
        	<option value="浦江县">浦江县</option>
        	<option value="磐安县">磐安县</option>
        </@select.page>
        <#import "/WEB-INF/views/common/referM.ftl" as referM>
        <@referM.page title="角色" name="roleIds" fileName="roleR" queryName="roleR" p="" e="_id_,_name_" fn="" idIndex=0 nameIndex=1 echoIds="${roleIds?if_exists}" echoNames="${roleNames?if_exists}" required=true ></@referM.page>
        <#import "/WEB-INF/views/common/file.ftl" as file><@file.page type="img" uuid="${uuid?if_exists}" filePathPrefix="${filePathPrefix?if_exists}" title="头像" param="headPortrait" fileNumLimit=1 auto=true required=false></@file.page>
    </div>
    <div class="box-footer" style="text-align: center;" >
        <button type="submit" class="btn btn-primary" style="width: 10%;">保存</button>
        <button type="button" class="btn btn-default" style="width: 10%;" onclick="closes();">关闭</button>
    </div>
</form>
</@form.page>
</#escape>