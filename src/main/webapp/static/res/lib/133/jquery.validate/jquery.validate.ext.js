$.metadata.setType("attr", "vld");
//用户名
$.validator.addMethod("username", function(value) {
	if(value.length==0) {return true;}
	var p = /^[0-9a-zA-Z\u4e00-\u9fa5\.\-@_]+$/;
	return p.exec(value) ? true : false;
}, "Please enter only letters,digits,chinese and '_','-','@'");
//路径名
$.validator.addMethod("path", function(value) {
	if(value.length==0) {return true;}
	var p = /^[0-9a-zA-Z]+$/;
	return p.exec(value) ? true : false;
}, "Please enter only letters and digits");
//手机号
$.validator.addMethod("mobile", function(value, element) {
    var length = value.length;   
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
    return (length == 11 && mobile.exec(value))? true:false;
}, "请正确填写您的手机号码");
//邮编验证
$.validator.addMethod("zipcode", function(value, element) {       
    var tel = /^[0-9]{6}$/;       
    return (tel.exec(value))?true:false;       
}, "请正确填写您的邮编");

//手机版错误显示
$.validator.setDefaults({
	/**
	 * 修改validator默认的showErrors方法，错误提示信息以message来进行提示
	 */
	showErrors : function(errorMap, errorList) {
		var msg = "";
		$.each( errorList, function(i,v){
		  msg += (v.message+"|");
		  //v.element.placeholder+=" "+v.message;
		});
		if(msg!="") {
	        $(".errMessage").css("color", "#ff0000");
	        $(".errMessage").text(msg);
		}		
	},
	/* 失去焦点时不验证 */ 
	onfocusout: false
}); 


$.extend($.validator.messages, {
	required : "请填写必填项",
	remote : "请修正该字段",
	email : "请输入正确格式的电子邮件",
	url : "请输入合法的网址",
	date : "请输入合法的日期",
	dateISO : "请输入合法的日期 ",
	number : "请输入合法的数字",
	digits : "只能输入整数",
	creditcard : "请输入合法的信用卡号",
	equalTo : "请再次输入相同的值",
	accept : "请输入拥有合法后缀名的字符串",
	maxlength : $.format("请输入一个长度最多是 {0} 的字符串"),
	minlength : $.format("请输入一个长度最少是 {0} 的字符串"),
	rangelength : $.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	range : $.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max : $.format("该项不能大于 {0}"),
	min : $.format("该项不能小于 {0}"),
	username : "只能输入字符、数字、中文、和 _ - @ 的组合",
	mobile : "请正确填写您的手机号码",
	zipcode : "请正确填写您的邮编",
	path : "只能输入字符和数字的组合"
});