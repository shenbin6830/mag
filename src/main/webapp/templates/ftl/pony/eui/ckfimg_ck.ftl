<#--
ckfimg_ckfinder 图片选择
-->
<#macro ckfimg_ckfinder
	label="" nameid="" value="" class="easyui-validatebox" dataoptions=""  help="" moreopt=""
	>
<script type="text/javascript">
function browserver_${nameid}(){
	finder.selectActionFunction = function(fileUrl){
		$("#${nameid}").val(fileUrl);
		$("#${nameid}src").attr("src",fileUrl);
		};
	finder.popup();
}
</script>		
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<input name="${nameid}" id="${nameid}" type="text" class="${class}" data-options="${dataoptions}"  value="${value}" ${moreopt} /><#rt/>
<input type="button" value="<@s.m 'global.select.pic'/>" onclick="browserver_${nameid}();" /><br/>
<img id="${nameid}src" name="${nameid}src" src="${value}" style="height:100px;"/>  
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
