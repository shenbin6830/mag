/**
 * 
 */

/**鼠标移动到表格行上选择的数据*/
var hoverId=0;
/**下拉框搜索的map 格式：[{'field':'value'}] ex.[{'sex':0}] */
var searchMap= new Map();
/**
 * 根据行的参数，打开新窗口
 * @param url
 * @returns
 */
function openNewWinRow(url) {
	var id=selectRow();
	if(id==null) return;		
	window.open(url+id);
}
/**
 * 打开窗口2 用于打开自带new edit之类的表，没有js/css头
 * @param url 地址
 * @param title 标题
 * @returns
 */
function openW2(url,title) {
	if(title==null || title=='')title='窗口';
	$('#w2').window({title:title});
	$('#w2').window('open');
	$('#w2').window('refresh', url);
	$('#w2').window('maximize');
}

/**
 * 打开窗口2，必须在点击行后，常用于new edit show
 * @param url
 * @param title
 * @returns
 */
function openW2WithRow(url,title) {
	var id=selectRow();
	if(id==null) return;		
	openW2(url+id,title);
}
function openWP(url,title){
	var id=selectRow();
	location.href=url+id;
}
/**
 * 打开窗口2，允许不点击行，常用于new edit show
 * @param url
 * @param title
 * @returns
 */
function openW2WithRowCan0(url,title) {
	var id=selectRowCan0();
	openW2(url+id,title);
}
/**
 * 关闭窗口2
 * @returns
 */
function closeW2(){
	$('#w2').window('close');
}
/**
 * 打开窗口1，常用于打开其它表，打开的html有完整的js/css头
 * @param url
 * @returns
 */
function openW1(url) {
	$('#show_w1').attr("src",url);
	$('#w1').window('open');
	$('#w1').window('maximize');
	setTimeout(function(){addCancelW1();},3000);
}
/**
 * 根据行的参数，打开其它表
 * @param url
 * @returns
 */
function openW1WithRow(url) {
	var id=selectRow();
	if(id==null) return;		
	openW1(url+id);	
	setTimeout(function(){addCancelW1();},3000);
}
/**
 * 根据行的参数，自己的跳转
 * @param url
 */
function hrefMeWithRow(url) {
	var id=selectRow();
	if(id==null) return;		
	window.location.href=url+id;
}
/**
 * 打开窗口1，并希望得到返回值
 * @param url
 * @param thisid 被赋值的控件id
 */
function openW1WishRet(url,thisid) {
	//用ls来传递
	localStorage.setItem("THISID",thisid);
	openW1(url);
}
/**
 * 根据行的参数打开窗口1，并希望得到返回值
 * @param url
 * @param thisid 被赋值的控件id
 */
function openW1WishRetWithRow(url,thisid) {
	//用ls来传递
	localStorage.setItem("THISID",thisid);
	var id=selectRow();
	if(id==0) return;		
	openW1(url+id);
}
/**
 * 关闭窗口1
 * @returns
 */
function closeW1(){
	$('#w1').window('close');
}
/**
 * new.html初始化 ueditor版本
 * 提交方式：<a class="easyui-linkbutton" data-options="iconCls:'tick'" href="javascript:void(0)" id="formSubmit">提交</a>
 * @param url /base/user/{className}/create.html or createmuti.html
 * @returns
 */
function newHtmlInit2(url){
	//检查要提交的表单	   
	 $("#formSubmit").click(function() {
		 var formParam = $("#form").serialize();//序列化表格内容为字符串 
		 $.ajax({
			 	type : 'POST',
		        url : url,
		        data: formParam,
		        dataType : 'json',
		        //同步
		        async : false,
		        success : function(data) {
		        	parent.$.messager.progress('close');
					if (data.success) {
						loadData();
						closeW2();
						if(data.msg=="redirect")
							window.parent.location.href=data.obj;
					} else {
						alert(data.msg);
					}		        	
		        },
		        beforeSend:function(){
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					var isValid = $(this).form('validate');
					if (!isValid) {
						parent.$.messager.progress('close');
					}
					return isValid;		        	
		        },
		        error : function() {
		        	parent.$.messager.progress('close');
			    }
			});
	 });
}

/**
 * new.html初始化 ckeditor版本
 * 提交方式<a class="easyui-linkbutton" data-options="iconCls:'tick'" href="javascript:void(0)" onclick="javascript:$('#form').submit()">提交</a>
 * @param url /base/user/{className}/create.html or createmuti.html
 * @returns
 */
