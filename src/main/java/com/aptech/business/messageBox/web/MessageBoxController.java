package com.aptech.business.messageBox.web;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.business.messageBox.domain.MessageBoxEntity;
import com.aptech.business.messageBox.service.MessageBoxService;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.common.web.base.ResultListObj;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.orm.OrmUtil;
import com.aptech.framework.orm.Page;
import com.aptech.framework.orm.Sort;
import com.aptech.framework.orm.search.Condition;
import com.aptech.framework.util.JsonUtil;
import com.aptech.framework.web.base.PageUtil;

/**
 * 
 * 收件箱配置控制器
 *
 * @author 
 * @created 2017-08-16 09:59:57
 * @lastModified 
 * @history
 *
 */
@Controller
@RequestMapping("/messageBox")
public class MessageBoxController extends BaseController<MessageBoxEntity> {
	
	@Autowired
	private MessageBoxService messageBoxService;
	
	@Override
	public IBaseEntityOperation<MessageBoxEntity> getService() {
		return messageBoxService;
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
		List<MessageBoxEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("messageBoxTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboMessageBoxVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("messageBoxCombobox", JsonUtil.toJson(comboMessageBoxVO.getOptions()));
		return this.createModelAndView("messageBox/resource/views/messageBoxList", model);
	}
	
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/getAdd")
	public ModelAndView getAddPage(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();
		List<MessageBoxEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("messageBoxTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboMessageBoxVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("messageBoxCombobox", JsonUtil.toJson(comboMessageBoxVO.getOptions()));
		
		return this.createModelAndView("messageBox/resource/views/messageBoxAdd", model);
	}
	
	/**
	 *	跳转到修改页面
	 */
	@RequestMapping("/getEdit/{id}")
	public ModelAndView getEditPage(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		MessageBoxEntity messageBoxEntity = (MessageBoxEntity)messageBoxService.findById(id);
		model.put("entity", messageBoxEntity);
		model.put("entityJson", JsonUtil.toJson(messageBoxEntity));
		
		List<MessageBoxEntity> treeNodeList = null; 
		//TODO下拉树具体内容根据具体业务定制
		model.put("messageBoxTreeList", JsonUtil.toJson(treeNodeList));
		ComboboxVO comboMessageBoxVO = new ComboboxVO();
		//TODO下拉框具体内容根据具体业务定制
		model.put("messageBoxCombobox", JsonUtil.toJson(comboMessageBoxVO.getOptions()));
		
		return this.createModelAndView("messageBox/resource/views/messageBoxEdit", model);
	}
}