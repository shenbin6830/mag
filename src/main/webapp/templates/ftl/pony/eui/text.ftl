<#--
<input type="text"/>
-->
<#macro text
	label="" nameid="" class="easyui-validatebox" dataoptions="" value="" help="" moreopt=""
	>
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<input name="${nameid}" id="${nameid}" type="text" placeholder='<@s.mtct ct="global.pleaseinput" /><#if label!=""><@s.mtct ct="${label}"/></#if>' class="${class}" data-options="${dataoptions}"  value="${value}" ${moreopt} /><#rt/>  
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
