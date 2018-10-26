package com.uec.imonitor.es.bean.index;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  ES索引配置对象</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class IndexConfig {
	/**
	 * <p>Description: 索引名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields indexName 
	 */
	private String indexName;
	/**
	 * <p>Description: 类型名称</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields typeName 
	 */
	private String typeName;
	/**
	 * <p>Description: 分片数</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields shardsNum 
	 */
	private int shardsNum;
	/**
	 * <p>Description: 副本数</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields replicasNum 
	 */
	private int replicasNum;
	
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getShardsNum() {
		return shardsNum;
	}
	public void setShardsNum(int shardsNum) {
		this.shardsNum = shardsNum;
	}
	public int getReplicasNum() {
		return replicasNum;
	}
	public void setReplicasNum(int replicasNum) {
		this.replicasNum = replicasNum;
	}
	
}
