package com.uec.imonitor.news.utils;

import com.google.common.collect.ArrayListMultimap;
import com.uec.imonitor.common.exception.BadHanYuPinYinException;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * <br/>Description:获取新闻网站名字拼音工具类
 * <p>Author:xkwang/王西坤</p>
 * @return
 * @throws BaseException
 * @throws Exception
 */
public class Py4j {
	private ArrayListMultimap<String,String> duoYinZiMap;
	public Py4j(){
		Py4jDictionary.getDefault().init();
		duoYinZiMap = Py4jDictionary.getDefault().getDuoYinZiMap();
	}

	public String[] getPinyin(char ch) {
		try{
			HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
			outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

			if(ch>=32 && ch<=125){	//ASCII >=33 ASCII<=125的直接返回 ,ASCII码表：http://www.asciitable.com/
				return new String[]{String.valueOf(ch)};
			}
			return PinyinHelper.toHanyuPinyinStringArray(ch, outputFormat);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			throw new BadHanYuPinYinException(e);
		}

	}

	/**
	 * 获得字符串中的大写字母
	 * @param chinese
	 * @return
	 */
	public  String stringChange(String chiese) {
		String s = this.getPinyinAZ(chiese);
	        if (StringUtils.isBlank(s))
	            return "";
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < s.length(); i++) {
	            if (Character.isUpperCase(s.charAt(i))) {
	                sb.append(s.charAt(i));
	            }
	        }
	        return sb.toString().toLowerCase();
	    }
	public String getPinyinAZ(String chinese) {
		if(StringUtils.isEmpty(chinese)){
			return null;
		}
		
		chinese = chinese.replaceAll("[\\.，\\,！·\\!？\\?；\\;\\(\\)（）\\[\\]\\:： ]+", " ").trim();

		StringBuilder py_sb = new StringBuilder(32);
		char[] chs = chinese.toCharArray();
		for(int i=0;i<chs.length;i++){
			String[] py_arr = getPinyin(chs[i]);
			if(py_arr==null || py_arr.length<1){
				throw new BadHanYuPinYinException("pinyin array is empty, char:"+chs[i]+",chinese:"+chinese);
			}
			if(py_arr.length==1){
				py_sb.append(convertInitialToUpperCase(py_arr[0]));
			}else if(py_arr.length==2 && py_arr[0].equals(py_arr[1])){
				py_sb.append(convertInitialToUpperCase(py_arr[0]));
			}else{
				String resultPy = null, defaultPy = null;;
				for (String py : py_arr) {
					String left = null;	//向左多取一个字,例如 银[行]
					if(i>=1 && i+1<=chinese.length()){
						left = chinese.substring(i-1,i+1);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(left)){
							resultPy = py;
							break;
						}
					}
					
					String right = null;	//向右多取一个字,例如 [长]沙
					if(i<=chinese.length()-2){
						right = chinese.substring(i,i+2);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(right)){
							resultPy = py;
							break;
						}
					}
					
					String middle = null;	//左右各多取一个字,例如 龙[爪]槐
					if(i>=1 && i+2<=chinese.length()){
						middle = chinese.substring(i-1,i+2);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(middle)){
							resultPy = py;
							break;
						}
					}
					String left3 = null;	//向左多取2个字,如 芈月[传],列车长
					if(i>=2 && i+1<=chinese.length()){
						left3 = chinese.substring(i-2,i+1);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(left3)){
							resultPy = py;
							break;
						}
					}
					
					String right3 = null;	//向右多取2个字,如 [长]孙无忌
					if(i<=chinese.length()-3){
						right3 = chinese.substring(i,i+3);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(right3)){
							resultPy = py;
							break;
						}
					}
					
					if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(String.valueOf(chs[i]))){	//默认拼音
						defaultPy = py;
					}
				}
				
				if(StringUtils.isEmpty(resultPy)){
					if(StringUtils.isNotEmpty(defaultPy)){
						resultPy = defaultPy;
					}else{
						resultPy = py_arr[0];
					}
				}
				py_sb.append(convertInitialToUpperCase(resultPy));
			}
		}
		
		return py_sb.toString();
	}
	
	public String getPinyin(String chinese) {
		if(StringUtils.isEmpty(chinese)){
			return null;
		}
		
		chinese = chinese.replaceAll("[\\.，\\,！·\\!？\\?；\\;\\(\\)（）\\[\\]\\:： ]+", " ").trim();

		StringBuilder py_sb = new StringBuilder(32);
		char[] chs = chinese.toCharArray();
		for(int i=0;i<chs.length;i++){
			String[] py_arr = getPinyin(chs[i]);
			if(py_arr==null || py_arr.length<1){
				throw new BadHanYuPinYinException("pinyin array is empty, char:"+chs[i]+",chinese:"+chinese);
			}
			if(py_arr.length==1){
				py_sb.append(convertInitialToUpperCase(py_arr[0]));
			}else if(py_arr.length==2 && py_arr[0].equals(py_arr[1])){
				py_sb.append(convertInitialToUpperCase(py_arr[0]));
			}else{
				String resultPy = null, defaultPy = null;;
				for (String py : py_arr) {
					String left = null;	//向左多取一个字,例如 银[行]
					if(i>=1 && i+1<=chinese.length()){
						left = chinese.substring(i-1,i+1);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(left)){
							resultPy = py;
							break;
						}
					}
					
					String right = null;	//向右多取一个字,例如 [长]沙
					if(i<=chinese.length()-2){
						right = chinese.substring(i,i+2);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(right)){
							resultPy = py;
							break;
						}
					}
					
					String middle = null;	//左右各多取一个字,例如 龙[爪]槐
					if(i>=1 && i+2<=chinese.length()){
						middle = chinese.substring(i-1,i+2);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(middle)){
							resultPy = py;
							break;
						}
					}
					String left3 = null;	//向左多取2个字,如 芈月[传],列车长
					if(i>=2 && i+1<=chinese.length()){
						left3 = chinese.substring(i-2,i+1);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(left3)){
							resultPy = py;
							break;
						}
					}
					
					String right3 = null;	//向右多取2个字,如 [长]孙无忌
					if(i<=chinese.length()-3){
						right3 = chinese.substring(i,i+3);
						if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(right3)){
							resultPy = py;
							break;
						}
					}
					
					if(duoYinZiMap.containsKey(py) && duoYinZiMap.get(py).contains(String.valueOf(chs[i]))){	//默认拼音
						defaultPy = py;
					}
				}
				
				if(StringUtils.isEmpty(resultPy)){
					if(StringUtils.isNotEmpty(defaultPy)){
						resultPy = defaultPy;
					}else{
						resultPy = py_arr[0];
					}
				}
				py_sb.append(convertInitialToUpperCase(resultPy));
			}
		}
		String str = py_sb.toString().toLowerCase();
		return str.replace(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}
	
	private String convertInitialToUpperCase(String str) {
		if (str == null || str.length()==0) {
			return "";
		}
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
}

