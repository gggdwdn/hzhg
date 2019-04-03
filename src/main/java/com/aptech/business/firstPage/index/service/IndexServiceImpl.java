package com.aptech.business.firstPage.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.firstPage.index.dao.IndexDao;
import com.aptech.business.firstPage.index.domain.IndexEntity;
import com.aptech.business.firstPage.indexCollocation.domain.IndexCollocationEntity;
import com.aptech.business.firstPage.indexCollocation.service.IndexCollocationService;
import com.aptech.framework.orm.AbstractBaseEntityOperation;
import com.aptech.framework.orm.IBaseEntityOperation;

/**
 * 
 * 首页配置应用管理服务实现类
 *
 * @author 
 * @created 2018-02-26 16:08:34
 * @lastModified 
 * @history
 *
 */
@Service("indexService")
@Transactional
public class IndexServiceImpl extends AbstractBaseEntityOperation<IndexEntity> implements IndexService {
	
	@Autowired
	private IndexDao indexDao;
	@Autowired
	private IndexCollocationService indexCollocationService;
	
	@Override
	public IBaseEntityOperation<IndexEntity> getDao() {
		return indexDao;
	}
	
	@Override
	public void addEntity(IndexEntity t) {
		if(t.getId()!=null){
			indexDao.updateEntity(t);
		}else{
			indexDao.addEntity(t);
		}
		indexCollocationService.deleteByCondition("deleteByIndexId", t.getId());
		List<IndexCollocationEntity> indexCollocationList = t.getCollocationList();
		for(IndexCollocationEntity indexCollocationEntity : indexCollocationList){
			indexCollocationEntity.setIndexId(t.getId());
			indexCollocationService.addEntity(indexCollocationEntity);
		}
	}
}