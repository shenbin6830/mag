<input type="radio" name="${nameid}" id="${nameid}" value="${rkey}" ${moreopt}<#rt/> 
<#if (rkey?string=="" && (!value?? || value?string=="")) || (value?? && value?string!="" && value?string==rkey?string)> checked="checked"</#if><#rt/>
/><@s.mt code=rvalue text=rvalue/>&nbsp;&nbsp;<#if hasNext> </#if>