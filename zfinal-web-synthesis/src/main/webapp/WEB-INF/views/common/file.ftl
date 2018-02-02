<#-- type 类型(img图片、all全部)、 uuid 唯一标示、 filePathPrefix 文件路径前缀、title 标题、param 变量/唯一标示、fileNumLimit 文件数量、auto 是否自动上传、required 是否必须、mimeTypes 文件类型 -->
<#macro page type="all" uuid="" filePathPrefix="" title="附件管理" param="" fileNumLimit=10 auto=false readonly=false required=false mimeTypes="*">
<style>
    .picker {
        display: inline-block;
        line-height: 1.428571429;
        vertical-align: middle;
        margin: 0 12px 0 0;
    }
    .picker .webuploader-pick {
        padding: 6px 12px;
        display: block;
    }

    .uploader-list {
        width: 100%;
        overflow: hidden;
    }
    .file-item {
        float: left;
        position: relative;
        margin: 0 20px 20px 0;
        padding: 4px;
    }
    .file-item .error {
        position: absolute;
        top: 4px;
        left: 4px;
        right: 4px;
        background: red;
        color: white;
        text-align: center;
        height: 20px;
        font-size: 14px;
        line-height: 23px;
    }
    .file-item .info {
        position: absolute;
        left: 4px;
        bottom: 4px;
        right: 4px;
        height: 20px;
        line-height: 20px;
        text-indent: 5px;
        background: rgba(0, 0, 0, 0.6);
        color: white;
        overflow: hidden;
        white-space: nowrap;
        text-overflow : ellipsis;
        font-size: 12px;
        z-index: 10;
    }
    .upload-state-done:after {
        content:"\f00c";
        font-family: FontAwesome;
        font-style: normal;
        font-weight: normal;
        line-height: 1;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        font-size: 32px;
        position: absolute;
        bottom: 0;
        right: 4px;
        color: #4cae4c;
        z-index: 99;
    }
    .file-item .progress {
        position: absolute;
        right: 4px;
        bottom: 4px;
        height: 3px;
        left: 4px;
        height: 4px;
        overflow: hidden;
        z-index: 15;
        margin:0;
        padding: 0;
        border-radius: 0;
        background: transparent;
    }
    .file-item .progress span {
        display: block;
        overflow: hidden;
        width: 0;
        height: 100%;
        background: #d14 ;
        -webit-transition: width 200ms linear;
        -moz-transition: width 200ms linear;
        -o-transition: width 200ms linear;
        -ms-transition: width 200ms linear;
        transition: width 200ms linear;
        -webkit-animation: progressmove 2s linear infinite;
        -moz-animation: progressmove 2s linear infinite;
        -o-animation: progressmove 2s linear infinite;
        -ms-animation: progressmove 2s linear infinite;
        animation: progressmove 2s linear infinite;
        -webkit-transform: translateZ(0);
    }

