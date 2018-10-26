package com.uec.imonitor.news.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uec.imonitor.peopledaily.controller.HttpManager;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyJPARepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleTest {
	
	@Autowired
	private IPeoplesDailyJPARepository peoplesDailyJPARepository;
	
	@Test
	public void pageNews() {
		
		try {
			String task = "";
			CloseableHttpClient httpClient = HttpManager.getHttpClient();
			//HttpPost httpPost = new HttpPost("http://newspaper.liangzibiao.com/home/api/receive");
			HttpPost httpPost = new HttpPost("http://114.115.148.171:8086/peoplesdaily");
			httpPost.addHeader("Content-Type","application/json;charset=utf-8");
			httpPost.setEntity(new StringEntity(task, "utf-8"));
			CloseableHttpResponse response;
			response = httpClient.execute(httpPost);
			org.apache.http.HttpEntity entity = response.getEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
