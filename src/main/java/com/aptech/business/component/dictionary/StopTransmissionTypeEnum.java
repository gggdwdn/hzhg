package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum StopTransmissionTypeEnum implements BaseCodeEnum{
	/*
	 * 停电
	 */
	STOP(0,"0","停电"),
	/*
	 * 送电
	 */
	SEND(1,"1","送电");

	private Integer id;
	
	private String code;
	
	private String name;
	
	StopTransmissionTypeEnum(Integer id, String code, String name){
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
