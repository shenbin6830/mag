package com.zmax.mag.service.spec;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.common.exception.BoException;
import com.zmax.common.utils.string.DateUtils;
import com.zmax.mag.domain.bean.CashHis;
import com.zmax.mag.domain.bean.CashmemberStatiDay;
import com.zmax.mag.domain.bean.CashmemberStatiMonth;
import com.zmax.mag.domain.bean.ExpHis;
import com.zmax.mag.domain.bean.ExpmemberStatiDay;
import com.zmax.mag.domain.bean.ExpmemberStatiMonth;
import com.zmax.mag.domain.bean.Member;
import com.zmax.mag.domain.bean.ScoreHis;
import com.zmax.mag.domain.bean.ScorememberStatiDay;
import com.zmax.mag.domain.bean.ScorememberStatiMonth;
import com.zmax.mag.service.my.CashHisService;
import com.zmax.mag.service.my.CashmemberStatiDayService;
import com.zmax.mag.service.my.CashmemberStatiMonthService;
import com.zmax.mag.service.my.ExpHisService;
import com.zmax.mag.service.my.ExpmemberStatiDayService;
import com.zmax.mag.service.my.ExpmemberStatiMonthService;
import com.zmax.mag.service.my.MemberService;
import com.zmax.mag.service.my.ScoreHisService;
import com.zmax.mag.service.my.ScorememberStatiDayService;
import com.zmax.mag.service.my.ScorememberStatiMonthService;


/**
 * 统计服务之积分现金等
 * @author zmax
 *
 */
