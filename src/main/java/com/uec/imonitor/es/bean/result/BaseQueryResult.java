package com.uec.imonitor.es.bean.result;

import java.util.List;
import java.util.Map;

import org.elasticsearch.common.unit.TimeValue;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  </p> 
 * <p>Author:jlchen/陈金梁</p>
 * @param <T>
 */
public class BaseQueryResult<T> {
	private long total; //总条数
	private TimeValue timeTook ;//查询时间消耗
	private float maxScore; //最高评分
	private List<T> resultList;//结果集
	private Map<String, Long> aggsMap ;// 聚合结果
	/**
	 * <br/>Description:无参构造函数
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public BaseQueryResult(){
		
	}
	/**
	 * <br/>Description: 带参构造函数
	 * <p>Author:jlchen/陈金梁</p>
	 * @param total
	 * @param timeTook
	 * @param maxScore
	 */
	public BaseQueryResult(long total, TimeValue timeTook, float maxScore){
		this.total = total;
		this.timeTook = timeTook;
		this.maxScore = maxScore;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public TimeValue getTimeTook() {
		return timeTook;
	}
	public void setTimeTook(TimeValue timeTook) {
		this.timeTook = timeTook;
	}
	public float getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(float maxScore) {
		this.maxScore = maxScore;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public Map<String, Long> getAggsMap() {
		return aggsMap;
	}
	public void setAggsMap(Map<String, Long> aggsMap) {
		this.aggsMap = aggsMap;
	}
	
	
}
