package com.aptech.business.component.dictionary;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aptech.common.system.dictionary.domain.SexEnum;
import com.aptech.common.system.dictionary.util.DictionaryUtil;
import com.aptech.common.system.dictionary.web.SysDictionaryVO;
import com.aptech.framework.util.ReflectionUtil;
import com.aptech.framework.util.StringUtil;

/**
 * 
 * 业务字典枚举类  枚举ID 100------65535
 *
 * @author zhangjx
 * @created 2017年3月14日 下午2:08:56 
 * @lastModified 
 * @history
 *
 */
public enum BusinessDictCategoryEnum {

	MODULE_TYPE(101, "业务类型"),
	DEVICE_TYPE(102, "设备类型"),
	CODE_SEX_TYPE(103, "性别类型", SexEnum.class),
	DEFECT_TICKET_TYPE(104, "缺陷票类型"),
	HANDLE_PRIORITY(105, "办理优先级"),
    DEFECT_TYPE(201, "缺陷类型"),
	DEFECT_STATUS(202, "缺陷流程状态",DefectStatusEnum.class),
	SOLVE_RESULT(203, "消缺结果"),
	OPERATION_STATUS(301, "操作票流程状态",OperationStatusEnum.class),
	OPERATION_ACTUAL(302, "操作票实际"),
	TYPICALTICKET_TYPE(401, "典型票类型",TypicalTicketTypeEnum.class),
	TYPICALTICKET_APPROVE_STATUS(402, "典型票流程状态",TypicalTicketApproveStatusEnum.class),
	WORKTICKET_TYPE(131, "工作票状态",WorkStatusEnum.class),//zzq  为了避免冲突 我从131 开始写  
	ISSET(132, "是否被设置为典型票",IssetEnum.class),//changl 票通用
	EXECUTE_SITUATION(133, "执行情况",ExecuteEnum.class),
	PARAMETER(134,"参数类型"),
    TIME_DIMENSION(106, "时间维度"),
    PROTECT_WAY(107, "保护变动方式"), 
    MEETING_FLAG(108, "会议种类"),
    CHECK_STATE(109, "措施检查状态"),
    RECORD_TYPE(110, "记录类型"),
    WORK_CYCLE(111, "实验周期"),
    DISPATCH_UNIT(112, "调度"),
    GR_STATE(113, "交接班状态"),
    DELAY_STATES(114, "是否延期"),
    DECLARE_TYPE(115, "项目申报类型"),
    RANGE_TYPE(116, "项目范围类型"),
    CONTENT_TYPE(117, "项目内容类型"),
    CHARGE_SOURCE(118, "资金来源"),
    PUR_WAY(119, "采购方式"),
    PROFESSION_TYPE(120, "专业类型"),
    JG_TYPE(121, "技改类型"),
    CARD_SORT(135, "作业类别第一种"),
    CARD_SORT_TWO(136, "作业类别第二种"),
    CARD_SORT_THREE(137, "作业类别第三种"),
    CARD_SORT_FOUR(138, "作业类别第四种"),
    IDENTITY(139, "鉴定结果",IdentifyEnum.class),
	MANAGEMENT_TYPE(140,"管理方式",ManagementTypeEnum.class),
	INSTOCK_TYPE(141,"入库类型"),
	OUTSTOCK_TYPE(142,"出库类型"),
	BILL_TYPE(143,"单据类型"),
	APPROVE_STATUS(144,"入库审核状态",InstockApproveStatusEnum.class),
	POWER_TYPE(145,"停送电类型",PowerTypeEnum.class),
	POWER_STATUS(146,"停送电流程状态",PowerStatusEnum.class),
	OUT_APPROVE_STATUS(147,"出库审核状态",OutstockApproveStatusEnum.class),
	PLANTIME_WEEK(122,"计划时间周"),
	KJ_CHARGE_SOURCE(123,"科技资金来源"),
	EQUIP_GRADE_NOW(127,"设备等级现评"),
	EQUIP_GRADE_PRE(128,"设备等级原评"),
	OVERHAUL_PLAN_STATUS(148,"检修计划流程状态",OverhaulPlanStatusEnum.class),
	MESSAGE_STATUS(149,"消息状态",MessageStatusEnum.class),
	MESSAGE_TYPE(150,"消息类型"),
	MESSAGE_CATEGORY(151,"消息分类"),
	INSPECT_AREA(154,"巡检区域"),
	PRODUCTION_FACTORY(152,"生产厂家"),
	DIGIT(153,"计数单位"),
	FIRE_STYLE(124,"动火方式"),
	MATERIAL_CATEGORY(125,"物资类别"),
    WORKTICKETFIRE_TYPE(126, "动火工作票状态",WorkFireStatusEnum.class),
	CONSTRUCTION_UNITS(156,"参建单位"),
	OUT_STATUS(157,"是否为外部人员"),
	PROTECT_STATUS(158, "定期工作计划",ProtectStatusEnum.class),
	RUN_CHECK(159,"检修状态"),
	SUPPLIER_TYPE(160,"供应商类型"),
	WAREHOUSE_TYPE(161,"仓库类型"),
	SEX_TYPE(162,"性别"),
	PARENT_WAREHOUSE(163,"父仓库"),
	WAREHOUSE_STATUS(164,"仓库启停用状态"),
	COLUMN_TYPE(165,"栏目类型",ColumnIndexTypeEnum.class),
	JUDGE_TYPE(166,"判断类型",JudgeTypeEnum.class),
	KNOWLEDGECLASSIFY(168,"知识库分类"),
	TRAINPLANSTATUS(169,"培训计划状态",TrainPlanStatusEnum.class),
	QUESTIONTYPE(170,"题型",TrainQuestionsTypeEnum.class),
	QUESTIONSTATUS(171,"试题状态",QuestionsStatusEnum.class),
	ONLINEQUESTIONSTATUS(172,"在线出题状态",OnlineQuestionStutasEnum.class),
	ONLINEEXAMSTATUS(173,"在线考试状态",OnlineExamEnum.class),
	TECHANSWERSTATUS(174,"技术问答状态",TechAnswerStatusEnum.class),
	SAFETYMEASURES(175,"安全措施票",SafetyMeasuresEnum.class),
	TRAINQUESTIONCLASSIFY(176,"试题分类"),
	TRAINANSWER(177,"题库答案",TrainAnswerEnum.class),
	TRAINTECHNICALSTATUS(178,"技术问题状态",TrainTechnicalQaStatusEnum.class),
	TRAINTECHNICALTYPE(179,"技术问题类型",TrainTechniccalQaTypeEnum.class),
	STOPTRANSMISSIONTYPE(180,"停送电方式",StopTransmissionTypeEnum.class),
	JOB_TYPE(186,"岗位类型",JobTypeEnum.class),
	APPLY_STATUS(193,"申请审批状态",ApplyStatusEnum.class),
	COMPANY_TYPE(195,"单位类型"),
	MAINTAIN_WAY(196,"维修方式"),
	MAINTAIN_PROJECT(197,"维修项目"),
	SCRAP_PROJECT(198,"报废项目"),
	SCRAP_STYLE(199,"报废类别"),
	ACCIDENT_TYPE(209,"事故类别"),
	PROCUREMENT_TYPE(204,"采购类型"),
	INVOICE_TYPE(205,"发票类型"),
	PLAN_STATUS(206,"计划审批状态",PlanStatusEnum.class),
	MAINTAIN_APPROVE_STATUS(207,"维修审批状态",MaintainApproveStatusEnum.class),
	MAINTAIN_STATUS(208,"维修状态",MaintainStatusEnum.class),
	LOSE_CONDITION(210,"损失情况"),
	ACCIDENT_LEVEL(211,"事故级别"),
	TASK_TYPE(212,"任务类型"),
	TASK_PROCESS_STATUS(213,"任务流程状态",TaskProcessStatusEnum.class),
	IS_USER(214,"是否关联用户"),
	SAFESTANDARD_TYPE(215,"安全标准化类型"),
	SGGL_TYPE(216, "类别",SgglTypeEnum.class),
	EMERGENCYTYPE(217,"应急类型"),
	HIDDEN_TROUBLE_TYPE(218,"隐患排查类别"),
	ASSET_DEFUALT_TYPE(219,"资质管理类型"),
	RUN_BUSINESS_TYPE(220,"设备运行类型",RunBusinessTypeEnum.class),
	WORK_INTERVENTION_STATUS(221,"介入工作票状态",WorkInterventionStatusEnum.class),
	WORK_REPAIR_STATUS(222,"紧急抢修单状态",WorkRepairStatusEnum.class);
	private Integer id;
	
