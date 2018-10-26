package com.uec.imonitor.task.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  失败任务管理对象</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Entity
@Table(name = "job_failure")
public class JobFailureEntity {

	/**
	 * <p>Description: 主键</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields innerid 
	 */
	@Id // 指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name = "innerid", nullable = false)
	private int innerid;
	
	/**
	 * <p>Description: 任务名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields jobName 
	 */
	@Column(name = "job_name")
	private String jobName;

	
	/**
	 * <p>Description: 数据表名</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields tableName 
	 */
	@Column(name = "table_name")
	private String tableName;

	
	/**
	 * <p>Description:记录id集 </p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields idSet 
	 */
	@Column(name = "id_set")
	private String idSet;

	/**
	 * <p>Description: 记录修改时间</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields lastModifyTime 
	 */
	@Column(name = "last_modify_time")
	private Date lastModifyTime;
	
	/**
	 * <p>Description:执行次数 </p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields num 
	 */
	@Column(name = "num")
	private Integer num;

	public int getInnerid() {
		return innerid;
	}

	public void setInnerid(int innerid) {
		this.innerid = innerid;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdSet() {
		return idSet;
	}

	public void setIdSet(String idSet) {
		this.idSet = idSet;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
