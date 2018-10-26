package com.uec.imonitor.task;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.uec.imonitor.common.util.CommonUtil;

@Configuration
public class QuartzConfig{
	Log log = LogFactory.getLog(QuartzConfig.class);
	@Value("${inews.quartz.properity.config}")
	private String quartzPropertity;
	
	/*@Value("${inews.ftpaccount.properity.config}")
	private String ftpaccount;

	@Bean(name="ftpAccountPropertity")
	public Properties getftpAccountProperties() throws IOException{
		Properties properties = PropertiesLoaderUtils.loadAllProperties(ftpaccount+".properties");
		return properties;
	}*/
	
	
	/**
	 * <br/>Description:从springboot的配置文件中获取Quartz相关配置
	 * <p>Author:jinlChen/陈金梁</p>
	 * @return
	 * @throws IOException 
	 */
	@Bean(name="quartzProperties")
	public Properties getQuartzProperties() throws IOException{
		Properties properties = PropertiesLoaderUtils.loadAllProperties(quartzPropertity+".properties");
		return properties;
	}
	/**
	 * <br/>Description:第一步： 配置SchedulerFactoryBean 
	 * <p>Author:jinlChen/陈金梁</p>
	 * @return
	 * @throws IOException 
	 */
	@Bean(name="schedulerFactoryBean")
	public SchedulerFactoryBean getSchedulerFactoryBean() throws IOException{
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//		schedulerFactoryBean.setStartupDelay(5);//单位秒秒，应用程序启动5秒后在执行定时任务，延迟5秒启动定时任务
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");//通过applicationContextSchedulerContextKey属性配置spring上下文
		schedulerFactoryBean.setQuartzProperties(this.getQuartzProperties());//设置quartz配置
		log.info("***********************");
		log.info(CommonUtil.toJson(this.getQuartzProperties())); 
		return schedulerFactoryBean;
	}
	
}
