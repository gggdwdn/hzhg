package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum WorkEarthBtnTypeEnum implements BaseCodeEnum {
	/**
	 * "1", "签发"
	 */
	QF(1, "1", "签发"),
	/**
	 * "2", "安监部负责人确认"
	 */
	QR(2, "2", "确认（安监部）"),
	/**
	 * 3", "许可
	 */
	XK(3, "3", "许可"),
	/**
	 * "4", "终结（负责人"
	 */
	ZJ(4, "4", "终结（负责人）"),
	/**
	 * "5", "废票
	 */
	FP(5, "5", "废票"),
	/**
	 * "6", "再提交
	 */
	ZTJ(6, "6", "再提交")	;
	private Integer id;
	
	private String code;
	
	private String name;
	
	WorkEarthBtnTypeEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
