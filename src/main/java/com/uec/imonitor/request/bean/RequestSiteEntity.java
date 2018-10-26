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
 * <p>Description: 需求-网站关联实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="request_tenant_site")
public class RequestSiteEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="innerid",nullable=false)
	private Integer innerid;   
	
	@Column(name="request_id")
	private Integer requestId;   //需求id
	
	@Column(name="site_id")
	private Integer siteId;    //网站id
	
	@Column(name="status")
	private Integer status;  //状态    0非核心网站，1核心网站重点关注
	
	@Column(name="create_datetime")
	private Date createDatetime;  //创建时间
	
	@Column(name="update_datetime")
	private Date updateDatetime;  //更新时间
	
	@Column(name="is_deleted")
	private Integer isDeleted;    //是否删除   0未删除   1删除
	
	@Column(name="is_white_list")
	private Integer isWhiteList;    //是否白名单网站，0否，1是

	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsWhiteList() {
		return isWhiteList;
	}

	public void setIsWhiteList(Integer isWhiteList) {
		this.isWhiteList = isWhiteList;
	}
	

}
