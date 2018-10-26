package com.uec.imonitor.dic.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 网站数据字典 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="dic_website")
public class WebsiteDicEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="website_id",nullable=false)
	private Integer websiteId;   //网站id
	
	@Column(name="name")
	private String name;    //网站名称
	
	@Column(name="domain")
	private String domain;  //域名
	 
	@Column(name="url")
	private String url;  //网站地址
	
	@Column(name="parent_id")
	private Integer parentId;  //上级网站id
	
	@Column(name="status")
	private Integer status;  //状态   0搜索引擎监控，1搜索引擎使用网站域名监控2网站单独监控
	
	@Column(name="description")
	private String description;  //描述
	
	@Column(name="create_datetime")
	private Date createDatetime;  //创建时间
	
	@Column(name="update_datetime")
	private Date updateDatetime;  //更新时间

	public Integer getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
