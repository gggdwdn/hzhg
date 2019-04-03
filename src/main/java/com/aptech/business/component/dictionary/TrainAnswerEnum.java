package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TrainAnswerEnum implements BaseCodeEnum{
	YES(0,"0","是"),
	NO(1,"1","否");
	
	private Integer id;
	private String code;
	private String name;
	private TrainAnswerEnum(Integer id,String code,String name){
		this.id=id;
		this.code=code;
		this.name=name;
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
