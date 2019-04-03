package com.aptech.business.firstPage.indexCollocation.domain;

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
@Alias("IndexCollocationEntity")
public class IndexCollocationEntity extends BaseEntity{
		/**
		 * 主键
		 */
    	private Long id;
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
    	private Integer columnSpan;
		/**
		 * 行高度
		 */
    	private Integer height;
		/**
		 * 栏目id
		 */
    	private Long columnId;
		/**
		 * 首页id
		 */
    	private Long indexId;
    	//栏目信息
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
    	/**
    	 * 栏目接口地址
    	 */
    	private String implementUrl;
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id = id;
		}
		public Integer getRow(){
			return row;
		}
		public void setRow(Integer row){
			this.row = row;
		}
		public Integer getColumn(){
			return column;
		}
		public void setColumn(Integer column){
			this.column = column;
		}
		public Integer getColumnSpan(){
			return columnSpan;
		}
		public void setColumnSpan(Integer columnSpan){
			this.columnSpan = columnSpan;
		}
		public Integer getHeight(){
			return height;
		}
		public void setHeight(Integer height){
			this.height = height;
		}
		public Long getColumnId(){
			return columnId;
		}
		public void setColumnId(Long columnId){
			this.columnId = columnId;
		}
		public Long getIndexId(){
			return indexId;
		}
		public void setIndexId(Long indexId){
			this.indexId = indexId;
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
		public String getMoreUrl() {
			return moreUrl;
		}
		public void setMoreUrl(String moreUrl) {
			this.moreUrl = moreUrl;
		}
		public String getHeadIcon() {
			return headIcon;
		}
		public void setHeadIcon(String headIcon) {
			this.headIcon = headIcon;
		}
		public String getImages() {
			return images;
		}
		public void setImages(String images) {
			this.images = images;
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
		
}