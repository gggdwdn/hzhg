package com.aptech.business.companyDynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.companyDynamic.dao.CompanyDynamicDao;
import com.aptech.business.companyDynamic.domain.CompanyDynamicEntity;
import com.aptech.framework.orm.AbstractBaseEntityOperation;
import com.aptech.framework.orm.IBaseEntityOperation;

/**
 * 
 * 公告应用管理服务实现类
 *
 * @author 
 * @created 2017-10-10 17:20:46
 * @lastModified 
 * @history
 *
 */
@Service("companyDynamicService")
@Transactional
public class CompanyDynamicServiceImpl extends AbstractBaseEntityOperation<CompanyDynamicEntity> implements CompanyDynamicService {
	
	@Autowired
	private CompanyDynamicDao companyDynamicDao;
	
	@Override
	public IBaseEntityOperation<CompanyDynamicEntity> getDao() {
		return companyDynamicDao;
	}
}