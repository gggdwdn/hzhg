package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/** @ClassName:    WorkEarthStatusEnum.java 
 * @author         zhangzq
 * @version        V1.0  
 * @Date           2017年6月5日 下午3:30:22 
 */
public enum WorkEarthStatusEnum implements BaseCodeEnum{
	/**
	 * 待提交1
	 */
	TOBESUBMIT(1, "1", "待提交"),
	/**
	 * 已提交待签发2
	 */
	TOBEISSUED(2, "2", "已提交待签发"),
	/**
	 * 已签发待安监部负责人确认3
	 */
	TICKETS(3, "3", "已签发待安监部负责人确认"),
	/**
	 * 安监部负责人已确认待许可4
	 */
	ALLOW(4, "4", "安监部负责人已确认待许可"),
	/**
	 * 待工作负责人确认签字5
	 */
	PICSURE(5, "5", "待工作负责人确认"),
	/**
	 * 已执行6
	 */
	END(21, "21", "已执行"),
	/**
	 * 作废7
	 */
	WORKSTATUS_TYPE_INVALID(7, "7", "工作票作废"),
	/**
	 * 驳回8
	 */
	WORKSTATUS_TYPE_TURNDOWN(8, "8", "驳回到工作负责人");
	
	private Integer id;
	
	private String code;
	
	private String name;
	WorkEarthStatusEnum(Integer id, String code, String name){
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
