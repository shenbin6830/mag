package com.zmax.mag.service.spec;



import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.common.conf.AttrStatic;
import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.OpenWeixinQQException;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;
import com.zmax.mag.domain.conf.*;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.utils.MyUtils;
import com.zmax.mag.service.utils.wxapi.QrcodeServAPI;



/**
 * 微信二维码服务和本地二维码服务
 * 微信永久：/res/img/qrcode/f/nnnn/waQrcodeticketSceneId.JPG
 * 微信临时：/res/img/qrcode/t/nnnn/waQrcodeticketSceneId.JPG
 * @author zmax
 *
 */
@Service
public class SpecWxQrService {
	private static final Logger logger = Logger.getLogger(SpecWxQrService.class);

	/**广告使用的关注二维码		关键字就是QRADV
	 * <br>已经注册用户在扫描后积分放到用户账户中
	 * <br>首次关注用户，需要在注册后，登录后进行发放。
	 * */
	public static final String WX_QR_ADV="QRADV";
	/**
	 * 用户扫描用户，创建二者的微信上下级关系
	 */
	public static final String WX_QR_WXR="QRWXR";

	@Autowired
	WxrService wxrService;
	@Autowired
	WxrbService wxrbService;
	@Autowired
	WaQrcodeticketSceneService waQrcodeticketSceneService;

	@Autowired
	PropSys propSys;
	@Autowired
	SpecUserService specUserService;
	@Autowired
	SpecWxService specWxService;
	@Autowired
	WaQradvService waQradvService;

