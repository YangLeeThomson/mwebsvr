package com.uec.imonitor.user.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 组织结构实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name="auth_org_user")
public class OrgUserEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue //注解默认使用主键生成方式为自增，hibernate会为我们自动生成一个名为HABERNATE_SEQUENCE的序列
	@Column(name="innerid",nullable=false)
	private Integer innerid;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="org_id")
	private Integer orgId;

	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


}
