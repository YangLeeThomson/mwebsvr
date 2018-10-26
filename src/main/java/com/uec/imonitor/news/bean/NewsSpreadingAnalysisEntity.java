package com.uec.imonitor.news.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻传播分析实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="news_spreading_analysis")
public class NewsSpreadingAnalysisEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="innerid",nullable=false)
	private Integer innerid;   
	
	@Column(name="webpage_code")
	private String webpageCode;    //新闻编码
	
	@Column(name="request_id")
	private Integer requestId;    //用户需求ID
	
	@Column(name="news_id")
	private Integer newsId;    //监控新闻id
	
	@Column(name="title")
	private String title;    //新闻标题
	
	@Column(name="req_news_title")
	private String reqNewsTitle;    //原新闻标题
	
	@Column(name="status")
	private Integer status;    //0未标识转载，版权存疑，1标识转载
	
	@Column(name="is_deleted")
	private Integer isDeleted;    //是否删除   0未删除   1删除
	
	@Column(name="reprint_type")
	private Integer reprintType;    //转载类型
	
	@Column(name="create_datetime")
	private Date createDatetime;  //创建时间
	
	@Column(name="update_datetime")
	private Date updateDatetime;  //更新时间
	
	@Column(name="title_similarity")
	private Double titleSimilarity;  //标题相似度
	
	@Column(name="content_similarity")
	private Double contentSimilarity;  //正文相似度

	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

	public String getWebpageCode() {
		return webpageCode;
	}

	public void setWebpageCode(String webpageCode) {
		this.webpageCode = webpageCode;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReprintType() {
		return reprintType;
	}

	public void setReprintType(Integer reprintType) {
		this.reprintType = reprintType;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Double getTitleSimilarity() {
		return titleSimilarity;
	}

	public void setTitleSimilarity(Double titleSimilarity) {
		this.titleSimilarity = titleSimilarity;
	}

	public Double getContentSimilarity() {
		return contentSimilarity;
	}

	public void setContentSimilarity(Double contentSimilarity) {
		this.contentSimilarity = contentSimilarity;
	}

	public String getReqNewsTitle() {
		return reqNewsTitle;
	}

	public void setReqNewsTitle(String reqNewsTitle) {
		this.reqNewsTitle = reqNewsTitle;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	
}
