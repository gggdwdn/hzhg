package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;


/**
* @ClassName: JoinLandTypeEnum
* @Description: 接地刀闸状态枚举类
* @author sunliang
* @date 2017年9月6日 上午11:41:38
*
*/
public enum RunBusinessTypeEnum implements BaseCodeEnum{
	/**
	 * 1:已分
	 */
	RUNWAY(1, "1", "运行方式"),
	/**
	 * 2 :接地线刀闸
	 */
	LINE(2, "2", "接地线刀闸"),
	/**
	 * 3: 两者都有
	 */
	TWOWAY(3, "3", "两者都有");

	private Integer id;
	
	private String code;
	
	private String name;
	
	RunBusinessTypeEnum(Integer id, String code, String name){
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
