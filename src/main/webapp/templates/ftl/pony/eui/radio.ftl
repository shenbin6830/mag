<#-- 
<input type="radio"/>
-->
<#macro radio
    list  
    label=""     nameid=""    value=""    help="" 
	listKey=""  listValue=""	required="false" moreopt="" >
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<#if list?is_sequence>
	<#if listKey!="" && listValue!="">
		<#list list as item>
			<#local rkey=item[listKey]>
			<#local rvalue=item[listValue]>
			<#local index=item_index>
			<#local hasNext=item_has_next>
			<#include "/ftl/pony/eui/radio-item.ftl"><#t/>
		</#list>
	<#else>
		<#list list as item>
			<#local rkey=item>
			<#local rvalue=item>
			<#local index=item_index>
			<#local hasNext=item_has_next>
			<#include "/ftl/pony/eui/radio-item.ftl"><#t/>
		</#list>
	</#if>
<#else>
	<#list list?keys as key>
		<#local rkey=key/>
		<#local rvalue=list[key]/>
		<#local index=key_index>
		<#local hasNext=key_has_next>
		<#include "/ftl/pony/eui/radio-item.ftl"><#t/>
	</#list>
</#if>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>