function newHtmlInit(url){
	parent.$.messager.progress('close');
	$('#form').form({
		url : url,
		onSubmit : function() {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			var isValid = $(this).form('validate');
			if (!isValid) {
				parent.$.messager.progress('close');
			}
			return isValid;
		},
		success : function(result) {
			parent.$.messager.progress('close');
			result = $.parseJSON(result);
			if (result.success) {
				loadData();
				closeW2();
				if(result.msg=="redirect")
					window.parent.location.href=result.obj;
			} else {
				parent.$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}


/**
 * edit.html初始化 ueditor版本
 * @param url '{base}/user/{className}/update.html'
 * @returns
 */
function editHtmlInit2(url){
	//检查要提交的表单	   
	 $("#formSubmit").click(function() {
		 var formParam = $("#form").serialize();//序列化表格内容为字符串 
		 $.ajax({
			 	type : 'POST',
		        url : url,
		        data: formParam,
		        dataType : 'json',
		        //同步
		        async : false,
		        success : function(data) {
		        	parent.$.messager.progress('close');
					if (data.success) {
						loadData();
						closeW2();
						if(data.msg=="redirect")
							window.parent.location.href=data.obj;
					} else {
						alert(data.msg);
					}		
		        },
		        beforeSend:function(){
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					var isValid = $(this).form('validate');
					if (!isValid) {
						parent.$.messager.progress('close');
					}
					return isValid;		        	
		        },
		        error : function() {
		        	parent.$.messager.progress('close');
			    }
			});
	 });
}

/**
 * edit.html初始化 ckeditor版本
 * @param url '{base}/user/{className}/update.html'
 * @returns
 */
function editHtmlInit(url){
	parent.$.messager.progress('close');
	$('#form').form({
		url : url,
		onSubmit : function() {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			var isValid = $(this).form('validate');
			if (!isValid) {
				parent.$.messager.progress('close');
			}
			return isValid;
		},
		success : function(result) {
			parent.$.messager.progress('close');
			result = $.parseJSON(result);
			if (result.success) {
				loadData();
				closeW2();
				if(result.msg=="redirect")
					window.location.href=result.obj;				
			} else {
				parent.$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}
/**
 * 选择行返回id
 * @returns 表.id
 */
function selectRow() {
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length==0){
		parent.$.messager.alert('错误','请选择需要操作的行(无需打勾)', 'error');
		return null;
	}
	id = rows[0].id;
	return id;
}
/**
 * 选择行返回id，可以为0
 * @returns 表.id
 */
function selectRowCan0() {
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length==0){
		return 0;
	}
	id = rows[0].id;
	return id;
}
/**
 * 选择行返回myfield
 * @param myfield
 * @returns
 */
function selectRowField(myfield) {
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length==0){
		parent.$.messager.alert('错误','请选择需要操作的行(无需打勾)', 'error');
		return null;
	}
	var value = rows[0][myfield];
	return value;
}
/**
 * 选择行返回很多field，使用占位符替换
 * @param str
 * @param myfields[]
 * @returns
 */
function selectRowManyField(str,myfields) {
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length==0){
		parent.$.messager.alert('错误','请选择需要操作的行(无需打勾)', 'error');
		return null;
	}
	var values = new Array(myfields.length);
	for(var i=0; i<myfields.length; i++){
		values[i]=rows[0][myfields[i]];
	}
	return formatdollormark(str,values);
}
/**
 * 选择行返回row
 * @returns 表.id
 */
function selectRowRetRow() {
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length==0){
		parent.$.messager.alert('错误','请选择需要操作的行(无需打勾)', 'error');
		return null;
	}
	return rows[0];
}
/**
 * 勾选择方式，选择行
 * @returns []ids 
 */
function checkedRow(){
	var ids = [];   
	var rows = dataGrid.datagrid('getChecked');   
	if (rows.length==0){
		parent.$.messager.alert('错误','请勾选数据', 'error');
		return ids;
	}
	for(var i=0;i<rows.length;i++){   
		ids.push(rows[i].id);   
	} 
	return ids;
}
/**
 * 选择行方式，用modal方式，打开子表（子表指child.id=my.id），放在w1
 * @param className 孩子名
 * @returns
 */
function modalOneChild(className){
	var id=selectRow();
	if(id==null) return;
	var url=base+"/user/"+className+"/index.html?queryhq=id='"+id+"'&newobj=id="+id+"&id="+id+"&other=fatherCreate";
	openW1(url);	
}
/**
 * 选择行方式，用modal方式，打开另一张表条件xxxId=my.id，放在w1
 * @param className 另一张表的表名
 * @param field 另一张表的xxxId字段名
 * @param other
 * @returns
 */
function modalById(className,field,other){
	var id=selectRow();
	if(id==null) return;
	var url=base+"/user/"+className+"/index.html?queryhq="+field+"='"+id+"'&newobj="+field+"="+id+"&other="+other;
	openW1(url);	
}
/**
 * 更多手工条件，选择行方式，用modal方式，打开另一张表条件xxxId=my.id，放在w1
 * ex.onclick="modalByIdMoreQuery('Orderr','memberId','',' and paid=1 ');"
 * @param className
 * @param field
 * @param other
 * @param morequery
 */
function modalByIdMoreQuery(className,field,other,morequery){
	var id=selectRow();
	if(id==null) return;
	var url=base+"/user/"+className+"/index.html?queryhq="+field+"='"+id+"'"+morequery+"&newobj="+field+"="+id+"&other="+other;
	openW1(url);	
}
/**
 * 选择行方式，用modal方式，打开另一张表条件hisfield=my.myfield，放在w1
 * ex. modalByField('SignMemberinfo','userId','id')
 * @param className
 * @param myfield
 * @param hisfield
 * @param other
 * @returns
 */
function modalByField(className,myfield,hisfield,other){
	var value = selectRowField(myfield);
	if(isblank(value))
		return;
	var url=base+"/user/"+className+"/index.html?queryhq="+hisfield+"='"+value+"'&newobj="+hisfield+"="+value+"&other="+other;
	openW1(url);	
}
/**
 * 选择行方式，用modal方式，打开另一张表2组条件hisfield1=my.myfield1 and hisfield2=my.myfield2，放在w1
 * ex.	modalByField2('Genkv','id','userId','roleId','roleId','','')
 * @param className
 * @param myfield
 * @param hisfield
 * @param other
 * @param moreqh 更多的查询条件
 * @returns
 */
function modalByField2(className,myfield1,hisfield1,myfield2,hisfield2,other,moreqh){
	var value1 = selectRowField(myfield1);
	if(isblank(value1))
		return;
	var value2 = selectRowField(myfield2);
	if(isblank(value2))
		return;
	var url=base+"/user/"+className+"/index.html?queryhq="+hisfield1+"='"+value1+"' and "+hisfield2+"='"+value2+"'";
	if(!isblank(moreqh)) url = url + " and ("+moreqh+")";
	url = url +"&newobj="+hisfield1+"="+value1+"&other="+other;
	openW1(url);	
}
/**
 * modalByField2的日期版本hisfield2，myfield2为日期
 * 选择行方式，用modal方式，打开另一张表2组条件hisfield1=my.myfield1 and hisfield2=my.myfield2，放在w1
 * ex.	modalByField2('Genkv','id','userId','roleId','roleId','','1=1')
 * @param className
 * @param myfield
 * @param hisfield
 * @param other
 * @param moreqh 更多的查询条件
 * @returns
 */
function modalByField3(className,myfield1,hisfield1,myfield2,hisfield2,other,moreqh){
	var value1 = selectRowField(myfield1);
	if(isblank(value1))
		return;
	var value2 = selectRowField(myfield2);
	if(isblank(value2))
		return;
	var str =value2.toString();
	str = str.replace(/-/g,"/");
	var date = new Date(str );
	var isdate = new Date((date/1000+86400)*1000)
	var pdate = isdate.getFullYear()+"-"+(isdate.getMonth()+1)+"-"+(isdate.getDate()); 
	var pdate2=pdate+" 00:00:00";
	var url=base+"/user/"+className+"/index.html?queryhq="+hisfield1+"='"+value1+"' and "+hisfield2+">'"+value2+"' and "+hisfield2+"<='"+pdate2+"' and ("+moreqh+")&newobj="+hisfield1+"="+value1+"&other="+other;
	openW1(url);	
}
/**
 * 选择行方式，用modal方式，打开另一张表条件hisfield=my.myfield+更多的查询，放在w1
 * ex.	modalByFieldMore('Genkv','id','userId','','ilevel=0')
 * @param className
 * @param myfield
 * @param hisfield
 * @param other
 * @param moreqh 更多的查询条件
 * @returns
 */
function modalByFieldMore(className,myfield,hisfield,other,moreqh){
	var value = selectRowField(myfield);
	if(isblank(value))
		return;
	var url=base+"/user/"+className+"/index.html?queryhq="+hisfield+"='"+value+"' and ("+moreqh+")&newobj="+hisfield+"="+value+"&other="+other;
	openW1(url);	
}
/**
 * 选择行方式，用链接方式，打开另一张表条件xxxId=my.id
 * @param className
 * @param field
 * @param other
 * @returns
 */
function linkById(className,field,other){
	var id=selectRow();
	if(id==null) return;
	var url=base+"/user/"+className+"/index.html?queryhq="+field+"='"+id+"'&newobj="+field+"="+id+"&other="+other;
	window.location.href=url;
}
/**
 * 选择行方式，用modal方式，打开另一张表条件hisfield=my.myfield，放在w1
 * @param className
 * @param myfield
 * @param hisfield
 * @param other
 * @returns
 */
function linkByField(className,myfield,hisfield,other){
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length==0)
		return 0;
	var value = rows[0][myfield];
	var url=base+"/user/"+className+"/index.html?queryhq="+hisfield+"='"+value+"'&newobj="+hisfield+"="+value+"&other="+other;
	window.location.href=url;
}
/**
 * 打开窗口选择另一张表，返回另一张表选中数据
 * <br>参数还包括url和 needField，但是在附属的div中
 * @param value 搜索框中输入的内容
 * @param name 类似XXXIdString的字符串，存放搜索结果
 */
function searchOtherId(value,name) {
	var searchboxId = name;
	var url = $('div[forid="'+ searchboxId +'"]').text();
	if(url.indexOf("http://")==0 || url.indexOf("https://")==0){}else{
		url=base+url;
	}
	var needField = $('div[forid="'+ searchboxId +'"]').attr('needField') + "";
	show_w1.location.href=url;
	$('#w1').window('open');
	$('#w1').window('maximize');
	setTimeout(function(){addCancelW1();addAcceptW1(searchboxId,needField);},3000);
}
/**
 * 通过勾选方式进行操作
 * @param url 操作的url ex.'{base}/user/{className}/delbatch.html'
 * @param isalert 是否要警告 true/false
 * @param msg 提示的内容 ex.'删除'
 * @param params 更多的提交参数对象数组 ex.[{name:'zmax'},{age:1}]
 */
function checkedDo(url,isalert,msg,params){
	var ids = checkedRow();
	if(ids.length==0) return;
	if(isalert){
		if(!confirm('您是否要'+msg+'所有勾选数据？')){
		       return false;
		}
	}
	
	var data={ids:ids};
	if(!isblanklist(params)){
		$.each( params, function(index, value){
			_.extend(data, value);
		}); 
	}
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据'+msg+'处理中，请稍后....'
	});
	
	callPost(msg,url,data);
}
/**
 * 通过点击选择行方式进行操作
 * @param url 操作的url ex.'{base}/user/{className}/delbatch.html'
 * @param isalert 是否要警告 true/false
 * @param msg 提示的内容 ex.'删除'
 * @param params 更多的提交参数对象数组 ex.[{name:'zmax'},{age:1}]
 */
