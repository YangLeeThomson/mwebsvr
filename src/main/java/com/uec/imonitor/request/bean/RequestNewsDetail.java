package com.uec.imonitor.request.bean;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 被监控新闻详情实体  </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class RequestNewsDetail  extends RequestNewsEntity {
	
	private String newsTypeName;    //新闻终端类型   1=网页、2=微博、3=微信等

	public String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	
}
