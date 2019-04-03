package com.aptech.business.messageCenter.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.messageBox.domain.MessageBoxEntity;
import com.aptech.business.messageBox.service.MessageBoxService;
import com.aptech.business.messageCenter.dao.MessageCenterDao;
import com.aptech.business.messageCenter.domain.MessageCenterEntity;
import com.aptech.common.system.duties.domain.SysDutiesEntity;
import com.aptech.common.system.duties.service.SysDutiesService;
import com.aptech.common.system.dutiesdetail.domain.SysDutiesDetailEntity;
import com.aptech.common.system.dutiesdetail.service.SysDutiesDetailService;
import com.aptech.common.system.user.domain.SysUserEntity;
import com.aptech.common.system.userUnit.domain.UserUnitRelEntity;
import com.aptech.common.system.userUnit.service.UserUnitRelService;
import com.aptech.framework.context.RequestContext;
import com.aptech.framework.orm.AbstractBaseEntityOperation;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.orm.search.Condition;
import com.aptech.framework.orm.search.FieldTypeEnum;
import com.aptech.framework.orm.search.MatchTypeEnum;
import com.aptech.framework.util.DateFormatUtil;
import com.aptech.framework.util.StringUtil;
import com.aptech.framework.web.base.ResultObj;
import com.sun.codemodel.util.MS1252Encoder;

/**
 * 
 * 消息中心应用管理服务实现类
 *
 * @author 
 * @created 2017-08-10 17:12:09
 * @lastModified 
 * @history
 *
 */
@Service("messageCenterService")
@Transactional
public class MessageCenterServiceImpl extends AbstractBaseEntityOperation<MessageCenterEntity> implements MessageCenterService {
	
	@Autowired
	private SysDutiesService sysDutiesService;
	@Autowired
	private SysDutiesDetailService sysdutiesDetailService;
	@Autowired
	private MessageBoxService messageBoxService;
	@Autowired
	private UserUnitRelService userUnitRelService;
	@Autowired
	private MessageCenterDao messageCenterDao;
	
	@Override
	public IBaseEntityOperation<MessageCenterEntity> getDao() {
		return messageCenterDao;
	}
	
	/**
	 * 发送信息接口
	 */
	public void addMessage(MessageCenterEntity messageEntity){
		if(messageEntity!=null){
			//存储信息主体
			this.addEntity(messageEntity);
			MessageBoxEntity messageBoxEntity = new MessageBoxEntity();
			String receivePersonString = messageEntity.getReceivePerson();
			messageBoxEntity.setContextId(messageEntity.getId());
			messageBoxEntity.setStatus("0");
			//人对人
			if(messageEntity.getType().equals("private")){
				//人对多人
				if(receivePersonString.contains(",")){
					String[] receivePersonArray = receivePersonString.split(",");
					for(int i=0;i<receivePersonArray.length;i++){
						messageBoxEntity.setReceivePerson(receivePersonArray[i]);
						messageBoxService.addEntity(messageBoxEntity);
					}
				}else{
					//人对个人
					messageBoxEntity.setReceivePerson(receivePersonString);
					messageBoxService.addEntity(messageBoxEntity);
				}
			}else if(messageEntity.getType().equals("public")){
				//人对职务
				String messageGroupTemp = messageEntity.getGroup();
				//整理多选下拉框返回的值
				if(messageGroupTemp.startsWith(",")){
					messageGroupTemp = messageGroupTemp.substring(1, messageGroupTemp.length());
				}
				if(messageGroupTemp.contains(",")){
					//向多个职务发送
					this.addSingleEntity(messageGroupTemp, messageBoxEntity, true);
				}else{
					//向单个职务发送
					this.addSingleEntity(messageGroupTemp, messageBoxEntity, false);
					}
				}
			}
		}
	