@Service
public class SpecStatiSCService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	SpecService specService;
	@Autowired
	MemberService memberService;

	@Autowired
	ScoreHisService scoreHisService;
	@Autowired
	ScorememberStatiDayService scorememberStatiDayService;
	@Autowired
	ScorememberStatiMonthService scorememberStatiMonthService;


	/**
	 * 【积分】日统计 + 月统计
	 * 1.获取这天流水
	 * 2.遍历流水汇总成日统计，创建日统计map
	 * 3.遍历日统计map，保存
	 * 没交易的人就不存了。
	 * @param date 被统计日0:0:0 如果被统计日是1号，结束后进行上月统计
	 * @param savedb
	 * @throws Exception
	 */
	public void statiScoreDay(Date date,boolean savedb) throws Exception{
		if(date==null)
			throw new Exception("date==null");
		date=DateUtils.thatdayMorning000(date);
		logger.info("积分日统计开始"+new SimpleDateFormat("yyyy-MM-dd").format(date));
		Date dateEnd=DateUtils.thatdayNight000(date);
		//计算结果	要被统计的日统计map[产品ID，日统计]
		Map<Integer,ScorememberStatiDay> mapScorememberStatiDay=new HashMap<Integer,ScorememberStatiDay>();
		//1.获取这天流水
		Date toNight=DateUtils.thatdayNight000(date);
		List<ScoreHis> listScoreHis=scoreHisService.listfind(null, "gmtCreate >= ?0 and gmtCreate < ?1", "memberId", new Object[]{date,toNight});
		if (logger.isDebugEnabled())
			logger.debug("listScoreHis.size()=" + listScoreHis.size());
		//2.遍历流水汇总成日统计，创建日统计map
		for (ScoreHis scoreHis : listScoreHis) {
			//获取统计对象
			ScorememberStatiDay scorememberStatiDay =scorememberStatiDayFromMap(mapScorememberStatiDay, scoreHis.getMemberId(),date);
			//加减
			scorememberStatiDay.setNum(scorememberStatiDay.getNum()+scoreHis.getNum());
		}

		//3.遍历日统计map，保存
		//删除那天的统计数据，重新插入数据库
		if(savedb){
			scorememberStatiDayService.execute(null, "delete from ScorememberStatiDay where gmtStati>=?0 and gmtStati<?1", new Object[]{date,dateEnd});
			if (logger.isDebugEnabled())
				logger.debug("删除ScorememberStatiDay数据");

		}
		//遍历
		Iterator<Entry<Integer, ScorememberStatiDay>> iter = mapScorememberStatiDay.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, ScorememberStatiDay> entry =  iter.next();
			Integer key = entry.getKey();
			ScorememberStatiDay scorememberStatiDay = entry.getValue();
			try {
				//上次的
				ScorememberStatiDay last=(ScorememberStatiDay)scorememberStatiDayService.getFirst(null, "gmtStati < ?0 and memberId=?1 ", "gmtStati desc", new Object[]{date,scorememberStatiDay.getMemberId()});
				if(last==null)
					last = new ScorememberStatiDay(
							null , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
							scorememberStatiDay.getMemberId() , //Integer 会员   
							0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
							0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
							0 , //Integer 月末数 default=0 下月1日0:0时的分数 
							null , //String 备注说明   
							null
							);				
				//初数末数
				scorememberStatiDay.setNstart(last.getNend());
				scorememberStatiDay.setNend(scorememberStatiDay.getNstart()+scorememberStatiDay.getNum());
				if(savedb){
					scorememberStatiDayService.saveCreate(null, new ScorememberStatiDay(), scorememberStatiDay, null, null);
					updateMemberScoreByDay(date, scorememberStatiDay, savedb);
				}				
			} catch (BoException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
		//月统计
		if(DateUtils.isFirstDayOfMonth(date)){
			Date lastMonth1st=DateUtils.lastMonth1st(date);
			statiScoreMonth(lastMonth1st, savedb);
		}
	}
	/**
	 * 【积分】月统计
	 * 1.取出本月所有日统计，根据日统计累加出月统计map
	 * 2.取出所有当时的member，如果不在月统计map，创建空月统计map
	 * 3.循环月统计map，计算出月初月末,保存
	 * 
	 * @param date 被统计月第一天0:0:0
	 * @param savedb
	 * @throws Exception
	 */
	private void statiScoreMonth(Date date, boolean savedb)throws Exception{
		if(date==null)
			throw new Exception("date==null");
		date=DateUtils.thisMonth1st(date);
		logger.info("月统计开始:"+new SimpleDateFormat("yyyy-MM-dd").format(date));
		if(DateUtils.isSameYearMonth(date, new Date())){
			throw new Exception("时间不够，不能做月统计!!");
		}
		//上月1日
		Date lastMonth=DateUtils.lastMonth1st(date);
		//被统计月本月1日
		Date thisMonth=DateUtils.thisMonth1st(date);
		//被统计月下月1日
		Date nextMonth=DateUtils.nextMonth1st(date);

		//1.取出本月所有日统计，根据日统计累加出月统计map
		//结果[MemberId,ScorememberStatiMonth]
		Map<Integer,ScorememberStatiMonth> mapScorememberStatiMonth=new HashMap<Integer,ScorememberStatiMonth>();		
		//本月日统计列表
		List<ScorememberStatiDay> listScorememberStatiDay=scorememberStatiDayService.listfind(null, "gmtStati >= ?0 and gmtStati < ?1", null, new Object[]{thisMonth,nextMonth});
		if (logger.isDebugEnabled())
			logger.debug("[日统计]listScorememberStatiDay.size()="
					+ listScorememberStatiDay.size());
		for (ScorememberStatiDay scorememberStatiDay : listScorememberStatiDay) {
			ScorememberStatiMonth scorememberStatiMonth=scorememberStatiMonthFromMap(mapScorememberStatiMonth, scorememberStatiDay.getMemberId(), thisMonth);
			//本月累加小计
			scorememberStatiMonth.setNum(scorememberStatiMonth.getNum()+scorememberStatiDay.getNum());
		}
		//2.取出所有当时的member，如果不在月统计map，创建空月统计map
		List<Member> listMember=memberService.listfind(null, "gmtCreate < ?0", null, new Object[]{nextMonth});
		for (Member member : listMember) {
			scorememberStatiMonthFromMap(mapScorememberStatiMonth, member.getId(), thisMonth);
		}
		//3.循环月统计map，计算出月初月末,保存
		//删除那月的统计数据，重新插入数据库
		if(savedb)
			scorememberStatiMonthService.execute(null, "delete from ScorememberStatiMonth where gmtStati=?0", new Object[]{thisMonth});
		//遍历
		Iterator<Entry<Integer, ScorememberStatiMonth>> iter = mapScorememberStatiMonth.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, ScorememberStatiMonth> entry =  iter.next();
			Integer key = entry.getKey();
			ScorememberStatiMonth scorememberStatiMonth = entry.getValue();
			try {
				//上个月的
				ScorememberStatiMonth last=(ScorememberStatiMonth)scorememberStatiMonthService.getFirst(null, "gmtStati=?0 and memberId=?1 ", null, new Object[]{lastMonth,scorememberStatiMonth.getMemberId()});
				if(last==null)
					last = new ScorememberStatiMonth(
							lastMonth , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
							scorememberStatiMonth.getMemberId() , //Integer 会员   
							0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
							0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
							0 , //Integer 月末数 default=0 下月1日0:0时的分数 
							null , //String 备注说明   
							null
							);				
				//初数末数
				scorememberStatiMonth.setNstart(last.getNend());
				scorememberStatiMonth.setNend(scorememberStatiMonth.getNstart()+scorememberStatiMonth.getNum());
				if(savedb)
					scorememberStatiMonthService.saveCreate(null, new ScorememberStatiMonth(), scorememberStatiMonth, null, null);
			} catch (BoException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}
	/**
	 * 【积分】从Map中获取ScorememberStatiDay，没有的话，创建一个
	 * @param map
	 * @param memberId
	 * @param date
	 * @return
	 */
	private ScorememberStatiDay scorememberStatiDayFromMap(Map<Integer,ScorememberStatiDay> map,Integer memberId,Date date){
		ScorememberStatiDay obj=map.get(memberId);
		if(obj==null){
			//精简构造 会员积分日统计
			obj = new ScorememberStatiDay(
					date , //Date 被统计日  >=每天0:0，<第二天0:0 
					memberId , //Integer 会员   
					0 , //Integer 本日增减分数 default=0 正负，正是增加，负是减少，零不会创建数据记录 
					0 , //Integer 日初数 default=0 0:0时的数值，初数+增减分数=末数 
					0 , //Integer 日末数 default=0 明天0:0时的数值 
					null , //String 备注说明   
					null
					);			
			map.put(memberId, obj);
		}
		return obj;
	}

	/**
	 * 【积分】从Map中获取ScorememberStatiMonth，没有的话，创建一个
	 * @param map
	 * @param memberId
	 * @param date
	 * @return
	 */
	private ScorememberStatiMonth scorememberStatiMonthFromMap(Map<Integer,ScorememberStatiMonth> map,Integer memberId,Date date){
		ScorememberStatiMonth obj=map.get(memberId);
		if(obj==null){
			//精简构造 会员积分月统计
			obj = new ScorememberStatiMonth(
					date , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
					memberId , //Integer 会员   
					0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
					0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
					0 , //Integer 月末数 default=0 下月1日0:0时的分数 
					null , //String 备注说明   
					null
					);			
			map.put(memberId, obj);
		}
		return obj;
	}
	/**
	 * 【积分】如果scorememberStatiDay是最后一次日统计，算下是计算结果与表是否一致，不一致member值赋为计算结果，更新member
	 * 例：假如现在是0505,member.num = 0504的日统计.end + 0505流水和
	 * @param date 被统计日0:0:0
	 * @param scorememberStatiDay
	 * @param savedb
	 */
	private void updateMemberScoreByDay(Date date,ScorememberStatiDay scorememberStatiDay,boolean savedb){
		if(!savedb)
			return;
		//下次的
		ScorememberStatiDay next=(ScorememberStatiDay)scorememberStatiDayService.getFirst(null, "gmtStati > ?0 and memberId=?1 ", null, new Object[]{date,scorememberStatiDay.getMemberId()});
		if(next!=null)
			return;
		String sql="SELECT SUM(num) AS sumnum FROM score_his WHERE gmt_create > ?0";
		Long lAdd=(Long)specService.getFirstBySql(sql, new Object[]{date});
		int calcuNum=scorememberStatiDay.getNend()+lAdd.intValue();
		Member member=memberService.get(null, scorememberStatiDay.getMemberId());
		if(member==null)
			return;
		if(member.getScore()==null)
			member.setScore(0);
		if(calcuNum!=member.getScore().intValue()){
			logger.error("统计错误,严重错误,【积分】不符:计算结果="+calcuNum+",数据库="+member.getScore()+",会员id="+member.getId());
			member.setScore(calcuNum);
			memberService.update(null, member);
		}
	}
	
	@Autowired
	CashHisService cashHisService;
	@Autowired
	CashmemberStatiDayService cashmemberStatiDayService;
	@Autowired
	CashmemberStatiMonthService cashmemberStatiMonthService;


	/**
	 * 【现金】日统计 + 月统计
	 * 1.获取这天流水
	 * 2.遍历流水汇总成日统计，创建日统计map
	 * 3.遍历日统计map，保存
	 * 没交易的人就不存了。
	 * @param date 被统计日0:0:0 如果被统计日是1号，结束后进行上月统计
	 * @param savedb
	 * @throws Exception
	 */
	public void statiCashDay(Date date,boolean savedb) throws Exception{
		if(date==null)
			throw new Exception("date==null");
		date=DateUtils.thatdayMorning000(date);
		logger.info("现金日统计开始"+new SimpleDateFormat("yyyy-MM-dd").format(date));
		Date dateEnd=DateUtils.thatdayNight000(date);
		//计算结果	要被统计的日统计map[产品ID，日统计]
		Map<Integer,CashmemberStatiDay> mapCashmemberStatiDay=new HashMap<Integer,CashmemberStatiDay>();
		//1.获取这天流水
		Date toNight=DateUtils.thatdayNight000(date);
		List<CashHis> listCashHis=cashHisService.listfind(null, "gmtCreate >= ?0 and gmtCreate < ?1", "memberId", new Object[]{date,toNight});
		if (logger.isDebugEnabled())
			logger.debug("listCashHis.size()=" + listCashHis.size());
		//2.遍历流水汇总成日统计，创建日统计map
		for (CashHis cashHis : listCashHis) {
			//获取统计对象
			CashmemberStatiDay cashmemberStatiDay =cashmemberStatiDayFromMap(mapCashmemberStatiDay, cashHis.getMemberId(),date);
			//加减
			cashmemberStatiDay.setNum(cashmemberStatiDay.getNum()+cashHis.getNum());
		}

		//3.遍历日统计map，保存
		//删除那天的统计数据，重新插入数据库
		if(savedb){
			cashmemberStatiDayService.execute(null, "delete from CashmemberStatiDay where gmtStati>=?0 and gmtStati<?1", new Object[]{date,dateEnd});
			if (logger.isDebugEnabled())
				logger.debug("删除CashmemberStatiDay数据");

		}
		//遍历
		Iterator<Entry<Integer, CashmemberStatiDay>> iter = mapCashmemberStatiDay.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, CashmemberStatiDay> entry =  iter.next();
			Integer key = entry.getKey();
			CashmemberStatiDay cashmemberStatiDay = entry.getValue();
			try {
				//上次的
				CashmemberStatiDay last=(CashmemberStatiDay)cashmemberStatiDayService.getFirst(null, "gmtStati < ?0 and memberId=?1 ", "gmtStati desc", new Object[]{date,cashmemberStatiDay.getMemberId()});
				if(last==null)
					last = new CashmemberStatiDay(
							null , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
							cashmemberStatiDay.getMemberId() , //Integer 会员   
							0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
							0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
							0 , //Integer 月末数 default=0 下月1日0:0时的分数 
							null , //String 备注说明   
							null
							);				
				//初数末数
				cashmemberStatiDay.setNstart(last.getNend());
				cashmemberStatiDay.setNend(cashmemberStatiDay.getNstart()+cashmemberStatiDay.getNum());
				if(savedb){
					cashmemberStatiDayService.saveCreate(null, new CashmemberStatiDay(), cashmemberStatiDay, null, null);	
				}				
			} catch (BoException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
		//月统计
		if(DateUtils.isFirstDayOfMonth(date)){
			Date lastMonth1st=DateUtils.lastMonth1st(date);
			statiCashMonth(lastMonth1st, savedb);
		}
	}
	/**
	 * 【现金】月统计
	 * 1.取出本月所有日统计，根据日统计累加出月统计map
	 * 2.取出所有当时的member，如果不在月统计map，创建空月统计map
	 * 3.循环月统计map，计算出月初月末,保存
	 * 
	 * @param date 被统计月第一天0:0:0
	 * @param savedb
	 * @throws Exception
	 */
	private void statiCashMonth(Date date, boolean savedb)throws Exception{
		if(date==null)
			throw new Exception("date==null");
		date=DateUtils.thisMonth1st(date);
		logger.info("月统计开始:"+new SimpleDateFormat("yyyy-MM-dd").format(date));
		if(DateUtils.isSameYearMonth(date, new Date())){
			throw new Exception("时间不够，不能做月统计!!");
		}
		//上月1日
		Date lastMonth=DateUtils.lastMonth1st(date);
		//被统计月本月1日
		Date thisMonth=DateUtils.thisMonth1st(date);
		//被统计月下月1日
		Date nextMonth=DateUtils.nextMonth1st(date);

		//1.取出本月所有日统计，根据日统计累加出月统计map
		//结果[MemberId,CashmemberStatiMonth]
		Map<Integer,CashmemberStatiMonth> mapCashmemberStatiMonth=new HashMap<Integer,CashmemberStatiMonth>();		
		//本月日统计列表
		List<CashmemberStatiDay> listCashmemberStatiDay=cashmemberStatiDayService.listfind(null, "gmtStati >= ?0 and gmtStati < ?1", null, new Object[]{thisMonth,nextMonth});
		if (logger.isDebugEnabled())
			logger.debug("[日统计]listCashmemberStatiDay.size()="
					+ listCashmemberStatiDay.size());
		for (CashmemberStatiDay cashmemberStatiDay : listCashmemberStatiDay) {
			CashmemberStatiMonth cashmemberStatiMonth=cashmemberStatiMonthFromMap(mapCashmemberStatiMonth, cashmemberStatiDay.getMemberId(), thisMonth);
			//本月累加小计
			cashmemberStatiMonth.setNum(cashmemberStatiMonth.getNum()+cashmemberStatiDay.getNum());
		}
		//2.取出所有当时的member，如果不在月统计map，创建空月统计map
		List<Member> listMember=memberService.listfind(null, "gmtCreate < ?0", null, new Object[]{nextMonth});
		for (Member member : listMember) {
			cashmemberStatiMonthFromMap(mapCashmemberStatiMonth, member.getId(), thisMonth);
		}
		//3.循环月统计map，计算出月初月末,保存
		//删除那月的统计数据，重新插入数据库
		if(savedb)
			cashmemberStatiMonthService.execute(null, "delete from CashmemberStatiMonth where gmtStati=?0", new Object[]{thisMonth});
		//遍历
		Iterator<Entry<Integer, CashmemberStatiMonth>> iter = mapCashmemberStatiMonth.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, CashmemberStatiMonth> entry =  iter.next();
			Integer key = entry.getKey();
			CashmemberStatiMonth cashmemberStatiMonth = entry.getValue();
			try {
				//上个月的
				CashmemberStatiMonth last=(CashmemberStatiMonth)cashmemberStatiMonthService.getFirst(null, "gmtStati=?0 and memberId=?1 ", null, new Object[]{lastMonth,cashmemberStatiMonth.getMemberId()});
				if(last==null)
					last = new CashmemberStatiMonth(
							lastMonth , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
							cashmemberStatiMonth.getMemberId() , //Integer 会员   
							0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
							0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
							0 , //Integer 月末数 default=0 下月1日0:0时的分数 
							null , //String 备注说明   
							null
							);				
				//初数末数
				cashmemberStatiMonth.setNstart(last.getNend());
				cashmemberStatiMonth.setNend(cashmemberStatiMonth.getNstart()+cashmemberStatiMonth.getNum());
				if(savedb)
					cashmemberStatiMonthService.saveCreate(null, new CashmemberStatiMonth(), cashmemberStatiMonth, null, null);
			} catch (BoException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}
	/**
	 * 【现金】从Map中获取CashmemberStatiDay，没有的话，创建一个
	 * @param map
	 * @param memberId
	 * @param date
	 * @return
	 */
	private CashmemberStatiDay cashmemberStatiDayFromMap(Map<Integer,CashmemberStatiDay> map,Integer memberId,Date date){
		CashmemberStatiDay obj=map.get(memberId);
		if(obj==null){
			//精简构造 会员现金日统计
			obj = new CashmemberStatiDay(
					date , //Date 被统计日  >=每天0:0，<第二天0:0 
					memberId , //Integer 会员   
					0 , //Integer 本日增减分数 default=0 正负，正是增加，负是减少，零不会创建数据记录 
					0 , //Integer 日初数 default=0 0:0时的数值，初数+增减分数=末数 
					0 , //Integer 日末数 default=0 明天0:0时的数值 
					null , //String 备注说明   
					null
					);			
			map.put(memberId, obj);
		}
		return obj;
	}

	/**
	 * 【现金】从Map中获取CashmemberStatiMonth，没有的话，创建一个
	 * @param map
	 * @param memberId
	 * @param date
	 * @return
	 */
	private CashmemberStatiMonth cashmemberStatiMonthFromMap(Map<Integer,CashmemberStatiMonth> map,Integer memberId,Date date){
		CashmemberStatiMonth obj=map.get(memberId);
		if(obj==null){
			//精简构造 会员现金月统计
			obj = new CashmemberStatiMonth(
					date , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
					memberId , //Integer 会员   
					0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
					0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
					0 , //Integer 月末数 default=0 下月1日0:0时的分数 
					null , //String 备注说明   
					null
					);			
			map.put(memberId, obj);
		}
		return obj;
	}

	@Autowired
	ExpHisService expHisService;
	@Autowired
	ExpmemberStatiDayService expmemberStatiDayService;
	@Autowired
	ExpmemberStatiMonthService expmemberStatiMonthService;


	/**
	 * 【经验】日统计 + 月统计
	 * 1.获取这天流水
	 * 2.遍历流水汇总成日统计，创建日统计map
	 * 3.遍历日统计map，保存
	 * 没交易的人就不存了。
	 * @param date 被统计日0:0:0 如果被统计日是1号，结束后进行上月统计
	 * @param savedb
	 * @throws Exception
	 */
	public void statiExpDay(Date date,boolean savedb) throws Exception{
		if(date==null)
			throw new Exception("date==null");
		date=DateUtils.thatdayMorning000(date);
		logger.info("经验日统计开始"+new SimpleDateFormat("yyyy-MM-dd").format(date));
		Date dateEnd=DateUtils.thatdayNight000(date);
		//计算结果	要被统计的日统计map[产品ID，日统计]
		Map<Integer,ExpmemberStatiDay> mapExpmemberStatiDay=new HashMap<Integer,ExpmemberStatiDay>();
		//1.获取这天流水
		Date toNight=DateUtils.thatdayNight000(date);
		List<ExpHis> listExpHis=expHisService.listfind(null, "gmtCreate >= ?0 and gmtCreate < ?1", "memberId", new Object[]{date,toNight});
		if (logger.isDebugEnabled())
			logger.debug("listExpHis.size()=" + listExpHis.size());
		//2.遍历流水汇总成日统计，创建日统计map
		for (ExpHis expHis : listExpHis) {
			//获取统计对象
			ExpmemberStatiDay expmemberStatiDay =expmemberStatiDayFromMap(mapExpmemberStatiDay, expHis.getMemberId(),date);
			//加减
			expmemberStatiDay.setNum(expmemberStatiDay.getNum()+expHis.getNum());
		}

		//3.遍历日统计map，保存
		//删除那天的统计数据，重新插入数据库
		if(savedb){
			expmemberStatiDayService.execute(null, "delete from ExpmemberStatiDay where gmtStati>=?0 and gmtStati<?1", new Object[]{date,dateEnd});
			if (logger.isDebugEnabled())
				logger.debug("删除ExpmemberStatiDay数据");

		}
		//遍历
		Iterator<Entry<Integer, ExpmemberStatiDay>> iter = mapExpmemberStatiDay.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, ExpmemberStatiDay> entry =  iter.next();
			Integer key = entry.getKey();
			ExpmemberStatiDay expmemberStatiDay = entry.getValue();
			try {
				//上次的
				ExpmemberStatiDay last=(ExpmemberStatiDay)expmemberStatiDayService.getFirst(null, "gmtStati < ?0 and memberId=?1 ", "gmtStati desc", new Object[]{date,expmemberStatiDay.getMemberId()});
				if(last==null)
					last = new ExpmemberStatiDay(
							null , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
							expmemberStatiDay.getMemberId() , //Integer 会员   
							0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
							0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
							0 , //Integer 月末数 default=0 下月1日0:0时的分数 
							null , //String 备注说明   
							null
							);				
				//初数末数
				expmemberStatiDay.setNstart(last.getNend());
				expmemberStatiDay.setNend(expmemberStatiDay.getNstart()+expmemberStatiDay.getNum());
				if(savedb){
					expmemberStatiDayService.saveCreate(null, new ExpmemberStatiDay(), expmemberStatiDay, null, null);
					updateMemberExpByDay(date, expmemberStatiDay, savedb);
				}				
			} catch (BoException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
		//月统计
		if(DateUtils.isFirstDayOfMonth(date)){
			Date lastMonth1st=DateUtils.lastMonth1st(date);
			statiExpMonth(lastMonth1st, savedb);
		}
	}
	/**
	 * 【经验】月统计
	 * 1.取出本月所有日统计，根据日统计累加出月统计map
	 * 2.取出所有当时的member，如果不在月统计map，创建空月统计map
	 * 3.循环月统计map，计算出月初月末,保存
	 * 
	 * @param date 被统计月第一天0:0:0
	 * @param savedb
	 * @throws Exception
	 */
	private void statiExpMonth(Date date, boolean savedb)throws Exception{
		if(date==null)
			throw new Exception("date==null");
		date=DateUtils.thisMonth1st(date);
		logger.info("月统计开始:"+new SimpleDateFormat("yyyy-MM-dd").format(date));
		if(DateUtils.isSameYearMonth(date, new Date())){
			throw new Exception("时间不够，不能做月统计!!");
		}
		//上月1日
		Date lastMonth=DateUtils.lastMonth1st(date);
		//被统计月本月1日
		Date thisMonth=DateUtils.thisMonth1st(date);
		//被统计月下月1日
		Date nextMonth=DateUtils.nextMonth1st(date);

		//1.取出本月所有日统计，根据日统计累加出月统计map
		//结果[MemberId,ExpmemberStatiMonth]
		Map<Integer,ExpmemberStatiMonth> mapExpmemberStatiMonth=new HashMap<Integer,ExpmemberStatiMonth>();		
		//本月日统计列表
		List<ExpmemberStatiDay> listExpmemberStatiDay=expmemberStatiDayService.listfind(null, "gmtStati >= ?0 and gmtStati < ?1", null, new Object[]{thisMonth,nextMonth});
		if (logger.isDebugEnabled())
			logger.debug("[日统计]listExpmemberStatiDay.size()="
					+ listExpmemberStatiDay.size());
		for (ExpmemberStatiDay expmemberStatiDay : listExpmemberStatiDay) {
			ExpmemberStatiMonth expmemberStatiMonth=expmemberStatiMonthFromMap(mapExpmemberStatiMonth, expmemberStatiDay.getMemberId(), thisMonth);
			//本月累加小计
			expmemberStatiMonth.setNum(expmemberStatiMonth.getNum()+expmemberStatiDay.getNum());
		}
		//2.取出所有当时的member，如果不在月统计map，创建空月统计map
		List<Member> listMember=memberService.listfind(null, "gmtCreate < ?0", null, new Object[]{nextMonth});
		for (Member member : listMember) {
			expmemberStatiMonthFromMap(mapExpmemberStatiMonth, member.getId(), thisMonth);
		}
		//3.循环月统计map，计算出月初月末,保存
		//删除那月的统计数据，重新插入数据库
		if(savedb)
			expmemberStatiMonthService.execute(null, "delete from ExpmemberStatiMonth where gmtStati=?0", new Object[]{thisMonth});
		//遍历
		Iterator<Entry<Integer, ExpmemberStatiMonth>> iter = mapExpmemberStatiMonth.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, ExpmemberStatiMonth> entry =  iter.next();
			Integer key = entry.getKey();
			ExpmemberStatiMonth expmemberStatiMonth = entry.getValue();
			try {
				//上个月的
				ExpmemberStatiMonth last=(ExpmemberStatiMonth)expmemberStatiMonthService.getFirst(null, "gmtStati=?0 and memberId=?1 ", null, new Object[]{lastMonth,expmemberStatiMonth.getMemberId()});
				if(last==null)
					last = new ExpmemberStatiMonth(
							lastMonth , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
							expmemberStatiMonth.getMemberId() , //Integer 会员   
							0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
							0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
							0 , //Integer 月末数 default=0 下月1日0:0时的分数 
							null , //String 备注说明   
							null
							);				
				//初数末数
				expmemberStatiMonth.setNstart(last.getNend());
				expmemberStatiMonth.setNend(expmemberStatiMonth.getNstart()+expmemberStatiMonth.getNum());
				if(savedb)
					expmemberStatiMonthService.saveCreate(null, new ExpmemberStatiMonth(), expmemberStatiMonth, null, null);
			} catch (BoException e) {
				logger.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}
	/**
	 * 【经验】从Map中获取ExpmemberStatiDay，没有的话，创建一个
	 * @param map
	 * @param memberId
	 * @param date
	 * @return
	 */
	private ExpmemberStatiDay expmemberStatiDayFromMap(Map<Integer,ExpmemberStatiDay> map,Integer memberId,Date date){
		ExpmemberStatiDay obj=map.get(memberId);
		if(obj==null){
			//精简构造 会员经验日统计
			obj = new ExpmemberStatiDay(
					date , //Date 被统计日  >=每天0:0，<第二天0:0 
					memberId , //Integer 会员   
					0 , //Integer 本日增减分数 default=0 正负，正是增加，负是减少，零不会创建数据记录 
					0 , //Integer 日初数 default=0 0:0时的数值，初数+增减分数=末数 
					0 , //Integer 日末数 default=0 明天0:0时的数值 
					null , //String 备注说明   
					null
					);			
			map.put(memberId, obj);
		}
		return obj;
	}

	/**
	 * 【经验】从Map中获取ExpmemberStatiMonth，没有的话，创建一个
	 * @param map
	 * @param memberId
	 * @param date
	 * @return
	 */
	private ExpmemberStatiMonth expmemberStatiMonthFromMap(Map<Integer,ExpmemberStatiMonth> map,Integer memberId,Date date){
		ExpmemberStatiMonth obj=map.get(memberId);
		if(obj==null){
			//精简构造 会员经验月统计
			obj = new ExpmemberStatiMonth(
					date , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
					memberId , //Integer 会员   
					0 , //Integer 本月增减分数 default=0 正负，正是增加，负是减少，零也创建记录 
					0 , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
					0 , //Integer 月末数 default=0 下月1日0:0时的分数 
					null , //String 备注说明   
					null
					);			
			map.put(memberId, obj);
		}
		return obj;
	}
	/**
	 * 【经验】如果expmemberStatiDay是最后一次日统计，算下是计算结果与表是否一致，不一致member值赋为计算结果，更新member
	 * 例：假如现在是0505,member.num = 0504的日统计.end + 0505流水和
	 * @param date 被统计日0:0:0
	 * @param expmemberStatiDay
	 * @param savedb
	 */
	private void updateMemberExpByDay(Date date,ExpmemberStatiDay expmemberStatiDay,boolean savedb){
		if(!savedb)
			return;
		//下次的
		ExpmemberStatiDay next=(ExpmemberStatiDay)expmemberStatiDayService.getFirst(null, "gmtStati > ?0 and memberId=?1 ", null, new Object[]{date,expmemberStatiDay.getMemberId()});
		if(next!=null)
			return;
		String sql="SELECT SUM(num) AS sumnum FROM exp_his WHERE gmt_create > ?0";
		Long lAdd=(Long)specService.getFirstBySql(sql, new Object[]{date});
		int calcuNum=expmemberStatiDay.getNend()+lAdd.intValue();
		Member member=memberService.get(null, expmemberStatiDay.getMemberId());
		if(member==null)
			return;
		if(member.getExp()==null)
			member.setExp(0);
		if(calcuNum!=member.getExp().intValue()){
			logger.error("统计错误,严重错误,【经验】不符:计算结果="+calcuNum+",数据库="+member.getExp()+",会员id="+member.getId());
			member.setExp(calcuNum);
			memberService.update(null, member);
		}
	}
}