function selectedDo(url,isalert,msg,params){
	var idvalue=selectRow();
	if(idvalue==null) return;
	if(isalert){
		if(!confirm('您是否要'+msg+'选中数据？')){
		       return false;
		}
	}
	
	var data={id:idvalue};
	if(!isblanklist(params)){
		$.each( params, function(index, value){
			_.extend(data, value);
		}); 
	}
	
	callPost(msg,url,data);
}
/**
 * 通过点击选择行方式进行操作之数据转换
 * @param url 操作的url ex.'{base}/user/{className}/delbatch.html'
 * @param isalert 是否要警告 true/false
 * @param msg 提示的内容 ex.'删除'
 * @param data 不需要转换的参数 ex.[{name:'cname'},{age:'age'}]，会提交&name=cname&age=age
 * @param params 需要转换的参数 ex.[{name:'cname'},{age:'age'}]，会提交&name=row['cname']&age=row['age']
 */
function selectedDoDataTrans(url,isalert,msg,data,params){
	alert("what");
	var row=selectRowRetRow();
	if(row==null) return;
	if(isalert){
		if(!confirm('您是否要'+msg+'选中数据？')){
		       return false;
		}
	}
	if(!isblanklist(params)){
		$.each( params, function(index, value){
			
			_.extend(data, row[value]);
		}); 
	}
	
	callPost(msg,url,data);
}
/**
 * 鼠标移到行上进行的操作
 * @param url
 * @param isalert
 * @param msg
 * @param params
 * @returns {Boolean}
 */