	public void addSingleEntity(Object obj, MessageBoxEntity messageBoxEntity,Boolean isMultiGroup){
		List<Condition> conditions = new ArrayList<Condition>();
		if(isMultiGroup){
			String[] messageGroupArray = ((String) obj).split(",");
			conditions.add(new Condition("C_DUTIES_ID", FieldTypeEnum.STRING, MatchTypeEnum.IN, messageGroupArray));
		}else{
			conditions.add(new Condition("C_DUTIES_ID", FieldTypeEnum.STRING, MatchTypeEnum.EQ, (String)obj));
		}
		List<SysDutiesDetailEntity>	sysDutiesDetailList = sysdutiesDetailService.findByCondition(conditions, null);
		if(!sysDutiesDetailList.isEmpty()){
			Set<String> userUnitRelIds = new HashSet<String>();
			for(SysDutiesDetailEntity sysDutiesDetailEntity : sysDutiesDetailList){
				userUnitRelIds.add(sysDutiesDetailEntity.getUserUnitRelId());
			}
			conditions.clear();
			conditions.add(new Condition("C_ID", FieldTypeEnum.LONG, MatchTypeEnum.IN, userUnitRelIds.toArray()));
			List<UserUnitRelEntity> userUnitRelList = userUnitRelService.findByCondition(conditions, null);
			if(!userUnitRelList.isEmpty()){
				Set<Long> userIds = new HashSet<Long>();
				for(UserUnitRelEntity userUnitRelEntity : userUnitRelList){
					userIds.add(userUnitRelEntity.getUserId());
				}
				for(Long userId : userIds){
					messageBoxEntity.setReceivePerson(userId.toString());
					messageBoxService.addEntity(messageBoxEntity);
				}
			}
		}
	}
	
	/**
	 * 
	 * 查询登录人未读信息的数量
	 * 
	 * @param @return
	 * @return int
	 * @throws 
	 * @author liweiran
	 * @created 2017年8月16日 下午7:15:02
	 * @lastModified
	 */
	public int searchMessageCount(){
		SysUserEntity sysUserEntity = RequestContext.get().getUser();
		List<Condition> list_condition = new ArrayList<Condition>();
		list_condition.add(new Condition("C_STATUS",FieldTypeEnum.STRING,MatchTypeEnum.EQ,0));
		list_condition.add(new Condition("C_RECEIVE_PERSON",FieldTypeEnum.STRING,MatchTypeEnum.EQ,sysUserEntity.getId()));
		List<MessageBoxEntity> messageBoxEntities = messageBoxService.findByCondition(list_condition, null);
		return messageBoxEntities.size();
	}

	@Override
	public ResultObj againSend(Long id) throws ParseException {
		// 
		MessageCenterEntity messageCenterEntity = this.findById(id);
		MessageCenterEntity m = new MessageCenterEntity();
		m.setTitle(messageCenterEntity.getTitle());
		m.setContext(messageCenterEntity.getContext());
		DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm:ss");
		Date sendMessageTime = df.parse(df.format(new Date())); 
		m.setSendTime(sendMessageTime);
		m.setSendPerson(messageCenterEntity.getSendPerson());
		m.setType(messageCenterEntity.getType());
		if(StringUtil.isNotEmpty(messageCenterEntity.getCategory())){
			m.setCategory(messageCenterEntity.getCategory());
			m.setGroup(messageCenterEntity.getGroup());
		}
		m.setDeleteFlag("1");
		this.addEntity(m);
		Long messageCenterId = m.getId();
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("C_CONTEXT_ID", FieldTypeEnum.LONG, MatchTypeEnum.EQ, id));
		List<MessageBoxEntity> messageBoxList = messageBoxService.findByCondition(conditions, null);
		if(!messageBoxList.isEmpty()){
			for(MessageBoxEntity messageBox : messageBoxList){
				MessageBoxEntity messageBoxEntity = new MessageBoxEntity();
				messageBoxEntity.setReceivePerson(messageBox.getReceivePerson());
				messageBoxEntity.setContextId(messageCenterId);
				messageBoxEntity.setStatus("0");
				messageBoxService.addEntity(messageBoxEntity);
			}
		}
		
		return new ResultObj();
		
	}
	
	
}