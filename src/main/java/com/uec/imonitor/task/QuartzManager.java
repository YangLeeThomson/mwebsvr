package com.uec.imonitor.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.uec.imonitor.task.service.IScheduledTaskService;



/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司 http://www.ronglian.com</p> 
 * <p>Description: 定时任务管理器 </p> 
 * <p>Author:jinlChen/陈金梁</p>
 */
@Component("quartzManager")
public class QuartzManager{
	Log log = LogFactory.getLog(QuartzManager.class);

	
	@Autowired
	@Qualifier("schedulerFactoryBean")
	SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	IScheduledTaskService  scheduledTaskService;
	
//	private static SchedulerFactory schedulerFactory = null;
//	private static final String JOB_GROUP_NAME = "uec_job_group";
//	private static final String TRIGGER_GROUP_NAME = "defaultTriggerGroup_";
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * <br/>Description:添加一个任务(定时触发，单次)
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param jobName
	 * @param job
	 * @param tiggerName Tigger的名称，同一个Group里不能用重复名称
	 * @param cronExpression
	 * @param map
	 * @throws SchedulerException
	 */
//	public void addJob(String jobName,Class<? extends Job> jobClass,CronExpression cronExpression,Map<String,Object> map) throws SchedulerException{
//		String tiggerName = this.getTriggerName(jobName);
//		//先删除指定的 任务
//		this.removeJob(jobName);
//		
//		//从 调度 工厂中获取一个 任务调度
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//
//		//要执行的任务
//		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,JOB_GROUP_NAME).build();
//		
//		//传入参数
//		jobDetail.getJobDataMap().putAll(map);
//		
//		//任务触发器
//		Trigger trigger = TriggerBuilder.newTrigger()
//										.withIdentity(tiggerName)
//										.startAt(new Date())
//										.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
//										.build();
//		//给触发设置需要触发的任务
//		scheduler.scheduleJob(jobDetail,trigger);
//		
//		//触发任务
//		scheduler.start();
//		log.info("Successed to add a scheduler task and the scheduler task name is "+tiggerName
//					+ "and the cron expression is '"+cronExpression+"'"
//				);
//		log.info("The scheduler task next fire time is " + sdf.format(trigger.getNextFireTime()));
//	}
	

	/**
	 * <br/>Description:添加一个任务(定时触发，单次)
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName Tigger的名称，同一个Group里不能用重复名称
	 * @param JobGroupName 任务组名称
	 * @param triggerPriority 优先级，默认5，取值0-9，值越大优先级越高
	 * @param jobClass
	 * @param cronExpression
	 * @param map
	 * @throws SchedulerException
	 */
	public void addJob(String jobName, String groupName, int triggerPriority, Class<? extends Job> jobClass,CronExpression cronExpression,Map<String,Object> map) throws SchedulerException{
		String tiggerName = this.getTriggerName(jobName);
		String jobGroupName = this.getJobGroupName(groupName);
		//先删除指定的 任务
		this.removeJob(jobName, groupName);
		
		//从 调度 工厂中获取一个 任务调度
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		//要执行的任务
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroupName).build();
		
		//传入参数
		jobDetail.getJobDataMap().putAll(map);
		
		//任务触发器
		Trigger trigger = TriggerBuilder.newTrigger()
										.withIdentity(tiggerName)
										.startAt(new Date())
										.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
										.withPriority(triggerPriority)
										.build();
		//给触发设置需要触发的任务
		scheduler.scheduleJob(jobDetail,trigger);
		
		//触发任务
		scheduler.start();
		log.info("Successed to add a scheduler task and the scheduler task name is "+tiggerName
					+ "and the cron expression is '"+cronExpression+"'"
				);
		log.info("The scheduler task next fire time is " + sdf.format(trigger.getNextFireTime()));
	}
	
	/**
	 * <br/>Description:添加定时任务，该定时任务，执行一次,且调用时就执行
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param jobName
	 * @param job
	 * @param tiggerName
	 * @param map
	 * @throws SchedulerException
	 */
