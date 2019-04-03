package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/**
 * 
 * 审批结果的枚举
 *
 * @author wangjw
 * @created 2017年3月14日 下午3:07:12
 * @lastModified
 * @history
 *
 */
public enum ExamResultUpEnum implements BaseCodeEnum{
	/**
	 * 同意
	 */
	AGREE(1, "AGREE", "同意"),
	/**
	 * 驳回
	 */
	BACK(2, "BACK", "驳回"),
	/**
	 * 驳回上一级
	 */
	BACK_END(3,"BACK_END","驳回上一级"),
	/**
	 * 作废
	 */
	CANCEL(4,"CANCEL","作废");
	
	
	ExamResultUpEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
	    this.name = name;
	}
	
	private Integer id;
	
	private String name;

	private String code;

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}	
}
