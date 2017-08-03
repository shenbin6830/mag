<#--
选择树之服务器版，动态树下拉框,打勾选中
<select><option></option></select>
-->
<#macro selecttree
    url	
    headerKey="" headerValue=""
    label=""     nameid=""   help="" multiple="" width=400  value=""
    class="easyui-combotree"  moreopt=""
    >
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>

<select name="${nameid}<#if !multiple=="">s</#if>" id="${nameid}<#if !multiple=="">s</#if>" class="${class}" data-options="url:'${url}',cascadeCheck:false" ${multiple} style="width:${width}px;" ${moreopt}>
<#if headerKey!="" || headerValue!="">
	<option value="${headerKey}"<#if headerKey==value?string> selected="selected"</#if>><@s.mt code=headerValue text=headerValue/></option><#t/>
</#if>
</select>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
