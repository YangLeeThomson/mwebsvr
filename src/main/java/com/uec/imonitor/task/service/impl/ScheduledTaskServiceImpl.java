package com.uec.imonitor.task.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.BusinessException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.ConstantUtil;
import com.uec.imonitor.es.service.IEsIndexService;
import com.uec.imonitor.task.QuartzManager;
import com.uec.imonitor.task.bean.ScheduledTaskEntity;
import com.uec.imonitor.task.dao.IScheduledTaskJPARepository;
import com.uec.imonitor.task.service.IScheduledTaskService;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 任务调度Service实现 </p> 
 * <p>Author:jinlChen/陈金梁</p>
 */

@Service("scheduledTaskService")
@Order(value=1)
public class ScheduledTaskServiceImpl extends BaseService implements IScheduledTaskService,CommandLineRunner{
	Log log = LogFactory.getLog(ScheduledTaskServiceImpl.class);
	@Value("${inews.quartz.task.isreload}")
	private boolean isReload;
	
	@Autowired
	IScheduledTaskJPARepository scheduledTaskJPARepository;
	
	@Autowired
	QuartzManager quartzManager;
	
	@Autowired
	private IEsIndexService esIndexService;
	/**
	 * <p>Description:项目启动执行</p>
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param arg0
	 * @throws Exception
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String ... arg0) throws Exception{
		log.info("ScheduledTaskServiceImpl running!");
//		esIndexService.EsIndexSetting();
//		esIndexService.initAllRequestNews();
//		esIndexService.initAllNewsSpreading();
		
		
		//-----------定时任务注解
		if(isReload){
			this.disableAllScheduledTasks();
			this.enableAllScheduledTasks();
		}
		
		
		log.info("Successed to enabled all scheduled tasks.");
	}
	
	@Override
	public List<ScheduledTaskEntity> listEnabled() {
		List<ScheduledTaskEntity> list = scheduledTaskJPARepository.listAllByEnable(true);
		for(ScheduledTaskEntity l : list) {
			log.info("+++++++++++"+l.toString()+":"+l.getClassName());
			
		}
		return list;
	}
	@Override
	public List<ScheduledTaskEntity> listDisabled() {
		List<ScheduledTaskEntity> list = scheduledTaskJPARepository.listAllByEnable(false);
		list.forEach(l->{
			log.info("-----------"+l.toString()+":"+l.getClassName());});
		list.stream().forEach(l->{
			log.info("-----------"+l.toString()+":"+l.getClassName());});

		return list;
	}
	
	@Override
//	@SystemLogDesc(desc="findByIdentification")
	public ScheduledTaskEntity findByIdentification(String identification) throws RequestParamException {
		if(StringUtils.isEmpty(identification)){
			throw new RequestParamException(new String[]{"identification"});
		}
		return scheduledTaskJPARepository.findByIdentification(identification);
	}


	/**
	 * <p>Description:修改</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: update
	 * @param scheduledTaskEntity
	 * @return
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#update(com.uec.imonitor.task.bean.ScheduledTaskEntity)
	 */
	@Override
//	@SystemLogDesc(desc="update")
	public ScheduledTaskEntity update(ScheduledTaskEntity scheduledTaskEntity) {
		return scheduledTaskJPARepository.saveAndFlush(scheduledTaskEntity);
	}

