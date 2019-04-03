package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum StopTransmissionStatusEnum implements BaseCodeEnum{
	/**
	 * 待提交1
	 */
	TOBESUBMIT(1,"1","待提交"),
	/**
	 * 已提交，待厂长审核2
	 */
	FACTORYAPPROVE(2,"2","待场长（站长）审核"),
	/**
	 * 待集控中心审核3
	 */
	CENTERAPPROVE(3,"3","待集控中心审核"),
	/**
	 * 待生产副总经理审核4
	 */
	MANAGEAPROVE(4,"4","待生产副总经理审核"),
	/**
	 * 已申请5
	 */
	END(5,"5","申请成功"),
	/**
	 * 厂长驳回6
	 */
	FACTORYREJECT(6,"6","场长（站长）驳回"),
	/**
	 * 集控中心驳回7
	 */
	CENTERREJECT(7,"7","集控中心驳回"),
	/**
	 * 生产副总经理驳回8
	 */
	MANAGEREJECT(8,"8","生产副总经理驳回"),
	/**
	 * 已取消9
	 */
	CANCELL(9,"9","已作废"),
	/**
	 * 驳回到工作负责人10
	 */
	REJECT(10,"10","驳回到工作负责人");
	private Integer id;
	
	private String code;
	
	private String name;
	
	StopTransmissionStatusEnum(Integer id, String code, String name){
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
