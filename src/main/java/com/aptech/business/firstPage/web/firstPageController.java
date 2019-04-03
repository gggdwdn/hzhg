package com.aptech.business.firstPage.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/firstPage")
public class firstPageController {
	/**
	 * 
	 * 首页待办任务模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年9月13日 下午5:59:21
	 * @lastModified
	 */
	
	
	@RequestMapping("/todoTaskTable")
	public ModelAndView getFirstPageTable(){
		Map<String,String> model = new HashMap<String, String>();
		return new ModelAndView("firstPage/todoTaskTable",model);
	}
	/**
	 * 
	 * 首页消息模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年9月13日 下午5:59:21
	 * @lastModified
	 */
	
	
	@RequestMapping("/messageCenterTable")
	public ModelAndView getTodoTaskTable(){
		Map<String,String> model = new HashMap<String, String>();
		return new ModelAndView("firstPage/messageCenterTable",model);
	}
	/**
	 * 
	 * 首页公告模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年9月13日 下午5:59:21
	 * @lastModified
	 */
	
	
	@RequestMapping("/noticeTable")
	public ModelAndView getNoticeTable(){
		Map<String,String> model = new HashMap<String, String>();
		return new ModelAndView("firstPage/noticeTable",model);
	}
	/**
	 * 
	 * 首页发电量统计模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年9月13日 下午5:59:21
	 * @lastModified
	 */
	
	
	@RequestMapping("/findEnergyCountEchart")
	public ModelAndView getFindEnergyCountEchart(){
		Map<String,String> model = new HashMap<String, String>();
		return new ModelAndView("firstPage/findEnergyCountEchart",model);
	}
	/**
	 * 
	 * 首页消缺率模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年9月13日 下午5:59:21
	 * @lastModified
	 */
	
	
	@RequestMapping("/findScaleEchart")
	public ModelAndView getFindScaleEchart(){
		Map<String,String> model = new HashMap<String, String>();
		return new ModelAndView("firstPage/findScaleEchart",model);
	}
	/**
	 * 
	 * 首页消缺类型占比模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年9月13日 下午5:59:21
	 * @lastModified
	 */
	
	
	@RequestMapping("/findSolveScaleEchart")
	public ModelAndView getFindSolveScaleEchart(){
		Map<String,String> model = new HashMap<String, String>();
		return new ModelAndView("firstPage/findSolveScaleEchart",model);
	}
	/**
	 * 
	 * 日历的模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年11月3日 下午5:59:21
	 * @lastModified
	 */
	@RequestMapping(value="/calendarPage")
	public ModelAndView getCalendarPage(){
		Map<String, String> model = new HashMap<String, String>();
		
		return new ModelAndView("login/calendarPage", model);
	}
	/**
	 * 
	 * 日历的模块defectStatisticsEchart
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年11月3日 下午5:59:21
	 * @lastModified
	 */
	@RequestMapping(value="/firstPageCalendar")
	public ModelAndView getFirstPageCalendar(){
		Map<String, String> model = new HashMap<String, String>();
		
		return new ModelAndView("firstPage/firstPageCalendar", model);
	}
	/**
	 * 
	 * 缺陷统计模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author songyi
	 * @created 2017年11月3日 下午5:59:21
	 * @lastModified
	 */
	@RequestMapping(value="/defectStatisticsEchart")
	public ModelAndView getDefectStatisticsEchart(){
		Map<String, String> model = new HashMap<String, String>();
		
		return new ModelAndView("firstPage/defectStatisticsEchart", model);
	}
	
	/**
	 * 
	 * 公司动态模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author wangyue
	 * @created 2018年7月4日 下午1:55:21
	 * @lastModified
	 */
	@RequestMapping(value="/companyDynamicTable")
	public ModelAndView getCompanyDynamicTable(){
		Map<String, String> model = new HashMap<String, String>();
		
		return new ModelAndView("firstPage/companyDynamicTable", model);
	}
}
