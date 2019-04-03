package com.aptech.business.taskManagement.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.Alias;

import com.aptech.common.system.dictionary.util.DictionaryUtil;
import com.aptech.common.system.dictionary.web.SysDictionaryVO;
import com.aptech.framework.json.JsonDateTime1Deserializer;
import com.aptech.framework.json.JsonDateTime1Serializer;
import com.aptech.framework.json.JsonDateTimeDeserializer;
import com.aptech.framework.json.JsonDateTimeSerializer;
import com.aptech.framework.orm.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * 任务管理实体类
 *
 * @author 
 * @created 2017-09-29 13:24:39
 * @lastModified 
 * @history
 *
 */
@Alias("TaskManagementEntity")
public class TaskManagementEntity extends BaseEntity{
		/**
	 * 
	 */
	private static final long serialVersionUID = 4218443876173333652L;
		/**
		 * 主键
		 */
    	private Long id;
		/**
		 * 编码
		 */
    	private String code;
		/**
		 * 下达人id
		 */
    	private Long issuerId;
		/**
		 * 下达人姓名
		 */
    	private String issuerName;
		/**
		 * 接收部门id
		 */
    	private Long receivingUnitId;
		/**
		 * 接收部门
		 */
    	private String receivingUnitName;
		/**
		 * 部门名称id
		 */
    	private Long unitId;
		/**
		 * 任务类型
		 */
    	private String type;
		/**
		 * 是否删除
		 */
    	private String status;
		/**
		 * 任务内容
		 */
    	private String content;
		/**
		 * 附件
		 */
    	private String enclosure;
		/**
		 * 接收人id
		 */
    	private Long receiveUserId;
		/**
		 * 接收人
		 */
    	private String receiveUserName;
		/**
		 * 计划完成时间
		 */
    	private Date planFinishTime;
		/**
		 * 实际完成时间
		 */
    	private Date actualFinishTime;
		/**
		 * 流程状态
		 */
    	private String processStatus;
    	
    	/**
    	 * 状态名称
    	 */
    	private String processStatusName;
		/**
		 * 部门接收人接收时间
		 */
    	private Date receivingUnitTime;
		/**
		 * 接收人接收时间
		 */
    	private Date receiveTime;
		/**
		 * 完成情况
		 */
    	private String completion;
		/**
		 * 部门接收意见和建议
		 */
    	private String suggestions;
		/**
		 * 备注
		 */
    	private String remark;

    	
    	private Date issuerTime;
    	
    	private String saveOrsubmit;
    	
    	private String selectUser;
    	
    	private String typeName;
    	/**
    	 * 部门接收人id
    	 */
    	private Long 	receivingUnitUserId;
    	/**
    	 * 部门接收人姓名
    	 */
    	private String 	receivingUnitUserName;
    	
    	private String userList;
    	
    	private String taskId;
    	private String procInstId;
    	
