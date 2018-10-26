package com.uec.imonitor.task.service;

import java.util.List;

import org.springframework.util.StringUtils;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.task.bean.ScheduledTaskEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 任务调度Service </p> 
 * <p>Author:jinlChen/陈金梁</p>
 */
public interface IScheduledTaskService{
	
	/**
	 * <br/>Description:根据唯一标识获取实体对象
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param identification
	 * @return
	 * @throws RequestParamException 
	 */
	ScheduledTaskEntity findByIdentification(String identification) throws RequestParamException ;
	
	/**
	 * <br/>Description:获取可执行任务列表
	 * <p>Author:jinlChen/陈金梁</p>
	 * @return
	 */
	List<ScheduledTaskEntity> listEnabled();
	/**
	 * <br/>Description:获取不可执行任务列表
	 * <p>Author:jinlChen/陈金梁</p>
	 * @return
	 */
	List<ScheduledTaskEntity> listDisabled();
	
	/**
	 * <br/>Description:修改
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param scheduledTaskEntity
	 * @return
	 */
	ScheduledTaskEntity update(ScheduledTaskEntity scheduledTaskEntity);
	
	/**
	 * <br/>Description:根据cron表达式修改定时任务
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param identification 唯一标识
	 * @param cronExpression cron表达式
	 * @return 修改后的实体
	 * @throws BaseException 
	 */
//	ScheduledTaskEntity updateCronExpression(String identification,String cronExpression, String className) throws BaseException;
	

	/**
	 * <br/>Description: 根据cron表达式修改定时任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @param groupName 组名称
	 * @param identification  唯一标识
	 * @param priority 优先级
	 * @param cronExpression cron表达式
	 * @param className
	 * @return
	 * @throws BaseException
	 */
	public ScheduledTaskEntity updateCronExpression(String groupName, String identification, Integer priority, String cronExpression, String className) throws BaseException;
	
	/**
	 * <br/>Description:根据唯一标识修改循环间隔时间
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param identification 唯一标识
	 * @param intervalInSeconds 轮询间隔时间，单位(秒)
	 * @return
	 * @throws BaseException 
	 */
//	ScheduledTaskEntity updateIntervalInSeconds(String identification,int intervalInSeconds, String className) throws BaseException;
	
	/**
	 * <br/>Description:
	 * <p>Author:jlchen/陈金梁</p>
	 * @param identification
	 * @param groupName
	 * @param priority
	 * @param intervalInSeconds
	 * @param className
	 * @return
	 * @throws BaseException
	 */
	public ScheduledTaskEntity updateCronExpression(String identification, String groupName, Integer priority,int intervalInSeconds, String className) throws BaseException;
	
	/**
	 * <br/>Description:
	 * <p>Author:jlchen/陈金梁</p>
	 * @param identification
	 * @param groupName
	 * @param priority
	 * @param intervalInSeconds
	 * @param className
	 * @return
	 * @throws BaseException
	 */
	public ScheduledTaskEntity updateIntervalInSeconds(String identification,String groupName, Integer priority,int intervalInSeconds, String className) throws BaseException;
	/**
	 * <br/>Description:启用定时任务
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param identification
	 * @return
	 * @throws RequestParamException 
	 */
	ScheduledTaskEntity enableScheduledTask(String identification) throws RequestParamException;
	
	/**
	 * <br/>Description:禁用定时任务
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param identification
	 * @return
	 * @throws BaseException 
	 */
//	ScheduledTaskEntity disableScheduledTask(String identification) throws  BaseException;
	
	/**
	 * <br/>Description:禁用定时任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @param identification
	 * @param groupName
	 * @return
	 * @throws BaseException
	 */
	ScheduledTaskEntity disableScheduledTask(String identification, String groupName) throws  BaseException;
	
	/**
	 * <br/>Description:启用所有定时任务
	 * <p>Author:jinlChen/陈金梁</p>
	 * @return
	 * @throws BaseException 
	 */
	boolean enableAllScheduledTasks() throws  BaseException;
	
	/**
	 * <br/>Description:禁用所有不需要执行的定时任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @return
	 * @throws BaseException
	 */
	public boolean disableAllScheduledTasks() throws BaseException;

}
