package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TrainPlanStatusEnum implements BaseCodeEnum {
	/**
	 * 未通知
	 */
	NONOTIFIED(0, "0", "未通知"),
	/**
	 * 已通知
	 */
	NOTIFIED(1, "1", "已通知");
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	TrainPlanStatusEnum(Integer id, String code, String name){
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