//	public void addJob(String jobName,Class<? extends Job> jobClass,Map<String,Object> map) throws SchedulerException{
//		String tiggerName = this.getTriggerName(jobName);
//		//先删除指定的 任务
//		this.removeJob(jobName);
//		//job
//		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,JOB_GROUP_NAME).build();
//		
//		//传入参数
//		jobDetail.getJobDataMap().putAll(map);
//		
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.MINUTE,5);
//				
//		//trigger
//		Trigger trigger = TriggerBuilder.newTrigger()
//							.withIdentity(tiggerName)
//							.startAt(cal.getTime())
//							.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInMinutes(10))
//							.build();
//		//scheduler
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		scheduler.scheduleJob(jobDetail,trigger);
//		scheduler.start();
//		
//		log.info("Successed to add a scheduler task and the scheduler task name is "+tiggerName + "and it will be excuted just one times .");
//		log.info("The scheduler task fire time is " + sdf.format(trigger.getNextFireTime()));
//	}
	
	/**
	 * <br/>Description:添加定时任务，该定时任务，执行一次,且调用时就执行
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName
	 * @param groupName
	 * @param triggerPriority
	 * @param jobClass
	 * @param map
	 * @throws SchedulerException
	 */
	public void addJob(String jobName, String groupName, int triggerPriority,Class<? extends Job> jobClass,Map<String,Object> map) throws SchedulerException{
		String tiggerName = this.getTriggerName(jobName);
		String jobGroupName = this.getJobGroupName(groupName);
		//先删除指定的 任务
		this.removeJob(jobName, groupName);
		//job
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroupName).build();
		
		//传入参数
		jobDetail.getJobDataMap().putAll(map);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE,5);
				
		//trigger
		Trigger trigger = TriggerBuilder.newTrigger()
							.withIdentity(tiggerName)
							.startAt(cal.getTime())
							.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInMinutes(10))
							.withPriority(triggerPriority)
							.build();
		//scheduler
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.scheduleJob(jobDetail,trigger);
		scheduler.start();
		
		log.info("Successed to add a scheduler task and the scheduler task name is "+tiggerName + "and it will be excuted just one times .");
		log.info("The scheduler task fire time is " + sdf.format(trigger.getNextFireTime()));
	}
	
	
	/**
	 * <br/>Description:添加一个任务(定时循环触发)
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param jobName
	 * @param job
	 * @param triggerStartTime
	 * @param intervalInSeconds 单位秒
	 * @param map
	 * @throws SchedulerException
	 */
//	public void addJob(String jobName,Class<? extends Job> jobClass,int intervalInSeconds,Map<String,Object> map) throws SchedulerException{
//		String tiggerName = this.getTriggerName(jobName);
//		//先删除指定的 任务
//		this.removeJob(jobName);
//		//从 调度 工厂中获取一个 任务调度
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		//要执行的任务
//		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,JOB_GROUP_NAME).build();
//		//传入参数
//		jobDetail.getJobDataMap().putAll(map);
//		//任务触发器
//		Trigger trigger = TriggerBuilder.newTrigger()
//										.withIdentity(tiggerName)
//										.startAt(new Date())
//										.withSchedule(SimpleScheduleBuilder
//														.simpleSchedule()
//														.withIntervalInSeconds(intervalInSeconds)
//														.repeatForever())//永远重复
//										.build();
//		
//		scheduler.scheduleJob(jobDetail,trigger);
//		
//		scheduler.start();		
//		
//		log.info("Successed to add a scheduler task and the scheduler task name is "+tiggerName
//				+ "and the interval in seconds is '"+intervalInSeconds+"'"
//			);
//		log.info("The scheduler task next fire time is " + sdf.format(trigger.getNextFireTime()));
//	}

	/**
	 * <br/>Description: 添加一个任务(定时循环触发)
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName
	 * @param groupName
	 * @param triggerPriority
	 * @param jobClass
	 * @param intervalInSeconds
	 * @param map
	 * @throws SchedulerException
	 */
	public void addJob(String jobName, String groupName, int triggerPriority, Class<? extends Job> jobClass,int intervalInSeconds,Map<String,Object> map) throws SchedulerException{
		String tiggerName = this.getTriggerName(jobName);
		String jobGroupName = this.getJobGroupName(groupName);
		//先删除指定的 任务
		this.removeJob(jobName, groupName);
		//从 调度 工厂中获取一个 任务调度
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//要执行的任务
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroupName).build();
		//传入参数
		jobDetail.getJobDataMap().putAll(map);
		//任务触发器
		Trigger trigger = TriggerBuilder.newTrigger()
										.withIdentity(tiggerName)
										.startAt(new Date())
										.withSchedule(SimpleScheduleBuilder
										.simpleSchedule()
										.withIntervalInSeconds(intervalInSeconds)
										.repeatForever())//永远重复
										.withPriority(triggerPriority)
										.build();
		
		scheduler.scheduleJob(jobDetail,trigger);
		
		scheduler.start();		
		
		log.info("Successed to add a scheduler task and the scheduler task name is "+tiggerName
				+ "and the interval in seconds is '"+intervalInSeconds+"'"
			);
		log.info("The scheduler task next fire time is " + sdf.format(trigger.getNextFireTime()));
	}
	/**
	 * <br/>Description:删除一个任务
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param jobName
	 * @throws SchedulerException
	 */
