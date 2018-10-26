package com.uec.imonitor.tenant.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="tenant")
public class TenantEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="tenant_id",nullable=false)
	private Integer tenantId;   //租户id
	
	@Column(name="tenant_name")
	private String tenantName;    //租户名称
	
	@Column(name="company")
	private String company;  //公司名称
	 
	@Column(name="crawl_freq")
	private Integer crawlFreq;  //租户默认采集频率
	
	@Column(name="create_datetime")
	private Date createDatetime;  //创建时间
	
	@Column(name="crawl_days")
	private Integer crawlDays;   //每篇新闻监控时长，单位，天
	
	@Column(name="reprint_name")
	private String reprintName;  //转载标识名称，标识本租户被转载时的转载名称，可能有多个，逗号分隔
	
	@Column(name="tenant_mark")
	private String tenantMark;  //租户系统标识
	 
	
	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public Integer getCrawlDays() {
		return crawlDays;
	}

	public void setCrawlDays(Integer crawlDays) {
		this.crawlDays = crawlDays;
	}

	public String getReprintName() {
		return reprintName;
	}

	public void setReprintName(String reprintName) {
		this.reprintName = reprintName;
	}

	public String getTenantMark() {
		return tenantMark;
	}

	public void setTenantMark(String tenantMark) {
		this.tenantMark = tenantMark;
	}
	

}