function hoverDo(url,isalert,msg,params){
	if(hoverId==0) return;
	if(isalert){
		if(!confirm('您是否要'+msg+'此数据？')){
		       return false;
		}
	}
	
	var data={id:hoverId};
	if(!isblanklist(params)){
		$.each( params, function(index, value){
			_.extend(data, value);
		}); 
	}
	
	callPost(msg,url,data);
}
/**
 * hover版的选择行方式，用modal方式，打开另一张表条件xxxId=my.id，放在w1
 * @param className 另一张表的表名
 * @param field 另一张表的xxxId字段名
 * @param other
 * @returns
 */
function hoverModalById(className,field,other){
	if(hoverId==0) return;
	var id=hoverId;
	var url=base+"/user/"+className+"/index.html?queryhq="+field+"='"+id+"'&newobj="+field+"="+id+"&other="+other;
	openW1(url);	
}

/**
 * 顶部工具栏中没有选择就进行了操作
 * @param url 操作的url ex.'{base}/user/{className}/delbatch.html'
 * @param isalert 是否要警告 true/false
 * @param msg 提示的内容 ex.'删除'
 * @param params 更多的提交参数对象数组 ex.[{name:'zmax'},{age:1}]
 */
function topNoSelectedDo(url,isalert,msg,params){
	if(isalert){
		if(!confirm('您是否要'+msg+'？')){
		       return false;
		}
	}
	
	var data={};
	if(!isblanklist(params)){
		$.each( params, function(index, value){
			_.extend(data, value);
		}); 
	}
	callPost(msg,url,data);
}
/**
 * 调用Post ajax
 * @param msg
 * @param url
 * @param data
 */
