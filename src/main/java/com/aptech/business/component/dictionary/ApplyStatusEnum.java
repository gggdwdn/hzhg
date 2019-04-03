package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum ApplyStatusEnum implements BaseCodeEnum {
	
	UNCOMMITTED(0,"0","未提交"),
	CHECKING(1,"1","审核中"),
	REJECT(2,"2","驳回"),
	PASS(3,"3","通过");
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	ApplyStatusEnum(Integer id, String code, String name){
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
