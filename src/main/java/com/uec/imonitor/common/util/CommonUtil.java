package com.uec.imonitor.common.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
//import org.apache.shiro.crypto.hash.Md5Hash;
//import org.apache.shiro.crypto.hash.Sha1Hash;
//import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 公共工具类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class CommonUtil{
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	/*
	 * 获取与当前时间的时间差
	 */
	public static String getTimeDiff(Date tmp){
		String ret = "";
		try{
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
			Date today = sdf.parse(sdf.format(now));  //当天0点
			Date thisYear = sdf2.parse(sdf2.format(now));
			if(tmp.after(now)){ return "1s前"; }
			long s = (now.getTime() - tmp.getTime()) / 1000;  //与当前时间的差     单位：秒
			long count = 0;
			if(tmp.getTime() < thisYear.getTime()){  //今年之前
				ret = sdf.format(tmp);
			}else if(tmp.getTime() < (today.getTime()-48*3600*1000)){ //前天之前
				ret = sdf1.format(tmp);
			}else if(tmp.getTime() < (today.getTime()-24*3600*1000)){  //昨天之前（前天）
				ret = "前天";
			}else if(tmp.getTime() < today.getTime()){   //昨天
				ret = "昨天";
			}else if((count = s / 3600) > 0){
				ret = count + "h前";
			}else if((count = s / 60) > 0){
				ret = count + "m前";
			}else{
				ret = s + "s前";
			}
			
//			if((count = s / (3600 * 24)) > 0){
//				ret = sdf1.format(tmp);
//			}else if((count = s / 3600) > 0){
//				ret = count + "h前";
//			}else if((count = s / 60) > 0){
//				ret = count + "m前";
//			}else{
//				ret = s + "s前";
//			}
		}catch(Exception e){
			System.out.println("时间转换异常***************************************");
		}

		return ret;
	}

	/**
	 * <br/>Description:根据相对路径(相对于classpath的路径)，获取properties文件里的key对应的value
	 * <p>Author:xpguo/郭晓鹏</p>
	 * 
	 * @param relativePath 相对路径(相对于classpath的路径)；比如：errorcode.properties
	 * @param key
	 * @return value
	 * @throws IOException
	 */
	public static String getPropertiesValue(String relativePath,String key) throws IOException{
		Properties properties = PropertiesLoaderUtils.loadAllProperties(relativePath);
		// String value = new
		// String(properties.getProperty(key).getBytes("iso-8859-1"),"utf-8");
		String value = properties.getProperty(key);
		return value;
	}

	/**
	 * <br/>Description: 获取今天零时的时间
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @return
	 */
	public static Date getTodayZero(){
//		long current = System.currentTimeMillis();// 当前时间毫秒数
//		long current = new Date().getTime();
//		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();// 今天零时零分零秒的毫秒数

//		Date nowDate = new Date();
//		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
//		String dateStr = dformat.format(nowDate);
//		dateStr += " 00:00:00";
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d = null;
//		try {
//			d = df.parse(dateStr);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		long zero = d.getTime(); 
		Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0); 
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
    	long zero = todayStart.getTime().getTime();  
	
    	logger.info("dayZero = " + new Date(zero));
		return new Date(zero);
	}

	/**
	 * <br/>Description:获取24小时前的时间
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @return
	 */
	public static Date getYesterday(){
		long yesterday = System.currentTimeMillis() - 24 * 3600 * 1000;// 昨天的这一时间毫秒数
		return new Date(yesterday);
	}

	/**
	 * <br/>Description:将utc标准格式 时间转换为Date()类型
	 * <p>Author:jlchen/陈金梁</p>
	 * @param utcTime
	 * @return
	 * @throws ParseException
	 */
	public static Date formatUTCtime(String utcTime) throws ParseException{
		utcTime = utcTime.replace("Z", " UTC");//注意是空格+UTC
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
		Date d = format.parse(utcTime);
		return d;
	}
	
	/**
	 * <br/>Description:获取小时数，格式"HH"
	 * <p>Author:jlchen/陈金梁</p>
	 * @param d
	 * @return
	 */
	public static String getHour(Date d){   
	    SimpleDateFormat formatter;   
	    formatter = new SimpleDateFormat ("HH");   
	    String ctime = formatter.format(d);   
	    return ctime;   
	    }   
	  
	/**
	 * <br/>Description:获取date的天数，格式"dd"
	 * <p>Author:jlchen/陈金梁</p>
	 * @param d
	 * @return
	 */
	public static String getDay(Date d){   
	      SimpleDateFormat formatter;   
	    formatter = new SimpleDateFormat ("dd");   
	    String ctime = formatter.format(d);   
	    return ctime;   
	    }   
	  
	/**
	 * <br/>Description:获取date的月数，格式"MM"
	 * <p>Author:jlchen/陈金梁</p>
	 * @param d
	 * @return
	 */
	public static String getMonth(Date d){   
	    SimpleDateFormat formatter;   
	    formatter = new SimpleDateFormat ("MM");   
	    String ctime = formatter.format(d);   
	    return ctime;   
	    }   
	  
	/**
	 * <br/>Description:获取date的年数，格式"yyyy"
	 * <p>Author:jlchen/陈金梁</p>
	 * @param d
	 * @return
	 */
	public static String getYear(Date d){   
	    SimpleDateFormat formatter;   
	    formatter = new SimpleDateFormat ("yyyy");   
	    String ctime = formatter.format(d);   
	    return ctime;   
	    }   
	/**
	 * <br/>Description:获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
	 * <p>Author:jlchen/陈金梁</p>
	 * @param i
	 * @return
	 */
	public static Date getDate(int i) // 
	 {
	 Date dat = null;
	 Calendar cd = Calendar.getInstance();
	 cd.add(Calendar.DATE, i);
	 dat = cd.getTime();
	 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Timestamp date = Timestamp.valueOf(dformat.format(dat));
	 return date;
	 }
	/**
	 * <br/>Description:实体转换为json
	 * <p>Author:xpguo/郭晓鹏</p>
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		return JSON.toJSONString(obj);
	}

	/**
	 * <br/>Description:json转化实体
	 * <p>Author:xpguo/郭晓鹏</p>
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseJson(String json,Class<T> clazz){
		return JSON.parseObject(json,clazz);
	}

	/**
	 * <br/>Description:json转化为List
	 * <p>Author:xpguo/郭晓鹏</p>
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseJson2List(String json,Class<T> clazz){
		return JSON.parseArray(json,clazz);
	}

	/**
	 * <br/>Description:json转化为泛型
	 * <p>Author:xpguo/郭晓鹏</p>
	 * 
	 * @param json
	 * @param typeReference
	 * @return
	 */
	public static <T> T parseJsonTypeReference(String json,TypeReference<T> typeReference){
		return JSON.parseObject(json,typeReference,Feature.AutoCloseSource);

	}
	
	/**
	 * <br/>Description:Map按照value排序
	 * <p>Author:jlchen/陈金梁</p>
	 * @param map
	 * @param topN
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByMapValue(
			Map<K, V> map, int topN) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (int i = 0; i < list.size() && i < topN; i++) {
			Map.Entry<K, V> entry = list.get(i);
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	
	/**
	 * <br/>Description:Map按照value值去除元素
	 * <p>Author:sq/孙强</p>
	 * @param map
	 * @param minValue：value的最小值，小于它的值不计入map中
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> removeByMapValue(
			Map<K, V> map, int minValue) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (int i = 0; i < list.size(); i++) {
			Map.Entry<K, V> entry = list.get(i);
			if((Integer)entry.getValue() > minValue){
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}
	
	/**
	 * <br/>Description:全国省份缩略和百度地图echart中对应数据的Map集，key为省份的前两个字符，value为百度echart对应的值
	 * <p>Author:sq/孙强</p>
	 * @return
	 */
	public static Map<String, String> getProvinceMap(){
		Map<String , String> provinceMap = new HashMap<String, String>();
		provinceMap.put("北京","北京");
		provinceMap.put("天津","天津");
		provinceMap.put("上海","上海");
		provinceMap.put("重庆","重庆");
		provinceMap.put("河北","河北");
		provinceMap.put("山西","山西");
		provinceMap.put("辽宁","辽宁");
		provinceMap.put("吉林","吉林");
		provinceMap.put("黑龙","黑龙江");
		provinceMap.put("江苏","江苏");
		provinceMap.put("浙江","浙江");
		provinceMap.put("安徽","安徽");
		provinceMap.put("福建","福建");
		provinceMap.put("江西","江西");
		provinceMap.put("山东","山东");
		provinceMap.put("河南","河南");
		provinceMap.put("湖北","湖北");
		provinceMap.put("湖南","湖南");
		provinceMap.put("广东","广东");
		provinceMap.put("海南","海南");
		provinceMap.put("四川","四川");
		provinceMap.put("贵州","贵州");
		provinceMap.put("云南","云南");
		provinceMap.put("陕西","陕西");
		provinceMap.put("甘肃","甘肃");
		provinceMap.put("青海","青海");
		provinceMap.put("台湾","台湾");
		provinceMap.put("内蒙","内蒙古");
		provinceMap.put("广西","广西");
		provinceMap.put("西藏","西藏");
		provinceMap.put("宁夏","宁夏");
		provinceMap.put("新疆","新疆");
		provinceMap.put("香港","香港");
		provinceMap.put("澳门","澳门");

		return provinceMap;
	}
	
	public static List<Map<String,Object>> getDefaultCommentAreaList(){

		String text = "[{name: '北京',value: 0 },{name: '天津',value: 0 },{name: '上海',value: 0 },{name: '重庆',value: 0 },{name: '河北',value: 0 },{name: '河南',value: 0 },{name: '云南',value: 0 },{name: '辽宁',value: 0 },{name: '黑龙江',value: 0 },{name: '湖南',value: 0 },{name: '安徽',value: 0 },{name: '山东',value: 0 },{name: '新疆',value: 0 },{name: '江苏',value: 0 },{name: '浙江',value: 0 },{name: '江西',value: 0 },{name: '湖北',value: 0 },{name: '广西',value: 0 },{name: '甘肃',value: 0 },{name: '山西',value: 0 },{name: '内蒙古',value: 0 },{name: '陕西',value: 0 },{name: '吉林',value: 0 },{name: '福建',value: 0 },{name: '贵州',value: 0 },{name: '广东',value: 0 },{name: '青海',value: 0 },{name: '西藏',value: 0 },{name: '四川',value: 0 },{name: '宁夏',value: 0 },{name: '海南',value: 0 },{name: '台湾',value: 0 },{name: '香港',value: 0 },{name: '澳门',value: 0 }]";
		List<Map<String,Object>> resultList = parseJsonTypeReference(text,new TypeReference<List<Map<String,Object>>>(){});
		
		return resultList;
	}
	
	private static Pattern pattern;
	/**
	 * <br/>Description:生成去标签模式
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public static void getTagCleaningPattern(){
		if(pattern == null ){
			 String p = "(?isu)<[^>]*>";	   //去除html标签，<>标签内的 内容全去除
		      // 创建 Pattern 对象
			 pattern = Pattern.compile(p);
		}
	}
	/**
	 * <br/>Description:去除
	 * <p>Author:jlchen/陈金梁</p>
	 * @param str
	 * @return
	 */
	public static String fiterHtmlTag(String str){		

	      // 现在创建 matcher 对象
		while(pattern == null){
			getTagCleaningPattern();//调用
		}
	      Matcher m = pattern.matcher(str);
	      StringBuffer sb = new StringBuffer();   
	        boolean result1 = m.find();   
	        while (result1) {   
	            m.appendReplacement(sb, " ");   //替换的内容
	            result1 = m.find();   
	        }   
	        m.appendTail(sb);   
//	        System.out.println(sb.toString()); 
	        return sb.toString();
	}
	
	 /**
	 * <br/>Description:获取属性名数组 
	 * <p>Author:jlchen/陈金梁</p>
	 * @param obj
	 * @return
	 */
	private static <T> String[] getFiledName(Class<T> obj){
	    Field[] fields=obj.getClass().getDeclaredFields();
	    String[] fieldNames=new String[fields.length];
	    for(int i=0;i<fields.length;i++){
	        System.out.println(fields[i].getType());
	        fieldNames[i]=fields[i].getName();
	    }
	    return fieldNames;
	   }
	
	
	/**
	 * <br/>Description:密码加密
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String encryptPassword(String password,String salt){
//		System.out.println(new Sha256Hash(new Md5Hash(password, salt, 2).toString(), salt, 3).toString());
		return new Sha256Hash(new Md5Hash(password, salt, 2).toString(), salt, 3).toString();
	}
	
	public static String getIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("Proxy-Client-IP");     
	     }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("WL-Proxy-Client-IP");     
	      }     
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	          ip = request.getRemoteAddr();     
	     }
	     
	     if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15   
	         if(ip.indexOf(",")>0){   
	             ip = ip.substring(0,ip.indexOf(","));   
	         }   
	     }   
	     return ip;     
	}

	public static List<String> parseStringToList(String string,String type){
		List<String> stringList = new ArrayList<String>();
		if (null != string) {
			String[] split = string.split(type);
			for (String tmp : split) {
				stringList.add(tmp);
			}
			return stringList;
		}else {
			return  null;
		}
	}

	public static String getIpAddress(HttpServletRequest request) {
		//String ip = request.getHeader("x-forwarded-for");
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
		{
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
		{
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1)
			{
				return ip.substring(0, index);
			}
			else
			{
				return ip;
			}
		}
		else
		{
			return request.getRemoteAddr();
		}
	}

	/**
	 * 日期转换成字符串
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

}
