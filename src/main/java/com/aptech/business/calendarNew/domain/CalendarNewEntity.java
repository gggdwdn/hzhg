package com.aptech.business.calendarNew.domain;

import org.apache.ibatis.type.Alias;
import com.aptech.framework.orm.BaseEntity;

/**
 * 
 * 首页日历新表实体类
 *
 * @author 
 * @created 2018-08-09 09:38:02
 * @lastModified 
 * @history
 *
 */
@Alias("CalendarNewEntity")
public class CalendarNewEntity extends BaseEntity{
		/**
		 * 主键
		 */
    	private Long id;
		/**
		 * 内容
		 */
    	private String detail;

		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id = id;
		}
		public String getDetail(){
			return detail;
		}
		public void setDetail(String detail){
			this.detail = detail;
		}
}