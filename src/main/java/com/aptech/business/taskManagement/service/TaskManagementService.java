package com.aptech.business.taskManagement.service;

import java.util.List;
import java.util.Map;

import com.aptech.business.taskManagement.domain.TaskManagementEntity;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.orm.Page;
import com.aptech.framework.web.base.ResultObj;

/**
 * 
 * 任务管理应用管理服务接口
 *
 * @author 
 * @created 2017-09-29 13:24:39
 * @lastModified 
 * @history
 *
 */
public interface TaskManagementService  extends IBaseEntityOperation<TaskManagementEntity> {
	/** 批量删除
	 * @author         zhangzq 
	 * @Date           2017年6月20日 下午3:34:59 
	 * @throws         Exception
	 */
	ResultObj deleteBulk(List<Integer> ids);
	/**
	 * @Description:   提交时候的验证
	 * @author         zhangzq 
	 * @Date           2017年6月29日 下午1:17:42 
	 * @throws         Exception
	 */
	ResultObj tijiaoValidate(Long id);
	/**
	 * 提交
	 * @Description:   
	 * @author         zhangzq 
	 * @Date           2017年6月12日 下午6:10:01 
	 * @throws         Exception
	 */
	void submit(String id, String selectUser);
	
	List<TaskManagementEntity> findByConditionWork( Map<String, Object> params,Page<TaskManagementEntity> page);
	
	/**
	 * 审批：流程
	 * 
	 * @param  id
	 */
	ResultObj approve(TaskManagementEntity t,Map<String, Object> params);
}