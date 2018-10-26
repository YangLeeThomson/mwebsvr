package com.uec.imonitor.request.bean;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import com.uec.imonitor.common.base.BaseEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 需求新闻展示实体，es更新实体 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class RequestNewsShow extends BaseEntity{
	
	public RequestNewsShow(){
		
	}
	
	public RequestNewsShow(RequestNewsEntity reqNews){
		BeanUtils.copyProperties(reqNews, this);
		super.setEsPrimaryId(String.valueOf(reqNews.getNewsId()));
	}
	@Id//指明这个属性映射为数据库的主键
	private Integer newsId;   
	
	private Integer requestId;   //需求id
	
	private String webpageCode;    //新闻编码
	
	private String originalCode;    //新闻原编码
	
	private String webpageUrl;    //新闻url
	
	private String title;    //新闻标题
	
	private String content;  //正文
	
	private String classification;    //新闻分类     政治、文化、娱乐等
	
	private Integer newsType;    //类型   1=网页、2=微博、3=微信等
	
	private String newsAuthor;    //新闻作者
	
	private String newsSource;    //新闻所属网站
	
	private String newsSection;    //新闻所属板块
	
	private Date reportDatetime;  //新闻发布时间
	
	private Date startDatetime;  //开始时间
	
	private Date endDatetime;  //结束时间
	
	
	private Integer isDeleted;    //是否删除   0未删除   1删除
	
	private String picPath;    //新闻截图路径
	
	private Date picDatetime;  //截图时间
	
	private String subtitle;  //新闻副标题
	
	private String newsSectionId;  //新闻所属板块ID
	
	private String newsTypeName; //新闻类型名称
	
	private Integer wordCount;   //字数
	
	private Integer reprintCount;  //被转载次数

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getWebpageCode() {
		return webpageCode;
	}

	public void setWebpageCode(String webpageCode) {
		this.webpageCode = webpageCode;
	}

	public String getOriginalCode() {
		return originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}

	public String getWebpageUrl() {
		return webpageUrl;
	}

	public void setWebpageUrl(String webpageUrl) {
		this.webpageUrl = webpageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getNewsSection() {
		return newsSection;
	}

	public void setNewsSection(String newsSection) {
		this.newsSection = newsSection;
	}

	public Date getReportDatetime() {
		return reportDatetime;
	}

	public void setReportDatetime(Date reportDatetime) {
		this.reportDatetime = reportDatetime;
	}

	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Date getPicDatetime() {
		return picDatetime;
	}

	public void setPicDatetime(Date picDatetime) {
		this.picDatetime = picDatetime;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getNewsSectionId() {
		return newsSectionId;
	}

	public void setNewsSectionId(String newsSectionId) {
		this.newsSectionId = newsSectionId;
	}

	public String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReprintCount() {
		return reprintCount;
	}

	public void setReprintCount(Integer reprintCount) {
		this.reprintCount = reprintCount;
	}
	
	
}