function callPost(msg,url,data){
	parent.$.messager.progress({
		title : '提示',
		text : '数据'+msg+'处理中，请稍后....'
	});
	
	$.post(url, data, function(result) {
		if (result.success) {
			if(result.msg!="noalert")
				parent.$.messager.alert('提示', result.msg, 'info');
			dataGrid.datagrid('clearChecked');
			loadData();
		}else{
			if(result.msg!="noalert")
				parent.$.messager.alert('出错了', result.msg, 'error');
		}
		parent.$.messager.progress('close');
	}, 'JSON');
}
/**
 * 调用Get ajax
 * @param url
 */
function callGet(url) {
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	$.get(url, function(result) {
		if (result.success) {
			if(result.msg!="noalert")
				parent.$.messager.alert('提示', result.msg, 'info');
			dataGrid.datagrid('clearChecked');
			loadData();
		}else{
			if(result.msg!="noalert")
				parent.$.messager.alert('出错了', result.msg, 'error');
		}
		parent.$.messager.progress('close');
	}, 'JSON');
}
/**
 * 勾选方式,删除
 * @param url '{base}/restpage/user/{className}/delbatch.html'
 * @returns
 */
function delByUrl(url) {
	checkedDo(url,true,"删除");
}
/**
 * 根据className删除
 * @param className
 * @returns
 */
function delCn(className) {
	var url=base+"/restpage/user/"+className+"/delbatch.html";
	delByUrl(url);
}
/**
 * 完成
 * @param url '{base}/user/{className}/finish.html'
 * @returns
 */
function finishByUrl(url) {
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		$.get(url, function(result) {
			if (result.success) {
				parent.$.messager.alert('提示', result.msg, 'info');
				loadData();
			}
			parent.$.messager.progress('close');
		}, 'JSON');
}
/**
 * 根据className完成
 * ex finishCn('Buy','statusPay=-1 and statusStore=-1')
 * @param className
 * @returns
 */