	/**
	 * 码表的一级分类名字
	 */
	private String name;
	
	/**
	 * 码表二级分类的获取方式，如果该字段为null，则系统默认从数据中对应的码表中获取二级分类
	 */
	private Class<?> itemProviderClass;
	
	BusinessDictCategoryEnum(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	private BusinessDictCategoryEnum(Integer id, String name,Class<?> itemProviderClass ) {
		this.id = id;
		this.name = name;
		this.itemProviderClass = itemProviderClass;
	}
	
	public Integer getId(){
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Class<?> getItemProviderClass(){
		return itemProviderClass;
	}
	
	
	/**
	 * 
	 * 获取数据库存储的编码类型
	 * 
	 * @param @param name
	 * @param @return
	 * @return List<Code>
	 * @throws 
	 * @author zhangjx
	 * @created 2017年3月13日 下午3:03:28
	 * @lastModified
	 */
	public static List<SysDictionaryVO> getDBCategoryList(String name){
		List<SysDictionaryVO> dictionaryList = new ArrayList<SysDictionaryVO>();
		for(BusinessDictCategoryEnum codeEnum : BusinessDictCategoryEnum.values()){
			if(StringUtil.isEmpty(name) || codeEnum.getName().matches(".*" + name + ".*")) {
				if(codeEnum.getItemProviderClass()==null){
					SysDictionaryVO dictionary = new SysDictionaryVO();
					dictionary.setId(Long.valueOf(codeEnum.getId()));
					dictionary.setCode(codeEnum.name());
					dictionary.setCategoryCode("business");
					dictionary.setParentId(new Long(-1));
					dictionary.setOrder(codeEnum.ordinal());
					dictionary.setName(codeEnum.getName());
					dictionary.setType("business");
					dictionaryList.add(dictionary);
				}
			}
		}
		return dictionaryList;
	}
	
	/**
	 * 
	 * 获取枚举的数据库字典类型
	 * 
	 * @param @param name
	 * @param @return
	 * @return List<SysDictionaryVO>
	 * @throws 
	 * @author zhangjx
	 * @created 2017年4月8日 下午2:02:18
	 * @lastModified
	 */
	public static List<SysDictionaryVO> getEnumCategoryList(String name){
		List<SysDictionaryVO> dictionaryList = new ArrayList<SysDictionaryVO>();
		for(BusinessDictCategoryEnum codeEnum : BusinessDictCategoryEnum.values()){
			if(StringUtil.isEmpty(name) || codeEnum.getName().matches(".*" + name + ".*")) {
				if(codeEnum.getItemProviderClass()!=null){
					SysDictionaryVO dictionary = new SysDictionaryVO();
					dictionary.setId(Long.valueOf(codeEnum.getId()));
					dictionary.setCode(codeEnum.name());
					dictionary.setCategoryCode("business");
					dictionary.setParentId(new Long(-1));
					dictionary.setOrder(codeEnum.ordinal());
					dictionary.setName(codeEnum.getName());
					dictionary.setType("business");
					dictionaryList.add(dictionary);
				}
			}
		}
		return dictionaryList;
	}
	
	/**
	 * 
	 * 根据ID获取字典分类
	 * 
	 * @param @param id
	 * @param @return
	 * @return SysDictionaryVO
	 * @throws 
	 * @author zhangjx
	 * @created 2017年3月13日 下午6:32:25
	 * @lastModified
	 */
	public static SysDictionaryVO getCategoryById(Integer id){
		SysDictionaryVO dictionary = null;
		if(id != null){
			for(BusinessDictCategoryEnum codeEnum : BusinessDictCategoryEnum.values()){
				if(codeEnum.getId().equals(id)){
					dictionary = new SysDictionaryVO();
					dictionary.setId(Long.valueOf(codeEnum.ordinal()));
					dictionary.setCode(codeEnum.name());
					dictionary.setParentId(new Long(-1));
					dictionary.setCategoryCode(codeEnum.name());
					dictionary.setOrder(codeEnum.ordinal());
					dictionary.setName(codeEnum.getName());
				}
			}
		}
		return dictionary;
	}
	
	/**
	 * 
	 * 获取字典项
	 * 
	 * @param @return
	 * @return Map<String,SysDictionaryVO>
	 * @throws 
	 * @author zhangjx
	 * @created 2017年3月13日 下午8:41:57
	 * @lastModified
	 */
	public Map<String, SysDictionaryVO> getDictItems() {
		Map<String, SysDictionaryVO> result = new LinkedHashMap<String, SysDictionaryVO>();
		if (itemProviderClass == null) {// 默认从数据中对应的码表中获取二级分类
			return DictionaryUtil.getDictionaries(name());
		} else if (itemProviderClass.isEnum()) {
			Method ennumCodeMethod = ReflectionUtil.getDeclaredMethod(itemProviderClass,"getCode");
			Method idMethod = ReflectionUtil.getDeclaredMethod(itemProviderClass,"getId");
			Method ordinalMethod = ReflectionUtil.getDeclaredMethod(itemProviderClass,"ordinal");
			Method nameMethod = ReflectionUtil.getDeclaredMethod(itemProviderClass,"getName");
			for (Object enumConstant : itemProviderClass.getEnumConstants()) {
				SysDictionaryVO codeObj = new SysDictionaryVO();
				String code = (String)ReflectionUtil.invokeMethod(ennumCodeMethod,enumConstant);
				Integer id = (Integer)ReflectionUtil.invokeMethod(idMethod,enumConstant);
				int ordinal = (Integer)ReflectionUtil.invokeMethod(ordinalMethod,enumConstant);
				String displayName = (String)ReflectionUtil.invokeMethod(nameMethod,enumConstant);
				codeObj.setId(Long.valueOf(id));
				codeObj.setCode(code);
				codeObj.setCategoryCode(this.name());
				codeObj.setName(displayName);
				codeObj.setOrder(ordinal);
				codeObj.setParentId(new Long(this.getId()));
				result.put(code, codeObj);
			}
		}
		return result;
	}
}
