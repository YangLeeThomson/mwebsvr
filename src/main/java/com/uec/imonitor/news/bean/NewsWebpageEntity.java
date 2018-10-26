package com.uec.imonitor.news.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uec.imonitor.common.base.BaseEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 爬取的新闻实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="news_webpage")
public class NewsWebpageEntity extends BaseEntity{
	
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="webpage_code")
	private String webpageCode;    //新闻编码
	
	@Column(name="news_type")
	private Integer newsType;    //类型   1=网页、2=微博、3=微信等
	
	@Column(name="news_section")
	private String newsSection;    //新闻发布板块
	
	@Column(name="webpage_url")
	private String webpageUrl;    //新闻url
	
	@Column(name="title")
	private String title;    //新闻标题
	
	@Column(name="keywords")
	private String keywords;  //关键词
	
	@Column(name="source_report")
	private String sourceReport;  //发稿来源
	
	@Column(name="source_crawl")
	private String sourceCrawl;  //抓取来源
	
	@Column(name="region")
	private Integer region;  //区域
	
	@Column(name="classification")
	private String classification;  //新闻分类,来源网站自身
	
	@Column(name="author")
	private String author;      //作者
	
	@Column(name="crawl_datetime")
	private Date crawlDatetime;          //抓取时间，索引
	
	@Column(name="release_datetime_str")
	private String releaseDatetimeStr;       //解析后的string类型的新闻发布时间或新闻创建时间
	
	@Column(name="release_datetime")
	private Date releaseDatetime;       //转换格式后的新闻发布时间或新闻创建时间，若无或转换异常，以爬取时间为准，索引
	
	@Column(name="update_datetime")
	private Date updateDatetime;         //更新时间
	
	@Column(name="is_deleted")
	private Integer isDeleted;      //是否删除,0没删，1删除
	
	@Column(name="weibo_id")
	private String weiboId;         //微博id
	
	@Column(name="weibo_pid")
	private String weiboPid;         //微博父节点id
	
	@Column(name="weibo_root_id")
	private String weiboRootId;         //根微博id
	
	@Column(name="pic_path")
	private String picPath;         //新闻截图路径
	
	@Column(name="pic_datetime")
	private Date picDatetime;         //新闻截图时间
	
	@Column(name="subtitle")
	private String subtitle;  //新闻副标题

	public String getWebpageCode() {
		return webpageCode;
	}

	public void setWebpageCode(String webpageCode) {
		this.webpageCode = webpageCode;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSourceReport() {
		return sourceReport;
	}

	public void setSourceReport(String sourceReport) {
		this.sourceReport = sourceReport;
	}

	public String getSourceCrawl() {
		return sourceCrawl;
	}

	public void setSourceCrawl(String sourceCrawl) {
		this.sourceCrawl = sourceCrawl;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCrawlDatetime() {
		return crawlDatetime;
	}

	public void setCrawlDatetime(Date crawlDatetime) {
		this.crawlDatetime = crawlDatetime;
	}

	public String getReleaseDatetimeStr() {
		return releaseDatetimeStr;
	}

	public void setReleaseDatetimeStr(String releaseDatetimeStr) {
		this.releaseDatetimeStr = releaseDatetimeStr;
	}

	public Date getReleaseDatetime() {
		return releaseDatetime;
	}

	public void setReleaseDatetime(Date releaseDatetime) {
		this.releaseDatetime = releaseDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}

	public String getWeiboPid() {
		return weiboPid;
	}

	public void setWeiboPid(String weiboPid) {
		this.weiboPid = weiboPid;
	}

	public String getWeiboRootId() {
		return weiboRootId;
	}

	public void setWeiboRootId(String weiboRootId) {
		this.weiboRootId = weiboRootId;
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

	public String getNewsSection() {
		return newsSection;
	}

	public void setNewsSection(String newsSection) {
		this.newsSection = newsSection;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	

}
