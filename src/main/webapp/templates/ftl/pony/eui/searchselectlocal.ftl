<#--
搜索版简单的下拉框本地版
<select><option></option></select>
-->
<#macro searchselectlocal
	mapname <#--js对象名-->
	headerKey="" headerValue="" <#--头显示与值通常是全部和人-->
    label=""     nameid=""    value=""    help="" 
    class="easyui-combobox" dataoptions="" moreopt=""
    >
 <script language="javascript" type="text/javascript">
 $(document).ready(function(){
		var app = "";
		var index=0;
		_.each(${mapname}, function(value,key){
		 app = app + "<option value='"+key+"' >"+value+"</option>";
		});
		$("#searchselectlocal_${nameid}").append(app); 
		console.log(app);
 		$('#searchselectlocal_${nameid}').combobox({
			onSelect: function(record){
				searchMap.set('${nameid}',record.value);
				var hql=searchMap2Hql();
				queryByHql(hql);
			}
		});
 })
 </script>    
 <#if label!=""><@s.mtct ct="${label}"/>:</#if>
<select name="searchselectlocal_${nameid}" id="searchselectlocal_${nameid}" class="${class}" data-options="data:${mapname},${dataoptions}" ${moreopt} <#rt/> 
<#include "/ftl/pony/eui/scripting-events.ftl"/><#rt/>
><#rt/>
<#if headerKey!="" || headerValue!="">
	<option value="${headerKey}"<#if headerKey==value?string> selected="selected"</#if>><@s.mt code=headerValue text=headerValue/></option><#t/>
</#if>
</select>

</#macro>
