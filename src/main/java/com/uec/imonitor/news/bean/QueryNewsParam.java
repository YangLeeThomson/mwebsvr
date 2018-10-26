package com.uec.imonitor.news.bean;

import java.util.Date;
import java.util.List;

import com.uec.imonitor.es.bean.params.AggsTermParams;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 转载新闻请求参数 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class QueryNewsParam {
	
	private Integer requestId;
	
	private List<Integer> newsIds;     //被监控新闻id
	
	private Integer reprintType;     //转载类型
	
	private Integer status;      //状态     0未标识转载，版权存疑，1标识转载
	
	private String sourceCrawl;    //爬取来源单位
	
	private String queryStr;    //搜索字符串    （标题或webpageCode）
	
	private Date startTime;
	
	private Date endTime;
	
	private String sortField;   //排序字段    
	
	private String sortType;    //排序类型       
	
	private Integer pageStart;	//从第几条开始
	
	private Integer pageSize;       //查询多少条
	
	private AggsTermParams aggsParams;  //聚合查询

	private Date reportStartTime; //原始新闻发布开始时间
	
	private Date reportEndTime;//原始新闻发布结束时间
	
	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getReprintType() {
		return reprintType;
	}

	public void setReprintType(Integer reprintType) {
		this.reprintType = reprintType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public AggsTermParams getAggsParams() {
		return aggsParams;
	}

	public void setAggsParams(AggsTermParams aggsParams) {
		this.aggsParams = aggsParams;
	}

	public String getSourceCrawl() {
		return sourceCrawl;
	}

	public void setSourceCrawl(String sourceCrawl) {
		this.sourceCrawl = sourceCrawl;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public List<Integer> getNewsIds() {
		return newsIds;
	}

	public void setNewsIds(List<Integer> newsIds) {
		this.newsIds = newsIds;
	}

	public Date getReportStartTime() {
		return reportStartTime;
	}

	public void setReportStartTime(Date reportStartTime) {
		this.reportStartTime = reportStartTime;
	}

	public Date getReportEndTime() {
		return reportEndTime;
	}

	public void setReportEndTime(Date reportEndTime) {
		this.reportEndTime = reportEndTime;
	}
	
	
}
