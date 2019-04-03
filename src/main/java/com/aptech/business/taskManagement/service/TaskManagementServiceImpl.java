package com.aptech.business.taskManagement.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.component.dictionary.ProcessMarkEnum;
import com.aptech.business.component.dictionary.TaskProcessStatusEnum;
import com.aptech.business.messageCenter.domain.MessageCenterEntity;
import com.aptech.business.messageCenter.service.MessageCenterService;
import com.aptech.business.taskManagement.dao.TaskManagementDao;
import com.aptech.business.taskManagement.domain.TaskManagementEntity;
import com.aptech.business.taskManagement.exception.TaskManagementException;
import com.aptech.business.taskManagement.exception.TaskManagementExceptionType;
import com.aptech.common.act.service.ActTaskService;
import com.aptech.common.fourcode.service.FourCodeService;
import com.aptech.common.system.unit.domain.SysUnitEntity;
import com.aptech.common.system.unit.service.SysUnitService;
import com.aptech.common.system.user.domain.SysUserEntity;
import com.aptech.common.system.user.service.SysUserService;
import com.aptech.common.workflow.modelEditor.domain.CandidateMarkEnum;
import com.aptech.common.workflow.todoTask.service.TodoTaskService;
import com.aptech.framework.context.RequestContext;
import com.aptech.framework.orm.AbstractBaseEntityOperation;
import com.aptech.framework.orm.DataStatusEnum;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.orm.OrmUtil;
import com.aptech.framework.orm.Page;
import com.aptech.framework.orm.Sort;
import com.aptech.framework.orm.search.Condition;
import com.aptech.framework.orm.search.FieldTypeEnum;
import com.aptech.framework.orm.search.MatchTypeEnum;
import com.aptech.framework.util.DateFormatUtil;
import com.aptech.framework.web.base.ResultObj;

/**
 * 
 * 任务管理应用管理服务实现类
 *
 * @author 
 * @created 2017-09-29 13:24:39
 * @lastModified 
 * @history
 *
 */
@Service("taskManagementService")
@Transactional
public class TaskManagementServiceImpl extends AbstractBaseEntityOperation<TaskManagementEntity> implements TaskManagementService {
	
	@Autowired
	private TaskManagementDao taskManagementDao;
	@Autowired
	private SysUnitService sysUnitService;
	@Autowired
	private FourCodeService fourCodeService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MessageCenterService messageCenterService;
	@Autowired
	private TodoTaskService todoTaskService;
	@Override
	public IBaseEntityOperation<TaskManagementEntity> getDao() {
		return taskManagementDao;
	}
	
