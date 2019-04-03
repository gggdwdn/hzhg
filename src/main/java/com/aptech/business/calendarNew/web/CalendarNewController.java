package com.aptech.business.calendarNew.web;

import java.util.ArrayList;
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

import com.aptech.business.calendarNew.domain.CalendarNewEntity;
import com.aptech.business.calendarNew.service.CalendarNewService;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.util.JsonUtil;
import com.aptech.framework.web.base.ResultObj;

/**
 * 
 * 首页日历新表配置控制器
 *
 * @author 
 * @created 2018-08-09 09:38:02
 * @lastModified 
 * @history
 *
 */
@Controller
@RequestMapping("/calendarNew")
public class CalendarNewController extends BaseController<CalendarNewEntity> {
	
	@Autowired
	private CalendarNewService calendarNewService;
	
	@Override
	public IBaseEntityOperation<CalendarNewEntity> getService() {
		return calendarNewService;
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
		List<CalendarNewEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("calendarNewTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboCalendarNewVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("calendarNewCombobox", JsonUtil.toJson(comboCalendarNewVO.getOptions()));
		return this.createModelAndView("calendarNew/resource/views/calendarNewList", model);
	}

	@RequestMapping("/saveAddPage")
	public @ResponseBody ResultObj add(@RequestBody CalendarNewEntity t, HttpServletRequest request) {
		this.getService().addEntity(t);
		ResultObj resultObj = new ResultObj();
		resultObj.setData(t);
		return resultObj;
	}


	@RequestMapping("/edit/{id}")
	public @ResponseBody ResultObj edit(@PathVariable Long id,@RequestBody CalendarNewEntity t, HttpServletRequest request) {
		this.getService().deleteEntity(id);
		this.getService().addEntity(t);
		ResultObj resultObj = new ResultObj();
		return resultObj;
	}
	
	@RequestMapping("/singleDelete/{id}")
	public @ResponseBody ResultObj delete(@PathVariable Long id) {
		this.getService().deleteEntity(id);
		ResultObj resultObj = new ResultObj();
		return resultObj;
	}
	
	@RequestMapping("/findAll")
	public @ResponseBody ResultObj findAll(HttpServletRequest request, Map<String, Object> params) {
//		Map<String, Object> model = new HashMap<String, Object>();
		ResultObj resultObj = new ResultObj();
		List<CalendarNewEntity> calendarNewEntities = calendarNewService.findAll();
		List<Object> calendarNewAll = new ArrayList<Object>();
		for(CalendarNewEntity calendarNewEntity:calendarNewEntities){
			String idString = "\"id\":"+"\""+calendarNewEntity.getId().toString()+"\"";
			calendarNewAll.add("{"+idString+","+calendarNewEntity.getDetail().substring(1));
		}
//		model.put("calendarNewEntities", JsonUtil.toJson(calendarNewEntities));
		resultObj.setData(calendarNewAll);
		return resultObj;
	}
	
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/getAdd")
	public ModelAndView getAddPage(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();
		List<CalendarNewEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("calendarNewTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboCalendarNewVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("calendarNewCombobox", JsonUtil.toJson(comboCalendarNewVO.getOptions()));
		
		return this.createModelAndView("calendarNew/resource/views/calendarNewAdd", model);
	}

	/**
	 *	跳转到修改页面
	 */
	@RequestMapping("/getEdit/{id}")
	public ModelAndView getEditPage(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		CalendarNewEntity calendarNewEntity = (CalendarNewEntity)calendarNewService.findById(id);
		model.put("entity", calendarNewEntity);
		model.put("entityJson", JsonUtil.toJson(calendarNewEntity));
		
		List<CalendarNewEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("calendarNewTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboCalendarNewVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("calendarNewCombobox", JsonUtil.toJson(comboCalendarNewVO.getOptions()));
		
		return this.createModelAndView("calendarNew/resource/views/calendarNewEdit", model);
	}
}