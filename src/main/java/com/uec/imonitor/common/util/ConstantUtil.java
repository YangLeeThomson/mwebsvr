package com.uec.imonitor.common.util;

import java.util.HashMap;
import java.util.Map;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 系统共用的常量 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class ConstantUtil {
	

	/**
	 * <p>Description: 需求新闻表名</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TABLE_WEBPAGE 
	 */
	public static final String TABLE_REQUEST_NEWS = "request_news";
	
	/**
	 * <p>Description: 监控分析结果表</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TABLE_SPREAD_ANALYSIS 
	 */
	public static final String TABLE_SPREAD_ANALYSIS = "news_spreading_analysis";
	
	/**
	 * <p>Description: 新闻详情表</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TABLE_NEWS_WEBPAGE 
	 */
	public static final String TABLE_NEWS_WEBPAGE = "news_webpage";
	
	/**
	 * <p>Description:传播分析结果新增 </p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TASK_SPREADING_INSERT 
	 */
	public static final String TASK_SPREADING_INSERT = "spreading_insert";
	
	/**
	 * <p>Description: 传播分析结果更新</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TASK_SPREADING_UPDATE 
	 */
	public static final String TASK_SPREADING_UPDATE = "spreading_update";
	
	/**
	 * <p>Description: 传播分析结果删除</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TASK_SPREADING_DELETE 
	 */
	public static final String TASK_SPREADING_DELETE = "spreading_delete";
	
	/**
	 * <p>Description: 失败任务数最大值，大于该值，将不再处理</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TASK_FAILURE_NUM 
	 */
	public static final Integer TASK_FAILURE_NUM = 3;
	/**
	 * <p>Description:传播分析结果新增 </p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TASK_SPREADING_INSERT 
	 */
	public static final String TASK_REQUEST_NEWS_INSERT = "request_news_insert";
	
	/**
	 * <p>Description: 传播分析结果更新</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TASK_SPREADING_UPDATE 
	 */
	public static final String TASK_REQUEST_NEWS_UPDATE = "request_news_update";
	
	/**
	 * <p>Description: 传播分析结果删除</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TASK_SPREADING_DELETE 
	 */
	public static final String TASK_REQUEST_NEWS_DELETE = "request_news_delete";
	/**
	 * <p>Description: 新闻传播分析转载状态</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields REPRINT_STATUS_MAP 
	 */
	public static Map<Integer, String> REPRINT_STATUS_MAP = new HashMap<>();
	
	static{//put方法
		REPRINT_STATUS_MAP.put(0, "版权存疑");
		REPRINT_STATUS_MAP.put(1, "标注来源");
		}
	
	/**
	 * <p>Description: 新闻传播分析转载类型</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields REPRINT_TYPE_MAP 
	 */
	public static Map<Integer, String> REPRINT_TYPE_MAP = new HashMap<>();
	
	static{//put方法
		REPRINT_TYPE_MAP.put(1, "网页转载");
		REPRINT_TYPE_MAP.put(2, "微博转载");
		REPRINT_TYPE_MAP.put(3, "微信转载");
		}
	
	/**
	 * <p>Description: 新闻实体新闻终端类型名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields NEWS_TYPE_NAME_MAP 
	 */
	public static Map<Integer, String> NEWS_TYPE_NAME_MAP = new HashMap<>();
	
	static{//put方法
		NEWS_TYPE_NAME_MAP.put(1, "网页");
		NEWS_TYPE_NAME_MAP.put(2, "微信");
		NEWS_TYPE_NAME_MAP.put(3, "微博");
		}
}
