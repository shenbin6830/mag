/**
 * 
 */
package com.zmax.mag.service.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;












import com.zmax.common.conf.AttrStatic;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.string.DateUtils;
import com.zmax.mag.domain.bean.Quick;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.service.my.QuickService;
import com.zmax.mag.service.spec.SpecStatiSCService;





/**
 * 积分类的定时器
 * cron：指定cron表达式 Seconds Minutes Hours DayofMonth Month DayofWeek(?) Year
 * fixedDelay：表示从上一个任务完成开始到下一个任务开始的间隔，单位是毫秒。
 * fixedRate：即从上一个任务开始到下一个任务开始的间隔，单位是毫秒
 * 
 * 
 * 定时任务
 * 字段 允许值 允许的特殊字符  
秒 0-59 , - * /  
分 0-59 , - * /  
小时 0-23 , - * /  
日期 1-31 , - * ? / L W C  
月份 1-12 或者 JAN-DEC , - * /  
星期 1-7 或者 SUN-SAT , - * ? / L C #  
年（可选） 留空, 1970-2099 , - * /  
表达式意义  
"0 0 12 * * ?" 每天中午12点触发  
"0 15 10 ? * *" 每天上午10:15触发  
"0 15 10 * * ?" 每天上午10:15触发  
"0 15 10 * * ? *" 每天上午10:15触发  
"0 15 10 * * ? 2005" 2005年的每天上午10:15触发  
"0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发  
"0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发  
"0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发  
"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发  
"0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发  
"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发  	
"0 15 10 15 * ?" 每月15日上午10:15触发  
"0 15 10 L * ?" 每月最后一日的上午10:15触发  
"0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发  
"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发  
"0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发  
每天早上6点  
0 0 6 * * ?  
每两个小时  
0 星/2 * * *  
晚上11点到早上8点之间每两个小时，早上八点  
0 23-7/2，8 * * *  
每个月的4号和每个礼拜的礼拜一到礼拜三的早上11点  
0 11 4 * 1-3  
1月1日早上4点  
0 4 1 1 *  

Scheduled(cron="0 0 0/2 * * ?" )   //每2h执行一次
Scheduled(fixedDelay=1*60*60*1000) //每1h执行一次
 * @author zmax
 *
 */
@Component
public class SpecTask {

	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	PropMy propmy;
	@Autowired
	PropSys propSys;

	@Autowired
	SpecStatiSCService specStatiService;
	@Autowired
	QuickService quickService;
	//////////////////启动后每过一段时间执行的任务

	/////////////////每天
	/**
	 * 积分日统计
	 */
	@Scheduled(cron="0 10 0 * * ?") //每天凌晨0:10 日统计
	public void scoreDaily(){
		if(!propSys.getTaskmain()){
			return;
		}
		try {
			Date yesterday=DateUtils.yesterday000(new Date());
			specStatiService.statiScoreDay(yesterday, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	/**
	 * 现金日统计
	 */
	@Scheduled(cron="0 30 0 * * ?") //每天凌晨0:30 日统计
	public void scoreCash(){
		if(!propSys.getTaskmain()){
			return;
		}
		try {
			Date yesterday=DateUtils.yesterday000(new Date());
			specStatiService.statiCashDay(yesterday, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	/**
	 * 每日悬赏发布
	 */
	@Scheduled(cron="0 1 0 * * ?") //每天凌晨0:30 日统计
	public void quickPublish(){
		List<Quick> list=quickService.listfind(new User(), "status=1", null, null);
		for(Quick quick:list)
		{
			//悬赏开始 那天早上0点
			Date startDate=quick.getGmtStart();
			
			if(DateUtils.isInToDay(startDate)){//isInToDay,表示传入Date是否在今天0:00~24:00之间,如果函数返回true，说明悬赏今天开始
				//将抢答状态改成开始抢答
				quick.setStatus(2);
				quickService.update(null, quick);
			}
		}
	}
	/**
	 * 每日抢答结束清算
	 */
	@Scheduled(cron="0 5 0 * * ?") //每天凌晨0:30 日统计
	public void quickEnd(){
		List<Quick> list=quickService.listfind(new User(), "status=2", null, null);
		for(Quick quick:list)
		{
			//悬赏开始 那天早上0点
			Date endDate=quick.getGmtOver();
			
			if(DateUtils.isInToDay(endDate)){
				//将抢答状态改成开始抢答
				quick.setStatus(3);
				quickService.update(null, quick);
			}
		}
	}
}
