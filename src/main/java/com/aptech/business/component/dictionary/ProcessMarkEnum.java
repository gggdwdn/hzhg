package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

/**
 * 
 * 流程标识的枚举
 *
 * @author wangjw
 * @created 2017年3月14日 下午3:07:12
 * @lastModified
 * @history
 *
 */
public enum ProcessMarkEnum implements BaseCodeEnum{
	/**
	 * 工作票流程试验用
	 */
	WORK_BILL_PROCESS_KEY(1, "WORK_BILL_PROCESS_KEY", "process_261"),
	/**
	 * 电气第一种工作票流程 正式
	 */
	WORK_TICKET_PROCESS_KEY(2, "WORK_TICKET_PROCESS_KEY", "process_269"),
	/**
	 * 电气第二种工作票流程 正式
	 */
	WORK_TICKET_SECOND_PROCESS_KEY(3, "WORK_TICKET_SECOND_PROCESS_KEY", "process_277"),	
	/**
	 * 检修计划流程 正式
	 */
	OVERHAUL_PLAN_PROCESS_KEY(4, "WORK_TICKEOCESS_KEY", "process_274"),
	/**
	 * 缺陷管理 正式
	 */
	DEFECT_PROCESS_KEY(4, "DEFECT_PROCESS_KEY", "process_268"),
	/**
	 * 操作票 正式
	 */
	OPERATIONTICKET_PROCESS_KEY(5,"OPERATIONTICKET_PROCESS_KEY","process_286"),
	/**
	 * 典型票并行正式
	 */
	TYPICALTICKET_PROCESS_KEY20(20,"TYPICALTICKET_PROCESS_KEY","process_289"),
	/**
	 * 典型票非并行正式
	 */
	TYPICALTICKET_PROCESS_KEY(6,"TYPICALTICKET_PROCESS_KEY","process_290"),
	/**
	 * 风力机械工作票流程 正式
	 */
	WORK_TICKET_WINDMECHANICAL_PROCESS_KEY(7, "WORK_TICKET_WINDMECHANICAL_KEY", "process_288"),
    /**
     * 保护投退正式
     */
    PROTECT_PROCESS_KEY(8,"PROTECT_PROCESS_KEY","process_271"),
    /**
     * 定期工作计划正式
     */
    WORKPLAN_PROCESS_KEY(9,"WORKPLAN_PROCESS_KEY","process_275"),
    /**
     * 定期工作记录正式
     */
    WORKRECORD_PROCESS_KEY(10,"WORKRECORD_PROCESS_KEY","process_276"),
    /**
     * 动土工作票正式
     */
    WORK_TICKET_EARTH_PROCESS_KEY(11,"WORK_TICKET_EARTH_PROCESS_KEY","process_291"),
	/**
	 * 入库管理
	 */
	IN_STOCK_PROCESS_KEY(12,"IN_STOCK_PROCESS_KEY","process_292"),
	/**
	 * 出库管理
	 */
	OUT_STOCK_PROCESS_KEY(13,"OUT_STOCK_PROCESS_KEY","process_293"),
	/**
	 * 介入票
	 */
	INTERVENTIONTICKE_PROCESS_KEY(14, "WORK_TICKET_SECOND_PROCESS_KEY", "process_294"),
	/**
	 * 停送电管理
	 */
	POWER_PROCESS_KEY(15, "POWER_PROCESS_KEY", "process_295"),
	/**
	 * 抢修单
	 */
	REPAIRTICKE_PROCESS_KEY(16, "WORK_TICKET_SECOND_PROCESS_KEY", "process_296"),
	/**
	 * 风力自控工作票流程
	 */

	WORK_TICKET_WINDAUTO_PROCESS_KEY(17, "WORK_TICKET_WINDAUTO_PROCESS_KEY", "process_300"),
	
    /**
     * 一级动火工作票
     */
    WORK_TICKET_FIRE_PROCESS_KEY(18, "WORK_TICKET_FIRE_PROCESS_KEY", "process_302"),
    /**
	 * 设备异动流程
	 */
	EQUIP_ABNORMAL_PROCESS_KEY(19,"EQUIP_ABNORMAL_PROCESS_KEY","process_301"),
    
    /**
     * 二级动火工作票
     */
    WORK_TICKET_FIRE_TWO_PROCESS_KEY(20, "WORK_TICKET_FIRE_TWO_PROCESS_KEY", "process_303"),
    /**
     * 任务管理流程
     */
	TASK_MANAGEMENT_PROCESS_KEY(21, "TASK_MANAGEMENT_PROCESS_KEY", "process_304"),
    /**
     * 停电计划流程
     */
	STOP_TRANMISSION_KEY(25,"STOP_TRANMISSION_KEY","process_309"),
	/**
	 * 停电计划流程
	 */
	WORK_TICKET_WIND_REPORT_KEY(26,"WORK_TICKET_WIND_REPORT_KEY","process_306");
	ProcessMarkEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
	    this.name = name;
	}
	
	private Integer id;
	
	private String name;

	private String code;

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}	
}
