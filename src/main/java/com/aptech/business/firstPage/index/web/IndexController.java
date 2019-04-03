package com.aptech.business.firstPage.index.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.business.firstPage.column.domain.ColumnEntity;
import com.aptech.business.firstPage.column.service.ColumnService;
import com.aptech.business.firstPage.index.domain.IndexEntity;
import com.aptech.business.firstPage.index.service.IndexService;
import com.aptech.common.system.dictionary.util.DictionaryUtil;
import com.aptech.common.system.dictionary.web.SysDictionaryVO;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.common.workflow.modelEditor.domain.NodeConfigEntity;
import com.aptech.common.workflow.modelEditor.service.NodeConfigService;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.util.JsonUtil;

/**
 * 
 * 首页配置配置控制器
 *
 * @author 
 * @created 2018-02-26 16:08:34
 * @lastModified 
 * @history
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController<IndexEntity> {
	
	@Autowired
	private IndexService indexService;
	@Autowired
	private ColumnService columnService;
	@Autowired
	private NodeConfigService nodeConfigService;
	
	@Override
	public IBaseEntityOperation<IndexEntity> getService() {
		return indexService;
	}
	

	
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/index")
	public ModelAndView getAddPage(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();
		ComboboxVO codeDateTypesCombobox = new ComboboxVO();
		Map<String, SysDictionaryVO> codeDateTypeMap  =  DictionaryUtil.getDictionaries("JUDGE_TYPE");
		
		for(String key :  codeDateTypeMap.keySet()){
			SysDictionaryVO sysDictionaryVO = codeDateTypeMap.get(key);
			codeDateTypesCombobox.addOption(sysDictionaryVO.getCode(), sysDictionaryVO.getName());
		}
		model.put("types", JsonUtil.toJson(codeDateTypesCombobox.getOptions()));	
		
		List<ColumnEntity> columnList= columnService.findAll();
		ComboboxVO columnVO = new ComboboxVO();
		for(ColumnEntity columnEntity : columnList){
			columnVO.addOption(columnEntity.getId().toString(), columnEntity.getTitle());
	    }
		//TODO下拉框具体内容根据具体业务定制
		model.put("columnCombobox", JsonUtil.toJson(columnVO.getOptions()));
		List<IndexEntity> list = indexService.findAll();
		IndexEntity entity = new IndexEntity();
		if(list!=null && list.size()>0){
			entity = list.get(0);
		}
		model.put("entity", entity);
		model.put("entityJson", JsonUtil.toJson(entity));
		return this.createModelAndView("firstPage/index/indexAdd", model);
	}
	
	
}