	/**
	 * <p>Description:根据cron表达式修改定时任务</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: updateCronExpression
	 * @param identification 唯一标识
	 * @param cronExpression cron表达式
	 * @param className job名
	 * @return
	 * @throws BaseException 
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#updateCronExpression(java.lang.String, java.lang.String, java.lang.String)
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	@SystemLogDesc(desc="updateCronExpression:根据cron表达式修改定时任务")
//	public ScheduledTaskEntity updateCronExpression(String identification,String cronExpression, String className) throws BaseException {
//		if(StringUtils.isEmpty(identification)){
//			throw new RequestParamException(new String[]{"identification"});
//		}
//		if(StringUtils.isEmpty(cronExpression)){
//			throw new RequestParamException(new String[]{"cronExpression"});
//		}
//		CronExpression cronExpr = null;
//		try{
//			cronExpr = new CronExpression(cronExpression);
//		}catch(ParseException e1){
//			throw new BusinessException("005001001",new String[]{cronExpression});//The cronExpression--'"+cronExpression+"' is not available
//		}
//		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
//		if(null == scheduledTaskEntity){
//			throw new BusinessException("005001002",new String[]{identification});//The ScheduledTaskEntity is not exist witch the identification is '"+identification+"'.
//		}
//		try{
//			//quartzManager.addJob(identification,TaskJob.class,cronExpr,new HashMap<String,Object>());
//			quartzManager.addJob(identification, (Class<? extends Job>)Class.forName(ConstantUtil.JOB_PACKAGE+className), cronExpr,new HashMap<String,Object>());
//		}catch(SchedulerException e){
//			log.error("Failed to update scheduled task in job_scheduled_tasks subsystem witch the identification is '"+identification+"'.");
//			throw new BaseException(e);
//		} catch (ClassNotFoundException e) {
//			throw new BaseException(e);
//		}
//		if(null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()){
//			scheduledTaskEntity.setEnable(true);
//		}
//		scheduledTaskEntity.setCronExpression(cronExpression);
//		scheduledTaskEntity.setIntervalInSeconds(null);
//		scheduledTaskEntity.setTriggerStartTime(new Date());
//		return this.update(scheduledTaskEntity);
//	}


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
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#updateCronExpression(java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
//	@SystemLogDesc(desc="updateCronExpression:根据cron表达式修改定时任务")
	public ScheduledTaskEntity updateCronExpression(String identification, String groupName, Integer priority, String cronExpression, String className) throws BaseException {
		if(StringUtils.isEmpty(identification)){
			throw new RequestParamException(new String[]{"identification"});
		}
		if(StringUtils.isEmpty(cronExpression)){
			throw new RequestParamException(new String[]{"cronExpression"});
		}
		if(StringUtils.isEmpty(groupName)){
			throw new RequestParamException(new String[]{"groupName"});
		}
		if(priority == null){
//			priority = ConstantUtil.TASK_DEFAULT_PRIORITY;
			priority = 5;
		}
		CronExpression cronExpr = null;
		try{
			cronExpr = new CronExpression(cronExpression);
		}catch(ParseException e1){
			throw new BusinessException("005001001",new String[]{cronExpression});//The cronExpression--'"+cronExpression+"' is not available
		}
		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
		if(null == scheduledTaskEntity){
			throw new BusinessException("005001002",new String[]{identification});//The ScheduledTaskEntity is not exist witch the identification is '"+identification+"'.
		}
		try{
			//quartzManager.addJob(identification,TaskJob.class,cronExpr,new HashMap<String,Object>());
			quartzManager.addJob(identification,groupName, priority, (Class<? extends Job>)Class.forName("com.uec.imonitor.task.job."+className), cronExpr,new HashMap<String,Object>());
		}catch(SchedulerException e){
			log.error("Failed to update scheduled task in job_scheduled_tasks subsystem witch the identification is '"+identification+"'.");
			throw new BaseException(e);
		} catch (ClassNotFoundException e) {
			throw new BaseException(e);
		}
		if(null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()){
			scheduledTaskEntity.setEnable(true);
		}
		scheduledTaskEntity.setCronExpression(cronExpression);
		scheduledTaskEntity.setIntervalInSeconds(null);
		scheduledTaskEntity.setTriggerStartTime(new Date());
		scheduledTaskEntity.setGroupName(groupName);
		scheduledTaskEntity.setPriority(priority); 
		return this.update(scheduledTaskEntity);
	}
	
	/**
	 * <p>Description:修改定时轮询时间</p>
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param identification 唯一标识
	 * @param intervalInSeconds 轮询间隔时间，单位(秒)
	 * @return
	 * @throws BaseException 
	 * @see com.ronglian.scheduledTask.service.IScheduledTaskService#updateIntervalInSeconds(java.lang.String, int)
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	@SystemLogDesc(desc="updateIntervalInSeconds:修改定时轮询时间")
//	public ScheduledTaskEntity updateIntervalInSeconds(String identification,int intervalInSeconds, String className) throws BaseException{
//		if(StringUtils.isEmpty(identification)){
//			throw new RequestParamException(new String[]{"identification"});
//		}
//		if(intervalInSeconds <= 0){
//			throw new BusinessException("005001003");//参数intervalInSeconds必须大于0.
//		}
//		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
//		try{
//			//quartzManager.addJob(identification,TaskJob.class,intervalInSeconds,new HashMap<String,Object>());
//			quartzManager.addJob(identification, (Class<? extends Job>)Class.forName(ConstantUtil.JOB_PACKAGE+className), intervalInSeconds,new HashMap<String,Object>());
//		}catch(SchedulerException e){
//			log.error("Failed to update scheduled task in job_scheduled_tasks subsystem witch the identification is "+identification+".");
//			throw new BaseException(e);
//		}catch (ClassNotFoundException e) {
//			throw new BaseException(e);
//		}
//		if(null == scheduledTaskEntity){
//			throw new BusinessException("005001004",new String[]{identification});//identification值为{0}的ScheduledTaskEntity实体不存在。
//		}
//		if(null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()){
//			scheduledTaskEntity.setEnable(true);
//		}
//		scheduledTaskEntity.setIntervalInSeconds(intervalInSeconds);
//		scheduledTaskEntity.setCronExpression(null);
//		scheduledTaskEntity.setTriggerStartTime(new Date());
//		return this.update(scheduledTaskEntity);
//	}

	@SuppressWarnings("unchecked")
	@Override
//	@SystemLogDesc(desc="updateIntervalInSeconds:修改定时轮询时间")
	public ScheduledTaskEntity updateIntervalInSeconds(String identification,String groupName, Integer priority,int intervalInSeconds, String className) throws BaseException{
		if(StringUtils.isEmpty(identification)){
			throw new RequestParamException(new String[]{"identification"});
		}
		if(StringUtils.isEmpty(groupName)){
			throw new RequestParamException(new String[]{"groupName"});
		}
		if(priority == null){
//			priority = ConstantUtil.TASK_DEFAULT_PRIORITY;
			priority = 5;
		}
		if(intervalInSeconds <= 0){
			throw new BusinessException("005001003");//参数intervalInSeconds必须大于0.
		}
		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
		try{
			//quartzManager.addJob(identification,TaskJob.class,intervalInSeconds,new HashMap<String,Object>());
			quartzManager.addJob(identification, groupName, priority, (Class<? extends Job>)Class.forName("com.uec.imonitor.task.job."+className), intervalInSeconds,new HashMap<String,Object>());
		}catch(SchedulerException e){
			log.error("Failed to update scheduled task in job_scheduled_tasks subsystem witch the identification is "+identification+".");
			throw new BaseException(e);
		}catch (ClassNotFoundException e) {
			throw new BaseException(e);
		}
		if(null == scheduledTaskEntity){
			throw new BusinessException("005001004",new String[]{identification});//identification值为{0}的ScheduledTaskEntity实体不存在。
		}
		if(null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()){
			scheduledTaskEntity.setEnable(true);
		}
		scheduledTaskEntity.setIntervalInSeconds(intervalInSeconds);
		scheduledTaskEntity.setCronExpression(null);
		scheduledTaskEntity.setTriggerStartTime(new Date());
		scheduledTaskEntity.setGroupName(groupName);
		scheduledTaskEntity.setPriority(priority); 
		return this.update(scheduledTaskEntity);
	}
	/**
	 * <p>Description:修改定时轮询时间</p>
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param identification 唯一标识
	 * @param intervalInSeconds 轮询间隔时间，单位(秒)
	 * @return
	 * @throws BaseException 
	 * @see com.ronglian.scheduledTask.service.IScheduledTaskService#updateIntervalInSeconds(java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
//	@SystemLogDesc(desc="updateIntervalInSeconds:修改定时轮询时间")
	public ScheduledTaskEntity updateCronExpression(String identification, String groupName, Integer priority,int intervalInSeconds, String className) throws BaseException{
		if(StringUtils.isEmpty(identification)){
			throw new RequestParamException(new String[]{"identification"});
		}
		if(intervalInSeconds <= 0){
			throw new BusinessException("005001003");//参数intervalInSeconds必须大于0.
		}
		if(StringUtils.isEmpty(groupName)){
			throw new RequestParamException(new String[]{"groupName"});
		}
		if(priority == null){
//			priority = ConstantUtil.TASK_DEFAULT_PRIORITY;
			priority = 5;
		}
		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
		try{
			//quartzManager.addJob(identification,TaskJob.class,intervalInSeconds,new HashMap<String,Object>());
			quartzManager.addJob(identification,groupName,priority, (Class<? extends Job>)Class.forName("com.uec.imonitor.task.job."+className), intervalInSeconds,new HashMap<String,Object>());
		}catch(SchedulerException e){
			log.error("Failed to update scheduled task in job_scheduled_tasks subsystem witch the identification is "+identification+".");
			throw new BaseException(e);
		}catch (ClassNotFoundException e) {
			throw new BaseException(e);
		}
		if(null == scheduledTaskEntity){
			throw new BusinessException("005001004",new String[]{identification});//identification值为{0}的ScheduledTaskEntity实体不存在。
		}
		if(null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()){
			scheduledTaskEntity.setEnable(true);
		}
		scheduledTaskEntity.setIntervalInSeconds(intervalInSeconds);
		scheduledTaskEntity.setCronExpression(null);
		scheduledTaskEntity.setTriggerStartTime(new Date());
		scheduledTaskEntity.setGroupName(groupName);
		scheduledTaskEntity.setPriority(priority); 
		return this.update(scheduledTaskEntity);
	}

	/**
	 * <p>Description:启用定时任务</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: enableScheduledTask
	 * @param identification
	 * @return
	 * @throws RequestParamException 
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#enableScheduledTask(java.lang.String)
	 */
	@Override
//	@SystemLogDesc(desc="enableScheduledTask:启用定时任务")
	public ScheduledTaskEntity enableScheduledTask(String identification) throws RequestParamException  {
		if(StringUtils.isEmpty(identification)){
			throw new RequestParamException(new String[]{"identification"});
		}

		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
		if(null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()){
			scheduledTaskEntity.setEnable(true);
			return this.update(scheduledTaskEntity);
		}else{
			return scheduledTaskEntity;
		}
	}

