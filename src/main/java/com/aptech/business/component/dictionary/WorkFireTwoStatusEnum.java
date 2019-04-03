package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/** @ClassName:    WorkStatusEnum.java 
 * @author         zhangzq
 * @version        V1.0  
 * @Date           2017年6月5日 下午3:30:22 
 */
public enum WorkFireTwoStatusEnum implements BaseCodeEnum{
	/**
	 * 待提交1
	 */
	TOBESUBMIT(1, "1", "待提交"),
	/**
	 * 已提交待签发2
	 */
	TOBEISSUED(2, "2", "已提交待签发"),
	/**
	 * 已签发待消防负责人审核3
	 */
	TOBEXFSH(3, "3", "已签发待消防负责人审核"),
	/**
	 * 待安监负责人审核4
	 */
	TOBEAJSH(4, "4", "待安监负责人审核"),
	/**
	 * 待许可人审核6
	 */
	ALLOW(5, "5", "待许可人审核"),
    /**
     * 待执行人确认签字7
     */
    EXESURE(6, "6", "待执行人确认"),	
	/**
	 * 待消防负责人确认8
	 */
	XFSURE(7, "7", "待消防监护人确认"),
	   /**
     * 待工作负责人确认9
     */
    FZRSURE(9, "9", "待工作负责人确认"),
	/**
	 * 待安监员确认10
	 */
    AJYSURE(10, "10", "待安监员确认"),
	/**
	 * 待执行12
	 */
	TOEXE(12, "12", "待执行"),
	/**
	 * 执行完毕待监护人验收13
	 */
	JHRCHECK(13, "13", "执行完毕待监护人验收"),
	/**
	 * 待负责人验收14
	 */
	FZRCHECK(14, "14", "待负责人验收"),
	/**
	 * 待许可-申请试运15
	 */
	XKRCHECK(15, "15", "待许可人验收"),

	/**
	 * 已执行16
	 */
	END(21, "21", "已执行"),
	/**
     * 驳回17
     */
    WORKSTATUS_TYPE_TURNDOWN(17, "17", "驳回到工作负责人"),
    /**
     * 作废18
     */
    WORKSTATUS_TYPE_INVALID(18, "18", "废票"),
   
    /**
     * 驳回19
     */
    WORK_ALLOW_REJECT(19, "19", "驳回到许可人"),
   
    /**
     * 驳回20
     */
    WORK_EXE_REJECT(20, "20", "驳回到执行人");
	private Integer id;
	
	private String code;
	
	private String name;
	
	WorkFireTwoStatusEnum(Integer id, String code, String name){
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
