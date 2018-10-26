package com.uec.imonitor.peopledaily.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.news.utils.TxtUtil;
import com.uec.imonitor.peopledaily.bean.PeoplesDaily;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;

public interface IPeoplesDailyService {
	
	public List<PeoplesDailyEntity> savePeopleDaily(String json)throws BaseException, Exception;

	public Page<PeoplesDailyEntity> pagePeopleNews(DataTablesRequestEntity aoData) throws BaseException, Exception;
	
	public PeoplesDailyEntity findByWebpageCode(String webpageCode) throws BaseException, Exception;
	
	public PeoplesDailyEntity peoplesDailyOpera(String webpageCode) throws BaseException, Exception;
	
	public void dataToAPICloud();
	public void dataToImedia();

	public Map<String, String> groupByOrg() throws Exception;

	public Map<String, String> groupByChannel() throws Exception;

	public Map<String, String>  pushDataChannelHour() throws Exception;

	public Map<String, String>  pushDataOrgHour() throws Exception;
	public Map<String, String> groupByOrgAll() throws Exception;

	public Map<String, String> groupByChannelAll() throws Exception;

	public Map<String, String>  pushDataChannelHourAll() throws Exception;

	public Map<String, String>  pushDataOrgHourAll() throws Exception;

	public PeoplesDailyEntity verifyArticle(String webpageCode) throws BaseException, Exception;

	public String pushDataToCloud(List<String> contentList,String postUrl) throws Exception ;

	public void dataToImediaTest();

	public PeoplesDailyEntity saveNewsForMediaTest() throws Exception;
}
