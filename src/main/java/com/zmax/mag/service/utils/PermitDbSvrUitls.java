package com.zmax.mag.service.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.mag.domain.bean.Permitfield;
import com.zmax.mag.domain.bean.Permittable;
import com.zmax.mag.service.my.PermitfieldService;
import com.zmax.mag.service.my.PermittableService;

/**
 * 把所有的表和字段放到权限表中，理论上表或字段发生改变时，进行一次变更操作即可。
 * 实际上每次启动都有操作，目前还没有自动删除功能。
 * @author zmax
 *
 */
@Service
public class PermitDbSvrUitls {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	PermittableService permittableService;
	@Autowired
	PermitfieldService permitfieldService;
	
	public PermitDbSvrUitls(){

	}
	public void doInit(){
		loadAllTf();
		putfield();
		permitTable();
	}	
	/**
	 * 所有的权限之表管理
	 */
	public void permitTable(){
		for (String[] atl : tList) {
			p1(atl[0], atl[1]);
		}
	}
	/**
	 * 根据表名保存系列role的permitTable
	 * @param tbname 表名
	 * @param tbalias 表说明
	 */
	public void p1(String tbname,String tbalias){
		List<Permittable> listTbAdd=new ArrayList<Permittable>();
		List<Permitfield> listFieldAdd=new ArrayList<Permitfield>();
		Map<String,String> roleMap=MapDb.getInstance().getMymap().get("User.RoleId");
		Iterator<Entry<String,String>> iter = roleMap.entrySet().iterator();
		int  pall=0, btnnew=0, btnedit=0, btndel=0, btnshow=0;
		String queryaddhql="", modigvy="";
		while (iter.hasNext()) {
			Entry<String,String> entry = iter.next(); 
			String key = entry.getKey();
			//String val = entry.getValue();
			Integer roleId=Integer.parseInt(key);
			Permittable permittable=findExistTb(roleId, tbname);
			if(permittable==null){
				permittable=new Permittable(null, roleId, tbname, tbalias, pall, btnnew, btnedit, btndel, btnshow, queryaddhql, modigvy, null);
				//permittableService.save(permittable);
				listTbAdd.add(permittable);
				
			}
			t1(listFieldAdd,roleId,tbname);
		}
		permittableService.saveOrUpdateAll(null,listTbAdd);
		permitfieldService.saveOrUpdateAll(null,listFieldAdd);
	}
	
	/**
	 * 如果不存在，保存permitField
	 * @param roleId
	 * @param permittableId
	 * @param tbname
	 */
	public void t1(List<Permitfield> listFieldAdd,int roleId, String tbname){
		List<String[]> fl=tfMap.get(tbname);
		for (String[] fs : fl) {
			Permitfield permitfield=findExistFd(roleId, tbname, fs[0]);
			if(permitfield==null){
				permitfield=new Permitfield(null, roleId,tbname, fs[0], fs[1], "", "", "", "", null);
				listFieldAdd.add(permitfield);
			}
			
		}
	}
	
	/**表及字段的Map<tbname,List<{fieldname,fieldalias}>>*/
	Map<String,List<String[]>> tfMap=new HashMap<String, List<String[]>>();
	/**表的List<{tbname,tbalias}>*/
	List<String[]> tList=new ArrayList<String[]>();
	
	/**数据库取出的表*/
	List<Permittable> listTbDb=new ArrayList<Permittable>();
	/**数据库取出的字段*/
	List<Permitfield> listFieldDb=new ArrayList<Permitfield>();
	/**
	 * 找已经存在的Tb，找到返回该对象，找不到返回null
	 * @param roleId
	 * @param tbname
	 * @return
	 */
	public Permittable findExistTb(Integer roleId,String tbname){
		for (Permittable obj : listTbDb) {
			if(roleId.equals(obj.getRoleId())&&tbname.equals(obj.getTbname())){
				return obj;
			}
		}
		return null;
	}
	public Permitfield findExistFd(Integer roleId,String tbname,String fieldname){
		for (Permitfield obj : listFieldDb) {
			if(roleId.equals(obj.getRoleId())&&tbname.equals(obj.getTbname())&&fieldname.equals(obj.getFieldname())){
				return obj;
			}
		}
		return null;
	}
	/**
	 * 从数据库中取出
	 */
	public void loadAllTf(){
		listTbDb=permittableService.listAll(null);
		listFieldDb=permitfieldService.listAll(null);
	}