	/**
	 * <p>Description:禁用定时任务</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: disableScheduledTask
	 * @param identification
	 * @return
	 * @throws BaseException 
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#disableScheduledTask(java.lang.String)
	 */
//	@Override
//	@SystemLogDesc(desc="disableScheduledTask:禁用定时任务")
//	public ScheduledTaskEntity disableScheduledTask(String identification) throws BaseException{
//		if(StringUtils.isEmpty(identification)){
//			throw new RequestParamException(new String[]{"identification"});
//		}
//		try{
//			quartzManager.removeJob(identification);
//		}catch(SchedulerException e){
//			log.error("Failed to remove a scheduled task in job_scheduled_tasks subsystem witch the identification is "+identification+".");
//			throw new BaseException(e);
//		}
//		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
//		if(null == scheduledTaskEntity.getEnable() || true == scheduledTaskEntity.getEnable()){
//			scheduledTaskEntity.setEnable(false);
//			return this.update(scheduledTaskEntity);
//		}else{
//			return scheduledTaskEntity;
//		}
//	}
	
	/**
	 * <p>Description:禁用定时任务</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: disableScheduledTask
	 * @param identification
	 * @return
	 * @throws BaseException 
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#disableScheduledTask(java.lang.String)
	 */
	@Override
//	@SystemLogDesc(desc="disableScheduledTask:禁用定时任务")
	public ScheduledTaskEntity disableScheduledTask(String identification, String groupName) throws BaseException{
		if(StringUtils.isEmpty(identification)){
			throw new RequestParamException(new String[]{"identification"});
		}
		try{
			quartzManager.removeJob(identification, groupName);
		}catch(SchedulerException e){
			log.error("Failed to remove a scheduled task in job_scheduled_tasks subsystem witch the identification is "+identification+".");
			throw new BaseException(e);
		}
		ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
		if(null == scheduledTaskEntity.getEnable() || true == scheduledTaskEntity.getEnable()){
			scheduledTaskEntity.setEnable(false);
			return this.update(scheduledTaskEntity);
		}else{
			return scheduledTaskEntity;
		}
	}

