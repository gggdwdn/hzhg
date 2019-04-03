package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TypicalTicketTypeEnum implements BaseCodeEnum {
	
	WORKTICKETONE(1, "1", "标准电气第一种工作票"),
	WORKTICKETTWO(2, "2", "标准电气第二种工作票"),
	WORKTICKETFIREONE(5, "5", "标准一级动火工作票"),
	WORKTICKETFIRETWO(6, "6", "标准二级动火工作票"),
	WORKTICKETEARTH(7, "7", "标准动土工作票"),
	WORKTICKETWINDONE(8, "8", "标准风力机械工作票"),
	WORKTICKETWINDTWO(9, "9", "标准风力自控工作票"),
	REPAIRTICKET(10, "10", "标准紧急抢修单"),
	OPERATIONTICKET(3, "3", "标准操作票"),
	INTERVENTIONTICKET(4, "4", "标准介入工作票");
	private Integer id;
	
	private String code;
	
	private String name;
	
	TypicalTicketTypeEnum(Integer id, String code, String name){
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
