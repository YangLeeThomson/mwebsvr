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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.ftp.service.FtpProperties;
import com.uec.imonitor.ftp.service.IFtpJobService;
import com.uec.imonitor.news.service.INewsSpreadingAnalysisService;
import com.uec.imonitor.news.utils.FtpUtil;
import com.uec.imonitor.news.utils.TxtUtil;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.request.service.ITenantRequestService;

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
@Service("ftpJob")
@DisallowConcurrentExecution
public class FtpJob extends QuartzJobBean {
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	/*private String serverPath = "/";
//	private String localPath = "G:\\" + "zgjyb";
//	private String localPath = "G:\\" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	private String localPath = "G:\\xkwang";
	private String copyPath = "F:\\中国经营网原稿存档";*/

	/**
	 * 定时任务读取中国经营网FTP服务器TXT文档
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info(new Date() + "进入ftpJob...");
		
		/*IFtpJobService ftpJobService = null;
		ApplicationContext ctx;
		try {
			ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			ftpJobService = (IFtpJobService) ctx.getBean("ftpJobService");
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}
		ftpJobService.ftpDown();*/
		
		log.info(new Date() + "结束ftpJob...");






		
		/*long startTime = System.currentTimeMillis(); // 获取开始时间
		IRequestNewsService requestNewsService = null;
		ITenantRequestService tenantRequestService = null;
		ApplicationContext ctx;
		try {
			ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			requestNewsService = (IRequestNewsService) ctx.getBean("requestNewsService");
			tenantRequestService = (ITenantRequestService) ctx.getBean("tenantRequestService");
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}
		String host = "219.238.162.204";
		int port = 22;
		String username = "cxli";
		String password = "cxli_cb_170427";
		String webName = "中国经营报";
		Integer request_id = 1;
		FtpUtil ftpUtil = new FtpUtil();
		try {
			ftpUtil.getFTPClient(host, port, username, password);
			log.info("开始从服务器下载文件。。。");
			ftpUtil.batchDownLoadFile(serverPath, localPath);
			ftpUtil.disconnectFtp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		log.info("从服务器下载文件结束。。。");
		log.info("本次下载文件耗时。。。" + (endTime - startTime) / 1000 + "s");
		// 处理txt文件
		TxtUtil txtUtil = new TxtUtil();
		// ++++++++++++++++++++++++++++++++++
		//拷贝
		try {
			txtUtil.copy(new File(localPath), new File(copyPath));
			log.info("拷贝文件结束。。。");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// ++++++++++++++++++++++++++++++++++
		Map<String, Map<String, String>> readAllTxt = txtUtil.readAllTxt(localPath);
		//本地文件删除功能
		txtUtil.deleteDirectory(localPath);
		log.info("本地删除文件结束。。。");
		System.err.println(readAllTxt.size());
		int successCount = 0;
		int failCount = 0;
		for (Map.Entry<String, Map<String, String>> entry : readAllTxt.entrySet()) {
			Map<String, String> valueMap = entry.getValue();
			RequestNewsEntity requestNewsEntity = new RequestNewsEntity();
			requestNewsEntity.setNewsSectionId(valueMap.get("sectionNo"));
			requestNewsEntity.setNewsSection(valueMap.get("section"));
			requestNewsEntity.setTitle(valueMap.get("Headline"));
			requestNewsEntity.setSubtitle(valueMap.get("title"));
			requestNewsEntity.setNewsAuthor(valueMap.get("author"));
			requestNewsEntity.setContent(valueMap.get("content"));
			requestNewsEntity.setIsDeleted(0);
			requestNewsEntity.setRequestId(request_id);
			requestNewsEntity.setNewsSource("中国经营报");
			requestNewsEntity.setOriginalCode(valueMap.get("originalCode"));
			String webpagecode = txtUtil.hashKeyForDisk(webName + valueMap.get("author") + valueMap.get("Headline"));
			requestNewsEntity.setWebpageCode(webpagecode);
			
			try {
				// 稿件创建时间
				String createTime = valueMap.get("createTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(createTime);
				requestNewsEntity.setReportDatetime(date);

				Integer crawlDays = tenantRequestService.findById(request_id).getCrawlDays();
				Calendar calendar = Calendar.getInstance();
				date = new Date(System.currentTimeMillis());
				requestNewsEntity.setCreateDatetime(date);
				requestNewsEntity.setStartDatetime(date);
				calendar.setTime(date);
				calendar.add(Calendar.DATE, +crawlDays);
				date = calendar.getTime();
				requestNewsEntity.setEndDatetime(date);
				// end
				requestNewsService.add(requestNewsEntity);
				log.info("文件路径为" + entry.getKey() + "的文件保存成功。。。");
				log.info("入库成功文件个数" + ++successCount);
			} catch (Exception e) {
				failCount++;
				System.err.println("文件路径为" + entry.getKey() + "的文件保存失败。。。");
				log.info("文件路径为" + entry.getKey() + "的文件保存失败。。。");
				log.info("入库失败文件个数" + failCount);
				e.printStackTrace();
			}
		}
		System.err.println(readAllTxt.size());
		log.info("入库失败文件个数" + failCount);
		endTime = System.currentTimeMillis(); // 获取结束时间
		log.info("本次文件入库耗时。。。" + (endTime - startTime) / 1000 + "s");*/
		/*log.info("***ftpJob Start...");
		long startTime = System.currentTimeMillis(); // 获取开始时间
		IRequestNewsService requestNewsService = null;
		ITenantRequestService tenantRequestService = null;
		ApplicationContext ctx;
		try {
			ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			requestNewsService = (IRequestNewsService) ctx.getBean("requestNewsService");
			tenantRequestService = (ITenantRequestService) ctx.getBean("tenantRequestService");
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}

		String host = "219.238.162.204";
		int port = 22;
		String username = "cxli";
		String password = "cxli_cb_170427";
		String webName = "中国经营报";
		Integer request_id = 1;
		try {
			FtpUtil ftpUtil = new FtpUtil();
			ftpUtil.getFTPClient(host, port, username, password);
			log.info("开始从服务器下载文件。。。");
			ftpUtil.batchDownLoadFile(serverPath, localPath);
			ftpUtil.disconnectFtp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		log.info("从服务器下载文件结束。。。");
		log.info("本次下载文件耗时。。。" + (endTime - startTime) / 1000 + "s");
		TxtUtil txtUtil = new TxtUtil();
		// ++++++++++++++++++++++++++++++++++
		//拷贝
		try {
			txtUtil.copy(new File(localPath), new File(copyPath));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// ++++++++++++++++++++++++++++++++++
		//处理文件
		Map<String, Map<String, String>> readAllTxt = txtUtil.readAllTxt(localPath);
		System.err.println(readAllTxt.size());
		// ++++++++++++++++++++++++++++++++++
		//本地文件删除功能
		txtUtil.deleteDirectory(localPath);
		int successCount = 0;
		int failCount = 0;
		for (Map.Entry<String, Map<String, String>> entry : readAllTxt.entrySet()) {
			Map<String, String> valueMap = entry.getValue();
			RequestNewsEntity requestNewsEntity = new RequestNewsEntity();
			requestNewsEntity.setNewsSectionId(valueMap.get("sectionNo"));
			requestNewsEntity.setNewsSection(valueMap.get("section"));
			requestNewsEntity.setTitle(valueMap.get("Headline"));
			requestNewsEntity.setSubtitle(valueMap.get("title"));
			requestNewsEntity.setNewsAuthor(valueMap.get("author"));
			requestNewsEntity.setContent(valueMap.get("content"));
			requestNewsEntity.setIsDeleted(0);
			requestNewsEntity.setRequestId(request_id);
			requestNewsEntity.setNewsSource("中国经营报");
			requestNewsEntity.setOriginalCode(valueMap.get("originalCode"));
			String webpagecode = txtUtil.hashKeyForDisk(webName + valueMap.get("author") + valueMap.get("Headline"));
			requestNewsEntity.setWebpageCode(webpagecode);
			try {
				// 稿件发稿时间、创建时间、开始时间、结束时间
				String createTime = valueMap.get("createTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(createTime);
				requestNewsEntity.setReportDatetime(date);

				Integer crawlDays = tenantRequestService.findById(request_id).getCrawlDays();
				Calendar calendar = Calendar.getInstance();
				date = new Date(System.currentTimeMillis());
				requestNewsEntity.setCreateDatetime(date);
				requestNewsEntity.setStartDatetime(date);
				calendar.setTime(date);
				calendar.add(Calendar.DATE, +crawlDays);
				date = calendar.getTime();
				requestNewsEntity.setEndDatetime(date);
				// end
				requestNewsService.add(requestNewsEntity);
				log.info("文件路径为" + entry.getKey() + "的文件保存成功。。。");
				log.info("入库成功文件个数" + ++successCount);
			} catch (Exception e) {
				log.error("文件路径为" + entry.getKey() + "的文件保存失败。。。");
				log.info("入库失败文件个数" + ++failCount);
				e.printStackTrace();
			}
		}
		log.info("入库失败文件个数" + failCount);
		endTime = System.currentTimeMillis(); // 获取结束时间
		log.info("本次文件入库耗时。。。" + (endTime - startTime) / 1000 + "s");*/
	}
	
	
}