</style>
<#if type=="all">
<script>
	fileSizeC${param?if_exists} = 0;
    $(function(){
        ${param?if_exists} = WebUploader.create({
            swf: '${request.contextPath}/resources/lib/webuploader/Uploader.swf',
            server: '${request.contextPath}/admin/file/save',
            pick: '#picker${param?if_exists}',
            formData:{"uuid":"${uuid?if_exists}","filePathPrefix":"${filePathPrefix?if_exists}","param":"${param?if_exists}"},
            fileVal:'files',
            duplicate:true,
            <#if auto>
                auto:true,
                <#else>
                auto:false,
            </#if>
            fileNumLimit:${fileNumLimit?if_exists},
            accept: {
                mimeTypes: '${mimeTypes?if_exists}'
            }
        });
        ${param?if_exists}.on( 'fileQueued', function( file ) {
            var html = '<div class="callout callout-info item" id="' + file.id + '">';
            html += '<span class="info" style="font-weight: bold;">' + file.name + '</span><br>';
            html += '<span class="state" style="font-weight: bold;">等待上传...</span>';
            html += '</div>';
            var $div = $(html);
            $('#filelist${param?if_exists}').append( $div );
            $div.on('click', function() {
                top.confirms("是否移除"+file.name+"？",{},function () {
                    ${param?if_exists}.removeFile( file,true );
                });
            });
        });
        ${param?if_exists}.on( 'fileDequeued', function( file ) {
            $( '#'+file.id ).remove();
        });
        ${param?if_exists}.on( 'uploadProgress', function( file, percentage ) {
            var $div = $( '#'+file.id ),$percent = $div.find('.progress .progress-bar');
            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo( $div ).find('.progress-bar');
            }
            $div.find('.state').text('上传中');
            $percent.css( 'width', percentage * 100 + '%' );
        });
        ${param?if_exists}.on( 'uploadSuccess', function( file ) {
            $( '#'+file.id ).find('.state').text('已上传');
        });
        ${param?if_exists}.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('.state').text('上传出错');
        });
        ${param?if_exists}.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        initFileList${param?if_exists}();

    });

    function fileQueueSize${param?if_exists}(){
        var stats = ${param?if_exists}.getStats();
        var files = ${param?if_exists}.getFiles();
        return files.length - stats.cancelNum;
    }

    function fileSuccessSize${param?if_exists}(){
        var stats = ${param?if_exists}.getStats();
        return stats.successNum;
    }
    
    function fileSize${param?if_exists}(){
        return fileSizeC${param?if_exists} + fileQueueSize${param?if_exists}();
    }

    function downloadFile${param?if_exists}(id){
        window.open('${request.contextPath}/admin/file/download?id=' + id);
    }

    function deleteFile${param?if_exists}(id,name){
        top.confirms("是否删除"+name+"？",{},function () {
            $.post('${request.contextPath}/admin/file/delete',{'id':id},function(datas){
                if (datas.isSuccess){
                    top.toastrinfo(name + '删除成功');
                    initFileList${param?if_exists}();
                }
            },'json');
        });
    }

    function initFileList${param?if_exists}(){
        $.post("${request.contextPath}/admin/file/getFileList",{"uuid":"${uuid?if_exists}","param":"${param?if_exists}"},function(datas){
            if(datas.isSuccess){
            	fileSizeC${param?if_exists} = datas.data.length;
                if(datas.data.length > 0){
                    $('#fileListDiv${param?if_exists}').show();
                    $('#fileListTb${param?if_exists} tr:gt(0)').remove();
                    for (var i=0;i<datas.data.length;i++){
                        var html = '<tr>';
                        html += '<td style="text-align: center;width: 20%;"><button type="button" class="btn btn-info" onclick="downloadFile${param?if_exists}(\''+datas.data[i].id+'\')">下 载</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger" onclick="deleteFile${param?if_exists}(\''+datas.data[i].id+'\',\''+datas.data[i].oldFileName+'\')">删 除</button></td>';
                        html += '<td style="text-align: center;width: 40%;">'+ datas.data[i].oldFileName +'</td>';
                        html += '<td style="text-align: center;width: 20%;">'+ top.bytesToSize(datas.data[i].fileSize) +'</td>';
                        html += '<td style="text-align: center;width: 20%;">'+datas.data[i].createTime+'</td>';
                        html += '</tr>';
                        $('#fileListTb${param?if_exists}').append(html);
                    }
                }else{
                    $('#fileListDiv${param?if_exists}').hide();
                }
            }else{
                $('#fileListDiv${param?if_exists}').hide();
            }
        },'json');
    }

</script>
<div class="form-group">
    <label class="col-sm-3 control-label">${title?if_exists}<#if required == true><i class="required">*</i></#if></label>
    <div class="col-sm-9">
        <div class="box box-success">
            <div class="box-header with-border">
                <h3 class="box-title" style="font-weight: bold;"><i class="fa fa-file" style="margin-right: 5px;"></i></h3>
                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                            title="">
                        <i class="fa fa-minus"></i></button>
                </div>
            </div>
            <div class="box-body"  style="background-color: #ecf0f5;">
                <div id="filelist${param?if_exists}" class="uploader-list"></div>
                <#if !readonly>
                    <div id="picker${param?if_exists}" class="picker" style="display: inline-block;vertical-align: middle;">选择文件</div>
                    <#if !auto>
                        <button type="button" class="btn btn-primary" style="margin-left: 10px;" onclick="${param?if_exists}.upload();">开始上传</button>
                    </#if>
                </#if>
                <div id="fileListDiv${param?if_exists}" class="box box-primary" style="margin-top: 20px; display: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title" style="font-weight: bold;">文件列表</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                    title="">
                                <i class="fa fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered" id="fileListTb${param?if_exists}">
                            <tr>
                                <th style="text-align: center;">操作</th>
                                <th style="text-align: center;">文件名称</th>
                                <th style="text-align: center;">文件大小</th>
                                <th style="text-align: center;">上传时间</th>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <#elseif type=="img">
