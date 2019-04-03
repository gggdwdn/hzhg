package com.aptech.business.firstPage.indexCollocation.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.business.firstPage.indexCollocation.domain.IndexCollocationEntity;
import com.aptech.business.firstPage.indexCollocation.service.IndexCollocationService;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.util.JsonUtil;

/**
 * 
 * 首页栏目配置配置控制器
 *
 * @author 
 * @created 2018-02-27 15:20:05
 * @lastModified 
 * @history
 *
 */
@Controller
@RequestMapping("/indexCollocation")
public class IndexCollocationController extends BaseController<IndexCollocationEntity> {
	
	@Autowired
	private IndexCollocationService indexCollocationService;
	
	@Override
	public IBaseEntityOperation<IndexCollocationEntity> getService() {
		return indexCollocationService;
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
		List<IndexCollocationEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("indexCollocationTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboIndexCollocationVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("indexCollocationCombobox", JsonUtil.toJson(comboIndexCollocationVO.getOptions()));
		return this.createModelAndView("indexCollocation/resource/views/indexCollocationList", model);
	}
	
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/getAdd")
	public ModelAndView getAddPage(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();
		List<IndexCollocationEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("indexCollocationTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboIndexCollocationVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("indexCollocationCombobox", JsonUtil.toJson(comboIndexCollocationVO.getOptions()));
		
		return this.createModelAndView("indexCollocation/resource/views/indexCollocationAdd", model);
	}
	
	/**
	 *	跳转到修改页面
	 */
	@RequestMapping("/getEdit/{id}")
	public ModelAndView getEditPage(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		IndexCollocationEntity indexCollocationEntity = (IndexCollocationEntity)indexCollocationService.findById(id);
		model.put("entity", indexCollocationEntity);
		model.put("entityJson", JsonUtil.toJson(indexCollocationEntity));
		
		List<IndexCollocationEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("indexCollocationTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboIndexCollocationVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("indexCollocationCombobox", JsonUtil.toJson(comboIndexCollocationVO.getOptions()));
		
		return this.createModelAndView("indexCollocation/resource/views/indexCollocationEdit", model);
	}
}