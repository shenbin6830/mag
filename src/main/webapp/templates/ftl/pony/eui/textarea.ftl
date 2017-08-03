<#--
<input type="text"/>
-->
<#macro textarea
	label="" nameid="" class="easyui-validatebox" dataoptions="" value="" help="" style="height:100px;" moreopt="" 
	>
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<textarea name="${nameid}" id="${nameid}" placeholder='<@s.mtct ct="global.pleaseinput" /><#if label!=""><@s.mtct ct="${label}"/></#if>' class="${class}" data-options="${dataoptions}" style="${style}" ${moreopt} >${value}</textarea>  
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
