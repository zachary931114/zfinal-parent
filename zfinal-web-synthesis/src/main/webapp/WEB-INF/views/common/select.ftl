<#macro page title="" name="" change="" readonly=false value="" multiple=false required=false>
<script>
    $(function () {
        $(".select2").select2({
            language: 'zh-CN',
            placeholder: '请选择'
        });
        
        $("#${name?if_exists}").on('change',function(e){
        	<#if change?exists && change != "">
        		if(e.currentTarget.selectedOptions.length > 0){
        			${change?if_exists}(e.currentTarget.value,e.currentTarget.selectedOptions[0].innerText);
        		}else{
        			${change?if_exists}(e.currentTarget.value,'');
        		}
        	</#if>
        });
        
        <#if value?exists && value != "">
            <#if multiple == true>
                $("#${name?if_exists}").select2("val", ["${value?if_exists}".split(",")]);
                <#else>
                $("#${name?if_exists}").select2("val", ["${value?if_exists}"]);
            </#if>

        </#if>

        <#if readonly == true>
            $("#${name?if_exists}").prop("disabled", true);
        </#if>

    });
</script>
<div class="form-group">
    <label for="${name?if_exists}" class="col-sm-3 control-label">${title?if_exists}<#if required == true><i class="required">*</i></#if></label>
    <div class="col-sm-9">
        <select class="form-control select2" id="${name?if_exists}" name="${name?if_exists}" style="width: 70%" <#if multiple == true>multiple="multiple"</#if>>
            <#nested/>
        </select>
    </div>
</div>
</#macro>