	/**
	 * <p>Description:启用所有需要执行的定时任务</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: enableAllScheduledTasks
	 * @return
	 * @throws BaseException 
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#enableAllScheduledTasks()
	 */
//	@Override
//	@SystemLogDesc(desc="enableAllScheduledTasks:启用所有需要执行的定时任务")
//	public boolean enableAllScheduledTasks() throws BaseException{
//		List<ScheduledTaskEntity> list = this.listEnabled();
//		if (CollectionUtils.isEmpty(list)) {
//			log.info("There is no scheduled task.");
//			return true;
//		} else {
//			List<String> identificationList = new ArrayList<>();
//			try {
//				for (ScheduledTaskEntity entity : list) {
//					// 当cronExpression和intervalInSeconds都有值时，抛出异常
//					if (!StringUtils.isEmpty(entity.getCronExpression()) && null != entity.getIntervalInSeconds()
//							&& 0 != entity.getIntervalInSeconds()) {
//						throw new BusinessException("005001005");// 参数'cronExpression'和'intervalInSeconds',不能同时都有值.
//					} else {
//						if (StringUtils.isEmpty(entity.getIdentification())) {
//							throw new RequestParamException(new String[] { "identification" });
//						}
//						if (!StringUtils.isEmpty(entity.getCronExpression())) {
//							this.updateCronExpression(entity.getIdentification(), entity.getCronExpression(),
//									entity.getClassName());
//						} else if (null != entity.getIntervalInSeconds()) {
//							this.updateIntervalInSeconds(entity.getIdentification(), entity.getIntervalInSeconds(),
//									entity.getClassName());
//						} else {
//							throw new BusinessException("005001006");// 参数'cronExpression'和'intervalInSeconds'的值,
//																		// 不能同时为'NULL'.
//						}
//					}
//					identificationList.add(entity.getIdentification());
//				}
//			}catch (BaseException e) {
//				// 回滚定时任务
//				removeListJobs(identificationList);
//				throw e;
//			} catch (Exception e) {
//				// 回滚定时任务
//				removeListJobs(identificationList);
//				new BaseException(e);
//			}
//		}
//		return false;
//	}
	