<script>
	fileSizeC${param?if_exists} = 0;
    $(function(){
        ${param?if_exists} = WebUploader.create({
            swf: '${request.contextPath}/resources/lib/webuploader/Uploader.swf',
            server: '${request.contextPath}/admin/file/save',
            pick: '#picker${param?if_exists}',
            formData:{"uuid":"${uuid?if_exists}","filePathPrefix":"${filePathPrefix?if_exists}","param":"${param?if_exists}"},
            fileVal:'files',
            duplicate:true,
            fileNumLimit:${fileNumLimit?if_exists},
            <#if auto>
                auto:true,
            <#else>
                auto:false,
            </#if>
            accept: {
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },
            compress: {
            	quality: 50,
			    noCompressIfLarger: false,
			    compressSize: 0
			}
        });
        ${param?if_exists}.on( 'fileQueued', function( file ) {
            var $div = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
                '</div>'
            ),
            $img = $div.find('img');
            $('#filelist${param?if_exists}').append( $div );
            $div.on('click', function() {
                top.confirms("是否移除"+file.name+"？",{},function () {
                    ${param?if_exists}.removeFile( file,true );
                });
            })
            ${param?if_exists}.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
                $img.attr( 'src', src );
            }, 180, 180 );
        });
        ${param?if_exists}.on( 'fileDequeued', function( file ) {
            $( '#'+file.id ).remove();
        });
        ${param?if_exists}.on( 'uploadProgress', function( file, percentage ) {
            var $div = $( '#'+file.id ),
                    $percent = $div.find('.progress span');
            if ( !$percent.length ) {
                $percent = $('<p class="progress"><span></span></p>')
                        .appendTo( $div )
                        .find('span');
            }
            $percent.css( 'width', percentage * 100 + '%' );
        });

        ${param?if_exists}.on( 'uploadSuccess', function( file ) {
            $( '#'+file.id ).addClass('upload-state-done');
        });
        ${param?if_exists}.on( 'uploadError', function( file ) {
            var $div = $( '#'+file.id ),
                    $error = $div.find('div.error');
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $div );
            }
            $error.text('上传失败');
        });
        ${param?if_exists}.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });

        initFileList${param?if_exists}();

    });

    function fileQueueSize${param?if_exists}(){
        var stats = ${param?if_exists}.getStats();
        var files = ${param?if_exists}.getFiles();
        return files.length - stats.cancelNum;
    }

    function fileSuccessSize${param?if_exists}(){
        var stats = ${param?if_exists}.getStats();
        return stats.successNum;
    }
    
    function fileSize${param?if_exists}(){
        return fileSizeC${param?if_exists} + fileQueueSize${param?if_exists}() ;
    }

    function deleteFile${param?if_exists}(id){
        top.confirms("是否删除选中的图片？",{},function () {
            $.post('${request.contextPath}/admin/file/delete',{'id':id},function(datas){
                if (datas.isSuccess){
                    top.toastrinfo('删除成功');
                    initFileList${param?if_exists}();
                }
            },'json');
        });
    }

    function initFileList${param?if_exists}(){
        $.post("${request.contextPath}/admin/file/getFileList",{"uuid":"${uuid?if_exists}","param":"${param?if_exists}"},function(datas){
            if(datas.isSuccess){
            	fileSizeC${param?if_exists} = datas.data.length;
                if(datas.data.length > 0){
                    $('#fileListDiv${param?if_exists}').show();
                    $('#fileListTb${param?if_exists} img').remove();
                    for (var i=0;i<datas.data.length;i++){
                        var html = '<img src="${filesUrl?if_exists}'+datas.data[i].filePath+'" style="width:200px;height:200px;margin-left:8px;" onclick="deleteFile${param?if_exists}(\''+datas.data[i].id+'\')"/>';
                        $('#fileListTb${param?if_exists}').append(html);
                    }
                }else{
                    $('#fileListDiv${param?if_exists}').hide();
                }
            }else{
                $('#fileListDiv${param?if_exists}').hide();
            }
        },'json');
    }

</script>
<div class="form-group">
    <label class="col-sm-3 control-label">${title?if_exists}<#if required == true><i class="required">*</i></#if></label>
    <div class="col-sm-9">
        <div class="box box-success">
            <div class="box-header with-border">
                <h3 class="box-title" style="font-weight: bold;"><i class="fa fa-file" style="margin-right: 5px;"></i></h3>
                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                            title="">
                        <i class="fa fa-minus"></i></button>
                </div>
            </div>
            <div class="box-body"  style="background-color: #ecf0f5;">
                <div id="filelist${param?if_exists}" class="uploader-list"></div>
                <#if !readonly>
                    <div id="picker${param?if_exists}" class="picker" style="display: inline-block;vertical-align: middle;">选择文件</div>
                    <#if !auto>
                        <button type="button" class="btn btn-primary" style="margin-left: 10px;" onclick="${param?if_exists}.upload();">开始上传</button>
                    </#if>
                </#if>
                <div id="fileListDiv${param?if_exists}" class="box box-primary" style="margin-top: 20px; display: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title" style="font-weight: bold;">图片列表</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                    title="">
                                <i class="fa fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="box-body" id="fileListTb${param?if_exists}">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</#if>
</#macro>