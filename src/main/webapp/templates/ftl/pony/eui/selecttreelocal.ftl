<#--
选择树之本地树版，需要加载相应的本地树.js
示例:<圈p.selecttreelocal tree_field="tree_areaid" label="db.Hospital.areaid" nameid="areaid" value="${hospital.areaid}" help="${hospital.areaidString}" />
-->
<#macro selecttreelocal
    label=""     nameid=""    value=""    help="" multiple="false" 
    class="easyui-combotree" tree_field="tree_areaid"
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
    onSelect="" moreopt=""
    >
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<#if value?length==0><#local value="0"></#if>
<select name="${nameid}" id="${nameid}" class="${class}" ${multiple} data-options="data:${tree_field},value:${value},cascadeCheck:false" ${moreopt} <#rt/> 
<#include "/ftl/pony/eui/scripting-events.ftl"/><#rt/>
><#rt/>
</select>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
