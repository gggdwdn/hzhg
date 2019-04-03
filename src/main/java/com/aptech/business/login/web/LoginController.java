/*	
 * Web 5.0 Copyright 2016 Aptech, Co.ltd. All rights reserved.
 * 		 
 * FileName: LoginController.java
 *
 */

package com.aptech.business.login.web;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aptech.business.component.dictionary.ColumnIndexTypeEnum;
import com.aptech.business.component.dictionary.SysUnitLevelEnum;
import com.aptech.business.firstPage.index.domain.IndexEntity;
import com.aptech.business.firstPage.index.service.IndexService;
import com.aptech.business.firstPage.indexCollocation.domain.ColumnShowEntity;
import com.aptech.business.firstPage.indexCollocation.domain.IndexCollocationEntity;
import com.aptech.business.firstPage.indexCollocation.service.IndexCollocationService;
import com.aptech.business.util.LunarUtil;
import com.aptech.common.system.config.domain.SysConfigEntity;
import com.aptech.common.system.config.service.SysConfigService;
import com.aptech.common.system.unit.domain.SysUnitEntity;
import com.aptech.common.system.unit.domain.UnitLevelEnum;
import com.aptech.common.system.unit.service.SysUnitService;
import com.aptech.common.system.user.domain.SysUserEntity;
import com.aptech.common.system.user.service.SysUserService;
import com.aptech.common.system.userUnit.domain.UserUnitRelEntity;
import com.aptech.common.system.userUnit.service.UserUnitRelService;
import com.aptech.common.userOperateLog.domain.UserOperateEnum;
import com.aptech.common.userOperateLog.domain.UserOperateModuleEnum;
import com.aptech.common.userOperateLog.service.UserOperateLogService;
import com.aptech.framework.context.HttpContextExtractor;
import com.aptech.framework.context.RequestContext;
import com.aptech.framework.orm.DataStatusEnum;
import com.aptech.framework.orm.search.Condition;
import com.aptech.framework.orm.search.FieldTypeEnum;
import com.aptech.framework.orm.search.MatchTypeEnum;
import com.aptech.framework.util.EncryptUtil;
import com.aptech.framework.util.JsonUtil;
import com.aptech.framework.web.base.ResultObj;

