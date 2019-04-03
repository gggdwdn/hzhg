package com.aptech.business.firstPage.column.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.business.firstPage.column.domain.ColumnEntity;
import com.aptech.business.firstPage.column.service.ColumnService;
import com.aptech.common.system.dictionary.util.DictionaryUtil;
import com.aptech.common.system.dictionary.web.SysDictionaryVO;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.util.JsonUtil;

/**
 * 
 * 栏目管理配置控制器
 *
 * @author 
 * @created 2018-02-26 16:08:17
 * @lastModified 
 * @history
 *
 */
@Controller
@RequestMapping("/column")
public class ColumnController extends BaseController<ColumnEntity> {
	
	@Autowired
	private ColumnService columnService;
	
	@Override
	public IBaseEntityOperation<ColumnEntity> getService() {
		return columnService;
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
		ComboboxVO codeDateTypesCombobox = new ComboboxVO();
		Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("COLUMN_TYPE");
		
		for(String key :  codeDateTypeMap.keySet()){
			SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
			codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
		}
		model.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
		return this.createModelAndView("firstPage/column/columnList", model);
	}
	
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/getAdd")
	public ModelAndView getAddPage(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();
	    ComboboxVO codeDateTypesCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("COLUMN_TYPE");
        
        for(String key :  codeDateTypeMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
        	if(sysDictionaryVO.getId()>4){
            	codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        	}
        }
        model.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
		return this.createModelAndView("firstPage/column/columnAdd", model);
	}
	
	/**
	 *	跳转到修改页面
	 */
	@RequestMapping("/getEdit/{id}")
	public ModelAndView getEditPage(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		ColumnEntity columnEntity = (ColumnEntity)columnService.findById(id);
		model.put("entity", columnEntity);
		model.put("entityJson", JsonUtil.toJson(columnEntity));
		
		ComboboxVO codeDateTypesCombobox = new ComboboxVO();
        Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("COLUMN_TYPE");
        
        for(String key :  codeDateTypeMap.keySet()){
        	SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
        	if(sysDictionaryVO.getId()>4){
            	codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
        	}
        }
        model.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
		return this.createModelAndView("firstPage/column/columnEdit", model);
	}
	/**
	 *	跳转到修改页面
	 */
	@RequestMapping("/getDetail/{id}")
	public ModelAndView getDetail(HttpServletRequest request, @PathVariable Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		// 返回前台数据项
		ColumnEntity columnEntity = (ColumnEntity)columnService.findById(id);
		model.put("entity", columnEntity);
		model.put("entityJson", JsonUtil.toJson(columnEntity));
		
		ComboboxVO codeDateTypesCombobox = new ComboboxVO();
		Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("COLUMN_TYPE");
		
		for(String key :  codeDateTypeMap.keySet()){
			SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
			codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
		}
		model.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));
		return this.createModelAndView("firstPage/column/columnDetail", model);
	}
}