	/**
	 * @Description: 查询
	 * @author changl
	 * @Date 2016年11月7日 下午6:00:01
	 * @throws Exception
	 */
	@Override
	public <O> List<O> findByCondition(Map<String, Object> params, Page<O> page) {
		SysUserEntity userEntity= RequestContext.get().getUser();

		if (page == null) {
			page = new Page<O>();
			page.setPageSize(Integer.MAX_VALUE);
		}
		page.setOrders(OrmUtil.changeMapToOrders(params));
		page.addOrder(Sort.desc("issuerTime"));
		List<Condition> conditions = OrmUtil.changeMapToCondition(params);
		conditions.add(new Condition("C_STATUS", FieldTypeEnum.STRING, MatchTypeEnum.EQ, String.valueOf(DataStatusEnum.NORMAL.ordinal())));
		conditions.add(new Condition("C_ISSUER_ID ="+userEntity.getId()+" or C_RECEIVING_UNIT_USER_ID="+userEntity.getId()+" or C_RECEIVE_USER_ID="+userEntity.getId()));		

		List<TaskManagementEntity> resultList = (List<TaskManagementEntity>)super.findByCondition(conditions, page);
		return (List<O>)resultList;
	}
	/**
	 * 添加实体
	 * 
	 * @param t
	 */
	@Override
	public void addEntity(TaskManagementEntity t) {
		t.setStatus(String.valueOf(DataStatusEnum.NORMAL.ordinal()));
		t.setProcessStatus(TaskProcessStatusEnum.PENDING.getCode());
		SysUnitEntity sysunit=sysUnitService.findById(Long.valueOf(t.getReceivingUnitId()));
        t.setReceivingUnitName(sysunit.getName());
		taskManagementDao.addEntity(t);
		//提交
		String saveOrsubmit=t.getSaveOrsubmit();
		String selectUser=t.getSelectUser();
		if(saveOrsubmit!=null&&!saveOrsubmit.equals("")){
			//准备启动流程
  		    String key=ProcessMarkEnum.TASK_MANAGEMENT_PROCESS_KEY.getName();	
			Map<String, Object> vars=new HashMap<String,Object>();
	  		vars.put(CandidateMarkEnum.NEXT_HANDLERS.getName(), selectUser);//选定的审批人
		    actTaskService.startProcess(key, t.getId().toString(), vars);
			
		    TaskManagementEntity taskManagementEntity=this.findById(t.getId());
		    SysUserEntity userE = sysUserService.findByLoginName(selectUser);
		    if(userE!=null){
			    taskManagementEntity.setReceivingUnitUserId(userE.getId());
			    taskManagementEntity.setReceivingUnitUserName(userE.getName());  	
		    }

		    taskManagementEntity.setId(t.getId());
		    taskManagementEntity.setProcessStatus(TaskProcessStatusEnum.RECEIVE.getCode());
			SysUnitEntity sysunit2=sysUnitService.findById(Long.valueOf(t.getReceivingUnitId()));
			Map<String, Object> codeparams=new HashMap<String, Object> ();
			codeparams.put("farmCode", sysunit2.getCode());
			codeparams.put("year", new Date());
			String code=fourCodeService.getBusinessCode("任务编码", codeparams);
			taskManagementEntity.setCode(code);
			super.updateEntity(taskManagementEntity);
			MessageCenterEntity messageEntity = new MessageCenterEntity();
			DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm:ss");
			messageEntity.setTitle(taskManagementEntity.getIssuerName()+"于"+df.format(taskManagementEntity.getIssuerTime())+"下达新任务！");
			messageEntity.setContext(taskManagementEntity.getIssuerName()+"于"+df.format(taskManagementEntity.getIssuerTime())+"下达任务,任务内容为："+taskManagementEntity.getContent());
			messageEntity.setType("private");
			messageEntity.setCategory("1");
			SysUserEntity userList = sysUserService.findByLoginName(selectUser);
			messageEntity.setReceivePerson(userList.getId().toString());
			//发送时间
			try {
				Date sendMessageTime = df.parse(df.format(new Date()));
				messageEntity.setSendTime(sendMessageTime);
			} catch (ParseException e) {
			} 
			//发送人
			SysUserEntity sysUserEntity = RequestContext.get().getUser();
			messageEntity.setSendPerson(sysUserEntity.getId().toString());
			messageCenterService.addMessage(messageEntity);
		}
	}
	
