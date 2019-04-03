package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum OnlineExamEnum implements BaseCodeEnum{

	/**
	 * 取消考试
	 */
	FAILEXAM(0,"0","取消考试"),
	/**
	 * 考试中
	 */
	EXAMING(1,"1","考试中"),
	/**
	 * 考试完毕
	 */
	EXAMED(2,"2","考试完毕"),
	/**
	 * 未考试
	 */
	UNEXAMED(3,"3","未考试");

	
	private Integer id;
	
	private String code;
	
	private String name;
	
	OnlineExamEnum(Integer id, String code, String name){
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
