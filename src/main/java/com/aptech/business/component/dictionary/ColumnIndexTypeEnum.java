package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/** @ClassName:    WorkStatusEnum.java 
 * @author         zhangzq
 * @version        V1.0  
 * @Date           2017年6月5日 下午3:30:22 
 */
public enum ColumnIndexTypeEnum implements BaseCodeEnum{
	
	HAVEBACKGROUNDTABLE(1, "haveBackgroundTable", "代办任务"),
	MESSAGE(2, "message", "消息"),
	NOTICE(3, "notice", "公告"),
	CALENDAR(4,"calendar","工作日历"),
	SLIDER(5, "slider", "轮播图"),
	ECHARTTABLE(6,"echartTable","自定义类型"),
	ADVERTISEMENT(7,"advertisement","广告"),
	NOTITLE(8,"noHeaderBox","无标题类型");

	private Integer id;
	
	private String code;
	
	private String name;
	
	ColumnIndexTypeEnum(Integer id, String code, String name){
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
