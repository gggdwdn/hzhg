package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum QuestionsStatusEnum implements BaseCodeEnum {

	/**
	 * 启用
	 */
	ENABLE(0,"0","启用"),
	/**
	 * 弃用
	 */
	DISABLE(1,"1","弃用");
	private Integer id;
	
	private String code;
	
	private String name;
	
	QuestionsStatusEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