	/**
	 * QR二维码图ID和二级路径的计算规则
	 * @param id waQrcodeticketScene.getId()
	 * @return
	 */
	public int dirQrNum(int id){
		return (id/1000+1)*1000;
	}
	/**
	 * 临时二维码的URL地址
	 * @param id waQrcodeticketScene.getId()
	 * @return
	 */
	public String urlQrt(int id){
		String filename = id+".JPG";
		String qrcodeUrl = propSys.getDomain()+"/"+propSys.getWebnamee()+"/res/img/qrcode/t/"+dirQrNum(id)+"/"+filename;
		return qrcodeUrl;
	}
	/**
	 * 永久二维码的URL地址
	 * @param id waQrcodeticketScene.getId()
	 * @return
	 */
	public String urlQrf(int id){
		String filename = id+".JPG";
		String qrcodeUrl = propSys.getDomain()+"/"+propSys.getWebnamee()+"/res/img/qrcode/f/"+dirQrNum(id)+"/"+filename;
		return qrcodeUrl;
	}
	/**
	 * 微信通用版二维码，包括永久与临时
	 * @param request
	 * @param bizId
	 * @param waQrcodeticketScene 
	 * @return
	 * @throws BoException
	 * @throws Exception
	 * @return imgurl二维码的互联网地址
	 */
	public String saveQr(HttpServletRequest request,Integer bizId,WaQrcodeticketScene waQrcodeticketScene) throws BoException,Exception{
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		waQrcodeticketSceneService.saveOrUpdate(null,waQrcodeticketScene);
		//保存的初步路径
		String realPath = request.getSession().getServletContext().getRealPath("/res/img/qrcode");
		//二维码图片名称，借助id唯一来标明
		String filename = waQrcodeticketScene.getId()+".JPG";
		String tf = "t";//临时二维码图片文件夹目录
		if(waQrcodeticketScene.getStatusTemporary().intValue()==1){
			tf =  "f";//永久二维码图片文件夹目录
		}
		//0-999 ->/1000,1000-1999->/2000
		int dirNum = dirQrNum(waQrcodeticketScene.getId());
		//返回二维码图片的路径(给前端用)
		String qrcodeUrl = propSys.getDomain()+"/"+propSys.getWebnamee()+"/res/img/qrcode/"+tf+"/"+dirNum+"/"+filename;
		logger.debug("propSys.getWebnamee()==="+propSys.getWebnamee());
		logger.debug("qrcodeUrl==="+qrcodeUrl);
		//二维码存储的空间地址
		File qrcode = new File(realPath+"/"+tf+"/"+dirNum+"/"+filename);
		//判断二维码图片的上级目录是否存在，不存在则创建一个
		File qrcodeParent = new File(qrcode.getParent());
		if(!qrcodeParent.exists()){
			qrcodeParent.mkdirs();
		}
		//0:临时二维码处理方式
		//没有就创建，有的话判断生成日期，超过7天(7天是临时二维码的有效时间，为了防止时间错误可以设置为6天)，则删除图片，重新生成
		//1:永久二维码处理方式
		//没有就创建
		if(waQrcodeticketScene.getStatusTemporary().intValue()==0){	//0：处理临时二维码
			//用来标记临时二维码是否需要创建
			boolean isneedCreate=false;
			if(!qrcode.exists()){
				isneedCreate = true;
			}else{
				long createTime = qrcode.lastModified();//返回文件最后修改时间，是以个long型毫秒数
				long nowTime = System.currentTimeMillis();
				//超过600000s则删除原临时二维码图片
				if((nowTime-createTime)/1000>600000){
					if(qrcode.isFile()){
						qrcode.delete();
					}
				}
				isneedCreate = true;
			}
			if(isneedCreate){
				WaQrcodeticketParam waQrcodeticketParam=new WaQrcodeticketParam(2592000, "QR_SCENE", new WaQrcodeticketActioninfo(waQrcodeticketScene,null,null), null, null);
				if (logger.isDebugEnabled()){
					logger.debug("生成临时关注二维码时 ,waQrcodeticketScene="+waQrcodeticketScene.toString());
					logger.debug("生成临时关注二维码时 ,waQrcodeticketParam="+waQrcodeticketParam.toString());
				}
				Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
				WaQrcodeticketRet waQrcodeticketRet=QrcodeServAPI.qrcodeticket(wxcfg.getAccesstoken(), waQrcodeticketParam);
				if(StringUtils.isBlank(waQrcodeticketRet.getTicket())){
					throw new BoException("在生成关注二维码时ticket为为空");
				}
				byte[] bb=QrcodeServAPI.qrcodePic(waQrcodeticketRet.getTicket());
				FileUtils.writeByteArrayToFile(qrcode, bb);
			}
		}else if(waQrcodeticketScene.getStatusTemporary().intValue()==1){//1：处理永久二维码
			if(!qrcode.exists()){
				WaQrcodeticketParam waQrcodeticketParam=new WaQrcodeticketParam(null, "QR_LIMIT_SCENE", new WaQrcodeticketActioninfo(waQrcodeticketScene,null,null), null, null);
				if (logger.isDebugEnabled()){
					logger.debug("生成永久关注二维码时 ,waQrcodeticketScene="+waQrcodeticketScene.toString());
					logger.debug("生成永久关注二维码时 ,waQrcodeticketParam="+waQrcodeticketParam.toString());
				}
				Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
				WaQrcodeticketRet waQrcodeticketRet=QrcodeServAPI.qrcodeticket(wxcfg.getAccesstoken(), waQrcodeticketParam);
				if(StringUtils.isBlank(waQrcodeticketRet.getTicket())){
					throw new BoException("在生成关注二维码时ticket为为空");
				}
				byte[] bb=QrcodeServAPI.qrcodePic(waQrcodeticketRet.getTicket());
				FileUtils.writeByteArrayToFile(qrcode, bb);
			}
		}
		if (logger.isDebugEnabled()){
			logger.debug("关注二维码图片路径(给js用)="+qrcodeUrl);
		}
		return qrcodeUrl;
	}

	/**
	 * 扫码产生的二维码关注或进入公众号
	 * @param sceneId Integer 场景ID
	 * @param wxouser 这是已经是从数据库中取出的wxouse了
	 * @param waRecvmsg
	 * @param bizId
	 * @return xml
	 * @throws OpenWeixinQQException
	 * @throws BoException
	 * @throws Exception
	 */
	public String attationOrEnterByScan(Integer sceneId,Wxouser wxouser,WaRecvmsg waRecvmsg,Integer bizId) throws OpenWeixinQQException, BoException, Exception {
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		WaQrcodeticketScene waQrcodeticketScene=waQrcodeticketSceneService.get(null,sceneId);
		if(waQrcodeticketScene==null){
			if (logger.isDebugEnabled())
				logger.debug("关注二维码:null");
			return specWxService.msgRespXml(waRecvmsg,"欢迎来到本公众号，二维码已经过期，请重新生成，若与客服联系");
		}
		//广告二维码
		if(WX_QR_ADV.equals(waQrcodeticketScene.getActType())){
			if (logger.isDebugEnabled())
				logger.debug("广告二维码:notnull");
			return updateUseQrAdv(waQrcodeticketScene, waRecvmsg, wxouser);
		}
		//上下级二维码
		if(WX_QR_WXR.equals(waQrcodeticketScene.getActType())){
			if (logger.isDebugEnabled())
				logger.debug("上下级二维码:notnull");
			return updateUseQrWxr(waQrcodeticketScene, waRecvmsg, wxouser);
		}
		return specWxService.msgRespXml(waRecvmsg,"欢迎来到本公众号，我们将竭力提供最好的服务!");
	}

