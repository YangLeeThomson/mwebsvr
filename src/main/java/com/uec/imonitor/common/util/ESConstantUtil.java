package com.uec.imonitor.common.util;

import java.util.ArrayList;
import java.util.List;

import com.uec.imonitor.es.bean.params.SortParams;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: es用到的常量 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class ESConstantUtil {

	/**
	 * <p>Description: or连接方式，或连接</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields DEFAULT_OPT_IN 
	 */
	public static final String OPT_OR = "or";//or连接符
	/**
	 * <p>Description: and连接方式，或连接</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields DEFAULT_OPT_OUT 
	 */
	public static final String OPT_AND = "and";//and连接符
	
	/**
	 * <p>Description: not连接</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields OPT_NOT 
	 */
	public static final String OPT_NOT = "not";//not连接
	
	/**
	 * <p>Description: 过滤查询连接</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields OPT_FILTER 
	 */
	public static final String OPT_FILTER = "filter";//filter连接


	/**
	 * <p>Description: 检索结果默认开始位置</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields START 
	 */
	public static final int DEFAULT_START = 0;
	
	/**
	 * <p>Description: 检索结果默认条数</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields SIZE 
	 */
	public static final int DEFAULT_SIZE = 20;
	
	/**
	 * <p>Description: 检索结果默认排序字段</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields DEFAULT_SORT_FIELD 
	 */
	public static final String DEFAULT_SORT_FIELD = "_score";
	
	/**
	 * <p>Description: 降序排序方式</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields SORT_DESC 
	 */
	public static final String SORT_DESC = "desc";
	
	/**
	 * <p>Description: 默认排序对象</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields sortParames 
	 */
	@SuppressWarnings("serial")
	public static final List<SortParams> DEFAULT_SORT_PARAMS = new ArrayList<SortParams>(){{add (new SortParams(DEFAULT_SORT_FIELD,SORT_DESC));}};
	
	/**
	 * <p>Description: 升序排序方式</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields SORT_ASC 
	 */
	public static final String SORT_ASC = "asc";
	
	/**
	 * <p>Description: 按索引来源一级分类进行聚合分析字段-</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_LABEL 
	 */
	public static final String AGGS_SOURCE_ONE_LABEL = "sourceCrawlLevelOne";
	
	/**
	 * <p>Description: 按索引来源一级分类进行聚合分析名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_SOURCE_NAME 
	 */
	public static final String AGGS_SOURCE_ONE_NAME = "sourceCrawlLevelOne";
	
	/**
	 * <p>Description: 按索引来源二级分类进行聚合分析字段-</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_LABEL 
	 */
	public static final String AGGS_SOURCE_TWO_LABEL = "sourceCrawlLevelTwo";
	
	/**
	 * <p>Description: 按索引来源二级分类进行聚合分析名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_SOURCE_NAME 
	 */
	public static final String AGGS_SOURCE_TWO_NAME = "sourceCrawlLevelTwo";
	
	/**
	 * <p>Description: 按新闻分类聚类分析字段</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_LABEL 
	 */
	public static final String AGGS_CLASSIFY_LABEL = "cusClassificationLevelOne";
	
	/**
	 * <p>Description: 按新闻分类聚类分析名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_SOURCE_NAME 
	 */
	public static final String AGGS_CLASSIFY_NAME = "cusClassificationLevelOne";
	
	/**
	 * <p>Description: 按原创转载进行聚合分析字段</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_LABEL 
	 */
	public static final String AGGS_ORIGINAL_LABEL = "isOriginal";
	
	/**
	 * <p>Description: 按原创转载进行聚合分析名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_SOURCE_NAME 
	 */
	public static final String AGGS_ORIGINAL_NAME = "isOriginal";
	
	/**
	 * <p>Description: 报道类型聚合分析名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_REPORT_TYPE_NAME 
	 */
	public static final String AGGS_REPORT_TYPE_NAME = "reportType";
	
	/**
	 * <p>Description: 按今日时间采集统计进行聚合分析名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields AGGS_SOURCE_NAME 
	 */
	public static final String AGGS_DAY_HOURS_NAME = "dayHours";
	
	/**
	 * <p>Description: 默认分组个数</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields DEFAULT_AGGS_NUM 
	 */
	public static final Integer DEFAULT_AGGS_NUM = 25;
	/**
	 * <p>Description: 抓取时间</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields COUNT_TOTAL_LABEL 
	 */
	public static final String CRAWL_DATETIME = "crawlDatetime";
	
	/**
	 * <p>Description: 发布时间字段</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields COUNT_TOTAL_LABEL 
	 */
	public static final String RELEASE_DATETIME = "releaseDatetime";
	/**
	 * <p>Description: ik智能分词,按最大长度切分</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields ANALYZER_IK_SMART 
	 */
	public static final String ANALYZER_IK_SMART ="ik_smart";
	
	/**
	 * <p>Description: ik最大词汇量切词，即细粒度切分</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields ANALYZER_IK_MAX 
	 */
	public static final String ANALYZER_IK_MAX = "ik_max_word";
	
	/**
	 * <p>Description: 不分词</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields ANALYZER_NOT 
	 */
	public static final String ANALYZER_NOT = "not_analyzed";
	
	/**
	 * <p>Description: 时区</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields TIME_ZONE 
	 */
	public static final String TIME_ZONE = "+08:00";
	
}
