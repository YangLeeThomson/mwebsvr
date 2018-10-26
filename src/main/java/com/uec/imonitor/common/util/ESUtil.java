package com.uec.imonitor.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.uec.imonitor.es.bean.NewsStatusEntity;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.request.bean.RequestNewsDetail;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: es公共方法 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class ESUtil {
	/**
	 * <br/>Description:将检索结果转换为结果对象
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param hits 检索结果
	 * @return
	 */
	public static <T> List<T> parseESHits(SearchHits hits,Class<T> clazz) {
		List<T> list = new ArrayList<>();
		//getClass() 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class。  
//		(Class<T>) persistentClass=(Class<T>)getSuperClassGenricType(getClass(), 0);
		for (SearchHit hit : hits) {
			String json = hit.getSourceAsString();
			T obj = CommonUtil.parseJson(json, clazz);
				list.add(obj); // 加入结果集
		}
		return list;
	}
	
    /**
     * <br/>Description:通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. 
     * <p>Author:jlchen/陈金梁</p>
     * @param clazz clazz The class to introspect 
     * @param index the Index of the generic ddeclaration,start from 0. 
     * @return the index generic declaration, or Object.class if cannot be 
     *         determined 
     */
    @SuppressWarnings("unchecked")  
    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {  
          
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。  
        Type genType = clazz.getGenericSuperclass();  
  
        if (!(genType instanceof ParameterizedType)) {  
           return Object.class;  
        }  
        //返回表示此类型实际类型参数的 Type 对象的数组。  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
  
        if (index >= params.length || index < 0) {  
                     return Object.class;  
        }  
        if (!(params[index] instanceof Class)) {  
              return Object.class;  
        }  
  
        return (Class) params[index];  
    }  
	/**
	 * <br/>Description:提取新闻状态对象recordid
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param esStatusList 状态记录集
	 * @return
	 */
	public static List<Integer> listRecordIdsOfEsStatusList(List<NewsStatusEntity> esStatusList) {
		List<Integer> idList = new ArrayList<>();
		if(esStatusList != null && !esStatusList.isEmpty()){
			for (NewsStatusEntity rs : esStatusList) {
				idList.add(rs.getRecordId());
			}
		}
		
		return idList;
	}
	
	/**
	 * <br/>Description:提取需求新闻状态对象id
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param esStatusList 状态记录集
	 * @return
	 */
	public static List<Integer> listIdsOfRequestNewsDetailList(List<RequestNewsDetail> requestNewsInsertList) {
		List<Integer> idList = new ArrayList<>();
		if(requestNewsInsertList != null && !requestNewsInsertList.isEmpty()){
			for (RequestNewsDetail rs : requestNewsInsertList) {
				idList.add(rs.getNewsId());
			}
		}
		return idList;
	}
	
	/**
	 * <br/>Description:提取 传播分析结果 对象id
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param esStatusList 状态记录集
	 * @return
	 */
	public static List<Integer> listIdsOfSpreadAnalysisDetailList(List<NewsSpreadingAnalysisDetail> newsSpreadingInsertList) {
		List<Integer> idList = new ArrayList<>();
		if(newsSpreadingInsertList != null && !newsSpreadingInsertList.isEmpty()){
			for (NewsSpreadingAnalysisDetail rs : newsSpreadingInsertList) {
				idList.add(rs.getInnerid());
			}
		}
		return idList;
	}
}
