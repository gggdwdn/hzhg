package com.aptech.business.notice.domain;

import org.apache.ibatis.type.Alias;

import com.aptech.framework.orm.BaseEntity;

/**
 * 
 * 公告实体类
 *
 * @author 
 * @created 2017-10-10 17:20:46
 * @lastModified 
 * @history
 *
 */
@Alias("NoticeEntity")
public class NoticeEntity extends BaseEntity{
		/**
		 * 主键
		 */
    	private Long id;
		/**
		 * 标题
		 */
    	private String title;
		/**
		 * 公告内容
		 */
    	private String content;
		/**
		 * 发布时间
		 */
    	private String publishDate;
		/**
		 * 发布人
		 */
    	private String publisher;

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
		public String getContent(){
			return content;
		}
		public void setContent(String content){
			this.content = content;
		}
	    /*@JsonSerialize(using = JsonDateTimeSerializer.class)
		public String getPublishDate(){
			return publishDate;
		}
	    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
		public void setPublishDate(String publishDate){
			this.publishDate = publishDate;
		}*/
		public String getPublisher(){
			return publisher;
		}
		public String getPublishDate() {
			return publishDate;
		}
		public void setPublishDate(String publishDate) {
			this.publishDate = publishDate;
		}
		public void setPublisher(String publisher){
			this.publisher = publisher;
		}
}