	/**
	 * 存放所有表和字段
	 */
	public void putfield(){
		List<String[]> fl;
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","所属用户"});
		fl.add(new String[]{"logo","图标"});
		fl.add(new String[]{"sharetxt","分享转发说明"});
		fl.add(new String[]{"headimg1","图1"});
		fl.add(new String[]{"headimgtxt1","图说明1"});
		fl.add(new String[]{"headimglink1","图链接1"});
		fl.add(new String[]{"headimg2","图2"});
		fl.add(new String[]{"headimgtxt2","图说明2"});
		fl.add(new String[]{"headimglink2","图链接2"});
		fl.add(new String[]{"headimg3","图3"});
		fl.add(new String[]{"headimgtxt3","图说明3"});
		fl.add(new String[]{"headimglink3","图链接3"});
		tfMap.put("Wwwhome", fl);
		tList.add(new String[]{"Wwwhome","手机页首页配置"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"parentid","父ID"});
		fl.add(new String[]{"priority","排列顺序"});
		fl.add(new String[]{"childrennum","孩子数量"});
		fl.add(new String[]{"cname","名称简介"});
		fl.add(new String[]{"ckey","表名字段名"});
		fl.add(new String[]{"cvalue","值"});
		tfMap.put("Tbfield", fl);
		tList.add(new String[]{"Tbfield","表字段"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"cname","名称简介"});
		fl.add(new String[]{"ckey","关键字"});
		fl.add(new String[]{"enumFieldType","字段类型"});
		fl.add(new String[]{"cmemo","备注说明"});
		fl.add(new String[]{"cvalue","值"});
		tfMap.put("Webset", fl);
		tList.add(new String[]{"Webset","网站设置"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"ctype","类型"});
		fl.add(new String[]{"priority","排列顺序"});
		fl.add(new String[]{"cmemo","备注说明"});
		fl.add(new String[]{"ckey","关键字"});
		fl.add(new String[]{"cvalue","值"});
		tfMap.put("Myiso", fl);
		tList.add(new String[]{"Myiso","参数设置"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"userId","所属用户"});
		fl.add(new String[]{"priority","排列顺序"});
		fl.add(new String[]{"cmemo","备注说明"});
		fl.add(new String[]{"ckey","关键字"});
		fl.add(new String[]{"cckey","保存值"});
		fl.add(new String[]{"ccvalue","显示"});
		tfMap.put("Myisou", fl);
		tList.add(new String[]{"Myisou","用户参数设置"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"ip","ip"});
		fl.add(new String[]{"userId","操作者"});
		fl.add(new String[]{"itype","操作类型"});
		fl.add(new String[]{"tbname","操作表"});
		fl.add(new String[]{"tbid","操作表id"});
		fl.add(new String[]{"uri","原始操作"});
		fl.add(new String[]{"cmemo","备注"});
		tfMap.put("Oplog", fl);
		tList.add(new String[]{"Oplog","操作日志"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"userId","卖家"});
		fl.add(new String[]{"statusValidOrNot","状态"});
		fl.add(new String[]{"templateIdShort","模板短编号"});
		fl.add(new String[]{"templateId","模板id"});
		tfMap.put("WaTemplateAdd", fl);
		tList.add(new String[]{"WaTemplateAdd","模板消息接口之获得模板ID"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"status","状态"});
		fl.add(new String[]{"keyw","关键字"});
		fl.add(new String[]{"title","图文消息标题"});
		fl.add(new String[]{"description","图文消息描述"});
		fl.add(new String[]{"pic","图片链接"});
		fl.add(new String[]{"picurl","图片完整链接"});
		fl.add(new String[]{"url","点击图文消息跳转链接"});
		tfMap.put("WaEntityArticle", fl);
		tList.add(new String[]{"WaEntityArticle","共用对象之图文"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"userId","卖家"});
		fl.add(new String[]{"mtype","菜单类型"});
		fl.add(new String[]{"type","响应动作类型"});
		fl.add(new String[]{"name","菜单标题"});
		fl.add(new String[]{"key1","菜单KEY值"});
		fl.add(new String[]{"url","网页链接"});
		fl.add(new String[]{"mediaId","合法media_id"});
		fl.add(new String[]{"parentid","父ID"});
		fl.add(new String[]{"childrennum","孩子数量"});
		tfMap.put("Wxmenu", fl);
		tList.add(new String[]{"Wxmenu","微信自定义菜单"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"tousername","服务者微信号"});
		fl.add(new String[]{"fromusername","发送方帐号"});
		fl.add(new String[]{"createtime","消息创建时间"});
		fl.add(new String[]{"msgtype","消息类型"});
		fl.add(new String[]{"content","文本消息内容"});
		fl.add(new String[]{"msgid","消息id"});
		fl.add(new String[]{"picurl","图片链接"});
		fl.add(new String[]{"mediaid","消息媒体id"});
		fl.add(new String[]{"format","语音格式"});
		fl.add(new String[]{"thumbmediaid","缩略图的媒体id"});
		fl.add(new String[]{"locationX","地理位置维度"});
		fl.add(new String[]{"locationY","地理位置经度"});
		fl.add(new String[]{"scale","地图缩放大小"});
		fl.add(new String[]{"label1","地理位置信息"});
		fl.add(new String[]{"title","消息标题"});
		fl.add(new String[]{"description","消息描述"});
		fl.add(new String[]{"url","消息链接"});
		fl.add(new String[]{"event","事件类型"});
		fl.add(new String[]{"eventkey","事件KEY值"});
		fl.add(new String[]{"ticket","二维码的ticket"});
		fl.add(new String[]{"latitude","地理位置纬度"});
		fl.add(new String[]{"longitude","地理位置经度"});
		fl.add(new String[]{"precision1","地理位置精度"});
		fl.add(new String[]{"recognition","语音识别结果"});
		tfMap.put("WaRecvmsg", fl);
		tList.add(new String[]{"WaRecvmsg","接收到的消息"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"userId","卖家"});
		fl.add(new String[]{"statusValidOrNot","状态"});
		fl.add(new String[]{"kfAccount","完整客服账号"});
		fl.add(new String[]{"nickname","客服昵称"});
		fl.add(new String[]{"password","登录密码"});
		tfMap.put("WaCustomAudParam", fl);
		tList.add(new String[]{"WaCustomAudParam","客服管理之增删改参数"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"userId","卖家"});
		fl.add(new String[]{"ckey","关键字"});
		fl.add(new String[]{"templateIdShort","模板短编号"});
		fl.add(new String[]{"templateId","模板id"});
		fl.add(new String[]{"title","说明"});
		fl.add(new String[]{"content","模板内容"});
		tfMap.put("Wxmsgtemplate", fl);
		tList.add(new String[]{"Wxmsgtemplate","微信自定义模板"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","分组id"});
		fl.add(new String[]{"sellerId","所属卖家"});
		fl.add(new String[]{"name","分组名字"});
		fl.add(new String[]{"count","分组内用户数量"});
		tfMap.put("WxUsergroup", fl);
		tList.add(new String[]{"WxUsergroup","用户分组"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"returnCode","返回状态码"});
		fl.add(new String[]{"returnMsg","返回信息"});
		fl.add(new String[]{"appid","公众账号ID"});
		fl.add(new String[]{"mchId","商户号"});
		fl.add(new String[]{"deviceInfo","设备号"});
		fl.add(new String[]{"nonceStr","随机字符串"});
		fl.add(new String[]{"sign","签名"});
		fl.add(new String[]{"resultCode","业务结果"});
		fl.add(new String[]{"errCode","错误代码"});
		fl.add(new String[]{"errCodeDes","错误代码描述"});
		fl.add(new String[]{"openid","用户标识"});
		fl.add(new String[]{"isSubscribe","是否关注公众账号"});
		fl.add(new String[]{"tradeType","交易类型"});
		fl.add(new String[]{"bankType","付款银行"});
		fl.add(new String[]{"totalFee","总金额"});
		fl.add(new String[]{"feeType","货币种类"});
		fl.add(new String[]{"cashFee","现金支付金额"});
		fl.add(new String[]{"cashFeeType","现金支付货币类型"});
		fl.add(new String[]{"couponFee","代金券或立减优惠金额"});
		fl.add(new String[]{"couponCount","代金券或立减优惠使用数量"});
		fl.add(new String[]{"transactionId","微信支付订单号"});
		fl.add(new String[]{"outTradeNo","商户订单号"});
		fl.add(new String[]{"attach","商家数据包"});
		fl.add(new String[]{"timeEnd","支付完成时间"});
		tfMap.put("WaGeneralNoticeRet", fl);
		tList.add(new String[]{"WaGeneralNoticeRet","微信支付回调通用结果"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","所属用户"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"statusApplyPassRefuse","状态"});
		fl.add(new String[]{"cname","名称"});
		fl.add(new String[]{"wxnum","微信号"});
		fl.add(new String[]{"itype","类型"});
		fl.add(new String[]{"appid","appid"});
		fl.add(new String[]{"appsecret","appsecret"});
		fl.add(new String[]{"token","token"});
		fl.add(new String[]{"encodingaeskey","encodingaeskey"});
		fl.add(new String[]{"mchid","微信支付商户号"});
		fl.add(new String[]{"partnerid","商户平台登录帐号"});
		fl.add(new String[]{"partnerkey","商户平台登录密码"});
		fl.add(new String[]{"paykey","商户支付密钥"});
		fl.add(new String[]{"appAppid","app的appid"});
		fl.add(new String[]{"appMchid","app的微信支付商户号"});
		fl.add(new String[]{"appPaykey","app的商户支付密钥"});
		fl.add(new String[]{"accesstoken","accesstoken"});
		fl.add(new String[]{"ticket","ticket"});
		tfMap.put("Wxcfg", fl);
		tList.add(new String[]{"Wxcfg","微信配置"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","微信openid"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"parentid","父openid"});
		fl.add(new String[]{"childrennum","孩子数量"});
		fl.add(new String[]{"userId","平台用户"});
		fl.add(new String[]{"roleId","角色"});
		fl.add(new String[]{"imgqr","二维码"});
		tfMap.put("Wxr", fl);
		tList.add(new String[]{"Wxr","微信用户关系"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"openid","openid"});
		fl.add(new String[]{"popenid","父openid"});
		fl.add(new String[]{"status","状态"});
		tfMap.put("Wxrb", fl);
		tList.add(new String[]{"Wxrb","微信用户非首推关系"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","微信openid"});
		fl.add(new String[]{"openid","微信openid"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"sellerId","所属卖家"});
		fl.add(new String[]{"userId","平台用户"});
		fl.add(new String[]{"statusuf","用户信息类型"});
		fl.add(new String[]{"nickname","用户昵称"});
		fl.add(new String[]{"sex","性别"});
		fl.add(new String[]{"province","省份"});
		fl.add(new String[]{"city","城市"});
		fl.add(new String[]{"country","国家"});
		fl.add(new String[]{"headimgurl","用户头像"});
		fl.add(new String[]{"privilege","用户特权信息"});
		fl.add(new String[]{"unionid","微信标识"});
		fl.add(new String[]{"realname","真实姓名"});
		fl.add(new String[]{"mobile","手机号"});
		fl.add(new String[]{"email","电子邮件"});
		fl.add(new String[]{"orderZip","收货邮编"});
		fl.add(new String[]{"orderAddress","收货地址"});
		fl.add(new String[]{"subscribe","是否订阅"});
		fl.add(new String[]{"subscribeTime","关注时间"});
		fl.add(new String[]{"remark","备注"});
		fl.add(new String[]{"groupid","所在的分组ID"});
		tfMap.put("Wxouser", fl);
		tList.add(new String[]{"Wxouser","微信用户"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","时间"});
		fl.add(new String[]{"sellerId","所属卖家"});
		fl.add(new String[]{"wxouserId","微信openid"});
		fl.add(new String[]{"act","操作"});
		tfMap.put("Wxousersubscribehis", fl);
		tList.add(new String[]{"Wxousersubscribehis","微信用户关注历史记录"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"sceneStr","场景值ID字符串形式"});
		fl.add(new String[]{"actType","操作类型"});
		fl.add(new String[]{"actDetailSplit","操作参数分隔符"});
		fl.add(new String[]{"actDetail","操作详情或参数"});
		fl.add(new String[]{"statusTemporary","是否为临时二维码"});
		fl.add(new String[]{"statusValid","是否有效"});
		fl.add(new String[]{"img1","图1"});
		tfMap.put("WaQrcodeticketScene", fl);
		tList.add(new String[]{"WaQrcodeticketScene","二维码场景及转换"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"title","标题"});
		fl.add(new String[]{"imgqr","二维码"});
		fl.add(new String[]{"rettype","关注后返回方式"});
		fl.add(new String[]{"num","关注数量"});
		fl.add(new String[]{"rettxt","返回的纯文本"});
		fl.add(new String[]{"waEntityArticleId","返回的图文"});
		tfMap.put("WaQradv", fl);
		tList.add(new String[]{"WaQradv","广告使用的关注二维码"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"memberId","父亲"});
		fl.add(new String[]{"memberCh","孩子"});
		tfMap.put("MemberRelation", fl);
		tList.add(new String[]{"MemberRelation","会员父子关系"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"memberId","签到者"});
		fl.add(new String[]{"sindex","顺序号"});
		tfMap.put("Signin", fl);
		tList.add(new String[]{"Signin","签到"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"priority","排列顺序"});
		fl.add(new String[]{"articlechannelId","文章频道内序号"});
		fl.add(new String[]{"memberId","作者内序号"});
		fl.add(new String[]{"title","名称"});
		fl.add(new String[]{"intro","简介"});
		fl.add(new String[]{"author","作者"});
		fl.add(new String[]{"linkto","直接链接到"});
		fl.add(new String[]{"img1","图1"});
		fl.add(new String[]{"hittimes","点击次数"});
		fl.add(new String[]{"genurl","生成相对地址"});
		tfMap.put("Article", fl);
		tList.add(new String[]{"Article","文章"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","商品ID"});
		fl.add(new String[]{"txt","详细介绍"});
		tfMap.put("ArticleExtTxt", fl);
		tList.add(new String[]{"ArticleExtTxt","文章内容"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"ckey","关键字"});
		fl.add(new String[]{"parentid","父ID"});
		fl.add(new String[]{"priority","排列顺序"});
		fl.add(new String[]{"childrennum","孩子数量"});
		fl.add(new String[]{"family","祖先"});
		fl.add(new String[]{"title","名称"});
		tfMap.put("Articlechannel", fl);
		tList.add(new String[]{"Articlechannel","频道"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"articleId","文章内序号"});
		fl.add(new String[]{"memberAu","作者内序号"});
		fl.add(new String[]{"memberCo","评论者内序"});
		fl.add(new String[]{"msg","评论"});
		tfMap.put("ArticleComment", fl);
		tList.add(new String[]{"ArticleComment","文章的评论"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"memberFr","发送者"});
		fl.add(new String[]{"memberTo","接收者"});
		fl.add(new String[]{"msg","内容"});
		tfMap.put("Message", fl);
		tList.add(new String[]{"Message","短消息"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"otype","类型"});
		fl.add(new String[]{"num","数量"});
		fl.add(new String[]{"oid","订单的id"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("CashHis", fl);
		tList.add(new String[]{"CashHis","现金流水"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtStati","被统计日"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"num","本日增减数量"});
		fl.add(new String[]{"nstart","日初数"});
		fl.add(new String[]{"nend","日末数"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("CashmemberStatiDay", fl);
		tList.add(new String[]{"CashmemberStatiDay","会员现金日统计"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtStati","被统计月"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"num","本月增减数量"});
		fl.add(new String[]{"nstart","月初数"});
		fl.add(new String[]{"nend","月末数"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("CashmemberStatiMonth", fl);
		tList.add(new String[]{"CashmemberStatiMonth","会员现金月统计"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"status","状态"});
		fl.add(new String[]{"roleId","角色"});
		fl.add(new String[]{"username","账号"});
		fl.add(new String[]{"password","密码"});
		fl.add(new String[]{"nickname","昵称"});
		fl.add(new String[]{"userId","隶属于"});
		fl.add(new String[]{"openid","微信openid"});
		fl.add(new String[]{"userobj","类型对象"});
		fl.add(new String[]{"pmtmap","权限列表"});
		fl.add(new String[]{"objmap","拥有的对象"});
		fl.add(new String[]{"openidmd5","微信openidMd5"});
		fl.add(new String[]{"token","token"});
		tfMap.put("User", fl);
		tList.add(new String[]{"User","账号信息修改"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","用户ID"});
		fl.add(new String[]{"nickname","昵称"});
		tfMap.put("Admin", fl);
		tList.add(new String[]{"Admin","超级管理员"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","用户ID"});
		fl.add(new String[]{"nickname","昵称"});
		tfMap.put("Cadmin", fl);
		tList.add(new String[]{"Cadmin","一般管理员"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","用户ID"});
		fl.add(new String[]{"mtype","用户类型"});
		fl.add(new String[]{"price","咨询费"});
		fl.add(new String[]{"name","姓名或名称"});
		fl.add(new String[]{"intro","简介"});
		fl.add(new String[]{"img1","头像图"});
		fl.add(new String[]{"imgqr","二维码图"});
		fl.add(new String[]{"idtype","类型之个人企业"});
		fl.add(new String[]{"idnum","证件号码"});
		fl.add(new String[]{"addr","地址"});
		fl.add(new String[]{"zip","邮编"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"email","电子邮件"});
		fl.add(new String[]{"score","积分"});
		fl.add(new String[]{"cash","余额"});
		fl.add(new String[]{"exp","经验"});
		fl.add(new String[]{"mlevel","等级"});
		tfMap.put("Member", fl);
		tList.add(new String[]{"Member","会员"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"roleId","角色"});
		fl.add(new String[]{"tbname","表名"});
		fl.add(new String[]{"tbalias","表说明"});
		fl.add(new String[]{"pall","表权限"});
		fl.add(new String[]{"btnnew","按钮增"});
		fl.add(new String[]{"btnedit","按钮改"});
		fl.add(new String[]{"btndel","按钮删"});
		fl.add(new String[]{"btnshow","按钮查"});
		fl.add(new String[]{"queryaddhql","查询增加的hql"});
		fl.add(new String[]{"modigvy","修改检查脚本"});
		tfMap.put("Permittable", fl);
		tList.add(new String[]{"Permittable","权限之表设定"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"roleId","角色"});
		fl.add(new String[]{"tbname","表名"});
		fl.add(new String[]{"fieldname","字段名"});
		fl.add(new String[]{"fieldalias","字段说明"});
		fl.add(new String[]{"pf41","增可写字段41"});
		fl.add(new String[]{"pf42","改可写字段42"});
		fl.add(new String[]{"pf51","查可看字段51"});
		fl.add(new String[]{"pf52","列表显示字段52"});
		tfMap.put("Permitfield", fl);
		tList.add(new String[]{"Permitfield","权限之字段设定"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"otype","类型"});
		fl.add(new String[]{"num","数量"});
		fl.add(new String[]{"oid","订单的id"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("ScoreHis", fl);
		tList.add(new String[]{"ScoreHis","积分流水"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtStati","被统计日"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"num","本日增减数量"});
		fl.add(new String[]{"nstart","日初数"});
		fl.add(new String[]{"nend","日末数"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("ScorememberStatiDay", fl);
		tList.add(new String[]{"ScorememberStatiDay","会员积分日统计"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtStati","被统计月"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"num","本月增减数量"});
		fl.add(new String[]{"nstart","月初数"});
		fl.add(new String[]{"nend","月末数"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("ScorememberStatiMonth", fl);
		tList.add(new String[]{"ScorememberStatiMonth","会员积分月统计"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"otype","类型"});
		fl.add(new String[]{"num","数量"});
		fl.add(new String[]{"oid","订单的id"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("ExpHis", fl);
		tList.add(new String[]{"ExpHis","经验流水"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtStati","被统计日"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"num","本日增减数量"});
		fl.add(new String[]{"nstart","日初数"});
		fl.add(new String[]{"nend","日末数"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("ExpmemberStatiDay", fl);
		tList.add(new String[]{"ExpmemberStatiDay","会员经验日统计"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtStati","被统计月"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"num","本月增减数量"});
		fl.add(new String[]{"nstart","月初数"});
		fl.add(new String[]{"nend","月末数"});
		fl.add(new String[]{"cmemo","备注说明"});
		tfMap.put("ExpmemberStatiMonth", fl);
		tList.add(new String[]{"ExpmemberStatiMonth","会员经验月统计"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"questionId","一对一问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuestion", fl);
		tList.add(new String[]{"OrderrQuestion","订单之一对一问题提问"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"questionId","一对一问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuestionFinished", fl);
		tList.add(new String[]{"OrderrQuestionFinished","订单之一对一问题提问归档"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"questionId","一对一问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuestionDiscard", fl);
		tList.add(new String[]{"OrderrQuestionDiscard","订单之一对一问题提问放弃"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"quickId","抢答问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuick", fl);
		tList.add(new String[]{"OrderrQuick","订单之抢答问题提问"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"quickId","抢答问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuickFinished", fl);
		tList.add(new String[]{"OrderrQuickFinished","订单之抢答问题提问归档"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"quickId","抢答问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuickDiscard", fl);
		tList.add(new String[]{"OrderrQuickDiscard","订单之抢答问题提问放弃"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"questionId","一对一问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuestionview", fl);
		tList.add(new String[]{"OrderrQuestionview","订单之一对一问题观看"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"questionId","一对一问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuestionviewFinished", fl);
		tList.add(new String[]{"OrderrQuestionviewFinished","订单之一对一问题观看归档"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"questionId","一对一问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuestionviewDiscard", fl);
		tList.add(new String[]{"OrderrQuestionviewDiscard","订单之一对一问题观看放弃"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"quickId","抢答问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuickview", fl);
		tList.add(new String[]{"OrderrQuickview","订单之抢答问题观看"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"quickId","抢答问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuickviewFinished", fl);
		tList.add(new String[]{"OrderrQuickviewFinished","订单之抢答问题观看归档"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号ID"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"status","支付状态"});
		fl.add(new String[]{"itypePay","支付方式"});
		fl.add(new String[]{"memberId","会员"});
		fl.add(new String[]{"name","姓名"});
		fl.add(new String[]{"mobile","手机"});
		fl.add(new String[]{"quickId","抢答问题ID"});
		fl.add(new String[]{"title","问题"});
		fl.add(new String[]{"price","总价"});
		fl.add(new String[]{"paywxh5","微信支付H5对象"});
		tfMap.put("OrderrQuickviewDiscard", fl);
		tList.add(new String[]{"OrderrQuickviewDiscard","订单之抢答问题观看放弃"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"gmtAnswer","回答时间"});
		fl.add(new String[]{"priority","排列顺序"});
		fl.add(new String[]{"status","状态"});
		fl.add(new String[]{"ptype","公开状态"});
		fl.add(new String[]{"articlechannelId","频道内序号"});
		fl.add(new String[]{"memberQu","提问者"});
		fl.add(new String[]{"memberAn","回答者"});
		fl.add(new String[]{"price","咨询费"});
		fl.add(new String[]{"viewprice","观看费"});
		fl.add(new String[]{"answerwords","回答字数"});
		fl.add(new String[]{"viewnum","观看人数"});
		fl.add(new String[]{"goodnum","好评人数"});
		fl.add(new String[]{"badnum","差评人数"});
		fl.add(new String[]{"title","标题"});
		fl.add(new String[]{"quest","问题"});
		fl.add(new String[]{"ctype","评论情况"});
		fl.add(new String[]{"canread","能看不"});
		fl.add(new String[]{"questionTxt","答案"});
		tfMap.put("Question", fl);
		tList.add(new String[]{"Question","一对一问题"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","问题"});
		fl.add(new String[]{"answer","回答"});
		tfMap.put("QuestionTxt", fl);
		tList.add(new String[]{"QuestionTxt","一对一问题回答"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"questionId","一对一问题"});
		fl.add(new String[]{"memberQu","提问者"});
		fl.add(new String[]{"memberAn","回答者"});
		fl.add(new String[]{"qa","内容"});
		tfMap.put("QuestionAdd", fl);
		tList.add(new String[]{"QuestionAdd","一对一问题之追加"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"questionId","问题内序号"});
		fl.add(new String[]{"memberQu","提问者"});
		fl.add(new String[]{"memberAn","回答者"});
		fl.add(new String[]{"viewprice","观看费"});
		fl.add(new String[]{"memberVi","观看者"});
		fl.add(new String[]{"ctype","评论情况"});
		tfMap.put("QuestionLinkMemberView", fl);
		tList.add(new String[]{"QuestionLinkMemberView","观看问题的会员"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"gmtPay","支付时间"});
		fl.add(new String[]{"gmtStart","开始时间"});
		fl.add(new String[]{"gmtOver","结束时间"});
		fl.add(new String[]{"status","状态"});
		fl.add(new String[]{"ptype","公开状态"});
		fl.add(new String[]{"priority","排列顺序"});
		fl.add(new String[]{"articlechannelId","频道内序号"});
		fl.add(new String[]{"memberQu","提问者"});
		fl.add(new String[]{"priceeach","每个奖金"});
		fl.add(new String[]{"pricenum","奖金数量"});
		fl.add(new String[]{"price","总奖金"});
		fl.add(new String[]{"viewprice","观看费"});
		fl.add(new String[]{"answernum","回答人数"});
		fl.add(new String[]{"viewnum","观看人数"});
		fl.add(new String[]{"title","标题"});
		fl.add(new String[]{"question","问题"});
		fl.add(new String[]{"canread","能看不"});
		fl.add(new String[]{"listQuickTxt","答案列表"});
		tfMap.put("Quick", fl);
		tList.add(new String[]{"Quick","抢答"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"quickId","问题内序号"});
		fl.add(new String[]{"statusWin","中奖状态"});
		fl.add(new String[]{"memberQu","提问者"});
		fl.add(new String[]{"memberAn","回答者"});
		fl.add(new String[]{"goodnum","好评人数"});
		fl.add(new String[]{"badnum","差评人数"});
		fl.add(new String[]{"ctype","提问者评价"});
		fl.add(new String[]{"answer","回答"});
		tfMap.put("QuickTxt", fl);
		tList.add(new String[]{"QuickTxt","抢答回答"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"quickId","抢答"});
		fl.add(new String[]{"memberQu","提问者"});
		fl.add(new String[]{"memberAn","回答者"});
		fl.add(new String[]{"qa","内容"});
		tfMap.put("QuickAdd", fl);
		tList.add(new String[]{"QuickAdd","抢答之追加"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"quickId","抢答ID"});
		fl.add(new String[]{"memberQu","提问者"});
		fl.add(new String[]{"viewprice","观看费"});
		fl.add(new String[]{"memberVi","观看者"});
		tfMap.put("QuickLinkMemberView", fl);
		tList.add(new String[]{"QuickLinkMemberView","观看抢答的会员"});
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"quickTxtId","抢答回答ID"});
		fl.add(new String[]{"memberAn","回答者"});
		fl.add(new String[]{"memberVi","观看者"});
		fl.add(new String[]{"ctype","评论情况"});
		tfMap.put("QuickTxtComment", fl);
		tList.add(new String[]{"QuickTxtComment","抢答的评价"});
		
		
	}
}