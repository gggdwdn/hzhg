package com.aptech.business.firstPage.index.dao;

import org.springframework.stereotype.Repository;

import com.aptech.business.firstPage.index.domain.IndexEntity;
import com.aptech.framework.orm.AncestorDao;

/**
 * 
 * 首页配置应用数据类
 *
 * @author 
 * @created 2018-02-26 16:08:34
 * @lastModified 
 * @history
 *
 */
@Repository("indexDao")
public class IndexDaoImpl extends AncestorDao<IndexEntity> implements IndexDao{
	
	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "com.aptech.business.index";
	}
}
