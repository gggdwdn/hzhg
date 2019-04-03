package com.aptech.business.companyDynamic.dao;

import org.springframework.stereotype.Repository;

import com.aptech.business.companyDynamic.domain.CompanyDynamicEntity;
import com.aptech.framework.orm.AncestorDao;

/**
 * 
 * 公告应用数据类
 *
 * @author 
 * @created 2017-10-10 17:20:46
 * @lastModified 
 * @history
 *
 */
@Repository("companyDynamicDao")
public class CompanyDynamicDaoImpl extends AncestorDao<CompanyDynamicEntity> implements CompanyDynamicDao{
	
	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "com.aptech.business.companyDynamic";
	}
}