	/**
	 * 修改实体
	 * 
	 * @param t
	 */
		@Override
		public void updateEntity(TaskManagementEntity t) {
		TaskManagementEntity tEntity = this.findById(t.getId());
		if(validate(tEntity)){
			tEntity.setContent(t.getContent());
			tEntity.setReceivingUnitId(t.getReceivingUnitId());
			tEntity.setEnclosure(t.getEnclosure());
			SysUnitEntity sysunit=sysUnitService.findById(Long.valueOf(t.getReceivingUnitId()));

			tEntity.setReceivingUnitName(sysunit.getName());
		    tEntity.setType(t.getType());
			taskManagementDao.updateEntity(tEntity);
		}
	
	}
	
	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	@Override
	public void deleteEntity(Serializable id) {
		TaskManagementEntity t = this.findById(id);
		if(validate(t)){
			t.setStatus(String.valueOf(DataStatusEnum.DELETE.ordinal()));
			super.updateEntity(t);
		}
	}
	//基本验证
	public boolean validate(TaskManagementEntity t) {
		if (t == null) {
			throw new TaskManagementException(TaskManagementExceptionType.DEFECT_CODE_NULL);
		}
		if (!(t.getProcessStatus().equals(TaskProcessStatusEnum.PENDING.getCode()) || t
				.getProcessStatus().equals(TaskProcessStatusEnum.REJECT.getCode()))) {
			throw new TaskManagementException(TaskManagementExceptionType.DEFECT_CODE_STATUS);
		}
		return true;
	}
	/**
	 * @Description:   流程基本验证
	 * @author         wangcc 
	 * @Date           2017年8月18日 下午3:41:01 
	 * @throws         Exception
	 */
	public boolean validateStatus(TaskManagementEntity t) {
		TaskManagementEntity entity = this.findById(t.getId());
		if (entity == null) {
			throw new TaskManagementException(
					TaskManagementExceptionType.DEFECT_CODE_NULL);
		}
		if (!t.getProcessStatus().equals(entity.getProcessStatus())) {
			throw new TaskManagementException(
					TaskManagementExceptionType.DEFECT_CODE_REPEAT);
		}
		return true;
	}
	@Override
	public ResultObj deleteBulk(List<Integer> ids) {
		for (int i=0;i<ids.size();i++) {
			TaskManagementEntity  entity=this.findById(Long.valueOf(ids.get(i)));
			if(entity!=null){
				validateStatus(entity);
				entity.setStatus(String.valueOf(DataStatusEnum.DELETE.ordinal()));
				super.updateEntity(entity);
			}
		}
		return new ResultObj();
	}

	@Override
	public ResultObj tijiaoValidate(Long id) {
		TaskManagementEntity entity = this.findById(id);
		if(!entity.getProcessStatus().equals(TaskProcessStatusEnum.PENDING.getCode())){
			throw new TaskManagementException(
					TaskManagementExceptionType.DEFECT_CODE_REPEAT);
		}
		return new ResultObj();
	}

	@Override
	public void submit(String id, String selectUser) {
		//准备启动流程
	    String key=ProcessMarkEnum.TASK_MANAGEMENT_PROCESS_KEY.getName();	
		Map<String, Object> vars=new HashMap<String,Object>();
  		vars.put(CandidateMarkEnum.NEXT_HANDLERS.getName(), selectUser);//选定的审批人
	    actTaskService.startProcess(key, id, vars);
		
	    TaskManagementEntity taskManagementEntity=this.findById(Long.valueOf(id));
	    taskManagementEntity.setId(Long.valueOf(id));
	    taskManagementEntity.setProcessStatus(TaskProcessStatusEnum.RECEIVE.getCode());
	    SysUserEntity userE = sysUserService.findByLoginName(selectUser);
	    if(userE!=null){
		    taskManagementEntity.setReceivingUnitUserId(userE.getId());
		    taskManagementEntity.setReceivingUnitUserName(userE.getName());  	
	    }
		SysUnitEntity sysunit2=sysUnitService.findById(Long.valueOf(taskManagementEntity.getReceivingUnitId()));
		Map<String, Object> codeparams=new HashMap<String, Object> ();
		codeparams.put("farmCode", sysunit2.getCode());
		codeparams.put("year", new Date());
		String code=fourCodeService.getBusinessCode("任务编码", codeparams);
		taskManagementEntity.setCode(code);
		taskManagementEntity.setIssuerTime(new Date());//更新下达时间
		super.updateEntity(taskManagementEntity);
		DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm:ss");

		MessageCenterEntity messageEntity = new MessageCenterEntity();
		messageEntity.setTitle(taskManagementEntity.getIssuerName()+"于"+df.format(taskManagementEntity.getIssuerTime())+"下达新任务！");
		messageEntity.setContext(taskManagementEntity.getIssuerName()+"于"+df.format(taskManagementEntity.getIssuerTime())+"下达任务,任务内容为："+taskManagementEntity.getContent());
		messageEntity.setType("private");
		messageEntity.setCategory("1");
		SysUserEntity userList = sysUserService.findByLoginName(selectUser);
		messageEntity.setReceivePerson(userList.getId().toString());
		//发送时间
		try {
			Date sendMessageTime = df.parse(df.format(new Date()));
			messageEntity.setSendTime(sendMessageTime);
		} catch (ParseException e) {
		} 
		//发送人
		SysUserEntity sysUserEntity = RequestContext.get().getUser();
		messageEntity.setSendPerson(sysUserEntity.getId().toString());
		messageCenterService.addMessage(messageEntity);
	}

