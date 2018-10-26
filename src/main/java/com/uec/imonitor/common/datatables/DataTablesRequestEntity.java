package com.uec.imonitor.common.datatables;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 插件DataTables的请求实体对象 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class DataTablesRequestEntity{
	
	/**
	 * <p>Description: 次数，页面发来的参数，原样返回 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields sEcho 
	 */
	private int sEcho;
	/**
	 * <p>Description: 显示的起始记录数,起始索引</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields iDisplayStart 
	 */
	private int iDisplayStart;
	/**
	 * <p>Description: 每页显示条数</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields iDisplayLength 
	 */
	private int iDisplayLength;
	
	/**
	 * <p>Description:排序列序号，从0开始 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields iSortCol_0 
	 */
	private int iSortCol_0;
	
	/**
	 * <p>Description: 排序的规则，正序(asc)or倒序(desc)</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields sSortDir_0 
	 */
	private String sSortDir_0;
	
	/**
	 * <p>Description:第1列的熟属性名；数据属性名，从0开始，取值于columns的每一列的data的字符串值，比如；data:id,那这个的值就是“id” </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_0 
	 */
	private String mDataProp_0;
	/**
	 * <p>Description:第2列的熟属性名；</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_1 
	 */
	private String mDataProp_1;
	
	/**
	 * <p>Description:第3列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_2
	 */
	private String mDataProp_2;
	/**
	 * <p>Description:第4列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_3
	 */
	private String mDataProp_3;
	/**
	 * <p>Description:第5列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_4
	 */
	private String mDataProp_4;
	/**
	 * <p>Description:第6列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_5
	 */
	private String mDataProp_5;
	/**
	 * <p>Description:第7列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_6
	 */
	private String mDataProp_6;
	/**
	 * <p>Description:第7列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_7
	 */
	private String mDataProp_7;
	
	/**
	 * <p>Description:第8列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp87
	 */
	private String mDataProp_8;
	/**
	 * <p>Description:第10列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_9
	 */
	private String mDataProp_9;
	/**
	 * <p>Description:第11列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_10
	 */
	private String mDataProp_10;
	/**
	 * <p>Description:第12列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_11
	 */
	private String mDataProp_11;
	/**
	 * <p>Description:第13列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_12
	 */
	private String mDataProp_12;
	/**
	 * <p>Description:第14列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_13
	 */
	private String mDataProp_13;
	/**
	 * <p>Description:第15列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_14
	 */
	private String mDataProp_14;
	/**
	 * <p>Description:第16列的熟属性名</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields mDataProp_15
	 */
	private String mDataProp_15;
	
	public int getsEcho(){
		return sEcho;
	}
	public void setsEcho(int sEcho){
		this.sEcho = sEcho;
	}
	public int getiDisplayStart(){
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart){
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength(){
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength){
		this.iDisplayLength = iDisplayLength;
	}
	public int getiSortCol_0(){
		return iSortCol_0;
	}
	public void setiSortCol_0(int iSortCol_0){
		this.iSortCol_0 = iSortCol_0;
	}
	public String getsSortDir_0(){
		return sSortDir_0;
	}
	public void setsSortDir_0(String sSortDir_0){
		this.sSortDir_0 = sSortDir_0;
	}
	public String getmDataProp_0(){
		return mDataProp_0;
	}
	public void setmDataProp_0(String mDataProp_0){
		this.mDataProp_0 = mDataProp_0;
	}
	public String getmDataProp_1(){
		return mDataProp_1;
	}
	public void setmDataProp_1(String mDataProp_1){
		this.mDataProp_1 = mDataProp_1;
	}
	public String getmDataProp_2(){
		return mDataProp_2;
	}
	public void setmDataProp_2(String mDataProp_2){
		this.mDataProp_2 = mDataProp_2;
	}
	public String getmDataProp_3(){
		return mDataProp_3;
	}
	public void setmDataProp_3(String mDataProp_3){
		this.mDataProp_3 = mDataProp_3;
	}
	public String getmDataProp_4(){
		return mDataProp_4;
	}
	public void setmDataProp_4(String mDataProp_4){
		this.mDataProp_4 = mDataProp_4;
	}
	public String getmDataProp_5(){
		return mDataProp_5;
	}
	public void setmDataProp_5(String mDataProp_5){
		this.mDataProp_5 = mDataProp_5;
	}
	public String getmDataProp_6(){
		return mDataProp_6;
	}
	public void setmDataProp_6(String mDataProp_6){
		this.mDataProp_6 = mDataProp_6;
	}
	public String getmDataProp_7(){
		return mDataProp_7;
	}
	public void setmDataProp_7(String mDataProp_7){
		this.mDataProp_7 = mDataProp_7;
	}
	public String getmDataProp_9(){
		return mDataProp_9;
	}
	public void setmDataProp_9(String mDataProp_9){
		this.mDataProp_9 = mDataProp_9;
	}
	public String getmDataProp_10(){
		return mDataProp_10;
	}
	public void setmDataProp_10(String mDataProp_10){
		this.mDataProp_10 = mDataProp_10;
	}
	public String getmDataProp_11(){
		return mDataProp_11;
	}
	public void setmDataProp_11(String mDataProp_11){
		this.mDataProp_11 = mDataProp_11;
	}
	public String getmDataProp_12(){
		return mDataProp_12;
	}
	public void setmDataProp_12(String mDataProp_12){
		this.mDataProp_12 = mDataProp_12;
	}
	public String getmDataProp_13(){
		return mDataProp_13;
	}
	public void setmDataProp_13(String mDataProp_13){
		this.mDataProp_13 = mDataProp_13;
	}
	public String getmDataProp_14(){
		return mDataProp_14;
	}
	public void setmDataProp_14(String mDataProp_14){
		this.mDataProp_14 = mDataProp_14;
	}
	public String getmDataProp_15(){
		return mDataProp_15;
	}
	public void setmDataProp_15(String mDataProp_15){
		this.mDataProp_15 = mDataProp_15;
	}
	public String getmDataProp_8() {
		return mDataProp_8;
	}
	public void setmDataProp_8(String mDataProp_8) {
		this.mDataProp_8 = mDataProp_8;
	}
	
}
