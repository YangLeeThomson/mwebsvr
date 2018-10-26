package com.uec.imonitor.task.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.uec.imonitor.request.service.IRequestNewsService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  需求新闻模块新增新闻分析任务</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Service("requestNewsInsertJob")
@DisallowConcurrentExecution
public class RequestNewsInsertJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// 获取spring上下文，可以从上下文中获取所有被注入到spring的bean
		try {
			ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			IRequestNewsService requestNewsService = (IRequestNewsService) ctx.getBean("requestNewsService");
			/**
			 * 获取数据,新增的数据
			 */
			int do_num = 3000;//单次分析数
			requestNewsService.insertRequestNewsIndexJob(do_num); 
		} catch (SchedulerException e) {
			logger.error("通过spring 上下文获取bean异常,"+ e);
		}
	}
				
}
