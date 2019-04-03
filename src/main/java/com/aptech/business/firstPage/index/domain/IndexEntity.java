package com.aptech.business.firstPage.index.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.aptech.business.firstPage.indexCollocation.domain.IndexCollocationEntity;
import com.aptech.framework.orm.BaseEntity;

/**
 * 
 * 首页配置实体类
 *
 * @author 
 * @created 2018-02-26 16:08:34
 * @lastModified 
 * @history
 *
 */
@Alias("IndexEntity")
public class IndexEntity extends BaseEntity{
		/**
		 * 主键
		 */
    	private Long id;
		/**
		 * 新闻内容
		 */
    	private String news;
		/**
		 * 是否含有链接
		 */
    	private String isUrl;
		/**
		 * 新闻链接地址
		 */
    	private String newsUrl;

    	private List<IndexCollocationEntity> collocationList;
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id = id;
		}
		public String getNews(){
			return news;
		}
		public void setNews(String news){
			this.news = news;
		}
		public String getIsUrl(){
			return isUrl;
		}
		public void setIsUrl(String isUrl){
			this.isUrl = isUrl;
		}
		public String getNewsUrl(){
			return newsUrl;
		}
		public void setNewsUrl(String newsUrl){
			this.newsUrl = newsUrl;
		}
		public List<IndexCollocationEntity> getCollocationList() {
			return collocationList;
		}
		public void setCollocationList(List<IndexCollocationEntity> collocationList) {
			this.collocationList = collocationList;
		}
}