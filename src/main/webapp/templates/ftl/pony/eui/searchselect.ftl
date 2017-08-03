<#--
搜索版简单的下拉框(多条件搜索版本)
<select><option></option></select>
-->
<#macro searchselect
    list=[]   
    listKey="key"  listValue="value" listDeep=""
    headerKey="" headerValue=""
    label=""     nameid=""    value=""    help="" 
    class="easyui-combobox" dataoptions="" moreopt=""
    >
 <script language="javascript" type="text/javascript">
 $(document).ready(function(){
 		$('#search_${nameid}').combobox({
			onSelect: function(record){
				searchMap.set('${nameid}',record.value);
				var hql=searchMap2Hql();
				queryByHql(hql);
			}
		});
 })
 </script>    
 <#if label!=""><@s.mtct ct="${label}"/>:</#if>
<select name="search_${nameid}" id="search_${nameid}" class="${class}" data-options="${dataoptions}" ${moreopt} <#rt/> 
<#include "/ftl/pony/eui/scripting-events.ftl"/><#rt/>
><#rt/>
<#if headerKey!="" || headerValue!="">
	<option value="${headerKey}"<#if headerKey==value?string> selected="selected"</#if>><@s.mt code=headerValue text=headerValue/></option><#t/>
</#if>
<#if list?is_sequence>
		<#list list as item>
			<option value="${item[listKey]}" >${item[listValue]!}</option><#t/>
		</#list>
<#else>
	<#list list?keys as key>
		<option value="${key}"><@s.mt code=list[key] text=list[key]/></option><#t/>
	</#list>
</#if>
</select>

</#macro>
