package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/** @ClassName:    WorkStatusEnum.java 
 * @author         zhangzq
 * @version        V1.0  
 * @Date           2017年6月5日 下午3:30:22 
 */
public enum WorkInterventionStatusEnum implements BaseCodeEnum{
	/**
	 * 待提交1
	 */
	TOBESUBMIT(1, "1", "待提交"),
	/**
	 * 已提交待签发2
	 */
	TOBEISSUED(2, "2", "已提交待签发"),
	/**
	 * 已签发待收票3
	 */
	TICKETS(3, "3", "已签发待收票"),
	/**
	 * 已收票待值长确认4
	 */
	LONGVALUESURE(4, "4", "已收票待值长确认"),
	/**
	 * 值长已确认待许可5
	 */
	ALLOW(5, "5", "值长已确认待许可"),
	/**
	 * 待工作负责人确认签字6
	 */
	PICSURE(6, "6", "待工作负责人确认"),
	
	/**
	 * 待签发-变更9
	 */
	CHANGEISSUED(9, "9", "负责人变更待签发"),
	/**
	 * 已签发待许可-变更10
	 */
	CHANGALLOW(10, "10", "负责人变更待许可"),
	
	/**
	 * 待许可-终结14
	 */
	ALLOWEND(14, "14", "终结待许可"),
	/**
	 * 待许可-申请试运15
	 */
	ALLOWAPPLY(15, "15", "申请试运待许可"),
	
	/**
	 * 已执行21
	 */
	END(21, "21", "已执行"),
	
	/**
	 * 驳回7
	 */
	WORKSTATUS_TYPE_TURNDOWN(7, "7", "驳回到工作负责人"),
	/**
	 * 作废8
	 */
	WORKSTATUS_TYPE_INVALID(8, "8", "废票");
	private Integer id;
	
	private String code;
	
	private String name;
	
	WorkInterventionStatusEnum(Integer id, String code, String name){
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
