package com.aptech.business.firstPage.indexCollocation.dao;

import org.springframework.stereotype.Repository;

import com.aptech.business.firstPage.indexCollocation.domain.IndexCollocationEntity;
import com.aptech.framework.orm.AncestorDao;

/**
 * 
 * 首页栏目配置应用数据类
 *
 * @author 
 * @created 2018-02-27 15:20:05
 * @lastModified 
 * @history
 *
 */
@Repository("indexCollocationDao")
public class IndexCollocationDaoImpl extends AncestorDao<IndexCollocationEntity> implements IndexCollocationDao{
	
	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "com.aptech.business.indexCollocation";
	}
}
