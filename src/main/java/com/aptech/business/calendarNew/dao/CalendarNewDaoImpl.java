package com.aptech.business.calendarNew.dao;

import org.springframework.stereotype.Repository;

import com.aptech.business.calendarNew.domain.CalendarNewEntity;
import com.aptech.framework.orm.AncestorDao;

/**
 * 
 * 首页日历新表应用数据类
 *
 * @author 
 * @created 2018-08-09 09:38:02
 * @lastModified 
 * @history
 *
 */
@Repository("calendarNewDao")
public class CalendarNewDaoImpl extends AncestorDao<CalendarNewEntity> implements CalendarNewDao{
	
	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "com.aptech.business.calendarNew";
	}
}
