package com.aptech.business.calendarNew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.calendarNew.dao.CalendarNewDao;
import com.aptech.business.calendarNew.domain.CalendarNewEntity;
import com.aptech.framework.orm.AbstractBaseEntityOperation;
import com.aptech.framework.orm.IBaseEntityOperation;

/**
 * 
 * 首页日历新表应用管理服务实现类
 *
 * @author 
 * @created 2018-08-09 09:38:02
 * @lastModified 
 * @history
 *
 */
@Service("calendarNewService")
@Transactional
public class CalendarNewServiceImpl extends AbstractBaseEntityOperation<CalendarNewEntity> implements CalendarNewService {
	
	@Autowired
	private CalendarNewDao calendarNewDao;
	
	@Override
	public IBaseEntityOperation<CalendarNewEntity> getDao() {
		return calendarNewDao;
	}
}