	@Override
	public List<TaskManagementEntity> findByConditionWork( Map<String, Object> params,Page<TaskManagementEntity> page) {
		SysUserEntity userEntity = RequestContext.get().getUser();
		if (page == null) {
			page = new Page<TaskManagementEntity>();
			page.setPageSize(Integer.MAX_VALUE);
		}
		page.setOrders(OrmUtil.changeMapToOrders(params));
		page.addOrder(Sort.desc("issuerTime"));
		List<Condition> conditions = OrmUtil.changeMapToCondition(params);
		conditions.add(new Condition("C_STATUS", FieldTypeEnum.STRING, MatchTypeEnum.EQ, String.valueOf(DataStatusEnum.NORMAL.ordinal())));
		conditions.add(new Condition("C_PROCESS_STATUS", FieldTypeEnum.STRING, MatchTypeEnum.NE, TaskProcessStatusEnum.PENDING.getCode()));
		conditions.add(new Condition("C_RECEIVING_UNIT_ID", FieldTypeEnum.LONG, MatchTypeEnum.EQ,userEntity.getUnitId()));

		return findByCondition(conditions, page);
	}

	@Override
	public ResultObj approve(TaskManagementEntity t, Map<String, Object> params) {
		if(validateStatus(t)){
			// 修改状态
			SysUserEntity userEntity = RequestContext.get().getUser();
			TaskManagementEntity tEntity = this.findById(t.getId());
			tEntity.setProcessStatus(params.get("status").toString());
            DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm:ss");
            
			if(TaskProcessStatusEnum.REJECTRECEIVE.getCode().equals(params.get("status").toString())  ){
				SysUserEntity sysu = sysUserService.findById(tEntity.getReceivingUnitUserId());
				params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),sysu.getLoginName());
				tEntity.setReceiveTime(null);
				tEntity.setCompletion(null);
				tEntity.setReceiveUserId(null);
				tEntity.setReceiveUserName(null);
				tEntity.setPlanFinishTime(null);
				tEntity.setActualFinishTime(null);
                MessageCenterEntity messageEntity = new MessageCenterEntity();
        		messageEntity.setTitle(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"拒绝接收任务！");
        		messageEntity.setContext(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"拒绝接收任务,拒绝原因为："+t.getContent());
        		messageEntity.setType("private");
        		messageEntity.setCategory("1");
        		messageEntity.setReceivePerson(tEntity.getReceivingUnitUserId().toString());
        		//发送时间
        		try {
        			Date sendMessageTime = df.parse(df.format(new Date()));
        			messageEntity.setSendTime(sendMessageTime);
        		} catch (ParseException e) {
        		} 
        		//发送人
        		messageEntity.setSendPerson(userEntity.getId().toString());
        		messageCenterService.addMessage(messageEntity);
			}
			
