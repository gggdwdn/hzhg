package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum MaintainStatusEnum implements BaseCodeEnum {
	
	BREAK(0,"0","破损"),
	REPAIR(1,"1","已修复");
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	MaintainStatusEnum(Integer id, String code, String name){
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
