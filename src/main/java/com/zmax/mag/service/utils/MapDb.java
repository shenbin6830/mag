/**
 * 
 */
package com.zmax.mag.service.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.service.spec.MapSpec;

/**
 * 数据库表里的一些，字段属性ISO，如性别之男女
 * @author zmax
 *
 */
public class MapDb {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 由表设计时就进行确定的标准，格式:Db.TableName.ColumnName，各项首字大写
	 */
	public void init(){
		mymap.put("Oplog.Itype",JsonUtilAli.getMapFromJson("{'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}"));
		if (logger.isDebugEnabled())logger.debug("put Oplog.Itype Done");
		mymap.put("WaEntityArticle.Status",JsonUtilAli.getMapFromJson("{'0':'无效','1':'上线'}"));
		if (logger.isDebugEnabled())logger.debug("put WaEntityArticle.Status Done");
		mymap.put("Wxmenu.Mtype",JsonUtilAli.getMapFromJson("{'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxmenu.Mtype Done");
		mymap.put("Wxmenu.Type",JsonUtilAli.getMapFromJson("{'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxmenu.Type Done");
		mymap.put("WaRecvmsg.Msgtype",JsonUtilAli.getMapFromJson("{'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}"));
		if (logger.isDebugEnabled())logger.debug("put WaRecvmsg.Msgtype Done");
		mymap.put("WaRecvmsg.Event",JsonUtilAli.getMapFromJson("{'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}"));
		if (logger.isDebugEnabled())logger.debug("put WaRecvmsg.Event Done");
		mymap.put("Wxcfg.Itype",JsonUtilAli.getMapFromJson("{'1':'服务号','2':'企业号','3':'订阅号'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxcfg.Itype Done");
		mymap.put("Wxr.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxr.RoleId Done");
		mymap.put("Wxrb.Status",JsonUtilAli.getMapFromJson("{'0':'初创','1':'业务已处理'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxrb.Status Done");
		mymap.put("Wxouser.Statusuf",JsonUtilAli.getMapFromJson("{'0':'只有openid','1':'完全版'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxouser.Statusuf Done");
		mymap.put("Wxouser.Sex",JsonUtilAli.getMapFromJson("{'1':'男','2':'女','0':'未知'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxouser.Sex Done");
		mymap.put("Wxouser.Subscribe",JsonUtilAli.getMapFromJson("{'0':'没有关注该公众号','1':'关注过了该公众号'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxouser.Subscribe Done");
		mymap.put("Wxousersubscribehis.Act",JsonUtilAli.getMapFromJson("{'0':'取消关注','1':'关注'}"));
		if (logger.isDebugEnabled())logger.debug("put Wxousersubscribehis.Act Done");
		mymap.put("WaQrcodeticketScene.StatusTemporary",JsonUtilAli.getMapFromJson("{'0':'是临时二维码','1':'是永久二维码'}"));
		if (logger.isDebugEnabled())logger.debug("put WaQrcodeticketScene.StatusTemporary Done");
		mymap.put("WaQrcodeticketScene.StatusValid",JsonUtilAli.getMapFromJson("{'0':'无效','1':'有效'}"));
		if (logger.isDebugEnabled())logger.debug("put WaQrcodeticketScene.StatusValid Done");
		mymap.put("WaQradv.Rettype",JsonUtilAli.getMapFromJson("{'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}"));
		if (logger.isDebugEnabled())logger.debug("put WaQradv.Rettype Done");
		mymap.put("CashHis.Otype",JsonUtilAli.getMapFromJson("{'-2':'购买积分','-1':'提现','1':'现金充值','2':'积分变现','3':'支付宝充值','4':'微信充值'}"));
		if (logger.isDebugEnabled())logger.debug("put CashHis.Otype Done");
		mymap.put("User.Status",JsonUtilAli.getMapFromJson("{'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}"));
		if (logger.isDebugEnabled())logger.debug("put User.Status Done");
		mymap.put("User.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','5':'会员'}"));
		if (logger.isDebugEnabled())logger.debug("put User.RoleId Done");
		mymap.put("Member.Mtype",JsonUtilAli.getMapFromJson("{'0':'会员','1':'大师'}"));
		if (logger.isDebugEnabled())logger.debug("put Member.Mtype Done");
		mymap.put("Member.Idtype",JsonUtilAli.getMapFromJson("{'0':'个人','1':'企业'}"));
		if (logger.isDebugEnabled())logger.debug("put Member.Idtype Done");
		mymap.put("Permittable.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','5':'会员'}"));
		if (logger.isDebugEnabled())logger.debug("put Permittable.RoleId Done");
		mymap.put("Permittable.Pall",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		if (logger.isDebugEnabled())logger.debug("put Permittable.Pall Done");
		mymap.put("Permittable.Btnnew",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		if (logger.isDebugEnabled())logger.debug("put Permittable.Btnnew Done");
		mymap.put("Permittable.Btnedit",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		if (logger.isDebugEnabled())logger.debug("put Permittable.Btnedit Done");
		mymap.put("Permittable.Btndel",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		if (logger.isDebugEnabled())logger.debug("put Permittable.Btndel Done");
		mymap.put("Permittable.Btnshow",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		if (logger.isDebugEnabled())logger.debug("put Permittable.Btnshow Done");
		mymap.put("Permitfield.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','5':'会员'}"));
		if (logger.isDebugEnabled())logger.debug("put Permitfield.RoleId Done");
		mymap.put("Permitfield.Pf41",JsonUtilAli.getMapFromJson("{'0':'无','9':'有','1~sessionobj.id':'1~sessionobj.id'}"));
		if (logger.isDebugEnabled())logger.debug("put Permitfield.Pf41 Done");
		mymap.put("Permitfield.Pf42",JsonUtilAli.getMapFromJson("{'0':'无','9':'有'}"));
		if (logger.isDebugEnabled())logger.debug("put Permitfield.Pf42 Done");
		mymap.put("Permitfield.Pf51",JsonUtilAli.getMapFromJson("{'0':'无','9':'有'}"));
		if (logger.isDebugEnabled())logger.debug("put Permitfield.Pf51 Done");
		mymap.put("Permitfield.Pf52",JsonUtilAli.getMapFromJson("{'0':'无','9':'有'}"));
		if (logger.isDebugEnabled())logger.debug("put Permitfield.Pf52 Done");
		mymap.put("ScoreHis.Otype",JsonUtilAli.getMapFromJson("{'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}"));
		if (logger.isDebugEnabled())logger.debug("put ScoreHis.Otype Done");
		mymap.put("ExpHis.Otype",JsonUtilAli.getMapFromJson("{'1':'充值','2':'回答一对一','3':'回答抢答'}"));
		if (logger.isDebugEnabled())logger.debug("put ExpHis.Otype Done");
		mymap.put("OrderrQuestion.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestion.Status Done");
		mymap.put("OrderrQuestion.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestion.ItypePay Done");
		mymap.put("OrderrQuestionFinished.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionFinished.Status Done");
		mymap.put("OrderrQuestionFinished.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionFinished.ItypePay Done");
		mymap.put("OrderrQuestionDiscard.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionDiscard.Status Done");
		mymap.put("OrderrQuestionDiscard.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionDiscard.ItypePay Done");
		mymap.put("OrderrQuick.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuick.Status Done");
		mymap.put("OrderrQuick.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuick.ItypePay Done");
		mymap.put("OrderrQuickFinished.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickFinished.Status Done");
		mymap.put("OrderrQuickFinished.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickFinished.ItypePay Done");
		mymap.put("OrderrQuickDiscard.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickDiscard.Status Done");
		mymap.put("OrderrQuickDiscard.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickDiscard.ItypePay Done");
		mymap.put("OrderrQuestionview.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionview.Status Done");
		mymap.put("OrderrQuestionview.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionview.ItypePay Done");
		mymap.put("OrderrQuestionviewFinished.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionviewFinished.Status Done");
		mymap.put("OrderrQuestionviewFinished.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionviewFinished.ItypePay Done");
		mymap.put("OrderrQuestionviewDiscard.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionviewDiscard.Status Done");
		mymap.put("OrderrQuestionviewDiscard.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuestionviewDiscard.ItypePay Done");
		mymap.put("OrderrQuickview.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickview.Status Done");
		mymap.put("OrderrQuickview.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickview.ItypePay Done");
		mymap.put("OrderrQuickviewFinished.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickviewFinished.Status Done");
		mymap.put("OrderrQuickviewFinished.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickviewFinished.ItypePay Done");
		mymap.put("OrderrQuickviewDiscard.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickviewDiscard.Status Done");
		mymap.put("OrderrQuickviewDiscard.ItypePay",JsonUtilAli.getMapFromJson("{'0':'余额支付','2':'微信支付','3':'支付宝支付'}"));
		if (logger.isDebugEnabled())logger.debug("put OrderrQuickviewDiscard.ItypePay Done");
		mymap.put("Question.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已支付','2':'已回答'}"));
		if (logger.isDebugEnabled())logger.debug("put Question.Status Done");
		mymap.put("Question.Ptype",JsonUtilAli.getMapFromJson("{'0':'收费公开','1':'不公开'}"));
		if (logger.isDebugEnabled())logger.debug("put Question.Ptype Done");
		mymap.put("Question.Ctype",JsonUtilAli.getMapFromJson("{'0':'以后再评','1':'好评','-1':'差评'}"));
		if (logger.isDebugEnabled())logger.debug("put Question.Ctype Done");
		mymap.put("QuestionLinkMemberView.Ctype",JsonUtilAli.getMapFromJson("{'0':'以后再评','1':'好评','-1':'差评'}"));
		if (logger.isDebugEnabled())logger.debug("put QuestionLinkMemberView.Ctype Done");
		mymap.put("Quick.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}"));
		if (logger.isDebugEnabled())logger.debug("put Quick.Status Done");
		mymap.put("Quick.Ptype",JsonUtilAli.getMapFromJson("{'0':'收费公开','1':'不公开'}"));
		if (logger.isDebugEnabled())logger.debug("put Quick.Ptype Done");
		mymap.put("QuickTxt.StatusWin",JsonUtilAli.getMapFromJson("{'0':'没中奖','1':'中奖'}"));
		if (logger.isDebugEnabled())logger.debug("put QuickTxt.StatusWin Done");
		mymap.put("QuickTxt.Ctype",JsonUtilAli.getMapFromJson("{'0':'以后再评','1':'好评','-1':'差评'}"));
		if (logger.isDebugEnabled())logger.debug("put QuickTxt.Ctype Done");
		mymap.put("QuickTxtComment.Ctype",JsonUtilAli.getMapFromJson("{'0':'以后再评','1':'好评','-1':'差评'}"));
		if (logger.isDebugEnabled())logger.debug("put QuickTxtComment.Ctype Done");
		MapSpec.init(mymap);
	}

	/**Map[String,Map[String,String]]版配置列表*/
	private Map<String,Map<String,String>> mymap=new HashMap<String, Map<String,String>>();

	private static MapDb instance = null;   
	//单例
	public static MapDb getInstance(){   
		if(null == instance ) {
			synchronized(MapDb.class){ 
				if(null == instance) 
					instance = new MapDb(); 
			} 
		} 
		return instance;
	}
	
	
	/**
	 * 程序写死的的配置，不能在配置表中修改，否则程序会出错。
	 */
	public MapDb(){
		init();
	}

	/**
	 * 根据KEY获取整个map
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key) {
		Map<String, String> ret=mymap.get(key);
		if(ret==null)
			return new HashMap<String, String>();
		return ret;
	}
	/**
	 * 根据key获取map1,再从map1中根据key1获取value
	 * @param key
	 * @param key1
	 * @return
	 */
	public String getMapString(String key,String key1){
		return getMap(key).get(key1);
	}
	
	/**mymap*/
	public Map<String, Map<String, String>> getMymap() {
		return mymap;
	}

	/**mymap*/
	public void setMymap(Map<String, Map<String, String>> mymap) {
		this.mymap = mymap;
	}
}
