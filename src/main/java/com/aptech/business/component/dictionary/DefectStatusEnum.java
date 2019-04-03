package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum DefectStatusEnum implements BaseCodeEnum {
	REJECT(0, "0", "驳回"),//reject 
	PENDING(1, "1", "待提交"),//pending 
	MONITOR(2, "2", "待班长或主任审批"),//monitor 
	OVERHAUL(3, "3", "待检修专工鉴定"),//overhaul 
	FUNCTION(4, "4", "待运行专工鉴定"),//function 
	BIOTECH(5, "5", "待生技部专工鉴定"),//biotech 
	BIOTECHREJECT(6, "6", "生技部鉴定驳回"),//biotechReject 
	MONITORAPPROVE(7, "7", "待维护班长审批"),//monitorApprove 
	OVERHAULAPPROVE(8, "8", "待检修主任审批"),//overhaulApprove 
	BIOTECHAPPROVE(9, "9", "待生产部专工审批"),//biotechApprove
	PRODUCTIONREJECT(10, "10", "生产部专工审批驳回"),//productionReject 
	HANG(11, "11", "挂起"),//hang 
	FORMULATE(12, "12", "待制定操作人"),//formulate
	IMPLEMENT(13, "13", "待执行操作"),//implement 
	CHECK(14, "14", "待验收"),//check 
	CHECKREJECT(15, "15", "验收驳回"),//checkReject
	SOLVE(16, "16", "已消缺"),//solve 
	NODEFECT(17, "17", "非缺陷");//solve 


	private Integer id;
	
	private String code;
	
	private String name;
	
	DefectStatusEnum(Integer id, String code, String name){
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
