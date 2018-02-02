<#macro page title="正文:" name="" value="" readonly=false required=false>
<div class="form-group">
    <label class="col-sm-3 control-label">${title?if_exists}<#if required == true><i class="required">*</i></#if></label>
    <div class="col-sm-9">
        <script id="editor${name?if_exists}" name="${name?if_exists}" type="text/plain">${value?if_exists}</script>
    </div>
</div>
<script src="${request.contextPath}/ueditor/ueditor.config.js"></script>
<script src="${request.contextPath}/ueditor/ueditor.all.min.js"></script>
<script>
	$(function () {
		var ue = UE.getEditor('editor${name?if_exists}');
    });
</script>
</#macro>