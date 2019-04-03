package com.aptech.business.home.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.common.system.role.domain.SysRoleEntity;
import com.aptech.common.system.unit.domain.SysUnitEntity;
import com.aptech.common.system.user.domain.SysUserEntity;
import com.aptech.common.web.ComboboxVO;
import com.aptech.common.web.base.BaseController;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.util.JsonUtil;

@Controller
@RequestMapping("/home")
public class HomeController  extends BaseController<SysUserEntity>{

	@Override
	public IBaseEntityOperation<SysUserEntity> getService() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> model = new HashMap<String, Object>();        
		return new ModelAndView("home/index", model);
	}
	
}