function finishCn(className,finishWhere) {
	var url=base+"/restpage/user/"+className+"/finish.html?finishWhere="+finishWhere;
	finishByUrl(url);
}
/**
 * 批量更新
 * <br>ex.updatebatchByUrl('${base}/user/{className}/updatebatch.html','statusApplyPassRefuse=1')
 * @param url '{base}/user/{className}/updatebatch.html'
 * @returns
 */
function updatebatchByUrl(url,setfield) {
	checkedDo(url,true,"操作",[{setfield:setfield}]);
}
/**
 * 根据className批量更新
 * @param className
 * @returns
 */
function updatebatchCn(className,setfield) {
	var url=base+"/restpage/user/"+className+"/updatebatch.html";
	updatebatchByUrl(url,setfield);
}
/**
 * 向LINK表批量增加，有两个参数，一个这里传，另一个IDS是选的
 * ex. insertbatchOfLink('CmscontentTypeLinkCmscontent',1)
 * @param className link表名 ex.CmscontentTypeLinkCmscontent
 * @param id1 固定ID值
 */
function insertbatchOfLink(className,id1) {
	var url=base+"/user/"+className+"/insertbatch.html";
	var ids = [];   
	var rows = dataGrid.datagrid('getChecked');   
	if (rows.length==0){
		return;
	}
	for(var i=0;i<rows.length;i++){   
		ids.push(rows[i].id);   
	} 
	parent.$.messager.confirm('询问', '您是否要批量操作所有勾选数据？', function(b) {
		if (b) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据批量操作处理中，请稍后....'
			});
			$.post(url, {
				ids : ids,
				id1:id1
			}, function(result) {
				if (result.success) {
					parent.$.messager.alert('提示', result.msg, 'info');
					dataGrid.datagrid('clearChecked');
					loadData();
				}
				parent.$.messager.progress('close');
			}, 'JSON');
		}
	});
}
/**
 * 根据hql查询
 * @param dgname
 * @param hql
 * @returns
 */
function queryByHql(hql,dgname) {
	if(dgname==undefined || dgname=="" )
		dgname="dataGrid";
	var dg=$('#' +dgname);
	dg.datagrid('options').queryParams.queryhq=hql;
	loadData();
}

/**
 * 关闭查询
 * @param dgname
 * @returns
 */
function cleanSearchFun(dgname) {
	$('#isearch').searchbox({value:''});
	$('.easyui-combobox').combobox('setValue','');
	searchMap= new Map();
	queryByHql('',dgname);
}

/**
 * 是否登录
 * @param isgo 是否跳到登录页 true是 false否
 */
function islogin(isgo){
	$.ajax({
        type: "GET",
        url: base+"/islogin",
        success: function (data) {
        	data = $.parseJSON(data)
        	if (!data.success && isgo) {
        		//没登录，并且需要跳
        		window.location.href = base+"/login.html";
        		return;
        	}
        }
    });	
}
/**
 * 是否登录，如果没登录，跳到登录页
 * @param data
 */
function checkLoginDataJump(data){
	return;
	if(data==undefined || data==''){
		return;
	}
	if (!data.success && data.message=="nologin") {
		//没登录，跳
		window.location.href = base+"/login.html";
		return;
	}
}
//属性编辑器 start
/**
 * 打开属性编辑器窗口
 * @param ctl 控件名 ex:defaultvalue
 * @param win 窗口名 ex:w1
 * @returns
 */
function openPropSelect(ctl,win) {
	showdgProp(ctl,"dataGridProp");
	$('#' +win).window('open');
}
/**
 * 打开属性编辑器窗口
 * @param ctl 控件名 ex:defaultvalue
 * @param dgname 表格名 ex:dataGridProp
 * @param win 窗口名 ex:w1
 * @returns
 */
function openPropSelect(ctl,win,dgname) {
	if(dgname==undefined || dgname=="" )
		dgname="dataGridProp";
	showdgProp(ctl,dgname);
	$('#' +win).window('open');
}
var propSelectEditIndex;
/**
 * KV版
 * 属性编辑器，规定JSON数据为'[{"id":"红","value":"222","index":"1"},{"id":"黄","value":"111","index":"2"}]'
 * @param ctl 控件名，类型是textarea ex:dg
 * @returns
 */
