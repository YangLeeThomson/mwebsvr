package com.uec.imonitor.request.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户需求实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="request_tenant")
public class TenantRequestEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="request_id",nullable=false)
	private Integer requestId;   //需求id
	
	@Column(name="tenant_id")
	private Integer tenantId;    //租户id
	
	@Column(name="request_name")
	private String requestName;    //需求名称
	
	@Column(name="monitot_type")
	private Integer monitotType;  //监控类型,1传播,2侵权,3传播+侵权
	
	@Column(name="status")
	private Integer status;  //0正常，1禁用
	 
	@Column(name="crawl_freq")
	private Integer crawlFreq;  //采集频率，单位，小时
	
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
	
	@Column(name="crawl_days")
	private Integer crawlDays;   //每篇新闻监控时长，单位，天
	
	@Column(name="reprint_name")
	private String reprintName;  //转载标识名称，标识本租户被转载时的转载名称，可能有多个，逗号分隔

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}


	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public Integer getMonitotType() {
		return monitotType;
	}

	public void setMonitotType(Integer monitotType) {
		this.monitotType = monitotType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCrawlFreq() {
		return crawlFreq;
	}

	public void setCrawlFreq(Integer crawlFreq) {
		this.crawlFreq = crawlFreq;
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

	public String getReprintName() {
		return reprintName;
	}

	public void setReprintName(String reprintName) {
		this.reprintName = reprintName;
	}

	public Integer getCrawlDays() {
		return crawlDays;
	}

	public void setCrawlDays(Integer crawlDays) {
		this.crawlDays = crawlDays;
	}


}
