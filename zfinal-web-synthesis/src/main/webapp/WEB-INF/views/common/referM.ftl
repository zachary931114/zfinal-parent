<#--title：标题、name：id隐藏域名称、required：是否必须、idIndex：id对应元素位置、nameIndex：显示名称对应元素位置、fileName：查询文件名、queryName：查询名称、p：参数、e：元素、fn：回调方法名称、echoIds：回显id多个用逗号分开、echoNames：回显name多个用户逗号分开 -->
<#macro page title="" name="" required=false idIndex=0 nameIndex=0 fileName="" queryName="" p="" e="" fn="" echoIds="" echoNames="">
<script>
    $(function () {
        if("${echoIds?if_exists}" != "" && "${echoNames?if_exists}" != ""){
            $('#${name?if_exists}').val("${echoIds?if_exists}");
            var ids = "${echoIds?if_exists}".split(',');
            var names = "${echoNames?if_exists}".split(',');
            for(var i=0;i<ids.length;i++){
                if(!isExists${name?if_exists}(ids[i])){
                    addContent${name?if_exists}(ids[i],names[i]);
                }
            }
        }
    });
    function callback${name?if_exists}(datas) {
        if(datas[${idIndex}] != ""){
            var ids = datas[${idIndex}].split(',');
            var names = datas[${nameIndex}].split(',');

            for(var i=0;i<ids.length;i++){
                if(!isExists${name?if_exists}(ids[i])){
                    addContent${name?if_exists}(ids[i],names[i]);
                }
            }
            updateNameValue${name?if_exists}();
        }

        <#if fn?exists && fn != "">
            ${fn?if_exists}(datas);
        </#if>
    }

    function addContent${name?if_exists}(id,name) {
        var html = '<button type="button" data-id="'+id+'" class="btn btn-primary btn-flat" style="margin-bottom: 5px;margin-right: 5px;" onclick="deleteTags${name?if_exists}(\''+id+'\',\''+name+'\',this);">'+name+'<i class="fa fa-remove" style="margin-left: 5px;"></i></button>';
        $('#referMDiv${name?if_exists}').append(html);
    }
    
    function updateNameValue${name?if_exists}() {
        var ids = '';
        $('#referMDiv${name?if_exists} button').each(function () {
            ids += ',' + $(this).attr("data-id");
        });
        if(ids != ''){
            ids = ids.substring(1);
        }
        $('#${name?if_exists}').val(ids);
    }

    function isExists${name?if_exists}(id) {
        if($('#referMDiv${name?if_exists}').find('button[data-id="'+id+'"]').length > 0){
            return true;
        }else{
            return false;
        }
    }

    function deleteTags${name?if_exists}(id,name,e) {
        top.confirms("是否删除"+name+"？",{},function () {
            $(e).remove();
            updateNameValue${name?if_exists}();
        });
    }
</script>
<div class="form-group">
    <label class="col-sm-3 control-label">${title?if_exists}<#if required == true><i class="required">*</i></#if></label>
    <div class="col-sm-9">
        <button id="referMBtn${name?if_exists}" type="button" class="btn btn-info btn-flat refer" data-multiple="1" data-fileName="${fileName?if_exists}" data-queryName="${queryName?if_exists}"  data-p="${p?if_exists}" data-e="${e?if_exists}" data-fn="callback${name?if_exists}"><i class="fa fa-hand-pointer-o"></i></button>
        <input type="hidden" id="${name?if_exists}" name="${name?if_exists}"/>
        <div class="box box-primary" style="margin-top: 10px; float: left;">
            <div class="box-header with-border">
                <h3 class="box-title" style="font-weight: bold;">已选数据</h3>
                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                            title="">
                        <i class="fa fa-minus"></i></button>
                </div>
            </div>
            <div class="box-body" id="referMDiv${name?if_exists}">
            </div>
        </div>
    </div>
</div>
</#macro>