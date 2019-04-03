package com.aptech.business.firstPage.column.dao;

import org.springframework.stereotype.Repository;

import com.aptech.business.firstPage.column.domain.ColumnEntity;
import com.aptech.framework.orm.AncestorDao;

/**
 * 
 * 栏目管理应用数据类
 *
 * @author 
 * @created 2018-02-26 16:08:17
 * @lastModified 
 * @history
 *
 */
@Repository("columnDao")
public class ColumnDaoImpl extends AncestorDao<ColumnEntity> implements ColumnDao{
	
	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "com.aptech.business.column";
	}
}
