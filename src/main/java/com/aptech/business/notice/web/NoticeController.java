package com.aptech.business.notice.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.business.notice.domain.NoticeEntity;
import com.aptech.business.notice.service.NoticeService;
import com.aptech.common.system.user.domain.SysUserEntity;
import com.aptech.common.system.user.service.SysUserService;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.common.web.base.ResultListObj;
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
 * 公告配置控制器
 *
 * @author 
 * @created 2017-10-10 17:20:46
 * @lastModified 
 * @history
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController<NoticeEntity> {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private SysUserService sysUserService;
	@Override
	public IBaseEntityOperation<NoticeEntity> getService() {
		return noticeService;
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
		//发布人下拉列表
		ComboboxVO requestUserVO = new ComboboxVO();
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("a.C_STATUS", FieldTypeEnum.LONG, MatchTypeEnum.EQ,DataStatusEnum.NORMAL.ordinal()));
        List<SysUserEntity> allUsers = sysUserService.findByCondition(conditions, null);
        for(SysUserEntity sysUserEntity : allUsers){
        	requestUserVO.addOption(sysUserEntity.getId().toString(), sysUserEntity.getName());
        }
        model.put("allUser", JsonUtil.toJson(requestUserVO.getOptions()));
		return this.createModelAndView("notice/noticeList", model);
	}
	@RequestMapping(value = "/data/list")
	public  @ResponseBody ResultListObj dataList(HttpServletRequest request,@RequestBody Map<String, Object> params){
		//按照时间倒序排列
		Page<NoticeEntity> pages = PageUtil.getPage(params);
		pages.addOrder(Sort.desc("publishDate"));
		List<NoticeEntity> noticeEntities = noticeService.findByCondition(params, pages);
		for(NoticeEntity noticeEntity:noticeEntities){
			SysUserEntity SysUserEntity=sysUserService.findById(Long.valueOf(noticeEntity.getPublisher()));
			noticeEntity.setPublisher(SysUserEntity.getName());
		}
		ResultListObj resultObj = new ResultListObj();
		resultObj.setDraw((Integer)params.get("draw"));
		if (noticeEntities != null) {
			if (pages != null) {
				resultObj.setData(noticeEntities);
				resultObj.setRecordsTotal(pages.getTotal());
			}
		}
		return resultObj;
	}
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/getAdd")
	public ModelAndView getAddPage(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();
		return this.createModelAndView("notice/noticeAdd", model);
	}
	/**
	 * 
	 * 添加保存
	 * 
	 * @param @param entityMap
	 * @param @return
	 * @return ResultObj
	 * @throws 
	 * @author wangyue
	 * @created 2017年10月11日 上午10:57:22
	 * @lastModified
	 */
	@RequestMapping("/addSave")
	public @ResponseBody ResultObj addSave(@RequestBody Map<String, Object> entityMap){
		//获取系统时间
		Date now = new Date(); 
		DateFormatUtil sdf = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format( now );
		//获取登录人
		SysUserEntity sysUserEntity = RequestContext.get().getUser();
		String userId = String.valueOf(sysUserEntity.getId());
		NoticeEntity noticeEntity = new NoticeEntity();
		noticeEntity.setTitle(entityMap.get("title").toString());
		noticeEntity.setPublisher(userId);
		noticeEntity.setPublishDate(time);
		noticeEntity.setContent(entityMap.get("content").toString());
		noticeService.addEntity(noticeEntity);
		ResultObj resultObj = new ResultObj();
		return resultObj;
	}
	/**
	 *	跳转到修改页面
	 */
	@RequestMapping("/getEdit/{id}")
	public ModelAndView getEditPage(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		NoticeEntity noticeEntity = (NoticeEntity)noticeService.findById(id);
		model.put("entity", noticeEntity);
		model.put("entityJson", JsonUtil.toJson(noticeEntity));
		
		List<NoticeEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("noticeTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboNoticeVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("noticeCombobox", JsonUtil.toJson(comboNoticeVO.getOptions()));
		
		return this.createModelAndView("notice/noticeEdit", model);
	}
	/**
	 * 
	 * 修改保存
	 * 
	 * @param @param noticeEntity
	 * @param @param request
	 * @param @return
	 * @param @throws ParseException
	 * @return ResultObj
	 * @throws 
	 * @author wangyue
	 * @created 2017年10月11日 上午10:58:14
	 * @lastModified
	 */
	@RequestMapping("/saveEditPage")
	public @ResponseBody ResultObj saveEditPage(@RequestBody NoticeEntity noticeEntity,HttpServletRequest request) throws ParseException{
		//获取系统时间
		Date now = new Date(); 
		DateFormatUtil sdf = DateFormatUtil.getInstance("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format( now );
		//获取登录人
		SysUserEntity sysUserEntity = RequestContext.get().getUser();
		String userId = String.valueOf(sysUserEntity.getId());
		NoticeEntity noticeSetEntity= noticeService.findById(noticeEntity.getId());
		noticeSetEntity.setTitle(noticeEntity.getTitle());
		noticeSetEntity.setPublisher(userId);
		noticeSetEntity.setPublishDate(time);
		noticeSetEntity.setContent(noticeEntity.getContent());
		noticeService.updateEntity(noticeSetEntity);
		ResultObj resultObj = new ResultObj();
		return resultObj;
	}
	/**
	 * 
	 * 页面查看
	 * 
	 * @param @param request
	 * @param @param id
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author wangyue
	 * @created 2017年10月11日 上午10:58:32
	 * @lastModified
	 */
	@RequestMapping("/showPage/{id}")
	public ModelAndView getShowPage(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		NoticeEntity noticeEntity = (NoticeEntity)noticeService.findById(id);
		model.put("entity", noticeEntity);
		model.put("entityJson", JsonUtil.toJson(noticeEntity));
		
		List<NoticeEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("noticeTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboNoticeVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("noticeCombobox", JsonUtil.toJson(comboNoticeVO.getOptions()));
		
		return this.createModelAndView("notice/noticeShow", model);
	}
	/**
	 * 
	 * 首页页面展示
	 * 
	 * @param @param request
	 * @param @param params
	 * @param @return
	 * @return ResultListObj
	 * @throws 
	 * @author wangyue
	 * @created 2017年10月11日 上午10:58:56
	 * @lastModified
	 */
	@RequestMapping(value = "/recList")
	public  @ResponseBody ResultListObj recList(HttpServletRequest request,@RequestBody Map<String, Object> params){
		//按照时间倒序排列
		Page<NoticeEntity> pages = PageUtil.getPage(params);
		pages.addOrder(Sort.desc("publishDate"));
		pages.setPageSize(7);
		List<Condition> conditions = OrmUtil.changeMapToCondition(params);
		List<NoticeEntity> noticeEntities = noticeService.findByCondition(conditions, pages);
		//获得返回结果
		ResultListObj resultObj = new ResultListObj();
		resultObj.setDraw((Integer)params.get("draw"));
		if (noticeEntities != null) {
			if (pages != null) {
				resultObj.setData(noticeEntities);
				resultObj.setRecordsTotal(pages.getTotal());
			}
		}
		return resultObj;
	}
}