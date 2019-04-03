package com.aptech.business.notice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.business.notice.dao.NoticeDao;
import com.aptech.business.notice.domain.NoticeEntity;
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
@Service("noticeService")
@Transactional
public class NoticeServiceImpl extends AbstractBaseEntityOperation<NoticeEntity> implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public IBaseEntityOperation<NoticeEntity> getDao() {
		return noticeDao;
	}
}