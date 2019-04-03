package com.aptech.business.notice.dao;

import org.springframework.stereotype.Repository;

import com.aptech.business.notice.domain.NoticeEntity;
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
@Repository("noticeDao")
public class NoticeDaoImpl extends AncestorDao<NoticeEntity> implements NoticeDao{
	
	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "com.aptech.business.notice";
	}
}
