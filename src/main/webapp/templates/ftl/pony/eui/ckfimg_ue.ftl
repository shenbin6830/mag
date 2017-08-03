<#--
ckfimg_ue 图片选择
-->
<#macro ckfimg
	label="" nameid="" value="" class="easyui-validatebox" dataoptions=""  help="" moreopt="" 
	>
<script type="text/javascript">
var _editor_${nameid};
$(function() {
	_editor_${nameid} = UE.getEditor('upload_${nameid}');
	_editor_${nameid}.ready(function () {
		_editor_${nameid}.hide();
		_editor_${nameid}.addListener('afterInsertImage', function (t, arg) {
			$("#${nameid}").val(arg[0].src);
			$("#${nameid}src").attr("src",arg[0].src);
		});
	});
});
 //弹出图片上传的对话框
function upImage_${nameid}() {
	_editor_${nameid}.setOpt("uuhm","${uuhm}");
    _editor_${nameid}.getDialog("insertimage").open();
 }
</script>
<script type="text/plain" id="upload_${nameid}"></script>	
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<input name="${nameid}" id="${nameid}" type="text" class="${class}" data-options="${dataoptions}"  value="${value}" ${moreopt} /><#rt/>
<input type="button" value="<@s.m 'global.select.pic'/>" onclick="upImage_${nameid}();" /><br/>
<img id="${nameid}src" name="${nameid}src" src="${value}" style="height:100px;"/>  
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
