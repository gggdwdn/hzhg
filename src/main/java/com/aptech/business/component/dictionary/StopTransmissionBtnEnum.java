package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum StopTransmissionBtnEnum implements BaseCodeEnum{
	/**
	 * "1", "场长（站长）审核"
	 */
	CZ(1, "1", "场长（站长）审核"),
	/**
	 * "2", "集控中心审核"
	 */
	JKZX(2,"2","集控中心审核"),
	/**
	 * "3", "生产副总经理审核"
	 */
	SCFJL(3, "3", "生产副总经理审核"),
	/**
	 * 4", "作废"
	 */
	ZF(4, "4", "作废"),
	/**
	 * "5", "再提交
	 */
	ZTJ(5, "5", "再提交");

	private Integer id;
	
	private String code;
	
	private String name;
	
	StopTransmissionBtnEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
