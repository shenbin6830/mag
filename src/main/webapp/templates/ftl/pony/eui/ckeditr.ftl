<#--
ckeditor 富文本编辑器 远程ckfinder版
-->
<#macro ckeditr
	label="" nameid="" class="easyui-validatebox" dataoptions="" value="" help="" style="height:100px;" ckcfg="ckcfg.js" moreopt="" hm="" 
	>
<script type="text/javascript">
$(function() {
	if(CKEDITOR.instances["${nameid}"]){ //判断 是否已经被绑定
		CKEDITOR.remove(CKEDITOR.instances["${nameid}"]); //如果绑定了就解绑定
	} 
	CKEDITOR.replace("${nameid}", { 
		customConfig : '${res}/js/${ckcfg}'
	} );
	CKEDITOR.instances["${nameid}"].config.filebrowserImageBrowseUrl="http://me.maykeys.com/zres/index.jsp?userType=${uuser.roleId}&userId=${uuser.id}&hm=${hm}";
});
</script>	
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<textarea name="${nameid}" id="${nameid}" placeholder='<@s.mtct ct="global.pleaseinput" /><#if label!=""><@s.mtct ct="${label}"/></#if>' class="${class}" data-options="${dataoptions}" style="${style}" ${moreopt}>${value}</textarea>  
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
