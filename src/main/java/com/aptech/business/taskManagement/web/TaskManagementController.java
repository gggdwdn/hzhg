package com.aptech.business.taskManagement.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.business.component.dictionary.ProcessMarkEnum;
import com.aptech.business.component.dictionary.TaskProcessStatusEnum;
import com.aptech.business.excel.ExcelUtil;
import com.aptech.business.taskManagement.domain.TaskManagementEntity;
import com.aptech.business.taskManagement.service.TaskManagementService;
import com.aptech.common.system.dictionary.util.DictionaryUtil;
import com.aptech.common.system.dictionary.web.SysDictionaryVO;
import com.aptech.common.system.unit.domain.SysUnitEntity;
import com.aptech.common.system.unit.service.SysUnitService;
import com.aptech.common.system.user.domain.SysUserEntity;
import com.aptech.common.system.user.service.SysUserService;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.common.web.base.ResultListObj;
import com.aptech.common.workflow.definition.service.DefinitionService;
import com.aptech.common.workflow.modelEditor.domain.BranchMarkEnum;
import com.aptech.common.workflow.modelEditor.domain.CandidateMarkEnum;
import com.aptech.common.workflow.modelEditor.service.NodeConfigService;
import com.aptech.common.workflow.processNodeAuth.domain.ProcessNodeAuthEntity;
import com.aptech.common.workflow.processNodeAuth.service.ProcessNodeAuthService;
import com.aptech.common.workflow.todoTask.domain.ExamMarkEnum;
import com.aptech.common.workflow.todoTask.domain.ExamResultEnum;
import com.aptech.common.workflow.todoTask.domain.TodoTaskEntity;
import com.aptech.common.workflow.todoTask.service.TodoTaskService;
import com.aptech.framework.context.RequestContext;
import com.aptech.framework.orm.DataStatusEnum;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.orm.OrmUtil;
import com.aptech.framework.orm.Page;
import com.aptech.framework.orm.Sort;
import com.aptech.framework.orm.search.Condition;
import com.aptech.framework.orm.search.FieldTypeEnum;
import com.aptech.framework.orm.search.MatchTypeEnum;
import com.aptech.framework.util.DateFormatUtil;
import com.aptech.framework.util.JsonUtil;
import com.aptech.framework.web.base.PageUtil;
import com.aptech.framework.web.base.ResultObj;

/**
 * 
 * 任务管理配置控制器
 *
 * @author 
 * @created 2017-09-29 13:24:39
 * @lastModified 
 * @history
 *
 */
@Controller
@RequestMapping("/taskManagement")
public class TaskManagementController extends BaseController<TaskManagementEntity> {
	
	@Autowired
	private TaskManagementService taskManagementService;
	@Autowired
	private SysUnitService sysUnitService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private DefinitionService definitionService;
	@Autowired
	private NodeConfigService nodeConfigService;
	@Autowired
	private TodoTaskService todoTaskService;
	@Autowired
	private ProcessNodeAuthService processNodeAuthService;
	
	@Override
	public IBaseEntityOperation<TaskManagementEntity> getService() {
		return taskManagementService;
	}
	
