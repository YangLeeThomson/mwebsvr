package com.uec.imonitor.es.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  记录状态实体</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Entity
@Table(name="news_status")
public class NewsStatusEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="innerid",nullable=false)
	private Integer innerid;
	

	/**
	 * <p>Description: 被修改的数据表名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields tableName 
	 */
	@Column(name="table_name")
	private String tableName;
	
	/**
	 * <p>Description: 被修改记录的innerid</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields recordId 
	 */
	@Column(name="record_id")
	private Integer recordId;
	
	/**
	 * <p>Description: 新闻编号，针对标签和分析数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields webpageCode 
	 */
	@Column(name="webpage_code")
	private String webpageCode;
	
	/**
	 * <p>Description: 记录被修改的状态，0-增1-删2-改</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields status 
	 */
	@Column(name="status")
	private Integer status;
	
	/**
	 * <p>Description: 修改时间</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields lastModifyTime 
	 */
	@Column(name="last_modify_time")
	private Date lastModifyTime;

	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getWebpageCode() {
		return webpageCode;
	}

	public void setWebpageCode(String webpageCode) {
		this.webpageCode = webpageCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	
}
