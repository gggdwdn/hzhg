package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum MaintainApproveStatusEnum implements BaseCodeEnum {
	
	UNCOMMITTED(0,"0","未提交"),
	REJECT(1,"1","驳回"),
	PASS(2,"2","通过");
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	MaintainApproveStatusEnum(Integer id, String code, String name){
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
