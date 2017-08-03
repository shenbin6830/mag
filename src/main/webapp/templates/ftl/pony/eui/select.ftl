<#--
简单的下拉框
<select><option></option></select>
-->
<#macro select
    list  
    listKey=""  listValue="" listDeep="" multiple=""
    headerKey="" headerValue=""
    label=""     nameid=""    value=""    help="" 
    class="easyui-combobox" dataoptions=""
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
    onSelect="" moreopt=""
    >
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>


<select name="${nameid}" id="${nameid}" class="${class}" data-options="${dataoptions}" ${moreopt} <#rt/> 
<#include "/ftl/pony/eui/scripting-events.ftl"/><#rt/>
><#rt/>
<#if headerKey!="" || headerValue!="">
	<option value="${headerKey}"<#if headerKey==value?string> selected="selected"</#if>><@s.mt code=headerValue text=headerValue/></option><#t/>
</#if>
<#if list?is_sequence>
		<#if listDeep!="" && list?size gt 0><#local origDeep=list[0][listDeep]+1/></#if>
		<#list list as item>
			<option value="${item[listKey]}"<#if item[listKey]?string==value?string> selected="selected"</#if>><#if listDeep!="" && item[listDeep] gte origDeep><#list origDeep..item[listDeep] as i>&nbsp;&nbsp;</#list>></#if>${item[listValue]!}</option><#t/>
		</#list>
<#else>
	<#list list?keys as key>
		<option value="${key}"<#if key==value?string> selected="selected"</#if>><@s.mt code=list[key] text=list[key]/></option><#t/>
	</#list>
</#if>
</select>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
