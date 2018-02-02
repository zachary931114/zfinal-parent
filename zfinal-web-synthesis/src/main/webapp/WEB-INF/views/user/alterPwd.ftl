<#import "/WEB-INF/views/common/form.ftl" as form>
<#escape x as x?html>
<@form.page>
<script>
    $(function(){
        $(document.forms[0]).validate({
            rules:{
                "pwd":"required",
                "nPwd":"required"
            },
            submitHandler: function(form) {
            	$('#pwd').val(aes($('#pwd').val()));
            	$('#nPwd').val(aes($('#nPwd').val()));
				$(form).ajaxSubmit(options);
            }
        });


    });
</script>
<form class="form-horizontal" method="post" action="savePwd">
    <div class="box-body">
        <div class="form-group">
            <label for="pwd" class="col-sm-3 control-label">原密码<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="password" class="form-control formInput" name="pwd" id="pwd" placeholder="请输入..." />
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">新密码<i class="required">*</i></label>
            <div class="col-sm-9">
                <input type="password" class="form-control formInput" name="nPwd" id="nPwd" placeholder="请输入..." />
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