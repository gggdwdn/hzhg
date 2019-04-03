package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/** @ClassName:    WorkStatusEnum.java 
 * @author         zhangzq
 * @version        V1.0  
 * @Date           2017年6月5日 下午3:30:22 
 */
public enum WorkRepairStatusEnum implements BaseCodeEnum{
	/**
	 * 待提交1
	 */
	TOBESUBMIT(1, "1", "待提交"),
	/**
	 * 已提交待许可2
	 */
	TOBEISSUED(2, "2", "已提交待许可"),
	/**
	 * 已许可待负责人确认3
	 */
	TICKETS(3, "3", "已许可待负责人确认"),
	/**
	 * 待值长确认4
	 */
	LONGVALUESURE(4, "4", "待值长确认"),
	/**
	 * 待工作负责人确认签字6
	 */
	PICSURE(5, "5", "待工作负责人确认"),
	
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
	WORKSTATUS_TYPE_INVALID(8, "8", "废票"),
	/**
	 * 终结待许可9
	 */
	WORK_ALLOW_END(9, "9", "终结待许可"),
	/**
	 * 终结待值班长确定10
	 */
	WORK_MONITOR_END(10, "10", "终结待值班长确定");

	private Integer id;
	
	private String code;
	
	private String name;
	
	WorkRepairStatusEnum(Integer id, String code, String name){
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
