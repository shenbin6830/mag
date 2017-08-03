/**
 * 时间类工具
 */
package com.zmax.common.utils.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils{
	public static final String YMDHMS="yyyy-MM-dd HH:mm:ss";
	/**
	 * 那天早上0:0:0
	 * @return
	 */
	public static Date thatdayMorning000(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}
	/**
	 * 那天晚上0:0:0
	 * @return
	 */
	public static Date thatdayNight000(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR)+1);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}
	/**
	 * 今天早上0:0:0
	 * @return
	 */
	public static Date todayMorning000(){
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}
	/**
	 * 今天晚上0:0:0
	 * @return
	 */
	public static Date todayNight000(){
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR)+1);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}
	/**
	 * 昨天早上0:0:0
	 * @return
	 */
	public static Date yesterday000(Date date){
		if(date==null)
			date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)-1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 上个月1号，0:0:0
	 * @param date null=现在
	 * @return
	 */
	public static Date lastMonth1st(Date date){
		if(date==null)
			date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DATE, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 本月1号，0:0:0
	 * @param date null=现在
	 * @return
	 */
	public static Date thisMonth1st(Date date){
		if(date==null)
			date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 下月1号，0:0:0
	 * @param date null=现在
	 * @return
	 */
	public static Date nextMonth1st(Date date){
		if(date==null)
			date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 传入日是当月最后一天
	 * @param date null=现在
	 * @return
	 */
	public static boolean isLastDayOfMonth(Date date){
		if(date==null)
			date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return (calendar.get(Calendar.DAY_OF_MONTH)==1);
	}
	/**
	 * 传入日是当月第一天
	 * @param date null=现在
	 * @return
	 */
	public static boolean isFirstDayOfMonth(Date date){
		if(date==null)
			date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//200
		return (calendar.get(Calendar.DAY_OF_MONTH)==1);
	}
	/**
	 * 两个传入日是在同年同月
	 * @param date
	 * @param date  
	 * @return
	 */
	public static boolean isSameYearMonth(Date date1,Date date2){
		if(date1==null)
			date1=new Date();
		if(date2==null)
			date2=new Date();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		//200
		return (calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH));
	}
	/**
	 * 传入的时间，是不是今天晚上0:0:0之前的
	 * @param date
	 * @return
	 */
	public static boolean isInToDay(Date date) {
		if(todayNight000().after(date))
			return true;
		return false;

	}
	/**
	 * 
	 * 函数说明：返回yyyy-MM-dd HH:mm:ss
	 * @param Date date
	 * @return 
	 * @throws 
	 * @author zmax
	 */
	public static String getYMDFormat(Date date){
		return new SimpleDateFormat(YMDHMS).format(date); 
	} 
	/**
	 * 字符转时间yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static Date getYMDHMS(String date){
		try {
			return new SimpleDateFormat(YMDHMS).parse(date);
		} catch (ParseException e) {
			return null;
		} 
	} 	
	
	/**
	 * 
	 * 函数说明：返回yyyy-MM-dd
	 * @param Date date
	 * @return 
	 * @throws 
	 * @author zmax
	 */
	public static String getYYYYMMDDt(Date date){
		return new SimpleDateFormat("yyyyMMdd").format(date); 
	} 	
	/**
	 * 
	 * 函数说明：获取本月第一天
	 * @param 
	 * @return 
	 * @throws 
	 * @author zmax
	 */
	public static String thisMonthFirstDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

	}
	/**
	 * 
	 * 函数说明：获取本月第一天Date版
	 * @param 
	 * @return 
	 * @throws 
	 * @author zmax
	 */
	public static Date thisMonthFirstDayDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();

	}
	/**
	 * 
	 * 函数说明：以10毫秒为单位，返回当前时间的一个整数,范围本月
	 * @param 
	 * @return 
	 * @throws 
	 * @author zmax
	 */
	public static int MMddhhmmss(){
		//-2147483648->2147483647
		//最大：31 23:59:59=276479999
		Calendar calendar = Calendar.getInstance();
		int ret=0;

		//ret+=(calendar.get(Calendar.MONTH)+1)*100000000;
		ret+=calendar.get(Calendar.DAY_OF_MONTH)*60*60*24;
		ret+=calendar.get(Calendar.HOUR_OF_DAY)*60*60;
		ret+=calendar.get(Calendar.MINUTE)*60;
		ret+=calendar.get(Calendar.SECOND);
		ret=ret*100;
		ret+=calendar.get(Calendar.MILLISECOND)/10;
		System.out.println("ret=" + ret);
		return ret;
	}
	/**
	 * 本月的中文
	 * @return
	 */
	public static String thisMonthString(){
		return new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
	}
	/**
	 * 获取一个MM+1ddhhmmss的时间文本，月份是从0开始的
	 * @return
	 */
	public static String getTimeStamp(){
		Calendar now = Calendar.getInstance();
		String mm = totwo(String.valueOf(now.get(Calendar.MONTH) + 1));
		String dd = totwo(String.valueOf(now.get(Calendar.DAY_OF_MONTH)));
		String hh = totwo(String.valueOf(now.get(Calendar.HOUR_OF_DAY)));
		String ff = totwo(String.valueOf(now.get(Calendar.MINUTE)));
		String ss = totwo(String.valueOf(now.get(Calendar.SECOND)));
		return mm + dd + hh + ff + ss;
	}

	/**
	 * 返回两个时间差，毫秒
	 * @param dtbig 大的时间
	 * @param dtsmall 小的时间
	 * @return
	 */
	public static long diffDateWithMS(Date dtbig, Date dtsmall)
	{
		Calendar cBig = Calendar.getInstance();
		Calendar cSmall = Calendar.getInstance();
		cBig.setTime(dtbig);
		cSmall.setTime(dtsmall);
		return cBig.getTimeInMillis()- cSmall.getTimeInMillis();
	}

	/**
	 * 返回一个时间加上一个毫秒数，产生的时间
	 * @param date
	 * @param ms
	 * @return
	 */
	public static Date dateAddMs(Date date,int ms){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MILLISECOND, ms);
		return c.getTime();
	}

	/**
	 * 返回一个时间加上一个天数，产生的时间
	 * @param date
	 * @param ms
	 * @return
	 */
	public static Date dateAddDay(Date date,int day){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR,day);
		return c.getTime();
	}



	/** 当一个字符时转换成两个字符 **/
	private static String totwo(String s){
		if (s.length() < 2) {
			s = "0" + s;
			return s;
		}
		return s;
	}
	/**
	 * 计算年龄
	 * @param birthDay 
	 * @return
	 */
	public static int calcAge(Date birthDay)  {
		if(birthDay==null)
			return 1;
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            return 1;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;//注意此处，如果不加1的话计算结果是错误的
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    //do nothing
                }
            } else {
                //monthNow>monthBirth
                age--;
            }
        } else {
            //monthNow<monthBirth
            //donothing
        }

        return age;
    }


}