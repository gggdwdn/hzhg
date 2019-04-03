package com.aptech.business.firstPage.column.domain;

import org.apache.ibatis.type.Alias;

import com.aptech.business.component.dictionary.ColumnIndexTypeEnum;
import com.aptech.framework.orm.BaseEntity;

/**
 * 
 * 栏目管理实体类
 *
 * @author 
 * @created 2018-02-26 16:08:17
 * @lastModified 
 * @history
 *
 */
@Alias("ColumnEntity")
public class ColumnEntity extends BaseEntity{
		/**
		 * 主键
		 */
    	private Long id;
		/**
		 * 标题
		 */
    	private String title;
		/**
		 * 类型
		 */
    	private String type;
		/**
		 * 标题后链接
		 */
    	private String moreUrl;
		/**
		 * 标题图标
		 */
    	private String headIcon;
		/**
		 * 轮播图地址
		 */
    	private String images;
		/**
		 * 广告链接地址
		 */
    	private String url;

    	/**
    	 * 广告图片地址
    	 */
    	private String advertUrl;
    	
    	private String typeString;
    	
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id = id;
		}
		public String getTitle(){
			return title;
		}
		public void setTitle(String title){
			this.title = title;
		}
		public String getType(){
			return type;
		}
		public void setType(String type){
			this.type = type;
		}
		public String getMoreUrl(){
			return moreUrl;
		}
		public void setMoreUrl(String moreUrl){
			this.moreUrl = moreUrl;
		}
		public String getHeadIcon(){
			return headIcon;
		}
		public void setHeadIcon(String headIcon){
			this.headIcon = headIcon;
		}
		public String getImages(){
			return images;
		}
		public void setImages(String images){
			this.images = images;
		}
		public String getUrl(){
			return url;
		}
		public void setUrl(String url){
			this.url = url;
		}
		public String getAdvertUrl() {
			return advertUrl;
		}
		public void setAdvertUrl(String advertUrl) {
			this.advertUrl = advertUrl;
		}
		public String getTypeString() {
			for (ColumnIndexTypeEnum columnIndexTypeEnum : ColumnIndexTypeEnum.values()) {
				if (columnIndexTypeEnum.getCode().equals(this.type)) {
					return columnIndexTypeEnum.getName();
				}
			}
			return typeString;
		}
}