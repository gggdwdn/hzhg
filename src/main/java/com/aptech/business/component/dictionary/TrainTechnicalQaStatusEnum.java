package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum TrainTechnicalQaStatusEnum implements BaseCodeEnum{
	SUBMIT(0,"0","已提交"),
	OVER(1,"1","答疑完毕"),
	ANSWER(2,"2","答疑中");
	
	private Integer id;
	private String code;
	private String name;
	TrainTechnicalQaStatusEnum(Integer id,String code,String name){
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
