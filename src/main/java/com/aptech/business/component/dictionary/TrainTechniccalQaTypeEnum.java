package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TrainTechniccalQaTypeEnum implements BaseCodeEnum{
	ASK(0,"0","出题"),
	ANSWER(1,"1","答题");
	
	private Integer id;
	private String code;
	private String name;
	TrainTechniccalQaTypeEnum(Integer id,String code,String name){
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
