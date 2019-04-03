package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TaskProcessStatusEnum implements BaseCodeEnum {
	REJECT(0, "0", "驳回到下发人"),//reject 
	PENDING(1, "1", "待提交"),//pending 
	RECEIVE(2, "2", "待部门接收人接收"),//receive 
	UNEXECUTE(3, "3", "待执行"),//unexecute 
	EXECUTED(4, "4", "已执行待下达人"),
	CANCEL(5,"5","已作废"),
	REJECTRECEIVE(6, "6", "驳回到部门接收人"),
	END(7,"7","任务完成");//executed 
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	TaskProcessStatusEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

}