function showdgProp(ctl,dgname) {
	if(dgname==undefined || dgname=="" )
		dgname="dataGridProp";	
	var str=$('#' +ctl).val();
	console.log("dgname="+dgname+",ctl="+ctl+",str="+str);
	var dataPropSelect = $.parseJSON(str);
	$('#' +dgname).datagrid({
		data : dataPropSelect,
		idField : 'id',
		sortName : 'index',
		sortOrder : 'asc',
		remoteSort : false,
		border:false,
		fit:true,
		fitColumns:true,
		singleSelect:true,
		rownumbers:true,
		toolbar : [{
     	   text : '新建',
     	   iconCls : 'icon-add',
     	   handler : function() {
     		   $('#' +dgname).datagrid('appendRow', {});
     		   propSelectEditIndex = $('#' +dgname).datagrid('getRows').length - 1;
     		   $('#' +dgname).datagrid('selectRow', propSelectEditIndex);
     		   $('#' +dgname).datagrid('beginEdit', propSelectEditIndex);
     	   }
        },
        '-',
        {
     	   text : '删除',
     	   iconCls : 'icon-remove',
     	   handler : function() {
     		   var row = $('#' +dgname).datagrid('getSelected');
     		   if (row) {
     			   var index = $('#' +dgname).datagrid('getRowIndex',row);
     			   $('#' +dgname).datagrid('deleteRow',index);
     		   }
     	   }
        },
        '-',
        {
     	   text : '保存',
     	   iconCls : 'icon-save',
     	   handler : function() {
     		   $('#' +dgname).datagrid('acceptChanges');
     		   var jstr = $.toJSON($('#' +dgname).datagrid('getData')['rows']);
     		   //console.log(jstr);
     		   $('#' +ctl).val(jstr);
     	   }
        },
        '-',
        {
     	   text : '撤消',
     	   iconCls : 'icon-undo',
     	   handler : function() {
     		   $('#' +dgname).datagrid('rejectChanges');
     	   }
        } 
        ],
		onBeforeLoad : function() {
		        	   $(this).datagrid('rejectChanges');
		           },
		onClickRow : function(rowIndex) {
		        	   $('#' +dgname).datagrid('selectRow',rowIndex);
		        	   $('#' +dgname).datagrid('beginEdit',rowIndex);
		           },
		columns : [ [ 
		             {field : 'id',title : '显示',width : 80,editor : 'text'}, 
		             {field : 'value',title : '内容',width : 80,editor : 'text'}, 
		             {field : 'index',title : '顺序',width : 40,editor : 'text'} 
		          ] ]
	});
}
/**
 * id版
 * 属性编辑器，规定JSON数据为'[{"id":"红","iindex":"1"},{"id":"黄","iindex":"2"}]'
 * @param ctl 控件名，类型是textarea ex:dg
 * @returns
 */
function showdgProp1(ctl,dgname) {
	if(dgname==undefined || dgname=="" )
		dgname="dataGridProp";	
	var str=$('#' +ctl).val();
	console.log("dgname="+dgname+",ctl="+ctl+",str="+str);
	var dataPropSelect = $.parseJSON(str);
	$('#' +dgname).datagrid({
		data : dataPropSelect,
		idField : 'id',
		sortName : 'iindex',
		sortOrder : 'asc',
		remoteSort : false,
		border:false,
		fit:true,
		fitColumns:true,
		singleSelect:true,
		rownumbers:true,
		toolbar : [{
     	   text : '新建',
     	   iconCls : 'icon-add',
     	   handler : function() {
     		   $('#' +dgname).datagrid('appendRow', {});
     		   propSelectEditIndex = $('#' +dgname).datagrid('getRows').length - 1;
     		   $('#' +dgname).datagrid('selectRow', propSelectEditIndex);
     		   $('#' +dgname).datagrid('beginEdit', propSelectEditIndex);
     	   }
        },
        '-',
        {
     	   text : '删除',
     	   iconCls : 'icon-remove',
     	   handler : function() {
     		   var row = $('#' +dgname).datagrid('getSelected');
     		   if (row) {
     			   var index = $('#' +dgname).datagrid('getRowIndex',row);
     			   $('#' +dgname).datagrid('deleteRow',index);
     		   }
     	   }
        },
        '-',
        {
     	   text : '保存',
     	   iconCls : 'icon-save',
     	   handler : function() {
     		   $('#' +dgname).datagrid('acceptChanges');
     		   var jstr = $.toJSON($('#' +dgname).datagrid('getData')['rows']);
     		   //console.log(jstr);
     		   $('#' +ctl).val(jstr);
     	   }
        },
        '-',
        {
     	   text : '撤消',
     	   iconCls : 'icon-undo',
     	   handler : function() {
     		   $('#' +dgname).datagrid('rejectChanges');
     	   }
        } 
        ],
		onBeforeLoad : function() {
		        	   $(this).datagrid('rejectChanges');
		           },
		onClickRow : function(rowIndex) {
		        	   $('#' +dgname).datagrid('selectRow',rowIndex);
		        	   $('#' +dgname).datagrid('beginEdit',rowIndex);
		           },
		columns : [ [ 
		             {field : 'id',title : '内容',width : 100,editor : 'text'}, 
		             {field : 'iindex',title : '顺序',width : 100,editor : 'text'} 
		          ] ]
	});
}