	/**
	 *	list页面跳转
	 * @Title: 
	 * @Description:
	 * @param 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView list(HttpServletRequest request, Map<String, Object> params) {
		Map<String, Object> model = new HashMap<String, Object>();
		//单位列表
		List<SysUnitEntity> treeNodeList = sysUnitService.getUnitTreeNodeList();
	
		params.put("unitNameIdTreeList", JsonUtil.toJson(treeNodeList));
		
		//类型
        ComboboxVO codeDateTypesCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("TASK_TYPE");
        
        for(String key :  codeDateTypeMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
        	codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        }
        params.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
        
        //状态
        ComboboxVO processStatusCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> processStatusComboboxMap  =  DictionaryUtil.getDictionaries("TASK_PROCESS_STATUS");
        
        for(String key :  processStatusComboboxMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = processStatusComboboxMap.get(key);
        	processStatusCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        }
        params.put("processStatus", JsonUtil.toJson(processStatusCombobox.getOptions()));
		
        //人员
		ComboboxVO searchuser = new ComboboxVO();
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("a.C_STATUS", FieldTypeEnum.STRING, MatchTypeEnum.NE, String.valueOf(DataStatusEnum.DELETE.ordinal())));
		List<SysUserEntity> userList = sysUserService.findByCondition(conditions, null);
		for (SysUserEntity user : userList) {
			searchuser.addOption(user.getId().toString(), user.getName());
		}
		params.put("searchuser", JsonUtil.toJson(searchuser.getOptions()));
		
		return this.createModelAndView("taskManagement/taskManagementList", model);
	}
	
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/getAdd")
	public ModelAndView getAddPage(HttpServletRequest request){
		SysUserEntity userEntity= RequestContext.get().getUser();

		Map<String, Object> model = new HashMap<String, Object>();
		//单位列表
		List<SysUnitEntity> treeNodeList = sysUnitService.getUnitTreeNodeList();
	
		model.put("unitNameIdTreeList", JsonUtil.toJson(treeNodeList));
		
		//类型
        ComboboxVO codeDateTypesCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("TASK_TYPE");
        
        for(String key :  codeDateTypeMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
        	codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        }
        model.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
        
        model.put("userEntity",userEntity);
        DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm");
        model.put("date",df.format(new Date()));
		
		return this.createModelAndView("taskManagement/taskManagementAdd", model);
	}
	
	/**
	 *	跳转到修改页面
	 */
	@RequestMapping("/getEdit/{id}")
	public ModelAndView getEditPage(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		TaskManagementEntity taskManagementEntity = (TaskManagementEntity)taskManagementService.findById(id);
		model.put("entity", taskManagementEntity);
		model.put("entityJson", JsonUtil.toJson(taskManagementEntity));
		//单位列表
		List<SysUnitEntity> treeNodeList = sysUnitService.getUnitTreeNodeList();
	
		model.put("unitNameIdTreeList", JsonUtil.toJson(treeNodeList));
		
		//类型
        ComboboxVO codeDateTypesCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("TASK_TYPE");
        
        for(String key :  codeDateTypeMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
        	codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        }
        model.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
		
		return this.createModelAndView("taskManagement/taskManagementEdit", model);
	}
	/**
	 *	跳转到查看页面
	 */
	@RequestMapping("/getDetail/{id}")
	public ModelAndView getDetail(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		TaskManagementEntity taskManagementEntity = (TaskManagementEntity)taskManagementService.findById(id);
		model.put("entity", taskManagementEntity);
		return this.createModelAndView("taskManagement/taskManagementDetail", model);
	}
	
	/**
	 * @Description:   导出
	 * @author         changl 
	 * @Date           2017年6月28日 上午11:03:09 
	 * @throws         Exception
	 */
	@RequestMapping("/exportExcel")
	public void expData(HttpServletRequest req,HttpServletResponse res,Map<String, Object> params) throws UnsupportedEncodingException{
		String conditions=req.getParameter("conditions");
		params.put("conditions", JSONArray.fromObject(conditions));
		Page<TaskManagementEntity> pages = new Page<TaskManagementEntity>();
		pages.setOrders(OrmUtil.changeMapToOrders(params));
		pages.addOrder(Sort.desc("issuerTime"));
		List<TaskManagementEntity> dataList=taskManagementService.findByCondition(params, pages);
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("dataList", dataList);
		ExcelUtil.export(req, res, "任务管理报表模板.xlsx","任务管理.xlsx", resultMap);
	}
	/**
	 * @Description:   自己写的修改方法
	 * @author         zhangzq 
	 * @Date           2017年6月20日 下午3:34:59 
	 * @throws         Exception
	 */
	@RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
	public @ResponseBody ResultObj update(@RequestBody TaskManagementEntity t, HttpServletRequest request ,@PathVariable Long id) {
		taskManagementService.updateEntity(t);
		return new ResultObj();
	}
	/**
	 * @Description:   提交确认  弹出框      这里要调用第一个流程接口，得到下一步的那些人的id集合
	 * @author         zhangzq 
	 * @Date           2017年6月13日 
	 * @throws         Exception
	 */
	@RequestMapping("/sureSubmit")
	public ModelAndView sureSubmit(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("a.C_STATUS", FieldTypeEnum.LONG, MatchTypeEnum.EQ,DataStatusEnum.NORMAL.ordinal()));
		String id=request.getParameter("id");
		String unitId=request.getParameter("unitId");
		resultMap.put("id", id);
		if(id!=null && id!=""){
			TaskManagementEntity taskManageEntity = taskManagementService.findById(Long.valueOf(id));
			conditions.add(new Condition("b.C_UNIT_ID", FieldTypeEnum.LONG, MatchTypeEnum.EQ,taskManageEntity.getReceivingUnitId()));
		}else{
			conditions.add(new Condition("b.C_UNIT_ID", FieldTypeEnum.LONG, MatchTypeEnum.EQ,unitId));
		}

