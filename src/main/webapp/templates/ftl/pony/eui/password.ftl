<#-- 
<input type="password"/>
-->
<#macro password
	label="" nameid="" class="easyui-validatebox" dataoptions="" value="" help="" moreopt=""
	>
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
<input name="${nameid}" id="${nameid}" type="password" placeholder='<@s.mtct ct="global.pleaseinput" /><#if label!=""><@s.mtct ct="${label}"/></#if>' class="${class}" value="${value}" data-options="${dataoptions}" ${moreopt} /><#rt/>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>

<tr><#if label!=""><th><@s.mtct ct="global.again" /><@s.mtct ct="${label}"/></th></#if><#rt/><td> <#rt/>
<input name="re_${nameid}" id="re_${nameid}" type="password" placeholder='<@s.mtct ct="global.pleaseinput" /><#if label!=""><@s.mtct ct="${label}"/></#if>' class="${class}" value="${value}" data-options="required:true,validType:'eqPwd[\'#${nameid}\']'" />
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>