    	private String approveIdea;
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id = id;
		}
		public String getCode(){
			return code;
		}
		public void setCode(String code){
			this.code = code;
		}
		public Long getIssuerId(){
			return issuerId;
		}
		public void setIssuerId(Long issuerId){
			this.issuerId = issuerId;
		}
		public String getIssuerName(){
			return issuerName;
		}
		public void setIssuerName(String issuerName){
			this.issuerName = issuerName;
		}
		public Long getReceivingUnitId(){
			return receivingUnitId;
		}
		public void setReceivingUnitId(Long receivingUnitId){
			this.receivingUnitId = receivingUnitId;
		}
		public String getReceivingUnitName(){
			return receivingUnitName;
		}
		public void setReceivingUnitName(String receivingUnitName){
			this.receivingUnitName = receivingUnitName;
		}
		public Long getUnitId(){
			return unitId;
		}
		public void setUnitId(Long unitId){
			this.unitId = unitId;
		}
		public String getType(){
			return type;
		}
		public void setType(String type){
			this.type = type;
		}
		public String getStatus(){
			return status;
		}
		public void setStatus(String status){
			this.status = status;
		}
		public String getContent(){
			return content;
		}
		public void setContent(String content){
			this.content = content;
		}
		public String getEnclosure(){
			return enclosure;
		}
		public void setEnclosure(String enclosure){
			this.enclosure = enclosure;
		}
		public Long getReceiveUserId(){
			return receiveUserId;
		}
		public void setReceiveUserId(Long receiveUserId){
			this.receiveUserId = receiveUserId;
		}
		public String getReceiveUserName(){
			return receiveUserName;
		}
		public void setReceiveUserName(String receiveUserName){
			this.receiveUserName = receiveUserName;
		}
	    @JsonSerialize(using = JsonDateTime1Serializer.class)
		public Date getPlanFinishTime(){
			return planFinishTime;
		}
	    @JsonDeserialize(using = JsonDateTime1Deserializer.class)
		public void setPlanFinishTime(Date planFinishTime){
			this.planFinishTime = planFinishTime;
		}
	    @JsonSerialize(using = JsonDateTime1Serializer.class)
		public Date getActualFinishTime(){
			return actualFinishTime;
		}
	    @JsonDeserialize(using = JsonDateTime1Deserializer.class)
		public void setActualFinishTime(Date actualFinishTime){
			this.actualFinishTime = actualFinishTime;
		}
		public String getProcessStatus(){
			return processStatus;
		}
		public void setProcessStatus(String processStatus){
			this.processStatus = processStatus;
		}
	    @JsonSerialize(using = JsonDateTime1Serializer.class)
		public Date getReceivingUnitTime(){
			return receivingUnitTime;
		}
	    @JsonDeserialize(using = JsonDateTime1Deserializer.class)
		public void setReceivingUnitTime(Date receivingUnitTime){
			this.receivingUnitTime = receivingUnitTime;
		}
	    @JsonSerialize(using = JsonDateTime1Serializer.class)
		public Date getReceiveTime(){
			return receiveTime;
		}
	    @JsonDeserialize(using = JsonDateTime1Deserializer.class)
		public void setReceiveTime(Date receiveTime){
			this.receiveTime = receiveTime;
		}
	    
	    @JsonSerialize(using = JsonDateTime1Serializer.class)
		public Date getIssuerTime() {
			return issuerTime;
		}
	    @JsonDeserialize(using = JsonDateTime1Deserializer.class)
		public void setIssuerTime(Date issuerTime) {
			this.issuerTime = issuerTime;
		}
		public String getCompletion(){
			return completion;
		}
		public void setCompletion(String completion){
			this.completion = completion;
		}
		public String getSuggestions(){
			return suggestions;
		}
		public void setSuggestions(String suggestions){
			this.suggestions = suggestions;
		}
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark = remark;
		}
		public String getSaveOrsubmit() {
			return saveOrsubmit;
		}
		public void setSaveOrsubmit(String saveOrsubmit) {
			this.saveOrsubmit = saveOrsubmit;
		}
		public String getSelectUser() {
			return selectUser;
		}
		public void setSelectUser(String selectUser) {
			this.selectUser = selectUser;
		}
		public String getProcessStatusName() {
			if(!StringUtils.isEmpty(this.processStatus)){
				Map<String, SysDictionaryVO> instockTypeMap  =  DictionaryUtil.getDictionaries("TASK_PROCESS_STATUS");
				Map<String,String> instockTypeEnumMap = new HashMap<String, String>();
				for(String key : instockTypeMap.keySet()){
					SysDictionaryVO sysDictionaryVO = instockTypeMap.get(key);
					instockTypeEnumMap.put(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
				}
				return instockTypeEnumMap.get(this.processStatus);
			}
			return processStatusName;
		}
		public void setProcessStatusName(String processStatusName) {
			this.processStatusName = processStatusName;
		}
		public String getTypeName() {
			if(!StringUtils.isEmpty(this.type)){
				Map<String, SysDictionaryVO> instockTypeMap  =  DictionaryUtil.getDictionaries("TASK_TYPE");
				Map<String,String> instockTypeEnumMap = new HashMap<String, String>();
				for(String key : instockTypeMap.keySet()){
					SysDictionaryVO sysDictionaryVO = instockTypeMap.get(key);
					instockTypeEnumMap.put(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
				}
				return instockTypeEnumMap.get(this.type);
			}
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public Long getReceivingUnitUserId() {
			return receivingUnitUserId;
		}
		public void setReceivingUnitUserId(Long receivingUnitUserId) {
			this.receivingUnitUserId = receivingUnitUserId;
		}
		public String getReceivingUnitUserName() {
			return receivingUnitUserName;
		}
		public void setReceivingUnitUserName(String receivingUnitUserName) {
			this.receivingUnitUserName = receivingUnitUserName;
		}
		public String getPlanFinishTimeString() {
			if(planFinishTime!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				return  sdf.format(planFinishTime);
			}
			return "";
		}
		public String getActualFinishTimeString() {
			if(actualFinishTime!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				return  sdf.format(actualFinishTime);
			}
			return "";
		}
		public String getIssuerTimeString() {
			if(issuerTime!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				return  sdf.format(issuerTime);
			}
			return "";
		}
		public String getUserList() {
			return userList;
		}
		public void setUserList(String userList) {
			this.userList = userList;
		}
		public String getTaskId() {
			return taskId;
		}
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		public String getProcInstId() {
			return procInstId;
		}
		public void setProcInstId(String procInstId) {
			this.procInstId = procInstId;
		}
		public String getApproveIdea() {
			return approveIdea;
		}
		public void setApproveIdea(String approveIdea) {
			this.approveIdea = approveIdea;
		}
		
}