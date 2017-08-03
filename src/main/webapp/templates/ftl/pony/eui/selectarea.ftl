<#--
省市县地区选择，需要加载area.js
-->
<#macro selectarea	
    label=""     nameid=""    value=""    help="" multiple="false" 
    class="easyui-combotree" dataoptions="data:areaTree"
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
    onSelect="" moreopt=""
    >
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<#if value?length==0><#local value="0"></#if>
<select name="${nameid}" id="${nameid}" class="${class}" multiple="${multiple}" data-options="${dataoptions},value:${value},cascadeCheck:false" ${moreopt} <#rt/> 
<#include "/ftl/pony/eui/scripting-events.ftl"/><#rt/>
><#rt/>
</select>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
