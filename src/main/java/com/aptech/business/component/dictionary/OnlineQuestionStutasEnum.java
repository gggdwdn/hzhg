package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum OnlineQuestionStutasEnum implements BaseCodeEnum {
//
//	/**
//	 * 未考试
//	 */
//	NOEXAM(0,"0","未考试"),
	/**
	 * 考试中
	 */
	EXAMING(1,"1","考试中"),
	/**
	 * 考试结束
	 */
	EXAMED(2,"2","考试完毕"),
	/**
	 * 取消考试
	 */
	CANCELEXAM(3,"3","取消考试"),
	/**
	 * 未通知
	 */
	UNNOTICE(4,"4","未通知"),
	/**
	 * 已通知
	 */
	NOTICED(5,"5","已通知未考试");
	private Integer id;
	
	private String code;
	
	private String name;
	
	OnlineQuestionStutasEnum(Integer id, String code, String name){
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