	/**
	 * <p>Description:</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: enableAllScheduledTasks
	 * @return
	 * @throws BaseException
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#enableAllScheduledTasks()
	 */
	@Override
//	@SystemLogDesc(desc="enableAllScheduledTasks:启用所有需要执行的定时任务")
	public boolean enableAllScheduledTasks() throws BaseException{
		List<ScheduledTaskEntity> enablelist = this.listEnabled();
		if (CollectionUtils.isEmpty(enablelist)) {
			log.info("There is no scheduled task.");
			return true;
		} else {
			List<Map<String,String>> identificationList = new ArrayList<>();
			try {
				for (ScheduledTaskEntity entity : enablelist) {
					// 当cronExpression和intervalInSeconds都有值时，抛出异常
					if (!StringUtils.isEmpty(entity.getCronExpression()) && null != entity.getIntervalInSeconds()
							&& 0 != entity.getIntervalInSeconds()) {
						throw new BusinessException("005001005");// 参数'cronExpression'和'intervalInSeconds',不能同时都有值.
					} else {
						if (StringUtils.isEmpty(entity.getIdentification())) {
							throw new RequestParamException(new String[] { "identification" });
						}
						if(StringUtils.isEmpty(entity.getGroupName())){
							throw new RequestParamException(new String[]{"groupName"});
						}
						if(entity.getPriority() == null){
							entity.setPriority(5);
						}
						if (!StringUtils.isEmpty(entity.getCronExpression())) {
							this.updateCronExpression(entity.getIdentification(),entity.getGroupName(), entity.getPriority(), entity.getCronExpression(),
									entity.getClassName());
						} else if (null != entity.getIntervalInSeconds()) {
							this.updateIntervalInSeconds(entity.getIdentification(),entity.getGroupName(), entity.getPriority(), entity.getIntervalInSeconds(),
									entity.getClassName());
						} else {
							throw new BusinessException("005001006");// 参数'cronExpression'和'intervalInSeconds'的值,
																		// 不能同时为'NULL'.
						}
					}
					Map<String, String> entityMap = new HashMap<>();
					entityMap.put("jobName", entity.getIdentification());
					entityMap.put("groupName", entity.getGroupName());
					identificationList.add(entityMap);
				}
			}catch (BaseException e) {
				// 回滚定时任务
				removeListJobs(identificationList);
				throw e;
			} catch (Exception e) {
				// 回滚定时任务
				removeListJobs(identificationList);
				new BaseException(e);
			}
		}
		return false;
	}
	