		List<SysUserEntity> userList = sysUserService.findByCondition(conditions, null);
		resultMap.put("userList", userList);
		
		//TODO 增加遍历 安全措施 作业类别 作业风险分析与主要预控措施  其中有null 就返回
		return new ModelAndView("taskManagement/sureSubmitPerson",resultMap);
	}
	
	/** 批量删除
	 * @author         zhangzq 
	 * @Date           2017年6月20日 下午3:34:59 
	 * @throws         Exception
	 */
	@RequestMapping(value = "/bulkDelete/{ids}", method = RequestMethod.DELETE)
	public @ResponseBody ResultObj bulkDelete(@RequestBody List<Integer> ids) {
		ResultObj resultObj = new ResultObj();
		taskManagementService.deleteBulk(ids);
		return resultObj;
	}
	
	/**
	 * @Description:   提交时候的验证
	 * @author         zhangzq 
	 * @Date           2017年6月29日 下午1:17:42 
	 * @throws         Exception
	 */
	@RequestMapping(value = "/tijiaoValidate/{id}")
	public @ResponseBody ResultObj tijiaoValidate(HttpServletRequest request ,@PathVariable Long id) {
		return taskManagementService.tijiaoValidate(id);
	}
	
	/**
	 * @Description:   提交方法
	 * @author         zhangzq 
	 * @Date           2017年6月12日 上午11:39:16 
	 * @throws         Exception
	 */
	@RequestMapping("/submit")
	public @ResponseBody ResultObj submit(HttpServletRequest request){
		ResultObj resultObj = new ResultObj();
		try {
			String id=request.getParameter("id");
			String selectUser=request.getParameter("selectUser");
			taskManagementService.submit(id,selectUser);
			resultObj.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			resultObj.setResult("error");
		}
		return resultObj;
	}
	
	/**
	 * @Description:   缺陷单的选择带回
	 * @author         zhangzq 
	 * @Date           2017年8月1日 下午1:26:58 
	 * @throws         Exception
	 */
	@RequestMapping("/taskList")
	public ModelAndView defectList(HttpServletRequest request,
			Map<String, Object> params) {
		Map<String, Object> model = new HashMap<String, Object>();
		//单位列表
		List<SysUnitEntity> treeNodeList = sysUnitService.getUnitTreeNodeList();
	
		params.put("unitNameIdTreeList", JsonUtil.toJson(treeNodeList));
		
		//类型
        ComboboxVO codeDateTypesCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("TASK_TYPE");
        
        for(String key :  codeDateTypeMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
        	codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        }
        params.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
        
        //状态
        ComboboxVO processStatusCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> processStatusComboboxMap  =  DictionaryUtil.getDictionaries("TASK_PROCESS_STATUS");
        
        for(String key :  processStatusComboboxMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = processStatusComboboxMap.get(key);
        	processStatusCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        }
        params.put("processStatus", JsonUtil.toJson(processStatusCombobox.getOptions()));
		
        //人员
		ComboboxVO searchuser = new ComboboxVO();
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("a.C_STATUS", FieldTypeEnum.STRING, MatchTypeEnum.NE, String.valueOf(DataStatusEnum.DELETE.ordinal())));
		List<SysUserEntity> userList = sysUserService.findByCondition(conditions, null);
		for (SysUserEntity user : userList) {
			searchuser.addOption(user.getId().toString(), user.getName());
		}
		params.put("searchuser", JsonUtil.toJson(searchuser.getOptions()));
		return this.createModelAndView("taskManagement/workTaskList", model);
	}
	/**
	 * 条件查询
	 * 
	 * @Title: show
	 * @Description:
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/searchWork", method = RequestMethod.POST)
	public @ResponseBody ResultListObj searchWork(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		Page<TaskManagementEntity> page = PageUtil.getPage(params);
		List<TaskManagementEntity> entities = taskManagementService.findByConditionWork(params, page);
		ResultListObj resultObj = new ResultListObj();
		resultObj.setDraw((Integer)params.get("draw"));
		resultObj.setData(entities);
		if (page != null) {
			resultObj.setRecordsTotal(page.getTotal());
		} else {
			resultObj.setRecordsTotal((long) entities.size());
		}
		return resultObj;
	}
	/**
	 * @Description:   工作票
	 * @author         changl 
	 * @Date           2017年6月22日 上午10:01:07 
	 * @throws         Exception
	 */
	@RequestMapping("/workTicket")
	public ModelAndView workTicket(HttpServletRequest request,
			Map<String, Object> params) {
		Map<String, Object> model = new HashMap<String, Object>();
		return this.createModelAndView("taskManagement/taskWorkTicketList",model);
	}
	/**
	 * @Description:   工作票
	 * @author         changl 
	 * @Date           2017年6月22日 上午10:01:07 
	 * @throws         Exception
	 */
	@RequestMapping("/defectDetail")
	public ModelAndView defectDetail(HttpServletRequest request,
			Map<String, Object> params) {
		Map<String, Object> model = new HashMap<String, Object>();
		return this.createModelAndView("taskManagement/taskDefectList",model);
	}
	/**
	 * @Description:   操作票
	 * @author         changl 
	 * @Date           2017年6月22日 上午10:01:07 
	 * @throws         Exception
	 */
	@RequestMapping("/operationTicket")
	public ModelAndView operationTicket(HttpServletRequest request,
			Map<String, Object> params) {
		Map<String, Object> model = new HashMap<String, Object>();
		return this.createModelAndView("taskManagement/taskOperationTicketList",model);
	}
	
	
	/**
	 * @Description: 缺陷审批页面初始化
	 * @author changl
	 * @Date 2017年6月5日 下午1:17:55
	 * @throws Exception
	 */
	@RequestMapping("/approve/{id}/{type}")
	public ModelAndView approve(HttpServletRequest request, @PathVariable Long id, @PathVariable String type) throws ParseException{
		Map<String, Object> model = new HashMap<String, Object>();
		TaskManagementEntity taskManagementEntity = taskManagementService.findById(id);
		model.put("entity", taskManagementEntity);
		model.put("type", type);
		//查询各个人的按钮权限 开始
		SysUserEntity userEntity= RequestContext.get().getUser();
		List<Condition> conditionsLc=new ArrayList<Condition>();
		conditionsLc.add(new Condition("sys_user.C_LOGIN_NAME", FieldTypeEnum.STRING, MatchTypeEnum.EQ, userEntity.getLoginName()));
		conditionsLc.add(new Condition("procInst.BUSINESS_KEY_", FieldTypeEnum.STRING, MatchTypeEnum.EQ, id));
		conditionsLc.add(new Condition("task.end_time_ IS NULL"));
		conditionsLc.add(new Condition("procDef.KEY_", FieldTypeEnum.STRING, MatchTypeEnum.EQ, ProcessMarkEnum.TASK_MANAGEMENT_PROCESS_KEY.getName()));
		List<TodoTaskEntity> list =  todoTaskService.findByCondition(conditionsLc, null);
		TodoTaskEntity todoTaskEntity=null;
		if(!list.isEmpty()){
			todoTaskEntity=list.get(0);
			List<ProcessNodeAuthEntity> nodeList=processNodeAuthService.getAuthorityList(todoTaskEntity.getTaskDefKey());
			System.out.println(nodeList.size());
			model.put("nodeList", nodeList);
		}
		//查询各个人的按钮权限 结束
		return this.createModelAndView("taskManagement/taskManagementApprove",model);
	}
	
	/**
	 * @Description: 部门接收人接收页面跳转
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping("/approvePersonAccept/{taskId}/{id}")
	public ModelAndView approvePersonAccept(HttpServletRequest request,@PathVariable String taskId,@PathVariable Long id) {
		Map<String, Object> model = new HashMap<String, Object>();
		SysUserEntity userEntity= RequestContext.get().getUser();
		model.put("userEntity", userEntity);
		DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm");
		model.put("accpetTime",df.format(new Date()) );
		model.put("type","accept" );
		model.put("processStatus",TaskProcessStatusEnum.UNEXECUTE.getCode());

		//审批页面点击签发按钮的时候，把下一步的人查询出来
		List<Condition> conditions = new ArrayList<Condition>();
		TaskManagementEntity taskManageEntity = taskManagementService.findById(id);
		conditions.clear();
		conditions.add(new Condition("b.C_UNIT_ID", FieldTypeEnum.LONG, MatchTypeEnum.EQ,taskManageEntity.getReceivingUnitId()));
		List<SysUserEntity> userList = sysUserService.findByCondition(conditions, null);
		model.put("userList", userList);
		return this.createModelAndView("taskManagement/approveSubmitPerson", model);
	}
	/**
	 * @Description: 部门接收人拒绝页面跳转
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping("/approvePersonRefuse/{taskId}")
	public ModelAndView approvePersonRefuse(HttpServletRequest request,@PathVariable String taskId) {
		Map<String, Object> model = new HashMap<String, Object>();
		SysUserEntity userEntity= RequestContext.get().getUser();
		model.put("userEntity", userEntity);
		DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm");
		model.put("accpetTime",df.format(new Date()) );
		model.put("type","refuse" );
		model.put("processStatus",TaskProcessStatusEnum.REJECT.getCode());
		//审批页面点击签发按钮的时候，把下一步的人查询出来
		return this.createModelAndView("taskManagement/approveSubmitPerson", model);
	}
	/**
	 * @Description: 部门接收人拒绝页面跳转
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping("/approveRefuse/{taskId}")
	public ModelAndView approveRefuse(HttpServletRequest request,@PathVariable String taskId) {
		Map<String, Object> model = new HashMap<String, Object>();
		SysUserEntity userEntity= RequestContext.get().getUser();
		model.put("userEntity", userEntity);
		//审批页面点击签发按钮的时候，把下一步的人查询出来
		return this.createModelAndView("taskManagement/approveRefuse", model);
	}
	/**
	 * @Description: 接收人提交执行情况
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping("/approvePersonSubmitResult/{taskId}")
	public ModelAndView approvePersonSubmitResult(HttpServletRequest request,@PathVariable String taskId) {
		Map<String, Object> model = new HashMap<String, Object>();
		SysUserEntity userEntity= RequestContext.get().getUser();
		model.put("userEntity", userEntity);
		DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm");
		model.put("accpetTime",df.format(new Date()) );
		model.put("type","submitResult" );
		model.put("processStatus",TaskProcessStatusEnum.REJECT.getCode());
		//审批页面点击签发按钮的时候，把下一步的人查询出来
		return this.createModelAndView("taskManagement/approveSubmitPerson", model);
	}
	/**
	 * @Description: 接收人拒绝到部门接收人页面跳转
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping("/approveRefuseExecute/{taskId}")
	public ModelAndView approveRefuseExecute(HttpServletRequest request,@PathVariable String taskId) {
		Map<String, Object> model = new HashMap<String, Object>();
		SysUserEntity userEntity= RequestContext.get().getUser();
		model.put("userEntity", userEntity);
		DateFormatUtil df = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm");
		model.put("accpetTime",df.format(new Date()) );
		model.put("type","submitResult" );
		model.put("processStatus",TaskProcessStatusEnum.REJECT.getCode());
		//审批页面点击签发按钮的时候，把下一步的人查询出来
		return this.createModelAndView("taskManagement/approveRefuse", model);
	}
	
	/**
	 * @Description: 部门接收人接收确定
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/accept")
	public @ResponseBody
	ResultObj accept(@RequestBody TaskManagementEntity t) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.UNEXECUTE.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),t.getUserList());
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.AGREE.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(), t.getSuggestions());
		params.put(ExamMarkEnum.RESULT.getCode(), ExamResultEnum.AGREE.getName());
		return taskManagementService.approve(t,params);
	}
	
	/**
	 * @Description: 部门接收人拒绝确定
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/refuse")
	public @ResponseBody
	ResultObj refuse(@RequestBody TaskManagementEntity t) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.REJECT.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),"");
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.BACK.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(), t.getSuggestions());
		params.put(ExamMarkEnum.RESULT.getCode(), ExamResultEnum.BACK.getName());
		return taskManagementService.approve(t,params);
	}
	
	/**
	 * @Description: 接收人拒绝确定
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/refuseExecute")
	public @ResponseBody
	ResultObj refuseExecute(@RequestBody TaskManagementEntity t) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.REJECTRECEIVE.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),"");
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.BACK.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(), t.getApproveIdea());
		params.put(ExamMarkEnum.RESULT.getCode(), ExamResultEnum.BACK.getName());
		return taskManagementService.approve(t,params);
	}
	/**
	 * @Description: 下达人同意结束任务确定
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/agreeEnd")
	public @ResponseBody
	ResultObj agreeEnd(@RequestBody TaskManagementEntity t) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.END.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),"");
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.AGREE.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(), t.getApproveIdea());
		params.put(ExamMarkEnum.RESULT.getCode(), ExamResultEnum.AGREE.getName());
		return taskManagementService.approve(t,params);
	}
	/**
	 * @Description: 下达人拒绝结束任务确定
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/disagreeEnd")
	public @ResponseBody
	ResultObj disagreeEnd(@RequestBody TaskManagementEntity t) {
		TaskManagementEntity entity  = taskManagementService.findById(t.getId());
		SysUserEntity user = sysUserService.findById(entity.getReceiveUserId());
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.UNEXECUTE.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),user.getLoginName());
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.BACK.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(), t.getApproveIdea());
		params.put(ExamMarkEnum.RESULT.getCode(), ExamResultEnum.BACK.getName());
		return taskManagementService.approve(t,params);
	}
	
	/**
	 * @Description: 再提交
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/againSubmit/{id}")
	public @ResponseBody
	ResultObj againSubmit(@RequestBody TaskManagementEntity t) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.RECEIVE.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),t.getUserList());
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.AGREE.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(),  t.getSuggestions());
		params.put(ExamMarkEnum.RESULT.getCode(),ExamResultEnum.AGREE.getName());
		return taskManagementService.approve(t,params);
	}
	/**
	 * @Description: 取消流程
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancel/{id}")
	public @ResponseBody
	ResultObj cancel(@RequestBody TaskManagementEntity t) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.CANCEL.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),"");
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.BACK.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(), "");
		params.put(ExamMarkEnum.RESULT.getCode(),ExamResultEnum.BACK.getName());
		return taskManagementService.approve(t,params);
	}
	/**
	 * @Description: 提交执行结果
	 * @author changl
	 * @Date 2017年6月5日 下午1:19:30
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitResult")
	public @ResponseBody
	ResultObj submitResult(@RequestBody TaskManagementEntity t) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", TaskProcessStatusEnum.EXECUTED.getCode());
		params.put(CandidateMarkEnum.NEXT_HANDLERS.getName(),"");
		params.put(BranchMarkEnum.BRANCH_KEY.getName(), ExamResultEnum.AGREE.getId());
		params.put(ExamMarkEnum.COMMENT.getCode(),  t.getSuggestions());
		params.put(ExamMarkEnum.RESULT.getCode(),ExamResultEnum.AGREE.getName());
		return taskManagementService.approve(t,params);
	}
	//首页任务单的接口getTaskManagerNum
	@RequestMapping(value = "/getTaskManagerNum")
	public @ResponseBody ResultObj getTaskManagerNum(HttpServletRequest request){
		ResultObj resultObj = new ResultObj();
		Calendar cale = Calendar.getInstance();  
		DateFormatUtil dfuYM = DateFormatUtil.getInstance("yyyy-MM-dd ");
		String firstday, lastday;
		cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = dfuYM.format(cale.getTime()) + "00:00:00";
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = dfuYM.format(cale.getTime()) + "23:59:59";
		SysUserEntity user = RequestContext.get().getUser();
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("C_STATUS", FieldTypeEnum.STRING, MatchTypeEnum.EQ, String.valueOf(DataStatusEnum.NORMAL.ordinal())));
		conditions.add(new Condition("C_RECEIVE_USER_ID",FieldTypeEnum.STRING, MatchTypeEnum.EQ,user.getId()));
		conditions.add(new Condition("C_PROCESS_STATUS", FieldTypeEnum.LONG, MatchTypeEnum.EQ, TaskProcessStatusEnum.END.getId()));
		conditions.add(new Condition("C_ACTUAL_FINISH_TIME",FieldTypeEnum.DATE, MatchTypeEnum.LE,lastday ));
		conditions.add(new Condition("C_ACTUAL_FINISH_TIME",FieldTypeEnum.DATE, MatchTypeEnum.GE,firstday));
		List<TaskManagementEntity> resultList = taskManagementService.findByCondition(conditions, null);
		if(!resultList.isEmpty()){
			resultObj.setData(resultList.size());
		}else{
			resultObj.setData(0);
		}
		return resultObj;
	}
	
	
}