package com.uec.imonitor.news.bean;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.uec.imonitor.common.base.CommonDate;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻内容实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class NewsSpreadingAnalysisDetail extends NewsWebpageEntity {
	
	public NewsSpreadingAnalysisDetail(){
		
	}
	
	public NewsSpreadingAnalysisDetail(NewsWebpageEntity entity){
		BeanUtils.copyProperties(entity, this);
		Date crawlDatetime = this.getCrawlDatetime();
		if(null != crawlDatetime){
			crawlDatetimeDetail = new CommonDate(crawlDatetime);
		}
		
		Date releaseDatetime = this.getReleaseDatetime();
		if(null != releaseDatetime){
			releaseDatetimeDetail = new CommonDate(releaseDatetime);
		}
	}
	/**
	 * 主键
	 */
	private Integer innerid;   
	
	private Integer newsId;    //主键,监控新闻id
	
	private String reqNewsTitle;    //原新闻标题
	
	private Integer status;    //0未标识转载，版权存疑，1标识转载
	
	private Integer reprintType;    //转载类型
	
	private Date createDatetime;  //创建时间
	
	private String statusName; //状态名称
	
	private String reprintTypeName; //转载类型名称
	
	private String newsTypeName;  //新闻类型名称
	
	private String regionName;         //地域名称
	
	private String country;           
	
	private String province;
	
	private String provinceShort;    //省份简称
	
	private String city;
	
	private String cityShort;
	
	private Double titleSimilarity;  //标题相似度
	
	private Double contentSimilarity;  //正文相似度
	
	private Integer wordCount;   //字数
	
	private Integer requestId; //
	
	private CommonDate crawlDatetimeDetail;
	
	private CommonDate releaseDatetimeDetail;
	
	private Date reqReportDatetime ;
	
	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}


	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getReprintTypeName() {
		return reprintTypeName;
	}

	public void setReprintTypeName(String reprintTypeName) {
		this.reprintTypeName = reprintTypeName;
	}

	public String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceShort() {
		return provinceShort;
	}

	public void setProvinceShort(String provinceShort) {
		this.provinceShort = provinceShort;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityShort() {
		return cityShort;
	}

	public void setCityShort(String cityShort) {
		this.cityShort = cityShort;
	}

	public String getReqNewsTitle() {
		return reqNewsTitle;
	}

	public void setReqNewsTitle(String reqNewsTitle) {
		this.reqNewsTitle = reqNewsTitle;
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

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public CommonDate getCrawlDatetimeDetail() {
		return crawlDatetimeDetail;
	}

	public void setCrawlDatetimeDetail(CommonDate crawlDatetimeDetail) {
		this.crawlDatetimeDetail = crawlDatetimeDetail;
	}

	public CommonDate getReleaseDatetimeDetail() {
		return releaseDatetimeDetail;
	}

	public void setReleaseDatetimeDetail(CommonDate releaseDatetimeDetail) {
		this.releaseDatetimeDetail = releaseDatetimeDetail;
	}

	public Date getReqReportDatetime() {
		return reqReportDatetime;
	}

	public void setReqReportDatetime(Date reqReportDatetime) {
		this.reqReportDatetime = reqReportDatetime;
	}

}
