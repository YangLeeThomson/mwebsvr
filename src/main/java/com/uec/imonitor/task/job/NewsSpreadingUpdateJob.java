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

import com.uec.imonitor.news.service.INewsSpreadingAnalysisService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  传播分析结果 模块更新新闻分析任务</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Service("newsSpreadingUpdateJob")
@DisallowConcurrentExecution
public class NewsSpreadingUpdateJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// 获取spring上下文，可以从上下文中获取所有被注入到spring的bean
		try {
			ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			INewsSpreadingAnalysisService newsSpreadService = (INewsSpreadingAnalysisService) ctx.getBean("newsSpreadingAnalysisService");
			/**
			 * 获取数据,新增的数据
			 */
			int do_num = 2000;//单次分析数
			newsSpreadService.updateSpreadingAnalysisIndexJob(do_num);;//更新
			newsSpreadService.insertAndUpdateIndexFailureJob();//失败任务
			newsSpreadService.deletedSpreadingAnalysisIndexJob(do_num);; //删除 

		} catch (SchedulerException e) {
			logger.error("通过spring 上下文获取bean异常,"+ e);
		}
	}
				
}
