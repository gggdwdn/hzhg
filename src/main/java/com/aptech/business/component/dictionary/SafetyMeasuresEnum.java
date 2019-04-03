package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum SafetyMeasuresEnum implements BaseCodeEnum{

	/**
	 * 消防水箱
	 */
	FIREWATERTANK(5,"5","消防水箱"),
	/**
	 * 事故油池
	 */
	ACCIDENTOILPOOL(6,"6","事故油池"),
	/**
	 * 生活水箱
	 */
	WATERTANK(7,"7","生活水箱");
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	SafetyMeasuresEnum(Integer id, String code, String name){
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
