/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  </p>
 * <p>Author:sq/孙强</p>
 * @Title: HotNewsBosonSentimenAndAreaJob.java
 * @Package HotNewsBosonNerJob
 * @date 2017年1月18日 下午4:42:31 
 */
package com.uec.imonitor.task.job;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;
import com.uec.imonitor.peopledaily.service.impl.PeoplesDailyServiceImpl;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.uec.imonitor.peopledaily.service.IPeoplesDailyService;

import javax.xml.crypto.Data;


/**
 * <p>
 * Copyright: All Rights Reserved
 * </p>
 * <p>
 * Company: 北京荣之联科技股份有限公司 http://www.ronglian.com
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author:xkwang/王西坤
 * </p>
 */
@Service("peopleDailyJob")
@DisallowConcurrentExecution
public class PeopleDailyJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//private transient Logger log = LoggerFactory.getLogger(this.getClass());



	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("进入人民日报定时任务：" + new Date());
		
		IPeoplesDailyService peoplesDailyService = null;
		ApplicationContext ctx;
		try {
			ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			peoplesDailyService = (IPeoplesDailyService) ctx.getBean("peoplesDailyService");

			//peoplesDailyService.saveNewsForMediaTest();
			//peoplesDailyService.dataToImediaTest();

			peoplesDailyService.dataToAPICloud();
			peoplesDailyService.dataToImedia();

			//Thread1 thread1 = new Thread1(peoplesDailyService);
			//thread1.run();
            //
			//Thread2 thread2 = new Thread2(peoplesDailyService);
			//thread2.run();
		} catch (Exception e) {
			logger.error("人民日报定时任务测试失败：" + new Date() + "错误信息" + e);
			e.printStackTrace();
		}
	}


	class Thread1 implements Runnable {
		private  final Logger logger = LoggerFactory.getLogger(this.getClass());

		private IPeoplesDailyService peoplesDailyService;

		public Thread1(IPeoplesDailyService peoplesDailyService) {
			this.peoplesDailyService = peoplesDailyService;
		}

		@Override
		public void run() {
			Date dt = new Date();
			logger.info("进入APICloud推送线程，时间：" + dt);
			peoplesDailyService.dataToAPICloud();
		}
	}

	class Thread2 implements Runnable {
		private  final Logger logger = LoggerFactory.getLogger(this.getClass());
		private  IPeoplesDailyService peoplesDailyService ;

		public Thread2(IPeoplesDailyService peoplesDailyService) {
			this.peoplesDailyService = peoplesDailyService;
		}

		@Override
		public void run() {
			Date dt = new Date();
			logger.info("进入Imdeia推送线程，时间：" + dt);
			peoplesDailyService.dataToImedia();
		}
	}

	}