			if(TaskProcessStatusEnum.END.getCode().equals(params.get("status").toString())  ){
				//发送邮件给部门接收人
                MessageCenterEntity messageEntity = new MessageCenterEntity();
        		messageEntity.setTitle(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"终结完成！");
        		messageEntity.setContext(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"任务终结,任务内容为："+tEntity.getContent());
        		messageEntity.setType("private");
        		messageEntity.setCategory("1");
        		messageEntity.setReceivePerson(tEntity.getReceivingUnitUserId().toString());
        		//发送时间
        		try {
        			Date sendMessageTime = df.parse(df.format(new Date()));
        			messageEntity.setSendTime(sendMessageTime);
        		} catch (ParseException e) {
        		} 
        		//发送人
        		messageEntity.setSendPerson(userEntity.getId().toString());
        		messageCenterService.addMessage(messageEntity);
				//发送邮件给接收人
            	MessageCenterEntity messageEntity2 = new MessageCenterEntity();
            	messageEntity2.setTitle(userEntity.getName()+"于"+df.format(tEntity.getReceiveTime())+"终结任务！");
            	messageEntity2.setContext(userEntity.getName()+"于"+df.format(tEntity.getReceiveTime())+"任务终结,执行情况为："+tEntity.getCompletion());
            	messageEntity2.setType("private");
            	messageEntity2.setCategory("1");
            	messageEntity2.setReceivePerson(tEntity.getReceiveUserId().toString());
        		//发送时间
        		try {
        			Date sendMessageTime = df.parse(df.format(new Date()));
        			messageEntity2.setSendTime(sendMessageTime);
        		} catch (ParseException e) {
        		}
        		messageEntity2.setSendPerson(userEntity.getId().toString());
        		messageCenterService.addMessage(messageEntity2);
			}
			if(TaskProcessStatusEnum.UNEXECUTE.getCode().equals(params.get("status").toString())  ){
				tEntity.setSuggestions(t.getSuggestions());
                tEntity.setReceivingUnitTime(new Date());
                tEntity.setReceivingUnitUserId(userEntity.getId());
                tEntity.setReceivingUnitUserName(userEntity.getName());
                SysUserEntity useE = sysUserService.findByLoginName(t.getUserList());
                if(useE!=null){
                    tEntity.setReceiveUserId(useE.getId());
                    tEntity.setReceiveUserName(useE.getName());
                    //发送邮件给接收人
                    MessageCenterEntity messageEntity = new MessageCenterEntity();
            		messageEntity.setTitle(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"接收任务,并指派给您！");
            		messageEntity.setContext(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"接收任务并指派给您,任务内容为："+tEntity.getContent());
            		messageEntity.setType("private");
            		messageEntity.setCategory("1");
            		messageEntity.setReceivePerson(tEntity.getReceiveUserId().toString());
            		//发送时间
            		try {
            			Date sendMessageTime = df.parse(df.format(new Date()));
            			messageEntity.setSendTime(sendMessageTime);
            		} catch (ParseException e) {
            		} 
            		//发送人
            		messageEntity.setSendPerson(userEntity.getId().toString());
            		messageCenterService.addMessage(messageEntity);
                }else{
                	 //发送邮件给接收人
                    MessageCenterEntity messageEntity = new MessageCenterEntity();
            		messageEntity.setTitle(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"验收任务未成功！");
            		messageEntity.setContext(userEntity.getName()+"于"+df.format(tEntity.getReceivingUnitTime())+"验收任务未成功,任务内容为："+tEntity.getContent());
            		messageEntity.setType("private");
            		messageEntity.setCategory("1");
            		messageEntity.setReceivePerson(tEntity.getReceiveUserId().toString());
            		//发送时间
            		try {
            			Date sendMessageTime = df.parse(df.format(new Date()));
            			messageEntity.setSendTime(sendMessageTime);
            		} catch (ParseException e) {
            		} 
            		//发送人
            		messageEntity.setSendPerson(userEntity.getId().toString());
            		messageCenterService.addMessage(messageEntity);
                }
               
			}
            if(TaskProcessStatusEnum.REJECT.getCode().equals(params.get("status").toString()) ){
				tEntity.setSuggestions(t.getSuggestions());
                tEntity.setReceivingUnitTime(null);
                tEntity.setReceivingUnitUserId(null);
                tEntity.setReceivingUnitUserName(null);
                //发送邮件给下达人
            	MessageCenterEntity messageEntity = new MessageCenterEntity();
        		messageEntity.setTitle(userEntity.getName()+"于"+df.format(new Date())+"拒绝接收任务！");
        		messageEntity.setContext(userEntity.getName()+"于"+df.format(new Date())+"拒绝接收任务,其意见和建议为："+t.getSuggestions());
        		messageEntity.setType("private");
        		messageEntity.setCategory("1");
        		messageEntity.setReceivePerson(tEntity.getIssuerId().toString());
        		//发送时间
        		try {
        			Date sendMessageTime = df.parse(df.format(new Date()));
        			messageEntity.setSendTime(sendMessageTime);
        		} catch (ParseException e) {
        		} 
        		//发送人
        		messageEntity.setSendPerson(userEntity.getId().toString());
        		messageCenterService.addMessage(messageEntity);
            }
            	
        		
			if(TaskProcessStatusEnum.EXECUTED.getCode().equals(params.get("status").toString()) ){
				tEntity.setReceiveTime(new Date());
				tEntity.setCompletion(t.getCompletion());
				tEntity.setReceiveUserId(userEntity.getId());
				tEntity.setReceiveUserName(userEntity.getName());
				tEntity.setPlanFinishTime(t.getPlanFinishTime());
				tEntity.setActualFinishTime(t.getActualFinishTime());
        		//发送邮件给下达人
            	MessageCenterEntity messageEntity2 = new MessageCenterEntity();
            	messageEntity2.setTitle(userEntity.getName()+"于"+df.format(tEntity.getReceiveTime())+"完成任务！");
            	messageEntity2.setContext(userEntity.getName()+"于"+df.format(tEntity.getReceiveTime())+"完成任务,执行情况为："+tEntity.getCompletion());
        		messageEntity2.setType("private");
            	messageEntity2.setCategory("1");
        		messageEntity2.setReceivePerson(tEntity.getIssuerId().toString());
        		//发送时间
        		try {
        			Date sendMessageTime = df.parse(df.format(new Date()));
        			messageEntity2.setSendTime(sendMessageTime);
        		} catch (ParseException e) {
        		} 
        		//发送人
        		messageEntity2.setSendPerson(userEntity.getId().toString());
        		messageCenterService.addMessage(messageEntity2);
			}
			if(TaskProcessStatusEnum.RECEIVE.getCode().equals(params.get("status").toString())){
				SysUserEntity useE = sysUserService.findByLoginName(t.getUserList());
                if(useE!=null){
                    tEntity.setReceiveUserId(useE.getId());
                    tEntity.setReceiveUserName(useE.getName());
                }
				MessageCenterEntity messageEntity = new MessageCenterEntity();
				messageEntity.setTitle(tEntity.getIssuerName()+"于"+df.format(tEntity.getIssuerTime())+"下达新任务！");
				messageEntity.setContext(tEntity.getIssuerName()+"于"+df.format(tEntity.getIssuerTime())+"下达任务,任务内容为："+tEntity.getContent());
				messageEntity.setType("private");
				messageEntity.setCategory("1");
				SysUserEntity userList = sysUserService.findByLoginName(t.getUserList());
				messageEntity.setReceivePerson(userList.getId().toString());
				//发送时间
				try {
					Date sendMessageTime = df.parse(df.format(new Date()));
					messageEntity.setSendTime(sendMessageTime);
				} catch (ParseException e) {
				} 
				//发送人
				SysUserEntity sysUserEntity = RequestContext.get().getUser();
				messageEntity.setSendPerson(sysUserEntity.getId().toString());
				messageCenterService.addMessage(messageEntity);
			}
			super.updateEntity(tEntity);
			//设置当前流程,当前任务节点的下一个节点的办理人
			actTaskService.setVarialble(t.getTaskId(),params);
			// 调用流程接口
			actTaskService.complete(t.getTaskId(), t.getProcInstId(),
					params);
		}
		return  new ResultObj();
	}
}