<#--
ckf 文件选择
-->
<#macro ckfftl
	label="" nameid="" value="" class="easyui-validatebox" dataoptions=""  help="" moreopt=""
	>
<script type="text/javascript">
function browserver_${nameid}(){
	finder.selectActionFunction = function(fileUrl){
		$("#${nameid}").val(decodeURI(fileUrl));
		};
	finder.popup();
}
</script>		
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<input name="${nameid}" id="${nameid}" type="text" class="${class}" data-options="${dataoptions}"  value="${value}" ${moreopt} /><#rt/>
<input type="button" value="<@s.m 'global.select.file'/>" onclick="browserver_${nameid}();" /><br/>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
