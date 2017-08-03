<#--
ueditor 富文本编辑器
-->
<#macro ckedit
	label="" nameid="" class="easyui-validatebox" dataoptions="" value="" help="" style="height:100px;" ckcfg="ckcfg.js" moreopt=""
	>
<script type="text/javascript">
var _editor_${nameid};
$(function() {
	_editor_${nameid} = UE.getEditor('${nameid}');
	_editor_${nameid}.ready(function () {
		_editor_${nameid}.setOpt("uuhm","${uuhm}");
	});	
});
</script>	
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<script type="text/plain" id="${nameid}" name="${nameid}">${value}</script>	
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
