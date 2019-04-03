package com.aptech.business.firstPage.column.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.component.dictionary.ColumnIndexTypeEnum;
import com.aptech.business.firstPage.column.dao.ColumnDao;
import com.aptech.business.firstPage.column.domain.ColumnEntity;
import com.aptech.business.firstPage.column.exception.ColumnException;
import com.aptech.business.firstPage.column.exception.ColumnExceptionType;
import com.aptech.business.firstPage.indexCollocation.domain.IndexCollocationEntity;
import com.aptech.business.firstPage.indexCollocation.service.IndexCollocationService;
import com.aptech.framework.orm.AbstractBaseEntityOperation;
import com.aptech.framework.orm.IBaseEntityOperation;
import com.aptech.framework.orm.search.Condition;
import com.aptech.framework.orm.search.FieldTypeEnum;
import com.aptech.framework.orm.search.MatchTypeEnum;

/**
 * 
 * 栏目管理应用管理服务实现类
 *
 * @author 
 * @created 2018-02-26 16:08:17
 * @lastModified 
 * @history
 *
 */
@Service("columnService")
@Transactional
public class ColumnServiceImpl extends AbstractBaseEntityOperation<ColumnEntity> implements ColumnService {
	
	@Autowired
	private ColumnDao columnDao;
	@Autowired
	private IndexCollocationService indexCollocationService;
	
	@Override
	public IBaseEntityOperation<ColumnEntity> getDao() {
		return columnDao;
	}
	
	/**
	 * 添加实体
	 * 
	 * @param t
	 */
	@Override
	public void addEntity(ColumnEntity t){
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("title", FieldTypeEnum.STRING, MatchTypeEnum.EQ,t.getTitle()));
		List<ColumnEntity> titleColumnList = findByCondition(conditions, null);
		if(titleColumnList!=null && titleColumnList.size()>0){
			throw new ColumnException(ColumnExceptionType.COLUMN_CODE_TITLE);
		}
		conditions.clear();
		conditions.add(new Condition("url", FieldTypeEnum.STRING, MatchTypeEnum.EQ,t.getUrl()));
		List<ColumnEntity> urlColumnList = findByCondition(conditions, null);
		if(urlColumnList!=null && urlColumnList.size()>0){
			throw new ColumnException(ColumnExceptionType.COLUMN_CODE_URL);
		}
		super.addEntity(t);
	}

	/**
	 * 删除实体
	 * 
	 * @param t
	 */
	@Override
	public void updateEntity(ColumnEntity t){
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("title", FieldTypeEnum.STRING, MatchTypeEnum.EQ,t.getTitle()));
		conditions.add(new Condition("id", FieldTypeEnum.LONG, MatchTypeEnum.NE,t.getId()));
		List<ColumnEntity> titleColumnList = findByCondition(conditions, null);
		if(titleColumnList!=null && titleColumnList.size()>0){
			throw new ColumnException(ColumnExceptionType.COLUMN_CODE_TITLE);
		}
		conditions.clear();
		conditions.add(new Condition("url", FieldTypeEnum.STRING, MatchTypeEnum.EQ,t.getUrl()));
		conditions.add(new Condition("id", FieldTypeEnum.LONG, MatchTypeEnum.NE,t.getId()));
		List<ColumnEntity> urlColumnList = findByCondition(conditions, null);
		if(urlColumnList!=null && urlColumnList.size()>0){
			throw new ColumnException(ColumnExceptionType.COLUMN_CODE_URL);
		}
		super.updateEntity(t);
	}

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	@Override
	public void deleteEntity(Serializable id){
		validateColumn(id);
		super.deleteEntity(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @author Administrator
	 * @created 2012-8-28 上午6:20:16
	 */
	@Override
	public void bulkDelete(Serializable[] ids){
		for(Serializable id :ids){
			validateColumn(id);
		}
		super.bulkDelete(ids);
	}
	
	private void validateColumn(Serializable id){
		ColumnEntity columnEnity = this.findById(id);
		if(ColumnIndexTypeEnum.HAVEBACKGROUNDTABLE.getCode().equals(columnEnity.getType())
				|| ColumnIndexTypeEnum.NOTICE.getCode().equals(columnEnity.getType())
				|| ColumnIndexTypeEnum.MESSAGE.getCode().equals(columnEnity.getType())
				||ColumnIndexTypeEnum.CALENDAR.getCode().equals(columnEnity.getType())){
			throw new ColumnException(ColumnExceptionType.COLUMN_CODE_FIXED);
		}
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition("t.C_COLUMN_ID", FieldTypeEnum.LONG, MatchTypeEnum.EQ,id));
		List<IndexCollocationEntity> indexCollocationList = indexCollocationService.findByCondition(conditions, null);
		if(indexCollocationList!=null && indexCollocationList.size()>0){
			throw new ColumnException(ColumnExceptionType.COLUMN_CODE_USED);
		}
		
	}
}