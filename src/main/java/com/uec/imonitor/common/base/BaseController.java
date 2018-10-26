package com.uec.imonitor.common.base;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.log.ErrorCodeManager;



/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: Controller基础类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Scope("prototype")
public abstract class BaseController{
	Log log = LogFactory.getLog(BaseController.class);

	public HttpServletRequest request;
	
	public HttpServletResponse response;
	
	public HttpSession session;
	
	private final String ERROR_MSG = "未知异常，请联系管理员！";
	
	@Value("${inews.i18n:zh_CN}")//从application.properties中读取配置，若获取不到默认为“zh_CN”
	private String i18n;
	
	@ModelAttribute//绑定请求参数到命令对象
	public void setRequestParams(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.request = request;
		this.response = response;
		this.session = session;
	}
	
	public ModelMap getModelMap(Object resultObj){
		return this.getModelMap(true,resultObj,null ,null);
	}
	
	/**
	 * <br/>Description:获取异常的响应结果，返回给页面
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param e
	 * @return
	 * @throws IOException 
	 */
	public ModelMap getModelMap(BaseException e){
//		return this.getModelMap(false,null,e.getErrorcode(),e.getDiscription());
		String errorMsg = null;
		if(StringUtils.isNoneBlank(i18n)){
			errorMsg = ErrorCodeManager.getText(i18n,e.getErrorCode(),e.getParams());
		}else{
			errorMsg = ErrorCodeManager.getText(e.getErrorCode(),e.getParams());
		}
		return this.getModelMap(false,null,e.getErrorCode(),errorMsg);
	}
	/**
	 * <br/>Description:获取异常的响应结果，返回给页面
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param e
	 * @return
	 */
	public ModelMap getModelMap(Exception e){
//		return this.getModelMap(false,null,e.getErrorcode(),e.getDiscription());
		return this.getModelMap(false,null,null,ERROR_MSG);
	}
	
	public ModelMap getModelMap(boolean result,Object resultObj,String errorCode,String errorMsg){
		ModelMap map = new ModelMap();
		map.put("result",result);
		map.put("resultObj",resultObj);
		map.put("errorCode",errorCode);
		map.put("errorMsg",errorMsg);
		return map;
	}
	
	/**
	 * <br/>Description:获取排序方式,升序(ecs) or 倒序 (desc)
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param aoData
	 * @return
	 */
	public String getOrderType(DataTablesRequestEntity aoData){
		if(null != aoData){
			return aoData.getsSortDir_0();
		}
		return "desc";
	}
	
	/**
	 * <br/>Description:获取排序值
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param aoData
	 * @return
	 * @throws Exception
	 */
	public String getOrderValue(DataTablesRequestEntity aoData){
		String orderValue = null;
		int iSortCol = aoData.getiSortCol_0();//获取排序的列序号
//		String orderType = aoData.getsSortDir_0();//获取排序的对应列序号的规则:正序(asc)or倒序(desc)
		String getter = "getmDataProp_" + iSortCol;  
		try{
			Method method = aoData.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(aoData, new Object[] {});
			if(null != value){
				orderValue = (String)value;
			}
		}catch(Exception e){
			log.error("get the OrderType failed.");
			log.error(e);
		}  
//		if(StringUtils.isEmpty(orderValue)){
//			orderValue = Constant.ORDER_VALUE_DEFAULT_DB;
//		}
		return orderValue;
		
	}
	
//	/**
//	 * <br/>Description:根据属性名获取排序字段
//	 * <p>Author:xpguo/郭晓鹏</p>
//	 * @param fieldName 属性名称
//	 * @return 排序对应的数据库的列名
//	 */
//	public String getOrderValueByFiledName(String fieldName){
//    	String dbColumnName = "";
//		if(StringUtils.isEmpty(fieldName)){
//			return null;
//		}else{
//			if(StringUtils.isEmpty(dbColumnName)){
//				StringBuffer sb = new StringBuffer();
//				for(int i = 0; i < fieldName.length(); i++){
//				   char c = fieldName.charAt(i);
//				   if(Character.isUpperCase(c)){
//					   if(i == 0){
//						   sb.append(Character.toLowerCase(c));
//					   }else if(i == dbColumnName.length()-1){
//						   sb.append(Character.toLowerCase(c));
//					   }else{
//						   sb.append("_");
//						   sb.append(Character.toLowerCase(c));
//					   }
//				   }else{
//					   sb.append(c);
//				   }
//				  }
//				dbColumnName = sb.toString();
//			}
//		}
//		return dbColumnName;
//    }
}
