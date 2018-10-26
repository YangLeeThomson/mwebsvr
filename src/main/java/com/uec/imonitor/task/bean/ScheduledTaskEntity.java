package com.uec.imonitor.task.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司 http://www.ronglian.com</p> 
 * <p>Description: 定时任务实体对象 </p> 
 * <p>Author:jinlChen/陈金梁</p>
 */
@Entity
@Table(name="job_scheduled_task")
@DynamicInsert(true)//对应Hibernate的xml配置文件中的dynamic-insert;Default:false;将dynamic-insert设置为true,同样的保存,hibernate会动态生成SQL语句,没有值的字段不会出现在insert语句中,性能有所优化
@DynamicUpdate(true)//对应Hibernate的xml配置文件中的dynamic-update;Default:false;只更新部分字段
public class ScheduledTaskEntity {

	/**
	 * <p>Description: </p>
	 * <p>Author:jinlChen/陈金梁</p>
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = - 448924394162073243L;

	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@Column(name="innerid",nullable=false)
	private int innerid;
	
	
	/**
	 * 任务对象类名称
	 */
	@Column(name="class_name")
	private String className;
	
	/**
	 * 触发时间
	 */
	@Column(name="trigger_start_time")
	private Date triggerStartTime;
	
	/**
	 * 间隔时间 (秒)
	 */
	@Column(name="interval_in_seconds")
	private Integer intervalInSeconds;
	
	/**
	 * cron表达式
	 */
	@Column(name="cron_expression")
	private String cronExpression;
	
	/**
	 * 最近一次执行时间
	 */
	@Column(name="last_execution_time")
	private Date lastExecutionTime;

	
	/**
	 * 定时任务的唯一标识
	 */
	@Column(name="identification")
	private String identification;
	
	/**
	 * 定时任务是否可执行
	 */
	@Column(name="enable")
	private Boolean enable;
	
	@Column(name= "job_group_name")
	private String groupName;
	
	@Column(name = "priority")
	private Integer priority;
	/**
	 * 执行定时任务需要传递的参数
	 */
	@Column(name="param")
	private String param;

	public Date getTriggerStartTime(){
		return triggerStartTime;
	}

	public void setTriggerStartTime(Date triggerStartTime){
		this.triggerStartTime = triggerStartTime;
	}

	public Integer getIntervalInSeconds(){
		return intervalInSeconds;
	}

	public void setIntervalInSeconds(Integer intervalInSeconds){
		this.intervalInSeconds = intervalInSeconds;
	}

	public String getCronExpression(){
		return cronExpression;
	}

	public void setCronExpression(String cronExpression){
		this.cronExpression = cronExpression;
	}

	public Date getLastExecutionTime(){
		return lastExecutionTime;
	}

	public void setLastExecutionTime(Date lastExecutionTime){
		this.lastExecutionTime = lastExecutionTime;
	}

	public String getIdentification(){
		return identification;
	}

	public void setIdentification(String identification){
		this.identification = identification;
	}

	public String getParam(){
		return param;
	}

	public void setParam(String param){
		this.param = param;
	}

	public int getInnerid() {
		return innerid;
	}

	public void setInnerid(int innerid) {
		this.innerid = innerid;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
}
