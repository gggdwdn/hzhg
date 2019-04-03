package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum DeleteFlagEnum implements BaseCodeEnum {
	/**
	 *  没有删除
	 */
	NODELETE(1, "1", "1"),
	/**
	 * 删除
	 */
	DELETE(0, "0", "0");

	private Integer id;
	
	private String code;
	
	private String name;
	
	DeleteFlagEnum(Integer id, String code, String name){
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
