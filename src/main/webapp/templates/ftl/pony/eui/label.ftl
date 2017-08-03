<#-- 
<input type="label"/>
-->
<#macro label
	label="" nameid="" html="html" class="" dataoptions="" value="" help="" moreopt=""
	>
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<#if value?? && value?string!="">
<#if !html?? && html?string="html">
${value?html}
<#else>
${value}
</#if> ${moreopt}
</#if>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