/** 
 * 登录控制器类
 *
 * @author zhangjx
 * @created 2016年11月14日 下午6:15:52 
 * @lastModified 
 * @history
 * 
 */

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
    private SysConfigService sysConfigService;
	@Autowired
	private IndexService indexService;
	@Autowired
	private SysUnitService sysUnitService;
	@Autowired
	private UserUnitRelService userUnitRelService;
	@Autowired
	private IndexCollocationService indexCollocationService;
	@Autowired
	private UserOperateLogService userOperateLogService;
	/**
	 * 样式版本,老版本是default,新版本是default2
	 */
	public final static String THEMEVERSION = "default2";
	/**
	 * 加载主页面,老版本是main,新版本是mainNew
	 */
	public final static String THEMEVERSIONMAIN = "main2";
	/**
	 * 
	 * 登录页面跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author zhangjx
	 * @throws ParseException 
	 * @created 2016年11月18日 下午4:34:42
	 * @lastModified
	 */
	@RequestMapping(value="/login")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws ParseException{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("theme", THEMEVERSION);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		model.put("day", new LunarUtil(calendar).toString());
		return new ModelAndView("login/login", model);
	}
	
	/**
	 * 
	 * 登录验证授权
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangjx
	 * @created 2016年11月18日 下午4:34:58
	 * @lastModified
	 */
	@RequestMapping("/auth")
	public @ResponseBody ResultObj auth(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResultObj resultObj = new ResultObj();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SysUserEntity userEntity = sysUserService.findByLoginName(username,password);
		if(userEntity == null){
			resultObj.setData("用户名或密码不正确");
			resultObj.setResult("error");
		}else{
			if(userEntity.getStatus().equals("2")){
				resultObj.setData("用户已被锁定");
				resultObj.setResult("error");
			}else{
				resultObj.setData(userEntity.getId());
				List<Condition> conditions = new ArrayList<Condition>();
				SysUnitEntity unitEntity = sysUnitService.findById(userEntity.getUnitId());
				userEntity.setUnitName(unitEntity.getName());
//				conditions.add(new Condition("C_USER_ID",FieldTypeEnum.LONG,MatchTypeEnum.EQ,userEntity.getId()));
//				List<UserUnitRelEntity> userRelList = userUnitRelService.findByCondition(conditions, null);
//				List<Long> userUnitIdList = new ArrayList<Long>();
//				if(!userRelList.isEmpty()){
//					for(UserUnitRelEntity userUnitRelEntity : userRelList){
//						userUnitIdList.add(userUnitRelEntity.getUnitId());
//					}
//					conditions.clear();
//					conditions.add(new Condition("C_STATUS", FieldTypeEnum.STRING, MatchTypeEnum.EQ, String.valueOf(DataStatusEnum.NORMAL.ordinal())));	
//					//这句话有带商榷
//					conditions.add(new Condition("C_LEVEL", FieldTypeEnum.STRING, MatchTypeEnum.NE, UnitLevelEnum.STATION.getCode()));
//					conditions.add(new Condition("C_ID",FieldTypeEnum.LONG,MatchTypeEnum.IN,userUnitIdList.toArray()));
//					List<SysUnitEntity> sysUnitEntities = sysUnitService.findByCondition(conditions, null);
//					if(!sysUnitEntities.isEmpty()){
//						userEntity.setUnitId(sysUnitEntities.get(0).getId());
//						userEntity.setUnitName(sysUnitEntities.get(0).getName());
//					}
//				}
				//往字段userUnitRelIds中赋值
				conditions.add(new Condition("C_USER_ID", FieldTypeEnum.LONG, MatchTypeEnum.EQ, userEntity.getId()));
				List<UserUnitRelEntity> userUnitRelList = userUnitRelService.findByCondition(conditions, null);
				if(!userUnitRelList.isEmpty()){
					List<Long> unitIds = new ArrayList<Long>();
					for(UserUnitRelEntity userUnitRelEntity : userUnitRelList){
						unitIds.add(userUnitRelEntity.getUnitId());
					}
					userEntity.setUserUnitRelIds(unitIds);
				}
				HttpContextExtractor.setUser(request.getSession(), userEntity);
				userOperateLogService.addOperateLog(userEntity,UserOperateModuleEnum.SYS.getName(),UserOperateEnum.LOGIN.getName(),"");

			}
		}

		return resultObj;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		SysUserEntity  sysUserEntity= (SysUserEntity)request.getSession().getAttribute("session_key_user");
		userOperateLogService.addOperateLog(sysUserEntity,UserOperateModuleEnum.SYS.getName(),UserOperateEnum.LOGOUT.getName(),"");

		return new ModelAndView("redirect:/login/login");
	}
	
	/**
	 * 
	 * 主页面跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author zhangjx
	 * @created 2016年11月18日 下午4:35:39
	 * @lastModified
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> model = new HashMap<String, String>();
		model.put("theme", THEMEVERSION);
		String uid = request.getParameter("uid");
		model.put("uid", uid);
		List<SysConfigEntity> sysConfigEntityList=sysConfigService.findAll();
		request.getSession().setAttribute("sysConfigEntity", (SysConfigEntity)sysConfigEntityList.get(0));
		return new ModelAndView(THEMEVERSIONMAIN, model);
	}
	
	/**
	 * 
	 * 主页面跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author zhangjx
	 * @created 2016年11月18日 下午4:35:39
	 * @lastModified
	 */
	@RequestMapping(value="/index")
	public ModelAndView indexPage(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> model = new HashMap<String, String>();
		model.put("theme", THEMEVERSION);
		List<SysConfigEntity> sysConfigEntityList=sysConfigService.findAll();
		request.getSession().setAttribute("sysConfigEntity", (SysConfigEntity)sysConfigEntityList.get(0));
		return new ModelAndView("/index/index", model);
	}
	
	/**
	 * 
	 * 验证用户名密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @author zhangjx
	 * @throws Exception 
	 * @created 2016年11月18日 下午3:26:32
	 * @lastModified
	 */
	private boolean checkPassword(String password, String encryptPwd) throws Exception{
		return StringUtils.equals(new BigInteger(EncryptUtil.encryptSHA(password.getBytes())).toString(32),
				encryptPwd);
	}
	
	/**
	 * 
	 * 验证码验证方法
	 * 
	 * @param parameter
	 * @return
	 * @author zhangjx
	 * @created 2016年11月18日 下午3:26:02
	 * @lastModified
	 */
	private boolean checkVerification(String parameter){
		return true;
	}
	/**
	 * 
	 * 首页公告模块
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author liweiran
	 * @created 2017年9月13日 下午5:59:21
	 * @lastModified
	 */
	@RequestMapping(value="/firstPage")
	public ModelAndView getFirstPage(){
		Map<String, Object> model = new HashMap<String, Object>();
		List<IndexEntity> indexList = indexService.findAll();
		model.put("indexEntity", indexList.get(0));
		List<IndexCollocationEntity> indexCollocationList = indexCollocationService.findAll();
		List<ColumnShowEntity> showList = new ArrayList<ColumnShowEntity>();
		List<List<ColumnShowEntity>> resultList = new ArrayList<List<ColumnShowEntity>>();
		int i = 1;
		int maxrows = 1;
		for(IndexCollocationEntity indexCollocationEntity : indexCollocationList){
			if(indexCollocationEntity.getRow()>maxrows){
				maxrows = indexCollocationEntity.getRow();
			}
			ColumnShowEntity columnShowEntity = new ColumnShowEntity();
			columnShowEntity.setAdvertUrl(indexCollocationEntity.getAdvertUrl());
			List<Map<String,Object>> maplist = JsonUtil.jsonToObj(indexCollocationEntity.getImages(),List.class);
			List<Map<String,Object>> urlList  = new ArrayList<Map<String,Object>>();
			if(ColumnIndexTypeEnum.SLIDER.getCode().equals(indexCollocationEntity.getType())){
				String urls = indexCollocationEntity.getUrl();
				
				if(urls!=null && !"".equals(urls)){
					String[] urlsArr = urls.split(",");
					for(int j=0;j<maplist.size();j++){
						Map<String,Object> imgmap = new HashMap<String,Object>();
						imgmap.put("img", (String)maplist.get(j).get("url"));
						imgmap.put("url", urlsArr[j]);
						urlList.add(imgmap);
					}
				}else{
					for(int j=0;j<maplist.size();j++){
						Map<String,Object> imgmap = new HashMap<String,Object>();
						imgmap.put("img", (String)maplist.get(j).get("url"));
						imgmap.put("url", "#");
						urlList.add(imgmap);
					}
				}
				
				columnShowEntity.setUrlList(urlList);
			}
			columnShowEntity.setColumn(indexCollocationEntity.getColumn());
			columnShowEntity.setCospan(indexCollocationEntity.getColumnSpan());
			columnShowEntity.setHeadIcon(indexCollocationEntity.getHeadIcon());
			columnShowEntity.setHeight(indexCollocationEntity.getHeight());
			columnShowEntity.setMore_url(indexCollocationEntity.getMoreUrl());
			columnShowEntity.setRender("render"+i++);
			columnShowEntity.setRow(indexCollocationEntity.getRow());
			columnShowEntity.setTitle(indexCollocationEntity.getTitle());

			if(ColumnIndexTypeEnum.NOTICE.getCode().equals(indexCollocationEntity.getType()) || ColumnIndexTypeEnum.MESSAGE.getCode().equals(indexCollocationEntity.getType())){
				columnShowEntity.setType("noBackgroundTable");
			}else{
				columnShowEntity.setType(indexCollocationEntity.getType());
			}
			columnShowEntity.setUrl(indexCollocationEntity.getUrl());
			showList.add(columnShowEntity);
		}
		for(int j=1;j<=maxrows;j++){
			List<ColumnShowEntity> list = new ArrayList<ColumnShowEntity>();
			for(ColumnShowEntity columnShowEntity:showList){
				if(columnShowEntity.getRow() == j){
					list.add(columnShowEntity);
				}
			}
			resultList.add(list);
		}
		model.put("showList", JsonUtil.toJson(resultList));
		return new ModelAndView("login/firstPage", model);
	}
	/**
	 * 
	 * 安全管理模块跳转
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @throws 
	 * @author liweiran
	 * @created 2017年10月14日 下午6:41:45
	 * @lastModified
	 */
	@RequestMapping(value="/safePage")
	public ModelAndView getSafePage(){
		Map<String, String> model = new HashMap<String, String>();
		return new ModelAndView("error/safe404", model);
	}
}
