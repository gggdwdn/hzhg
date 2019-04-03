package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;


/**
* @ClassName: JoinLandTypeEnum
* @Description: 接地刀闸状态枚举类
* @author sunliang
* @date 2017年9月6日 上午11:41:38
*
*/
public enum JobTypeEnum implements BaseCodeEnum{
	/**
	 * 0：值班长
	 */
	MONITOR(0, "0", "值班长"),
	/**
	 * 1：主值班员
	 */
	MAINDUTY(1, "1", "主值班员"),
	/**
	 * 2:值班员
	 */
	DUTY(2,"2","值班员");

	private Integer id;
	
	private String code;
	
	private String name;
	
	JobTypeEnum(Integer id, String code, String name){
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