/**
 * 在w1窗口中的工具栏最前面新增一个关闭按钮
 */
function addCancelW1() {
  var cancelButton = '<div id="cancel" style="display:inline;"><a id="cancelButton" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:\'cancel\'" onclick="javascript:parent.$(\'#w1\').window(\'close\');" >关闭</a></div>';
  var iframe = $(window.frames["show_w1"].document),
      toolbar=iframe.find('#toolbar');
  toolbar.prepend(cancelButton);
  $.parser.parse(iframe.find('#cancel'));  
}

/**
 * 在w1窗口中的工具栏最前面新增一个确认按钮
 * @param searchboxId  需要赋值的目标ID，用于传入确认按钮所在的方法中
 * @param needField 需要从搜索结果中取出用于显示的字段名
 */
function addAcceptW1(searchboxId,needField) {
  var dataOptions = "plain:true,iconCls:'arrow_accept'",
      parameter = "'" + searchboxId + "','" + needField + "'",
      acceptButton = '<div id="accept" style="display:inline;"><a id="acceptButton" href="javascript:void(0);" class="easyui-linkbutton" data-options="' + dataOptions + '" onclick="javascript:setSelectedId(parent,'+ parameter +');" >确认</a></div>';
  var iframe = $(window.frames["show_w1"].document),
      toolbar=iframe.find('#toolbar');
  toolbar.prepend(acceptButton);
  $.parser.parse(iframe.find('#accept'));  
}

/**
 * 获得选中行的ID，并赋值给父集窗口中searchboxId所在的input，同时关闭当前窗口
 * @param parent iframe的父级页面对象
 * @param searchboxId 需要赋值的目标ID
 * @param needField 需要从搜索结果中取出用于显示的字段名
 */
function setSelectedId(parent,searchboxId,needField){
  var selectedRow = getSelectedRow(),
      searchbox = parent.$('#'+searchboxId);
  if (!searchbox) return;
  searchbox.searchbox('setValue',selectedRow['' + needField]);  //不能直接用userId.val()去赋值
  var targetId = searchboxId.substr(0,searchboxId.indexOf('String'));
  parent.$('#'+targetId).val(selectedRow['id']); //最终存放选中行的id的地方
  parent.$('#w1').window('close');
}

/**
 * 
 * @returns 选中行的所有信息
 */
function getSelectedRow() {
  var rows = dataGrid.datagrid('getSelections');
  if (rows.length==0)
    return 0;
  return rows[0];
}
//属性编辑器 end

/**
 * 日期范围选择 ex.onclick="searchFunDateBetween('gmtCreate')"
 * @param gmt 控件名
 */
function searchFunDateBetween(gmt) {
	var dateBegin=$('#'+gmt+'Begin').datebox('getValue');
	var dateEnd=$('#'+gmt+'End').datebox('getValue');		
	if(isblank(dateBegin) || isblank(dateEnd) || dateBegin>dateEnd){
		alert("日期不为能空，并且开始时间要小于结束时间！");
		return;
	}
	dateEnd=tomorrow(dateEnd);
	var searchlike =" ("+gmt+" >= ' "+dateBegin+" 00:00:00' and "+gmt+" < '"+dateEnd+" 00:00:00') ";
	queryByHql(searchlike);
}
/**
 * 下拉框搜索的map转hql
 * @returns {String}
 */
function searchMap2Hql() {
	var hql="";
	searchMap.forEach(function(key ,value ,index) {
		if(key && key!=""){
			hql+=value+'='+key+" and ";
		}
	});
	if(hql.length > 4)
		hql=hql.substring(0,(hql.length)-4);
	return hql;
}