<#-- 
<input type="text"/>
-->
<#macro searchforid
	label="" nameid="" class="easyui-searchbox" dataoptions="" value="" stringvalue="" help="" url="" needField="" moreopt=""
	>
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<input name="${nameid}String" id="${nameid}String" type="text" placeholder='<@s.mtct ct="global.pleaseinput" /><#if label!=""><@s.mtct ct="${label}"/></#if>' class="easyui-searchbox" data-options="${dataoptions}"  value="${stringvalue}" ${moreopt} /><#rt/>
<div id="${nameid}menu" style="display:none;">
	<div forid="${nameid}String" needField="${needField}" data-options="name:'${nameid}String',iconCls:'icon-ok'">${url}</div> 
</div>  
<input type="hidden" id="${nameid}" name="${nameid}" value="${value}">
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
