package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum ProtectResultEnum implements BaseCodeEnum {
    /**
     * 同意
     */
    AGREE(1, "AGREE", "同意"),
   /**
     * 驳回
     */
    BACK_END(2, "BACK_END", "驳回"),
    /**
     * 本级同意
     */
    FINALAGREE(3,"FINALAGREE","确定审批人");
	private Integer id;
	
	private String code;
	
	private String name;
	
	ProtectResultEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

}
