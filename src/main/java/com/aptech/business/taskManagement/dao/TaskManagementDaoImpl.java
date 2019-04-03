package com.aptech.business.taskManagement.dao;

import org.springframework.stereotype.Repository;

import com.aptech.business.taskManagement.domain.TaskManagementEntity;
import com.aptech.framework.orm.AncestorDao;

/**
 * 
 * 任务管理应用数据类
 *
 * @author 
 * @created 2017-09-29 13:24:39
 * @lastModified 
 * @history
 *
 */
@Repository("taskManagementDao")
public class TaskManagementDaoImpl extends AncestorDao<TaskManagementEntity> implements TaskManagementDao{
	
	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "com.aptech.business.taskManagement";
	}
}