	///////////////////////////////////////////////
	/////////下面是二维码业务，主要包括创建与使用
	/////////创建：二维码场景及转换表中保存:操作类型/关键字,图和对应的数据库表数据
	/////////使用：根据场景ID找到数据库表数据，再根据关键字进行相应操作
	/////////
	///////////////////////////////////////////////

	///////////////使用系列：
	/**
	 * 使用：
	 * 通过广告二维码进行了关注，
	 * 广告二维码逻辑：关键字=QRADV,转换场景中的act_detail就是wa_qradv.id,向用户返回文本消息或图文是由wa_qradv指定的。
	 * @param waQrcodeticketScene
	 * @param waRecvmsg
	 * @param wxouser
	 * @return
	 * @throws OpenWeixinQQException
	 * @throws BoException
	 * @throws Exception
	 */
	public String updateUseQrAdv(WaQrcodeticketScene waQrcodeticketScene,WaRecvmsg waRecvmsg,Wxouser wxouser) throws OpenWeixinQQException, BoException, Exception{
		Integer id=Integer.valueOf(waQrcodeticketScene.getActDetail());
		if(id==null) 
			return "";
		WaQradv waQradv=waQradvService.get(null, id);
		if(waQradv==null) 
			return "";
		//关注+1
		waQradvService.execute(null,"update WaQradv set num=num+1 where id="+id, null);
		if(waQradv.getRettype().intValue()==0){
			return "";
		}
		if(waQradv.getRettype().intValue()==1){
			return specWxService.msgRespXml(waRecvmsg,waQradv.getRettxt());
		}
		if(waQradv.getRettype().intValue()==2){
			return specWxService.msgRespXmlNewsById(waRecvmsg, waQradv.getWaEntityArticleId());
		}
		return "";
	}
	/**
	 * 使用：
	 * 扫描用户的上下级二维码进行了关注，
	 * 上下级二维码逻辑：关键字=QRWXR,转换场景中的act_detail就是wxr.id/popenid,向用户返回文本消息或图文是由wa_qradv指定的。
	 * 扫描后，创建wxr或者wxrb，如果是第一次，创建wxr，否则创建 wxrb
	 * 用户登录后，会根据openid,更新wxr中的userId,roleid等
	 * @param waQrcodeticketScene
	 * @param waRecvmsg
	 * @param wxouser
	 * @return
	 * @throws OpenWeixinQQException
	 * @throws BoException
	 * @throws Exception
	 */
	public String updateUseQrWxr(WaQrcodeticketScene waQrcodeticketScene,WaRecvmsg waRecvmsg,Wxouser wxouser) throws OpenWeixinQQException, BoException, Exception{
		if(waQrcodeticketScene.getActDetail()==null) 
			return "";
		boolean isNeedWxrb=true;
		String popenid=waQrcodeticketScene.getActDetail();
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
			Wxrb wxrb=(Wxrb)wxrbService.getFirst(null, "openid=?0 and popenid=?1", null, new Object[]{wxouser.getId(),popenid});
			if(wxrb==null){
				wxrbService.save(null,
						new Wxrb(
								wxouser.getId() , //String openid default=0  
								waQrcodeticketScene.getActDetail() , //String 父openid default=0
								0,//Integer 状态 default=0  {'0':'初创','1':'业务已处理'}
								null
							)
				);
			}
		}
		//TODO:根据父亲的类型返回不同图文	
		//Wxr father=wxrService.get(null,waQrcodeticketScene.getActDetail());
		/*//扫完后去创建医患关系
		specRelationService.member1sDoctorScan(me.getId());
		//如果被扫码的是医生码，返回注册链接
		if (logger.isDebugEnabled())
			logger.debug("popenid=" + popenid);
		if(specRelationService.isQrUserDoctor(popenid)){
			return specWxService.msgRespXmlNewsByDb(waRecvmsg, "QRDOCTOR");
		}
		//如果被扫码的是业务员码，返回注册链接
		if(specRelationService.isQrUserSeller(popenid)){
			return specWxService.msgRespXmlNewsByDb(waRecvmsg, "QRSELLER");
		}		*/
		return specWxService.msgRespXmlByDb(waRecvmsg, "WXR");
	}

	////////////////////创建系列


	/**
	 * 创建广告二维码，是个关注二维码，
	 * 在创建和更新后进行，因为场景需要waQradv.id，所以此时waQradv已经存过DB
	 * 数据量少，所以可以每次都做。
	 * @param request
	 * @param waQradv WaQradv
	 * @return
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void createAfterQrWaQradv(HttpServletRequest request,WaQradv waQradv) throws BoException, NeedLoginException, Exception{
		String ACTTYPE=WX_QR_ADV;
		if(waQradv.getId()==null)
			waQradvService.save(null,waQradv);

		//广告二维码逻辑：关键字=QRADV,转换场景中的act_detail就是wa_qradv.id,返回是wa_qradv指定的
		String actDetail=""+waQradv.getId();
		WaQrcodeticketScene waQrcodeticketScene=(WaQrcodeticketScene)waQrcodeticketSceneService.getFirst(null, "actType=?0 and actDetail=?1 ",null, new Object[]{ACTTYPE,actDetail});
		if(waQrcodeticketScene==null){
			waQrcodeticketScene = new WaQrcodeticketScene(
					null , //String 场景值ID字符串形式  字符串类型，长度限制为1到64，仅永久二维码支持此字段 
					ACTTYPE , //String 操作类型   {'pwxinvitememberId':'pwxinvitememberId','popenid':'popenid','sobargin':'sobargin'}
					null , //String 操作参数分隔符 default=, 默认逗号 
					actDetail , //String 操作详情或参数  主要用于唯一性查询 
					1 , //Integer 是否为临时二维码 default=0  {'0':'是临时二维码','1':'是永久二维码'}
					1 , //Integer 是否有效 default=1 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}
					null
					);
			waQrcodeticketSceneService.save(null,waQrcodeticketScene);
		}
		String qrimg1=saveQr(request, Conf.BIZID, waQrcodeticketScene);
		waQradv.setImgqr(qrimg1);
		waQradvService.update(null,waQradv);
	}
	/**
	 * 创建用户上下级二维码，未来使用在上下级关注中，用户二维码是操作员手工点击创建的，如果二维码图存在，则不会向腾讯申请二维码图
	 * @param request
	 * @param wxr Wxr
	 * @return
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void createOrUpdateWxrQr(HttpServletRequest request,Wxr wxr) throws BoException, NeedLoginException, Exception{
		if(wxr==null)
			throw new Exception("wxr==null");
		if(wxr.getUserId()==null)
			throw new BoException("必须是注册用户");
		if(wxr.getRoleId()==null)
			throw new BoException("角色为空");

		String ACTTYPE=WX_QR_WXR;
		if(wxr.getId()==null)
			wxrService.save(null,wxr);
		if(StringUtils.isNotBlank(wxr.getImgqr()))
			return;
		//用户上下级二维码：关键字=WX_QR_WXR,转换场景中的act_detail就是openid
		String actDetail=wxr.getId();
		WaQrcodeticketScene waQrcodeticketScene=(WaQrcodeticketScene)waQrcodeticketSceneService.getFirst(null, "actType=?0 and actDetail=?1 ",null, new Object[]{ACTTYPE,actDetail});
		if(waQrcodeticketScene==null){
				waQrcodeticketScene = new WaQrcodeticketScene(
					null , //String 场景值ID字符串形式  字符串类型，长度限制为1到64，仅永久二维码支持此字段 
					ACTTYPE , //String 操作类型   
					null , //String 操作参数分隔符 default=, 默认逗号 
					actDetail , //String 操作详情或参数  主要用于唯一性查询 
					1 , //Integer 是否为临时二维码 default=0  {'0':'是临时二维码','1':'是永久二维码'}
					1 , //Integer 是否有效 default=1 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}
					null
				);
			waQrcodeticketSceneService.save(null,waQrcodeticketScene);
		}
		String qrimg1=saveQr(request, Conf.BIZID, waQrcodeticketScene);
		wxr.setImgqr(qrimg1);
		wxrService.update(null,wxr);
	}
}
