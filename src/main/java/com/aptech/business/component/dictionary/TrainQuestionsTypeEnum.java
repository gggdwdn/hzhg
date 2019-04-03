package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TrainQuestionsTypeEnum implements BaseCodeEnum {

	SINGLE(0, "0", "单选"),//SINGLE
	MULTI(1, "1", "多选"),
	JUDGE(2, "2", "判断");//SINGLE;//MULTI 
	
	private Integer id;
	
	private String code;
	
	private String name;
	
	TrainQuestionsTypeEnum(Integer id, String code, String name){
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