//	public void removeJob(String jobName) throws SchedulerException{
//		String tiggerName = this.getTriggerName(jobName);
//		//从工厂中获取一个调度
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		
//		//从知道的 触发组 里删除一个指定名称的job
//		TriggerKey triggerKey = new TriggerKey(jobName,tiggerName);
//		//从任务调度里暂停一个 触发
//		scheduler.pauseTrigger(triggerKey);
//		//将触发变成不可触发的任务
//		scheduler.unscheduleJob(triggerKey);
//		
//		//从指定的 任务组 里获取一个 指定名称的 任务
//		JobKey jobKey = new JobKey(jobName,JOB_GROUP_NAME);
//		//从触发中删除一个指定的任务
//		scheduler.deleteJob(jobKey);
//		log.info("Successed to remove a scheduler task ,scheduler task's name is "+jobName);
//	}
	
	/**
	 * <br/>Description:删除一个任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName
	 * @param groupName
	 * @throws SchedulerException
	 */
	public void removeJob(String jobName, String groupName) throws SchedulerException{
		String tiggerName = this.getTriggerName(jobName);
		String jobGroupName = this.getJobGroupName(groupName);
		//从工厂中获取一个调度
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		//从知道的 触发组 里删除一个指定名称的job
		TriggerKey triggerKey = new TriggerKey(jobName,tiggerName);
		//从任务调度里暂停一个 触发
		scheduler.pauseTrigger(triggerKey);
		//将触发变成不可触发的任务
		scheduler.unscheduleJob(triggerKey);
		
		//从指定的 任务组 里获取一个 指定名称的 任务
		JobKey jobKey = new JobKey(jobName, jobGroupName);
		//从触发中删除一个指定的任务
		scheduler.deleteJob(jobKey);
		log.info("Successed to remove a scheduler task ,scheduler task's name is "+jobName);
	}
	/**
	 * <br/>Description:清除任务
	 * <p>Author:jinlChen/陈金梁</p>
	 * @throws SchedulerException
	 */
	public void clear() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.clear();
		log.info("Successed to cleared all scheduler task.");
	}
	
	/**
	 * <br/>Description:根据jobName获取TriggerName
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param jobName
	 * @return
	 */
	private String getTriggerName(String jobName){
		String triggerName = jobName+"_tiggerName";
		return triggerName;
	}
	
	/**
	 * <br/>Description:根据jobName获取TriggerName
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param jobName
	 * @return
	 */
	private String getJobGroupName(String groupName){
		String jobGroupName = groupName+"_GroupName";
		return jobGroupName;
	}
	
}
