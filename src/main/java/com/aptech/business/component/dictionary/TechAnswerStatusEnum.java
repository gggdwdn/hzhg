package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TechAnswerStatusEnum implements BaseCodeEnum {

	/**
	 * 已提问
	 */
	ASKQUESTIONS(0,"0","已提问"),
	/**
	 * 答疑中
	 */
	ANSWER(1,"1","答疑中"),
	/**
	 * 答疑完毕
	 */
	ANSWERED(2,"2","答疑完毕");
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	TechAnswerStatusEnum(Integer id, String code, String name){
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
