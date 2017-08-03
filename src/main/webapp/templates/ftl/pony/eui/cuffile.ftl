<#--
uu html 文件上传h5版
-->
<#macro cuffile
	label="选择文件" cmdlabel="上传" nameid="" value="" class="easyui-validatebox" dataoptions=""  help="" multiple="false" url="uploadFile.html" moreopt=""
	>
<script type="text/javascript">
$(function () {
    'use strict';
    $('#fileupload_${nameid}').fileupload({
        url: '${base}/user/${url}',
        dataType: 'json',
        done: function (e, data) {
        	//console.log("upload.jr="+data.result.msg);
            if(data.result.success){
				$("#${nameid}").val(data.result.msg);
				$("#${nameid}src").attr("src",data.result.msg);
            	alert("完成，"+data.result.msg);
            }else{
            	alert("上传["+data.fileInput["0"].value+"]失败");
            }
        },
        progressall: function (e, data) {
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});

</script>		
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<span class="btn btn-success fileinput-button">
    <i class="glyphicon glyphicon-plus"></i>
    <span>${cmdlabel}</span>
    <input id="fileupload_${nameid}" type="file" name="files_${nameid}[]" ${multiple} ${moreopt}>
</span>
<span>
	<input name="${nameid}" id="${nameid}" type="hidden" value="${value}" ${moreopt} />
	
</span>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>

