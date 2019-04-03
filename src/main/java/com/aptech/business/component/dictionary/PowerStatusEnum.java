package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/** @ClassName:    PowerStatusEnum.java 
 * @author         
 * @version        V1.0  
 * @Date           2017年7月31日 下午13:19:22 
 */
public enum PowerStatusEnum implements BaseCodeEnum{
	/**
	 * 0 待提交
	 */
	PENDING(0, "0", "待提交"),
	/**
	 * 1检修班长审核中
	 */
	OVERHAUL(1, "1", "检修班长审核中"),
	/**
	 * 2值长审核中
	 */
	MONITOR(2, "2", "值长审核中"),
	/**
	 * 3未执行
	 */
	UNEXECUTED(3, "3", "未执行"),
	/**
	 * 4 已执行
	 */
	EXECUTED(4, "4", "已执行"),
	/**
	 * 已驳回
	 */
	REJECT(5,"5","已驳回"),
	
	/**
	 * 取消流程
	 */
	CANCEL(6, "6", "取消流程");//CANCEL


	private Integer id;
	
	private String code;
	
	private String name;
	
	PowerStatusEnum(Integer id, String code, String name){
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
