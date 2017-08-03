<#-- radio 之根据本地map动态创建
<input type="radio"/>
-->
<#macro radiolocal
    mapname label="" nameid="" value="" help="" value="" required="false" moreopt="" >
<#include "/ftl/pony/eui/controlb.ftl"/><#rt/>
 <script language="javascript" type="text/javascript">
 $(document).ready(function(){
	var app = "";
	var index=0;
	_.each(${mapname}, function(value,key){
	 app = app + "<input type='radio' name='${nameid}' id='${nameid}_rd_"+(++index)+"' "+((key=='${value}')?" checked=\"checked\" ":"")+" value='"+key+"' />" + value + " ";
	});
	$("#group_${nameid}").append(app);
  
 })
 </script>  
 <span id="group_${nameid}"></span>
<#include "/ftl/pony/eui/controle.ftl"/><#rt/>
</#macro>