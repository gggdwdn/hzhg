package com.aptech.business.firstPage.indexCollocation.domain;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.aptech.framework.orm.BaseEntity;

/**
 * 
 * 首页栏目配置实体类
 *
 * @author 
 * @created 2018-02-27 15:20:05
 * @lastModified 
 * @history
 *
 */
@Alias("ColumnShowEntity")
public class ColumnShowEntity extends BaseEntity{
		/**
		 * 行数
		 */
    	private Integer row;
		/**
		 * 列数
		 */
    	private Integer column;
		/**
		 * 所占列数
		 */
    	private Integer cospan;
		/**
		 * 行高度
		 */
    	private Integer height;
    	/**
    	 * divid
    	 */
    	private String render;
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
    	private String more_url;
		/**
		 * 标题图标
		 */
    	private String headIcon;
		/**
		 * 轮播图地址
		 */
    	private List<Map<String,Object>> urlList;
		/**
		 * 栏目接口地址
		 */
    	private String url;
    	/**
    	 * 广告图片地址
    	 */
    	private String advertUrl;
    	/**
    	 * 广告链接地址
    	 */
    	private String implementUrl;
		public Integer getRow() {
			return row;
		}
		public void setRow(Integer row) {
			this.row = row;
		}
		public Integer getColumn() {
			return column;
		}
		public void setColumn(Integer column) {
			this.column = column;
		}
		public Integer getCospan() {
			return cospan;
		}
		public void setCospan(Integer cospan) {
			this.cospan = cospan;
		}
		public Integer getHeight() {
			return height;
		}
		public void setHeight(Integer height) {
			this.height = height;
		}
		public String getRender() {
			return render;
		}
		public void setRender(String render) {
			this.render = render;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getMore_url() {
			return more_url;
		}
		public void setMore_url(String more_url) {
			this.more_url = more_url;
		}
		public String getHeadIcon() {
			return headIcon;
		}
		public void setHeadIcon(String headIcon) {
			this.headIcon = headIcon;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getAdvertUrl() {
			return advertUrl;
		}
		public void setAdvertUrl(String advertUrl) {
			this.advertUrl = advertUrl;
		}
		public String getImplementUrl() {
			return implementUrl;
		}
		public void setImplementUrl(String implementUrl) {
			this.implementUrl = implementUrl;
		}
		public List<Map<String, Object>> getUrlList() {
			return urlList;
		}
		public void setUrlList(List<Map<String, Object>> urlList) {
			this.urlList = urlList;
		}
		
}