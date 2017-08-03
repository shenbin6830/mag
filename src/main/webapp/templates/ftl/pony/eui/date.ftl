<#-- 
<input type="text"/>
-->
<#macro date
	label="" nameid="" value="" help="" moreopt=""
	>
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<input name="${nameid}" id="${nameid}" class="easyui-datetimebox" value="${value}" style="width:150px" ${moreopt}/>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
