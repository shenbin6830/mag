var sysjsname="mag";
//全局配置
var global={
	//WEBSITE:"http://www.51silk.cn",
	WEBSITE:"http://mag1.dian-jin.com/",
	//WEBSITE:"http://192.168.0.158",
	//WEBSITE:"http://localhost/dfyc",
	userId:''
}
/**服务器base，遇到图片、资源时，会使用这个，表中图地址会包括sysname*/
var hbase=global.WEBSITE;

/**网站base，相对路径，常用于后台js*/
var base="/"+sysjsname;
/**网站base，绝对路径，常用于客户端js*/
var wbase=global.WEBSITE+"/"+sysjsname;
/**ajax 访问base*/
var abase=wbase+"/www";
/**用户资源base*/
var ufbase=wbase+"/userfiles";
/**用户页面base*/
var ubase=wbase+"/ru/www";

var userSiteUrl=wbase;
//var userSiteUrl="http://xxx.zjca.com.cn/user";
//var base="/"+sysjsname;
//document.domain='zjsc.org.cn';