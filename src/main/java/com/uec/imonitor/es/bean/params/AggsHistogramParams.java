package com.uec.imonitor.es.bean.params;

import java.util.Date;

import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  使用Histogram aggregation聚合匹配的参数集</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class AggsHistogramParams {
	private String aggsName;//聚合查询名称
	private String aggsField; //聚合字段
	private DateHistogramInterval interval; //时间间隔
	private String timeZone;//时区
	private String format;//返回时间格式化
	private Date minDate;//最小时间
	private Date maxDate;//最大时间
	public String getAggsName() {
		return aggsName;
	}
	public void setAggsName(String aggsName) {
		this.aggsName = aggsName;
	}
	
	public DateHistogramInterval getInterval() {
		return interval;
	}
	public void setInterval(DateHistogramInterval interval) {
		this.interval = interval;
	}
	public String getAggsField() {
		return aggsField;
	}
	public void setAggsField(String aggsField) {
		this.aggsField = aggsField;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
}
