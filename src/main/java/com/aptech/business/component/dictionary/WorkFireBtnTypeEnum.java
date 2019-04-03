package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum WorkFireBtnTypeEnum implements BaseCodeEnum {
	/**
	 * "1", "签发"
	 */
	QF(1, "1", "签发"),
	/**
	 * "2", "消防部门负责人审核"
	 */
	XFSH(2, "2", "消防部门负责人审核"),
	/**
	 * "3", "安监部门负责人审核"
	 */
	AJSH(3, "3", "安监部门负责人审核"),
	/**
     * 4", "主管领导审核
     */
    LDSH(4, "4", "主管领导审核"),
	/**
	 * 5", "许可
	 */
	XK(5, "5", "许可"),
	/**
	 * "6", "执行人确认措施
	 */
	QRCS(6, "6", "执行人确认措施"),
	/**
	 * "7", "监护人确认"
	 */
	JHRQR(7, "7", "监护人确认"),
	/**
	 * "8", "工作负责人确认"
	 */
	FZRQR(8, "8", "工作负责人确认"),
	/**
	 * "9", "安监人员确认"
	 */
	AJYQR(9, "9", "安监人员确认"),
	/**
	 * "10", "延期"
	 */
	LDQR(10, "10", "领导确认"),
	/**
	 * "11", "执行人验收
	 */
	ZXRYS(11, "11", "执行人验收"),
	/**
	 * "12", "监护人验收
	 */
	JHRYS(12, "12", "监护人验收"),
	/**
	 * "13", "工作负责人验收
	 */
	FZRYS(13, "13", "工作负责人验收"),
	/**
	 * "14", "许可人验收")
	 */
	XKRYS(14, "14", "许可人验收"),
	/**
	 * "15", "许可（终结）
	 */
	ZJXK(15, "15", "许可（终结）"),
	/**
	 * "22", "废票
	 */
	FP(22, "22", "废票"),
	/**
	 * "23", "再提交
	 */
	ZTJ(23, "23", "再提交"),
	/**
	 * "24", "值长（终结）
	 */
	ZJZZ(24, "24", "值长（终结）");


	private Integer id;
	
	private String code;
	
	private String name;
	
	WorkFireBtnTypeEnum(Integer id, String code, String name){
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
