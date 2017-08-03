<#--
日期范围选择，进行日期范围查询
-->
<#macro datebetween
	label="" nameid=""
	>
<#if label!=""><@s.mtct ct="${label}"/>:</#if>
从<input class="easyui-datebox" id="${nameid}Begin" data-options="editable:false" style="width:150px"/>
到<input class="easyui-datebox" id="${nameid}End" data-options="editable:false" style="width:150px"/>
<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="searchFunDateBetween('${nameid}')"></a>
</#macro>
