package com.aptech.business.firstPage.indexCollocation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.firstPage.indexCollocation.dao.IndexCollocationDao;
import com.aptech.business.firstPage.indexCollocation.domain.IndexCollocationEntity;
import com.aptech.framework.orm.AbstractBaseEntityOperation;
import com.aptech.framework.orm.IBaseEntityOperation;

/**
 * 
 * 首页栏目配置应用管理服务实现类
 *
 * @author 
 * @created 2018-02-27 15:20:05
 * @lastModified 
 * @history
 *
 */
@Service("indexCollocationService")
@Transactional
public class IndexCollocationServiceImpl extends AbstractBaseEntityOperation<IndexCollocationEntity> implements IndexCollocationService {
	
	@Autowired
	private IndexCollocationDao indexCollocationDao;
	
	@Override
	public IBaseEntityOperation<IndexCollocationEntity> getDao() {
		return indexCollocationDao;
	}
}