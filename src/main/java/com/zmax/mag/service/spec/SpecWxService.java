package com.zmax.mag.service.spec;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zmax.common.exception.BoException;
import com.zmax.common.exception.OpenWeixinQQException;
import com.zmax.common.utils.clazz.ClassUtils;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.common.utils.string.StringUtilz1;
import com.zmax.common.utils.string.XMLConverUtil;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;
import com.zmax.mag.domain.bean.wxa.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.conf.PropMe;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.domain.conf.Rt;
import com.zmax.mag.domain.dao.base.BaseRepo;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.utils.MyUtils;
import com.zmax.mag.service.utils.wxapi.*;



/**
 * 微信服务
 * @author zmax
 *
 */
@Service
public class SpecWxService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	/**平台id，用于单用户，多微信用户的路径是/pub/wx/{bizId}/xxxx，同时参数带@PathVariable Integer bizId*/
	private Integer bizId=Conf.BIZID;
	/**默认的头像地址*/
	static String DEFAULT_HEAD_IMG="http://res.maykeys.com/userfiles/users/0/images/201506/1433157041932025224.jpg"; 
	/**微信授权地址_完全确认版，如果从公众号进入，也是静默方式，如果这个地址要放在XML中，记得把&变成&amp;*/
	public static final String WX_snsapi_userinfo="https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	/**回复空消息，1、（推荐方式）直接回复success；2、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）*/
	static final String RESPSUCC="success";
	/**到腾讯通讯出错重试次数*/
	static int WX_TRY_TIMES=3;
	/**MAP中code和wxouser存活的时间(分钟)*/
	static long CODE_WXOUSER_OVERTIME_MINUTE=5;
	

	@Autowired
	BaseRepo<Wxcfg,Integer> baseRepoWxcfg;

	
	@Autowired
	PropSys propSys;
	@Autowired
	WaQrcodeticketSceneService waQrcodeticketSceneService;
	@Autowired
	SpecUserService specUserService;
	@Autowired
	SpecWxQrService specWxQrService;
	@Autowired
	UserService userService;
	@Autowired
	WxrService wxrService;
	@Autowired
	MemberService memberService;
	@Autowired
	WxrbService wxrbService;
	@Autowired
	WxcfgService wxcfgService;
	@Autowired
	WaRecvmsgService waRecvmsgService;
	@Autowired
	WxousersubscribehisService wxousersubscribehisService;
	@Autowired
	WxouserService wxouserService;
	@Autowired
	WxmenuService wxmenuService;
	@Autowired
	WaEntityArticleService waEntityArticleService;
	@Autowired
	PropMe propMe;

	/**
	 * 初始化
	 */
	public void init(){
		logger.debug("springInit");
		if("false".equals(propMe.getIsRunWxServer())||"test".equals(propMe.getIsRunWxServer()))
			return;
		List<Wxcfg> listWxcfg=baseRepoWxcfg.listByWhereOrder("Wxcfg", "statusApplyPassRefuse=1 and appid is not null and appsecret is not null", null, null);
		synchronized (Rt.wxcfgMap) {
			Rt.wxcfgMap.clear();
			for (Wxcfg wxcfg : listWxcfg) {
				Rt.wxcfgMap.put(wxcfg.getId(), wxcfg);
			}

		}
		if (logger.isDebugEnabled())
			logger.debug("Rt.wxcfgMap.size()=" + Rt.wxcfgMap.size());

	}
	/**
	 * 刷新Accesstoken和Ticket
	 */
	public void refreshTokens(){
		if("false".equals(propMe.getIsRunWxServer())||"test".equals(propMe.getIsRunWxServer()))
			return;
		if("true".equals(propMe.getIsRunWxServer()))
			refreshTokensApi();
		if("dbdb".equals(propMe.getIsRunWxServer()))
			refreshTokensByDb();		
	}
	/**
	 * 刷新Accesstoken和Ticket，通过腾讯api
	 */
	public void refreshTokensApi(){
		Iterator<Entry<Integer, Wxcfg>>  iter = Rt.wxcfgMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, Wxcfg> entry = (Entry<Integer, Wxcfg>) iter.next();
			Wxcfg wxcfg = entry.getValue();
			AccessToken accessToken=takeA(wxcfg);
			if(accessToken==null){
				logger.error("refreshTokens accessToken出错，已经试了n次");
				break;
			}
			JsapiTicket jsapiTicket = takeJ(accessToken);
			if(jsapiTicket==null){
				logger.error("refreshTokens jsapiTicket出错，已经试了n次");
				break;
			}
			synchronized (Rt.wxcfgMap) {
				wxcfg.setAccesstoken(accessToken.getAccessToken());
				wxcfg.setTicket(jsapiTicket.getTicket());
				//db也存储一下
				wxcfgService.execute(null,"update Wxcfg set accesstoken=?0,ticket=?1 where id=?2", new Object[]{wxcfg.getAccesstoken(),wxcfg.getTicket(),wxcfg.getId()});
			}
			logger.info("refreshTokens get id="+wxcfg.getId()+",name="+wxcfg.getCname()+",appId="+wxcfg.getAppid()+",Accesstoken="+wxcfg.getAccesstoken()+",Ticket="+wxcfg.getTicket());
		}
	}
	/**
	 * 获取AccessToken，出错试TRYTIMES次
	 * @param wxcfg
	 * @return
	 */
	private AccessToken takeA(Wxcfg wxcfg){
		for (int i = 0; i < WX_TRY_TIMES; i++) {
			AccessToken accessToken = BaseServAPI.token(wxcfg.getAppid(),wxcfg.getAppsecret());
			if(accessToken!=null)
				return accessToken;
		}
		return null;
	}
	/**
	 * 获取JsapiTicket，出错试TRYTIMES次
	 * @param wxcfg
	 * @return
	 */
	private JsapiTicket takeJ(AccessToken accessToken){
		for (int i = 0; i < WX_TRY_TIMES; i++) {
			JsapiTicket jsapiTicket = JSADKSevPI.ticketGetticket(accessToken.getAccessToken());
			if(jsapiTicket!=null)
				return jsapiTicket;
		}
		return null;
	}
	/**
	 * 刷新Accesstoken和Ticket，通过Db
	 */
	public void refreshTokensByDb(){
		Iterator<Entry<Integer, Wxcfg>>  iter = Rt.wxcfgMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, Wxcfg> entry = (Entry<Integer, Wxcfg>) iter.next();
			Wxcfg wxcfg = entry.getValue();
			synchronized (Rt.wxcfgMap) {
				Wxcfg wxcfgDb=  wxcfgService.get(null, wxcfg.getId());
				if(wxcfgDb!=null){
					wxcfg.setAccesstoken(wxcfgDb.getAccesstoken());
					wxcfg.setTicket(wxcfgDb.getTicket());
				}
			}
			logger.info("refreshTokensDB get id="+wxcfg.getId()+",name="+wxcfg.getCname()+",appId="+wxcfg.getAppid()+",Accesstoken="+wxcfg.getAccesstoken()+",Ticket="+wxcfg.getTicket());
		}
	}
	/**
	 * 获取该店的关注返回信息
	 * @param bizId
	 * @return
	 */
	public String shopAtmsg(Integer bizId){
		String msg="谢谢！";
		return msg;
	}
	/**
	 * 获取配置
	 * @param agentId 用户id，根据这个值去获取密钥等配置
	 * @param url 当前网页的URL，不包含#及其后面部分，要和其它参数一起被签名 
	 * @return WxConfig
	 * @throws Exception
	 */
	public WxConfig takeWxConfig(Integer bizId,String url) throws BoException,Exception{
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		if(wxcfg==null){
			throw new BoException("当前店家"+bizId+"微信配置为空");
		}
		WxConfig wxConfig=new WxConfig(wxcfg.getAppid(), new Date().getTime()/1000, UUID.randomUUID().toString().replace("-", ""), null,wxcfg.getTicket(),url);
		String sign=SignatureUtil.signWxConfig(wxConfig);
		wxConfig.setSignature(sign);

		if (logger.isDebugEnabled())
			logger.debug("wxConfig.toString()=" + wxConfig.toString());
		return wxConfig;
	}

	/**
	 * 消息处理
	 * 根据消息类型和事件类型进行数据处理
	 * @param waRecvmsg
	 * @param bizId
	 * @return xml 要回复的内容，如果为null空不回复
	 * @throws Exception
	 */
	public String saveSgAndRespAuto(WaRecvmsg waRecvmsg,Integer bizId) throws BoException,Exception{
		if(waRecvmsg==null)
			return RESPSUCC;
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		String openid=waRecvmsg.getFromusername();
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		if(wxcfg==null){
			return RESPSUCC;
		}
		//保存一下
		waRecvmsgService.save(null,waRecvmsg);
		//根据消息类型及事件类型进行判断处理
		/**msgtype 消息类型 String   {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}*/
		////////////事件
		/**event 事件类型 String   {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}*/
		//扫码和关注是两个类型，关注的优先级大于扫码，如果是扫码和关注，则后台收到的是关注
		if("event".equalsIgnoreCase(waRecvmsg.getMsgtype())){
			//事件之关注
			if("subscribe".equalsIgnoreCase(waRecvmsg.getEvent())){
				//获取用户信息
				Wxouser wxouser= UserManageServAPI.cgiBinUserInfo(wxcfg.getAccesstoken(), openid,null);
				if(wxouser==null){
					//wxouser是从腾讯取来的，偶尔会失败
					wxouser=new Wxouser(openid);
				}
				//保存数据库
				wxouser.setSubscribe(1);
				wxouser.setStatusuf(1);
				wxouser=saveUpdateWxouser(wxouser, bizId);

				wxousersubscribehisService.save(null,new Wxousersubscribehis(null, bizId, openid, 1, null));
				//通过二维码关注
				if(StringUtils.isNotBlank(waRecvmsg.getEventkey()) && waRecvmsg.getEventkey().startsWith("qrscene_")){
					//二维码关注，进行关注后的事件推送
					Integer sceneId=Integer.valueOf(waRecvmsg.getEventkey().split("_")[1]);
					String msg= specWxQrService.attationOrEnterByScan(sceneId, wxouser, waRecvmsg, bizId);
					return msg;
				}else{
					//普通订阅
					return msgRespXmlNewsByDb(waRecvmsg, Conf.DBKW_WAENTITYARTICLE_subscribe);
				}

			}
			if("unsubscribe".equals(waRecvmsg.getEvent())){
				//取消订阅
				updateUnsubscribeWxouser(waRecvmsg.getFromusername(), bizId);
			}
			if("SCAN".equals(waRecvmsg.getEvent())){
				//
				Wxr w=wxrService.csnewget(new User(), new Wxr(), null,null);
				w.setParentid(waRecvmsg.getFromusername());
				w.setGmtCreate(waRecvmsg.getGmtCreate());
				w.setId(openid);
				w.setImgqr(waRecvmsg.getPicurl());
				wxrService.saveOrUpdate(new User(),w);
				//获取用户信息
				Wxouser wxouser= UserManageServAPI.cgiBinUserInfo(wxcfg.getAccesstoken(), openid,null);
				//保存数据库
				wxouser.setSubscribe(1);
				wxouser.setStatusuf(1);
				wxouser=saveUpdateWxouser(wxouser, bizId);

				//扫描二维码
				Integer sceneId = Integer.valueOf(waRecvmsg.getEventkey());
				//通过二维码进入
				String msg= specWxQrService.attationOrEnterByScan(sceneId, wxouser, waRecvmsg, bizId);
				return msg;

			}
			if("LOCATION".equals(waRecvmsg.getEvent())){
				//return msgRespXml(waRecvmsg,"欢迎来到本公众号，我们将竭力提供最好的服务!");
			}	
			return RESPSUCC;
		}
		//文本信息：Msgtype:=text,,Content:=sj,Event:=<null>
		if("text".equalsIgnoreCase(waRecvmsg.getMsgtype())){
	
			//绑定微信关系
			if(StringUtils.indexOf(waRecvmsg.getContent(), "yy")==0){
				String mobile=waRecvmsg.getContent().substring(2);
				String  msg=updateBindWxouserAndUserByUsername(openid, mobile);
				if("0".equals(msg))
					return msgRespXml(waRecvmsg,"注册成功，欢迎您加入医生经纪人");
				return msgRespXml(waRecvmsg,"注册审核中");
			}
			//医生
			if("ys".equalsIgnoreCase(waRecvmsg.getContent())){
				String url1=propSys.getDomain()+"/"+propSys.getWebnamee()+"/pub/wx/index_b.html";
				String url=String.format(WX_snsapi_userinfo, wxcfg.getAppid(),URLEncoder.encode(url1, "UTF-8"));
				//return msgRespXml(waRecvmsg,url);
				//回个图文
				return msgRespXmlNewsByDb(waRecvmsg, "LDOCTOR");
			}	
			//业务员
			if("yw".equalsIgnoreCase(waRecvmsg.getContent())){
				String url1=propSys.getDomain()+"/"+propSys.getWebnamee()+"/pub/wx/index_c.html";
				String url=String.format(WX_snsapi_userinfo, wxcfg.getAppid(),URLEncoder.encode(url1, "UTF-8"));
				//return msgRespXml(waRecvmsg,url);
				//回个图文
				return msgRespXmlNewsByDb(waRecvmsg, "LSELLER");
			}	
			//医生下载
			if("ys1".equalsIgnoreCase(waRecvmsg.getContent())){
				String url="http://a.app.qq.com/o/simple.jsp?pkgname=com.zmax.yijianwww_b";
				return msgRespXml(waRecvmsg,url);
				//回个图文
				//return msgRespXmlNewsByDb(waRecvmsg, Conf.DBKW_WAENTITYARTICLE_SJ);
			}			

			//商家给发个首页地址
			if("sj".equalsIgnoreCase(waRecvmsg.getContent())){
				String url1=propSys.getDomain()+"/"+propSys.getWebnamee()+"/pub/wx/index_b.html";
				String url=String.format(WX_snsapi_userinfo, wxcfg.getAppid(),URLEncoder.encode(url1, "UTF-8"));
				return msgRespXml(waRecvmsg,url);
				//回个图文
				//return msgRespXmlNewsByDb(waRecvmsg, Conf.DBKW_WAENTITYARTICLE_SJ);
			}
			//会员给发个首页地址
			if("hy".equalsIgnoreCase(waRecvmsg.getContent())){
				String url1=propSys.getDomain()+"/"+propSys.getWebnamee()+"/pub/wx/index.html";
				String url=String.format(WX_snsapi_userinfo, wxcfg.getAppid(),URLEncoder.encode(url1, "UTF-8"));
				/*
				String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
						+wxcfg.getAppid()
						+"&redirect_uri="
						+URLEncoder.encode(url1, "UTF-8")
						+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
				 */						
				return msgRespXml(waRecvmsg,url);				
			}
			//testa1
			if("testa1".equalsIgnoreCase(waRecvmsg.getContent())){
				String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbc3195093247c63c&redirect_uri="+URLEncoder.encode(propSys.getDomain(),"UTF-8")+"%2fyijian%2fpub%2fwx%2findex.html&response_type=code&scope=snsapi_userinfo";
				if("true".equals(propMe.getIsYjtcm())){
					url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx112870cce7abcd4e&redirect_uri="+URLEncoder.encode(propSys.getDomain(),"UTF-8")+"%2fyjtcm%2fpub%2fwx%2findex.html&response_type=code&scope=snsapi_userinfo";
				}
				return msgRespXml(waRecvmsg,url);
			}
			//会员给发个注册成功的msg
			if("bf".equalsIgnoreCase(waRecvmsg.getContent())){
				User user=(User)userService.getFirst(null, "openid=?0", null, new Object[]{openid});
				if(user!=null){
					Random random=new Random();
					if("true".equals(propMe.getIsYjtcm())){
						return msgRespXml(waRecvmsg,"大御医感谢您的支持"+user.getGmtCreateString()+"("+(random.nextInt(9000) + 1000)+user.getId()+")");
					}
					return msgRespXml(waRecvmsg,"医药一键通感谢您的支持"+user.getGmtCreateString()+"("+(random.nextInt(9000) + 1000)+user.getId()+")");
				}else {
					return msgRespXml(waRecvmsg,"您还没有注册过会员！");
				}
			}
			if("clean".equalsIgnoreCase(waRecvmsg.getContent())){
				String url1=propSys.getDomain()+"/"+propSys.getWebnamee()+"/pub/wx/clean.html";
				String url=String.format(WX_snsapi_userinfo, wxcfg.getAppid(),URLEncoder.encode(url1, "UTF-8"));
				return msgRespXml(waRecvmsg,url);
			}	
			return msgRespXmlNewsByDb(waRecvmsg, waRecvmsg.getContent());
		}	
		return RESPSUCC;
	}

	/**
	 * 取消关注
	 * @param openid
	 * @param bizId
	 * @return
	 */
	public Wxouser updateUnsubscribeWxouser(String openid,Integer bizId){
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		Wxouser wxouser=wxouserService.get(null, openid);
		if(wxouser!=null){
			wxouser.setSubscribe(0);
			wxouserService.update(null,wxouser);
		}
		wxousersubscribehisService.save(null,new Wxousersubscribehis(null, bizId, openid, 0, null));
		return wxouser;
	}
	/**
	 * 回复文本信息
	 * @param waRecvmsg
	 * @param msg
	 * @return
	 */
	public String msgRespXml(WaRecvmsg waRecvmsg,String msg){
		msg=StringUtilz1.encodeXml(msg);
		WaSendautomsgText waRespmsgText=new WaSendautomsgText(waRecvmsg.getFromusername(), waRecvmsg.getTousername(), new Date().getTime()/1000, "text", msg, null);;
		String xml = XMLConverUtil.convertToXML(waRespmsgText);
		return xml;
	}
	/**
	 * 根据关键字从数据库中取个一个文本信息进行回复
	 * @param waRecvmsg
	 * @param keyw WaEntityArticle表的关键字
	 * @return
	 */
	public String msgRespXmlByDb(WaRecvmsg waRecvmsg,String keyw){
		String msg="";
		WaEntityArticle waEntityArticle=(WaEntityArticle)waEntityArticleService.getFirst(null, "keyw=?0 ",null,new Object[]{keyw});
		if(waEntityArticle!=null && waEntityArticle.getDescription()!=null)
			msg=waEntityArticle.getDescription();
		WaSendautomsgText waRespmsgText=new WaSendautomsgText(waRecvmsg.getFromusername(), waRecvmsg.getTousername(), new Date().getTime()/1000, "text", msg, null);;
		String xml = XMLConverUtil.convertToXML(waRespmsgText);
		return xml;
	}
	/**
	 * 根据id从数据库中取个一个图文信息/纯文本进行回复，返回类型决定于对象的picurl字段，如果picurl为空，则返回description的纯文本XML对象
	 * @param waRecvmsg
	 * @param id
	 * @return vs
	 */
	public String msgRespXmlNewsById(WaRecvmsg waRecvmsg,Integer id){
		String xml="";
		List<WaEntityArticle> listnews=new ArrayList<WaEntityArticle>();
		WaEntityArticle waEntityArticleDb=(WaEntityArticle)waEntityArticleService.get(null, id);
		if(waEntityArticleDb==null)
			return "";	
		WaEntityArticle waEntityArticle=new WaEntityArticle();
		BeanUtils.copyProperties(waEntityArticleDb, waEntityArticle);
		if(waEntityArticle.getDescription()!=null)
			waEntityArticle.setDescription(StringUtilz1.encodeXml(waEntityArticle.getDescription()));
		if(waEntityArticle.getUrl()!=null)
			waEntityArticle.setUrl(StringUtilz1.encodeXml(waEntityArticle.getUrl()));
		if(StringUtils.isBlank(waEntityArticle.getPicurl())){
			String msg=waEntityArticle.getDescription();
			WaSendautomsgText waRespmsgText=new WaSendautomsgText(waRecvmsg.getFromusername(), waRecvmsg.getTousername(), new Date().getTime()/1000, "text", msg, null);;
			xml = XMLConverUtil.convertToXML(waRespmsgText);

		}else{
			listnews.add(waEntityArticle);
			WaEntityArticles news=new WaEntityArticles(listnews, null, null);
			WaSendautomsgArticles resp=new WaSendautomsgArticles(waRecvmsg.getFromusername(), waRecvmsg.getTousername(), new Date().getTime()/1000, "news", 1, news, null, null);
			xml = XMLConverUtil.convertToXML(resp);
		}
		return xml;
	}
	/**
	 * 根据关键字从数据库中取个一个图文信息/纯文本进行回复，返回类型决定于对象的picurl字段，如果picurl为空，则返回description的纯文本XML对象
	 * @param waRecvmsg
	 * @param keyw WaEntityArticle表的关键字
	 * @return
	 */
	public String msgRespXmlNewsByDb(WaRecvmsg waRecvmsg,String keyw){
		if(StringUtils.isBlank(keyw))
			return "";
		keyw=StringUtils.lowerCase(keyw);
		String xml="";
		List<WaEntityArticle> listnews=new ArrayList<WaEntityArticle>();
		WaEntityArticle waEntityArticleDb=(WaEntityArticle)waEntityArticleService.getFirst(null, "keyw=?0",null, new Object[]{keyw});
		if(waEntityArticleDb==null){
			return manyCustomerService(waRecvmsg);
			//return "";	
		}

		//以下是有关键字的回复
		WaEntityArticle waEntityArticle=new WaEntityArticle();
		BeanUtils.copyProperties(waEntityArticleDb, waEntityArticle);
		if(waEntityArticle.getDescription()!=null)
			waEntityArticle.setDescription(StringUtilz1.encodeXml(waEntityArticle.getDescription()));
		if(waEntityArticle.getUrl()!=null)
			waEntityArticle.setUrl(StringUtilz1.encodeXml(waEntityArticle.getUrl()));
		if(StringUtils.isBlank(waEntityArticle.getPicurl())){
			String msg=waEntityArticle.getDescription();
			WaSendautomsgText waRespmsgText=new WaSendautomsgText(waRecvmsg.getFromusername(), waRecvmsg.getTousername(), new Date().getTime()/1000, "text", msg, null);;
			xml = XMLConverUtil.convertToXML(waRespmsgText);

		}else{
			listnews.add(waEntityArticle);
			WaEntityArticles news=new WaEntityArticles(listnews, null, null);
			WaSendautomsgArticles resp=new WaSendautomsgArticles(waRecvmsg.getFromusername(), waRecvmsg.getTousername(), new Date().getTime()/1000, "news", 1, news, null, null);
			xml = XMLConverUtil.convertToXML(resp);
		}
		return xml;
	}
	/**
	 * 转到多客服需要的msg
	 * @param waRecvmsg
	 * @return
	 */
	public String manyCustomerService(WaRecvmsg waRecvmsg){
		WaSendautomsgText waRespmsgText = new WaSendautomsgText(
				waRecvmsg.getFromusername() , //String 手机用户微信号   
				waRecvmsg.getTousername() , //String 公众平台的openid   
				new Date().getTime()/1000 , //Long 消息创建时间 （整型）   
				"transfer_customer_service" , //String 消息类型 default=text  
				null , //String 文本消息内容   
				null
				);
		String xml = XMLConverUtil.convertToXML(waRespmsgText);
		return xml;
	}
	/**
	 * 保存只有openid的微信wxouser
	 * @param wxouser
	 */
	public Wxouser createWxouserSimp(String openid,Integer bizId) throws Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		Wxouser wxouser=wxouserService.get(null, openid);
		if(wxouser==null){
			wxouser= new Wxouser(openid, bizId, null, 0, null, null, null, null, null, DEFAULT_HEAD_IMG, null, null, null, null, null, null, null, 0,"subscribeTime","remark",0, null);
			if(openid.equals(""+bizId)){
				wxouser.setUserId(bizId);
			}
			wxouserService.saveCreate(null, new Wxouser(), wxouser, null, null);
		}
		return wxouser;
	}
	/**
	 * 保存或更新wxouser，可以更新部分字段，比如userId
	 * @param wxouser 可以是很简单的内容，但一定要有id，空的部分不会保存到数据库中
	 * @param bizId
	 * @return
	 * @throws BoException
	 * @throws Exception
	 * 
	 */
	public Wxouser saveUpdateWxouser(Wxouser wxouser,Integer bizId) throws BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		if(null==wxouser||null==wxouser.getId())
			throw new BoException("openid为空");
		wxouser.setSellerId(bizId);
		Wxouser wxouserdb=wxouserService.get(null, wxouser.getId());
		if(wxouserdb==null){
			if(wxouser.getId().equals(""+bizId)){
				wxouser.setUserId(bizId);
			}
			wxouser.setGmtCreate(new Date());
			wxouserService.saveCreate(null, new Wxouser(), wxouser, null, null);
			if(logger.isDebugEnabled())
				logger.debug("wxouserService.save");
			return wxouser;
		}else{
			//不能使用BeanUtils.copyProperties，会把null也复制过来
			ClassUtils.beanDeepCopySkipEmpty(wxouser, wxouserdb);
			wxouserdb.setNickname(StringUtilz.removeSpecLetter(wxouserdb.getNickname()));
			wxouserService.update(null,wxouserdb);
			if(logger.isDebugEnabled())
				logger.debug("wxouserDao.update");
			return wxouserdb;
		}	
	}
	/**
	 * 更新openid和user的绑定关系，包括：wxouser,user,wxr
	 * 无差别全覆盖版
	 * @param userId 
	 * @param openid
	 */
	public void updateOpenidUserIdBind(Integer userId,String openid,Integer roleid){
		if(StringUtilz.integerNullOr0(userId))
			return;
		if(StringUtils.isBlank(openid))
			return;
		Wxouser wxouser=wxouserService.get(null, openid);
		if(wxouser==null){
			wxouser = new Wxouser(
					Conf.BIZID , //Integer 所属卖家  实际上是bizId 
					userId , //Integer 平台用户   
					0 , //Integer 用户信息类型 default=1  {'0':'只有openid','1':'完全版'}
					null , //String 用户昵称   
					0 , //Integer 性别 default=0  {'1':'男','2':'女','0':'未知'}
					null , //String 省份  160x160 
					null , //String 城市 default=0  
					null , //String 国家 default=0  
					null , //String 用户头像   
					null , //String 用户特权信息   
					null , //String 微信标识   
					null , //String 真实姓名   
					null , //String 手机号   
					null , //String 电子邮件   
					null , //String 收货邮编   
					null , //String 收货地址   
					1 , //Integer 是否订阅 default=0 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}
					null , //String 关注时间  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
					null , //String 备注  公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 
					null , //Integer 所在的分组ID  用户所在的分组ID 
					null
					);
			wxouser.setId(openid);
			wxouserService.save(null,wxouser);
		}else{
			wxouser.setUserId(userId);
			wxouserService.update(null,wxouser);	
		}
		userService.execute(null,"update User set openid=?0 where id=?1 ", new Object[]{openid,userId});
		wxrService.execute(null,"update Wxr set userId=?0,roleId=?1 where id=?2", new Object[]{userId,roleid,openid});
	}
	/**
	 * 根据openid和username，对Wxouser和User进行绑定
	 * 顺序检查版：Wxouser优先，User其次，如果Wxouser中已经绑定过，就不再继续了
	 * @param openid
	 * @param username
	 * @return
	 */
	public String updateBindWxouserAndUserByUsername(String openid,String username){
		Wxouser wxouser=wxouserService.get(null, openid);
		if(wxouser==null){
			return "-1,Wxouser不存在!";
		}
		if(wxouser.getUserId()!=null){
			return "-2,已经绑定过!";
		}
		User user=(User)userService.getFirst(null, "username=?0", null, new Object[]{username});
		if(user==null){
			return "-3,手机号不存在!";
		}
		Wxr wxr =wxrService.get(null, openid);
		if(wxr==null){
			wxr = new Wxr(
					null , //String 父openid   
					0	 , //Integer 孩子数量 default=0  
					null , //String 二维码  600x600 
					null
					);
		}
		wxr.setId(openid);
		wxr.setUserId(user.getId());
		wxr.setRoleId(user.getRoleId());
		user.setOpenid(openid);
		wxouser.setUserId(user.getId());
		userService.update(null, user);
		wxouserService.update(null, wxouser);
		wxrService.saveOrUpdate(null, wxr);

		return "0";
	}
	/**
	 * 从简单版过来的openid,根据openid找表中微信用户，看看是否为完全信息。
	 * @param code 微信传来的交换码，如果是test101234nnn则是测试号传过来的测试
	 * @param bizId
	 * @return wxouser int {'0':'只有openid','1':'完全版'}
	 */
	public Wxouser saveWxouserSimp(String code,Integer bizId,User user) throws OpenWeixinQQException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		String visiteropenid="";
		if(StringUtils.isNotBlank(code) && code.indexOf(Conf.TESTUSER)==0){
			//测试用户
			visiteropenid=code;
		}else{
			visiteropenid=takeOpenidBycode(code, bizId);
			if(logger.isDebugEnabled()){
				logger.debug("简单版  通过code交换得到visiteropenid="+visiteropenid);
			}
		}
		if(StringUtils.isBlank(visiteropenid))
			throw new OpenWeixinQQException();
		Wxouser wxouser=createWxouserSimp(visiteropenid, bizId);
		return wxouser;
	}
	/**
	 * 从完全版过来的，补全信息
	 * 拿到code,去QQ用api获取完全信息
	 * 用获取到的完全信息，保存或更新Wxouser表
	 * @param code 微信传来的交换码，如果是test101234nnn则是测试号传过来的测试
	 * @param bizId
	 * @return Wxouser 数据库表对象
	 * @throws Exception
	 */
	public Wxouser saveWxouserFull(String code,Integer bizId,User user) throws OpenWeixinQQException,BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		Wxouser wxouser=null;
		if(StringUtils.isNotBlank(code) && code.indexOf(Conf.TESTUSER)==0){
			//测试用户
			wxouser= new Wxouser(code, bizId, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0,"subscribeTime","remark",0, null);
		}else{
			wxouser=takeWxouserBycode(code,bizId);
		}
		//下面的wxouser不会为空，如果为空早就抛错出去了
		if(wxouser.getNickname()==null||wxouser.getNickname().equals("")){
			wxouser.setNickname("匿名用户");
		}
		String newNickname=StringUtilz.removeSpecLetter(wxouser.getNickname());
		wxouser.setNickname(newNickname);
		if(wxouser.getHeadimgurl()==null||StringUtils.isBlank(wxouser.getHeadimgurl())){
			wxouser.setHeadimgurl(propSys.getDomain()+"/"+propSys.getWebnamee()+"/res/img/person_tou.jpg");
		}

		//Wxouser操作
		Wxouser wxouserVisiterDb=wxouserService.get(null, wxouser.getId());
		if(wxouserVisiterDb==null){
			//新用户
			wxouserVisiterDb=wxouser;
			wxouserVisiterDb.setStatusuf(1);
			wxouserVisiterDb.setNickname(wxouser.getNickname());
			if(user!=null)wxouserVisiterDb.setUserId(user.getId());
			wxouserVisiterDb.setSellerId(bizId);
			wxouserService.saveCreate(null, new Wxouser(), wxouserVisiterDb, null, null);
		}else{
			//老用户信息不全 更新信息
			ClassUtils.beanDeepCopySkipEmpty(wxouser, wxouserVisiterDb);
			wxouserVisiterDb.setStatusuf(1);
			if(user!=null)wxouserVisiterDb.setUserId(user.getId());
			if(StringUtils.isBlank(wxouserVisiterDb.getSellerId().toString())) wxouserVisiterDb.setSellerId(bizId);
			wxouserService.update(null,wxouserVisiterDb);
		}
		return wxouserVisiterDb;
	}
	/**
	 * 关注版，已经关注用户的用户信息更新
	 * 拿到code,去QQ用api获取完全信息
	 * 用获取到的完全信息，保存或更新Wxouser表
	 * @param code 微信传来的交换码，如果是test101234nnn则是测试号传过来的测试
	 * @param bizId
	 * @return Wxouser 数据库表对象
	 * @throws Exception
	 */
	public Wxouser saveWxouserFullAttation(String code,Integer bizId) throws OpenWeixinQQException,BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		logger.debug("saveWxouserFullAttation");
		Wxouser wxouser=null;
		if(StringUtils.isNotBlank(code) && code.indexOf(Conf.TESTUSER)==0){
			logger.debug("StringUtils.isNotBlank(code)");
			//测试用户
			wxouser= new Wxouser(code, bizId, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0,"subscribeTime","remark",0, null);
			//没有经过关注，所以要在这里保存一下
			wxouserService.saveOrUpdate(null, wxouser);
		}else{
			wxouser=takeWxouserBycodeAttation(code, bizId);
			logger.debug("wxouserId="+wxouser.getId());
			Wxouser wxouserDb=wxouserService.get(null, wxouser.getId());
			if(wxouserDb==null){
				logger.debug("保存wxouser");
				wxouserService.save(null, wxouser);
			}
		}
		//关注时已经保存过了，就不做数据更新了。
		return wxouser;
	}	
	/**
	 * 创建或更新Wxr
	 * 如果已经存在wxr，则创建一个新wxr但是不进数据库
	 * @param wxouser 访问者
	 * @param popenid 父亲的openid
	 * @return Wxr
	 * @throws OpenWeixinQQException
	 * @throws BoException
	 * @throws Exception
	 */
	public Wxr saveWxr(Wxouser wxouser,String popenid) throws OpenWeixinQQException,BoException,Exception{
		if(wxouser==null || StringUtils.isBlank(wxouser.getId()))
			throw new BoException("微信用户为空");
		Wxr wxr=wxrService.get(null, wxouser.getId());
		Wxr wxr1 = new Wxr(wxouser.getId(), popenid, 0,0, null, null,null);
		//new Wxr(id, parentid, childrennum, sellerId, userId, notuse)
		//Wxr(String id ,String parentid ,Integer childrennum ,Integer userId ,Integer roleId ,String imgqr ,String notuse) {
		if(wxr==null){
			if(StringUtils.isBlank(popenid)){
				//throw new BoException("首次推荐商家为空");
				return wxr1;
			}
			wxrService.save(null,wxr1);
		}
		return wxr1;
	}
	/**
	 * 通过code获取openid，之微信用户信息简单版 snsapi_base
	 * <br>网站授 权 这个地址是需要在公众后台注册过的
	 * <br>1第一步：用户点击"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxcfg.getAppid()
				+"&redirect_uri="+urlEncodeUTF8("http://aaa.bbb.ccc.com/xxx.html?p1=p1&p2=p2")
				+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	 * <br>2 第二步：通过code换取网页授权access_token和openid
	 * @param code 微信传来的交换码
	 * @param bizId 店或代理或其它的商家
	 * @return openid
	 */
	public String takeOpenidBycode(String code,Integer bizId)throws BoException,OpenWeixinQQException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		if(StringUtils.isBlank(code)){
			logger.info("此时获得的code为空，请检查是否从微信进入的");
			throw new OpenWeixinQQException();
		}
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		if(wxcfg==null){
			logger.error("当前店家微信配置为空");
			throw new BoException("当前店家微信配置为空");
		}
		AccessTokenOauth accessTokenOauth=takeAccessTokenOauthFromWx(wxcfg, code);
		return accessTokenOauth.getOpenid();
	}


	/**
	 * 网页授权，用code交换AccessTokenOauth,包括了网页授权接口调用凭证和openid
	 * 有可能因为瞬断导致获取失败，试3次。。。
	 * 也有可能token失效导致交换失败，重新refreshTokens加载token，再试一轮，再出错就可能是网断了
	 * @param wxcfg
	 * @param code
	 * @return
	 * @throws BoException
	 * @throws OpenWeixinQQException
	 * @throws Exception
	 */
	private AccessTokenOauth takeAccessTokenOauthFromWx(Wxcfg wxcfg,String code)throws BoException,OpenWeixinQQException,Exception{
		AccessTokenOauth accessTokenOauth=takeAccessTokenOauth(wxcfg,code);
		//试三次
		if(isNullAccessTokenOauth(accessTokenOauth)){
			logger.error("腾讯用户接口失败,可能是【微信配置】更新后未参数重载，重刷Token 1");
			accessTokenOauth=refreshTkAndAt(wxcfg, code);
		}
		if(isNullAccessTokenOauth(accessTokenOauth)){
			logger.error("腾讯用户接口失败,可能是【微信配置】更新后未参数重载，重刷Token 2");
			accessTokenOauth=refreshTkAndAt(wxcfg, code);
		}
		if(isNullAccessTokenOauth(accessTokenOauth)){
			logger.error("腾讯用户接口失败,可能是【微信配置】更新后未参数重载，重刷Token 3");
			accessTokenOauth=refreshTkAndAt(wxcfg, code);
		}
		if(isNullAccessTokenOauth(accessTokenOauth)){
			throw new BoException("腾讯用户接口失败，已经重刷Token很多次了，还是失败，请联系开发人员");	
		}

		return accessTokenOauth;
	}
	/**
	 * 先刷新一次TOKEN，再去交换一次AccessTokenOauth
	 * @param wxcfg
	 * @param code
	 * @return AccessTokenOauth
	 * @throws BoException
	 * @throws OpenWeixinQQException
	 * @throws Exception
	 */
	private AccessTokenOauth refreshTkAndAt(Wxcfg wxcfg,String code) throws BoException,OpenWeixinQQException,Exception{
		refreshTokens();
		wxcfg=MyUtils.takeWxcfg(wxcfg.getId());
		//再刷一轮试下
		AccessTokenOauth accessTokenOauth=takeAccessTokenOauth(wxcfg,code);
		return accessTokenOauth;
	} 

	/**
	 * AccessTokenOaut是否为空
	 * @param accessTokenOauth
	 * @return
	 */
	private boolean isNullAccessTokenOauth(AccessTokenOauth accessTokenOauth){
		return (accessTokenOauth==null || accessTokenOauth.getOpenid()==null);
	}
	/**
	 * 获取AccessTokenOauth，出错试TRYTIMES次
	 * @param wxcfg
	 * @param code
	 * @return
	 */
	private AccessTokenOauth takeAccessTokenOauth(Wxcfg wxcfg,String code){
		for (int i = 0; i < WX_TRY_TIMES; i++) {
			AccessTokenOauth accessTokenOauth=UserManageServAPI.SnsOauth2AccessToken(wxcfg.getAppid(), wxcfg.getAppsecret(), code);
			if(!isNullAccessTokenOauth(accessTokenOauth)){
				return accessTokenOauth;
			}
			logger.error("第二步：通过code换取网页授权access_token和用户openid:accessTokenOauth==null,try="+i);
			if(accessTokenOauth==null){
				logger.error("accessTokenOauth==null");
			}else{
				logger.error("accessTokenOauth.getErrcode="+accessTokenOauth.getErrcode()+",getErrmsg="+accessTokenOauth.getErrmsg());
			}


		}
		return null;
	}

	/**
	 * 获取微信用户信息完全版 snsapi_userinfo
	 * <br>网站授 权 这个地址是需要在公众后台注册过的
	 * <br>1第一步：用户点击"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxcfg.getAppid()
				+"&redirect_uri="+urlEncodeUTF8("http://aaa.bbb.ccc.com/xxx.html?p1=p1&p2=p2")
				+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	 * <br>2 第二步：通过code换取网页授权access_token和openid
	 * <br>3 第三步：刷新access_token（如果需要）
	 * <br>4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 * @param code 微信传来的交换码，如果是test101234nnn则是测试号传过来的测试
	 * @param bizId 店或代理或其它的商家
	 * @return WxUser
	 */
	public Wxouser takeWxouserBycode(String code,Integer bizId) throws OpenWeixinQQException,BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		if(StringUtils.isBlank(code))
			throw new OpenWeixinQQException();
		if(StringUtils.isNotBlank(code) && code.indexOf(Conf.TESTUSER)==0){
			//测试用户
			return new Wxouser(code, bizId, null, 1, code, 1, "浙江省", "杭州市", "中国", null, null, null, code, code.replaceAll("test", ""), "test@maykeys.com", "310000", "下沙", null, null, null, null, null);
		}
		//		String openid=takeOpenidBycode(code, bizId);
		//		if(StringUtils.isBlank(openid)){
		//			throw new BoException("腾讯交换接口失败");
		//		}
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		if(wxcfg==null){
			logger.error("当前店家微信配置为空");
			throw new BoException("当前店家微信配置为空");
		}
		if(StringUtils.isBlank(wxcfg.getAppid()) || StringUtils.isBlank(wxcfg.getAppsecret())){
			logger.error("店家的【微信配置】appid或appsecret未填写");
			throw new BoException("店家的微信配置appid或appsecret未填写");
		}
		AccessTokenOauth accessTokenOauth=takeAccessTokenOauthFromWx(wxcfg, code);
		//AccessTokenOauth accessTokenOauth=UserManageServAPI.SnsOauth2AccessToken(wxcfg.getAppid(), wxcfg.getAppsecret(), code);
		if(accessTokenOauth==null || accessTokenOauth.getAccessToken()==null){
			logger.error("accessTokenOauth="+accessTokenOauth);
			throw new BoException("腾讯用户接口失败,请联系开发人员");
		}
		Wxouser wxouser=UserManageServAPI.snsUserInfo(accessTokenOauth.getAccessToken(), accessTokenOauth.getOpenid(), null);
		if(wxouser.getErrcode()==null || wxouser.getErrcode().intValue()==0){
			if(logger.isDebugEnabled()){
				logger.debug("UserManageServAPI.snsUserInfo  经过接口获取到的wxouser="+wxouser);
			}
			return wxouser;
		}else{
			logger.error("腾讯用户接口失败,可能是【微信配置】更新后未参数重载");
			throw new BoException("腾讯用户接口失败,可能是【微信配置】更新后未参数重载");
		}
	}
	/**
	 * 关注版，获取已关注用户的用户信息，只需要appid即可
	 * @param code
	 * @param bizId
	 * @return
	 * @throws OpenWeixinQQException
	 * @throws BoException
	 * @throws Exception
	 */
	public Wxouser takeWxouserBycodeAttation(String code,Integer bizId) throws OpenWeixinQQException,BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		if(StringUtils.isBlank(code))
			throw new OpenWeixinQQException();
		if(StringUtils.isNotBlank(code) && code.indexOf(Conf.TESTUSER)==0){
			//测试用户
			return new Wxouser(code, bizId, null, 1, code, 1, "浙江省", "杭州市", "中国", null, null, null, code, code.replaceAll("test", ""), "test@maykeys.com", "310000", "下沙", null, null, null, null, null);
		}

		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		//根据code获取openid
		String openid=takeOpenidBycode(code, bizId);
		if (logger.isDebugEnabled())
			logger.debug("openid=" + openid);

		Wxouser wxouser= UserManageServAPI.cgiBinUserInfo(wxcfg.getAccesstoken(), openid,null);
		if(wxouser==null)
			throw new BoException("腾讯用户接口失败,可能是【微信配置】更新后未参数重载");
		if(StringUtils.isBlank(wxouser.getOpenid())){
			//可能是token过期了，刷一下
			logger.error("wxouser不空但返回失败"+ wxouser.toJsonNotshow2()+",err="+wxouser.getErrcode()+","+wxouser.getErrmsg());
			refreshTokens();
			wxcfg=MyUtils.takeWxcfg(bizId);
			wxouser= UserManageServAPI.cgiBinUserInfo(wxcfg.getAccesstoken(), openid,null);
		}
		return wxouser;
	}
	/**
	 * 获取微信授权地址(根据issimple来决定到底是简单版还是完全版)
	 * @param bizId   店的id
	 * @param redirectUri	  回调地址
	 * @param isSimple 是否为简单版，false返回的是完全版的授权地址，true返回的是简单版的授权地址
	 * @return https://open.weixin.qq.com/...redirect_uri
	 * @throws Exception
	 */
	public String gotoWeixOauthUrl(Integer bizId,String redirectUri,boolean isSimple) throws BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		if(wxcfg==null){
			throw new BoException("当前店家微信配置为空");
		}
		if(isSimple){
			return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxcfg.getAppid()
					+"&redirect_uri="+StringUtilz.urlEncodeUTF8(redirectUri)
					+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		}
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxcfg.getAppid()
				+"&redirect_uri="+StringUtilz.urlEncodeUTF8(redirectUri)
				+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	}	
	/**
	 * 根据店家获取WaMenu，这个对象是给微信用的
	 * @param bizId
	 * @return  WaMenu
	 */
	public WaMenu takeWamenuByBizId(Integer bizId) throws BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		try {
			//根据bizId对应的菜单
			Wxmenu wxmenu0=wxmenuService.getFirst(null, "userId=?0 and parentid=0", null, new Object[]{bizId});
			if(wxmenu0 == null){
				logger.info("亲,"+bizId+"号店还没有配置菜单呢，不能生成微信自定义菜单");
				throw new BoException("亲,"+bizId+"号店还没有配置菜单呢，不能生成微信自定义菜单");
			}
			List<Wxmenu> firstWxmenuList =wxmenuService.listfind(null,"userId=?0 and parentid=?1","id desc", new Object[]{bizId,wxmenu0.getId()});
			if(firstWxmenuList == null){
				logger.info("亲,"+bizId+"号店还没有配置一级菜单呢，不能生成微信自定义菜单");
				throw new BoException("亲,"+bizId+"号店还没有配置一级菜单呢，不能生成微信自定义菜单");
			}
			List<WaButton> buttonList = new ArrayList<WaButton>();
			Wxmenu wxmenu=null;
			for (int i = 0; i < firstWxmenuList.size(); i++) {

				List<Wxmenu> secondWxmenuList =wxmenuService.listfind(null,"userId=?0 and parentid=?1","id desc", new Object[]{bizId,firstWxmenuList.get(i).getId()});
				List<WaSubButton> sub_buttonList = new ArrayList<WaSubButton>();
				for (int j = 0; j < secondWxmenuList.size(); j++) {
					//wxmenu的二级菜单   转化成 wamenu的二级菜单
					wxmenu=secondWxmenuList.get(j);
					WaSubButton waSubButton = new WaSubButton(wxmenu.getId(), wxmenu.getType(), wxmenu.getName(), wxmenu.getKey1(), wxmenu.getUrl(), wxmenu.getMediaId(), null, null);
					sub_buttonList.add(waSubButton);
				}
				//wxmenu的一级菜单   转化成 wamenu的一级菜单
				wxmenu=firstWxmenuList.get(i);
				WaButton waButton =new WaButton(wxmenu.getId(), wxmenu.getType(), wxmenu.getName(), wxmenu.getKey1(), wxmenu.getUrl(), wxmenu.getMediaId(), sub_buttonList, null, null);
				buttonList.add(waButton);
			}
			//生成wamenu对象
			WaMenu waMenu = new WaMenu(null, bizId, buttonList, null, null);
			return waMenu;
		} catch (BoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 向微信平台发送创建菜单
	 * @param bizId
	 * @throws BoException
	 * @throws Exception
	 */
	public void createWxMenu(Integer bizId) throws BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		Wxcfg wxcfg = MyUtils.takeWxcfg(bizId);
		WaMenu waMenu = takeWamenuByBizId(bizId);
		//腾讯返回对象
		BaseResult baseResult=MenuServAPI.menuCreate(waMenu, wxcfg.getAccesstoken());
		if(baseResult == null){
			logger.error("在创建微信自定义菜单时，发现baseResult为null,腾讯接口交换失败");
			throw new BoException("在创建微信自定义菜单时，发现baseResult为null,腾讯接口交换失败");
		}
		//若错误码为null或是0时，则说明接口调用正确
		if(baseResult.getErrcode()==null || baseResult.getErrcode().intValue()==0){
			if(logger.isDebugEnabled()){
				logger.debug(bizId+"号店创建微信自定义菜单成功");
			}
			//(bizId+"号店创建微信自定义菜单成功");
		}else{
			logger.error("在创建微信自定义菜单时，腾讯返回的错误在于："+baseResult.getErrmsg()+"("+baseResult.getErrcode()+")");
			throw new BoException("在创建微信自定义菜单时，腾讯返回的错误在于："+baseResult.getErrmsg()+"("+baseResult.getErrcode()+")");
		}
	}
	/**
	 * 同步User与Wxouser，双方openid保持一致
	 * @param user
	 * @param openid
	 * @param reloadUser 是否还要从数据库中重捞user
	 */
	public void updateSyncUserWxouser(User user,String openid,boolean reloadUser) throws Exception{
		if(StringUtils.isBlank(openid)||user.getId()==null)
			return;
		User userdb=null;
		if(reloadUser){
			userdb=userService.get(null, user.getId());
		}else{
			userdb=user;
		}			
		if(userdb!=null&&StringUtils.isBlank(userdb.getOpenid())){
			userdb.setOpenid(openid);
			userService.update(null,userdb);
		}
		Wxouser wxouser=wxouserService.get(null, openid);
		if(wxouser==null){
			wxouser=new Wxouser(openid);
			wxouser.setUserId(userdb.getId());
			wxouserService.saveCreate(null, new Wxouser(), wxouser, null, null);
		}else{
			if(wxouser.getUserId()==null){
				wxouser.setUserId(userdb.getId());
				wxouserService.update(null,wxouser);
			}
		}
	}
	/**
	 * 创建测试用户
	 * @param user
	 * @param mobile
	 * @throws BoException
	 * @throws Exception
	 */
	public void createTestUser(User user,String mobile) throws BoException,Exception{
		if(StringUtils.isBlank(mobile))
			return;
		if(mobile.indexOf(Conf.TESTUSER)!=0)
			return;
		if(user==null || StringUtils.isBlank(user.getOpenid())){
			user.setOpenid(mobile);
		}
		Wxouser wxouser=(Wxouser)wxouserService.get(null, user.getOpenid());
		if(wxouser==null){
			wxouser = new Wxouser(
					Conf.BIZID , //Integer 所属卖家  实际上是bizId 
					user.getId() , //Integer 平台用户   
					1 , //Integer 用户信息类型 default=1  {'0':'只有openid','1':'完全版'}
					"测试"+mobile , //String 用户昵称   
					1 , //Integer 性别 default=0  {'1':'男','2':'女','0':'未知'}
					null , //String 省份  160x160 
					null , //String 城市 default=0  
					null , //String 国家 default=0  
					null , //String 用户头像   
					null , //String 用户特权信息   
					null , //String 微信标识   
					"测试"+mobile , //String 真实姓名   
					mobile , //String 手机号   
					null , //String 电子邮件   
					null , //String 收货邮编   
					null , //String 收货地址   
					0 , //Integer 是否订阅 default=0 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}
					null , //String 关注时间  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
					null , //String 备注  公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 
					null , //Integer 所在的分组ID  用户所在的分组ID 
					null
					);
			wxouser.setId(user.getOpenid());
			wxouserService.saveCreate(null, new Wxouser(), wxouser, null, null);
		}		
	}
	/**
	 * 清理过期的Map[Code,Wxouser] 
	 */
	public void taskCleanMapCodeWxouser(){
		synchronized (Rt.mapCw) {
			Iterator it=Rt.mapCw.keySet().iterator(); 
			while(it.hasNext()) { 
				String code=(String)it.next(); 
				Wxouser wxouser=Rt.mapCw.get(code);
				if(isOvertimeWxouser(wxouser)){
					if (logger.isDebugEnabled())
						logger.debug("清理过期的Map[Code,Wxouser],wxouser=" + wxouser.getOpenid());
					it.remove(); 				
				}

			}
		}		
	}
	/**
	 * code+Wxouser有没有过期?
	 * @param wxouser
	 * @return
	 */
	private boolean isOvertimeWxouser(Wxouser wxouser){
		if(wxouser==null || wxouser.getObj1()==null ||!(wxouser.getObj1() instanceof Date))
			return true;
		Date last=(Date)wxouser.getObj1();
		Calendar now = Calendar.getInstance();
		boolean ret=(now.getTimeInMillis()-last.getTime())>CODE_WXOUSER_OVERTIME_MINUTE*60*1000;
		return ret;
	}
	/**
	 * 
	 * @param wxouser
	 * @param Fcode  父亲openid
	 * @param code openid
	 * @throws Exception
	 */
	public void createTestWxr(Wxouser wxouser,String Fcode,String code) throws Exception{
		boolean isNeedWxrb=true;
		//父亲openid
		String popenid=Fcode;
		Wxouser pwxouser=wxouserService.get(null, popenid);
		if(pwxouser==null){
			throw new BoException("父不存在");
		}
		//自己
		Wxr me=wxrService.get(null,wxouser.getId());
		if(me==null){
			me = new Wxr(
					popenid , //String 父openid default=0  
					0 , //Integer 孩子数量 default=0  
					null , //String 二维码  600x600 
					null
				);
			me.setId(wxouser.getId());
			wxrService.save(null,me);
			isNeedWxrb=false;
		}else{
			//父ID为空时放父亲
			if(StringUtils.isBlank(me.getParentid())){
				me.setParentid(popenid);
				wxrService.update(null, me);
				isNeedWxrb=false;
			}
		} 
		if (logger.isDebugEnabled())
			logger.debug("me=" + me.toJsonNotshow2());
		if(isNeedWxrb){
			Wxrb wxrb=(Wxrb)wxrbService.getFirst(null,"openid=?0 and popenid=?1", null, new Object[]{wxouser.getId(),popenid});
			if(wxrb==null){
				wxrbService.save(null,
						new Wxrb(
								wxouser.getId() , //String openid default=0  
								popenid , //String 父openid default=0
								0,//Integer 状态 default=0  {'0':'初创','1':'业务已处理'}
								null
							)
				);
			}
		}
	}
	/**
	 * 根据openid给wx用户发送消息
	 * @param dbuser
	 * @throws BoException
	 */
	public void sendWxMsgUid(User user) throws BoException{
		Random random=new Random();
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		String access_token=wxcfg.getAccesstoken();
		WaSendcustommsgTextParam msg=new WaSendcustommsgTextParam();
		msg.setTousername(user.getOpenid());
		WaEntityText waEntityText=new WaEntityText();
		if("true".equals(propMe.getIsYjtcm())){
			waEntityText.setContent("大御医感谢您的支持,默认密码111111,请及时修改"+user.getGmtCreateString()+"("+(random.nextInt(9000) + 1000)+user.getId()+")");
		}else{
			waEntityText.setContent("医药一键通感谢您的支持,默认密码111111,请及时修改"+user.getGmtCreateString()+"("+(random.nextInt(9000) + 1000)+user.getId()+")");
		}	
		msg.setText1(waEntityText);
		msg.setMsgtype("text");
		CustomServAPI.sendText(access_token, msg);
	}


}