	/**
	 * <p>Description:禁用所有不需要执行的定时任务</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: enableAllScheduledTasks
	 * @return
	 * @throws BaseException
	 * @see com.uec.imonitor.task.service.IScheduledTaskService#enableAllScheduledTasks()
	 */
	@Override
//	@SystemLogDesc(desc="disableAllScheduledTasks:禁用所有不需要执行的定时任务")
	public boolean disableAllScheduledTasks() throws BaseException {
		List<ScheduledTaskEntity> disablelist = this.listDisabled();
		if (CollectionUtils.isEmpty(disablelist)) {
			log.info("There is no scheduled task.");
			return true;
		} else {
			List<Map<String, String>> identificationList = new ArrayList<>();
			for (ScheduledTaskEntity entity : disablelist) {
				Map<String, String> entityMap = new HashMap<>();
				entityMap.put("jobName", entity.getIdentification());
				entityMap.put("groupName", entity.getGroupName());
				identificationList.add(entityMap);
			}
			removeListJobs(identificationList);

		}
		return false;
	}
	/**
	 * <br/>Description:批量删除job
	 *<p>Author:jinlChen/陈金梁</p>
	 * @param identificationList
	 */
//	@SystemLogDesc(desc="removeListJobs:批量删除job")
//	private void removeListJobs(List<String> identificationList){
//		if(CollectionUtils.isEmpty(identificationList)){
//			for(String identification:identificationList){
//				try{
//					quartzManager.removeJob(identification);
//				}catch(SchedulerException e){
//					log.error("Failed to remove scheduled task in uec_scheduled_tasks subsystem witch the identification is "+identification+".");
//					new BaseException(e);
//				}
//			}
//		}
//	}

//	@SystemLogDesc(desc="removeListJobs:批量删除job")
	private void removeListJobs(List<Map<String,String>> identificationList){
		if(!CollectionUtils.isEmpty(identificationList)){
			for(Map<String,String> identification:identificationList){
				try{
					quartzManager.removeJob(identification.get("jobName"), identification.get("groupName")); 
				}catch(SchedulerException e){
					log.error("Failed to remove scheduled task in uec_scheduled_tasks subsystem witch the identification is "+identification+".");
					new BaseException(e);
				}
			}
		}
	}


}
