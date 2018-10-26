package com.uec.imonitor.request.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.uec.imonitor.common.base.BaseEntity;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 被监控新闻实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="request_news")
public class RequestNewsEntity  extends BaseEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="news_id",nullable=false)
	private Integer newsId;   
	
	@Column(name="request_id")
	private Integer requestId;   //需求id
	
	@Column(name="webpage_code")
	private String webpageCode;    //新闻编码
	
	@Column(name="original_code")
	private String originalCode;    //新闻原编码
	
	@Column(name="webpage_url")
	private String webpageUrl;    //新闻url
	
	@Column(name="title")
	private String title;    //新闻标题
	
	@Column(name="content")
	private String content;    //新闻正文
	
	@Column(name="classification")
	private String classification;    //新闻分类     政治、文化、娱乐等
	
	@Column(name="news_type")
	private Integer newsType;    //类型   1=网页、2=微博、3=微信等
	
	@Column(name="news_author")
	private String newsAuthor;    //新闻作者
	
	@Column(name="news_source")
	private String newsSource;    //新闻所属网站
	
	@Column(name="news_section")
	private String newsSection;    //新闻所属板块
	
	@Column(name="report_datetime")
	private Date reportDatetime;  //新闻发布时间
	
	@Column(name="create_datetime")
	private Date createDatetime;  //创建时间
	
	@Column(name="start_datetime")
	private Date startDatetime;  //开始时间
	
	@Column(name="end_datetime")
	private Date endDatetime;  //结束时间
	
	@Column(name="comment")
	private String comment;    //备注
	
	@Column(name="is_deleted")
	private Integer isDeleted;    //是否删除   0未删除   1删除
	
	@Column(name="pic_path")
	private String picPath;    //新闻截图路径
	
	@Column(name="pic_datetime")
	private Date picDatetime;  //截图时间
	
	@Column(name="subtitle")
	private String subtitle;  //新闻副标题
	
	@Column(name="news_section_id")
	private String newsSectionId;  //新闻所属板块ID
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="news_id")
	private List<NewsSpreadingAnalysisEntity> reprintNewsList;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
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


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getOriginalCode() {
		return originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
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

	public List<NewsSpreadingAnalysisEntity> getReprintNewsList() {
		return reprintNewsList;
	}

	public void setReprintNewsList(List<NewsSpreadingAnalysisEntity> reprintNewsList) {
		this.reprintNewsList = reprintNewsList;
	}
	
	
}
