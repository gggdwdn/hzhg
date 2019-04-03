package com.aptech.tool.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具
 * @author dell
 *
 */
public class DateUtil {
	
	private static Calendar c=Calendar.getInstance();
	
	/**
	 * 年月日，当月天数
	 * @return
	 */
	public static Map<String,Integer> getNowDate(){
		Map<String, Integer> nowDate = new HashMap<String, Integer>();
		nowDate.put("year",c.get(Calendar.YEAR));
		nowDate.put("month",c.get(Calendar.MONTH) + 1);
		nowDate.put("day", c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DATE, 1);
        c.roll(Calendar.DATE, -1);
        int maxDate = c.get(Calendar.DATE);
        nowDate.put("days",maxDate);
		return nowDate;
	}
	
	/**
	 * 昨天日期 
	 * @return
	 */
	public static String getYesterday(){
		Calendar cal=Calendar.getInstance();
		//System.out.println(Calendar.DATE);//5
		cal.add(Calendar.DATE,-1);
		Date time=cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}
}
