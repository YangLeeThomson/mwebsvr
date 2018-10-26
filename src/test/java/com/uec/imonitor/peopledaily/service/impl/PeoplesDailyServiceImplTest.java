package com.uec.imonitor.peopledaily.service.impl;

import com.alibaba.fastjson.JSON;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.peopledaily.bean.PeoplesDaily;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;
import com.uec.imonitor.peopledaily.controller.HttpManager;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyJPARepository;
import com.uec.imonitor.peopledaily.service.IPeoplesDailyService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.DelayQueue;

import static org.junit.Assert.*;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p>
 * <p>Description:  </p>
 * <p>Author:xkwang/王西坤</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeoplesDailyServiceImplTest {

    @Autowired
    private IPeoplesDailyService peoplesDailyService;
    @Autowired
    private IPeoplesDailyJPARepository peoplesDailyJPARepository;

    @Test
    public void receiveXML () throws Exception {
       String jsonString = "[{\"errorCode\":1,\"errorMsg\":\"Analysis error!\",\"newsId\":\"123\"},{\"errorCode\":1,\"errorMsg\":\"Analysis error!\",\"newsId\":\"fer\"}]";
       //JSONObject jsonObject = new JSONObject(jsonString);
       // com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(jsonString);
       JSONArray jsonArray = new JSONArray(jsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            String errorCode = jsonArray.getJSONObject(i).getString("errorCode");
            String errorMsg = jsonArray.getJSONObject(i).getString("errorMsg");
            String newsId = jsonArray.getJSONObject(i).getString("newsId");
            System.out.println(errorCode + errorMsg + newsId);
        }
    }

    @Test
    public  void  replaceString(){
        String src = "南京市玄武区北京东路徐州市鼓楼区戏马台";
        src = src.replaceAll("(江苏省|玄武区|鼓楼区)", "");
        System.out.println(src);

        String src2 = "南京市玄武区北京东路徐州市鼓楼区戏马台";
        src2 = src2.replaceAll("(?:江苏省|玄武区|鼓楼区)", "");
        System.out.println(src2);
    }

    @Ignore
    @Before
    public void testMdeiaTestBefore() throws Exception {
        List<PeoplesDailyEntity> peoplesDailyEntityList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println("===第" + i + "次");
            PeoplesDailyEntity peoplesDailyEntity = new PeoplesDailyEntity();
            PeoplesDailyEntity peoplesDailyEntityTmp = new PeoplesDailyEntity();
            peoplesDailyEntity.setIsDelete(0);
            peoplesDailyEntity.setIsCore(1);
            peoplesDailyEntity.setContenttype(1);
            peoplesDailyEntity.setContent(System.currentTimeMillis() + "基于微信公众号开发的h5页面，使用jssdk成功分享之后，被分享的页面再次被分享的时候jssdk出错，出现这种错误的一种可能就是，url的错误假设我们的页面叫做test.html,我们将页面放在test.com域名下面，那么我们去微信后台计算签名的url应该是这样的");
            peoplesDailyEntity.setPub_time(CommonUtil.DateToStr(new Date()));
            peoplesDailyEntity.setSummary("summary---基于微信公众号开发的h5页面，使用jssdk成功分享之后");
            peoplesDailyEntity.setOriginal(1);
            peoplesDailyEntity.setIsDelete(0);
            peoplesDailyEntity.setStatus(0);
            peoplesDailyEntity.setAuthors("UEC" + System.currentTimeMillis());
            peoplesDailyEntity.setTitle("Title" + System.currentTimeMillis());
            String newsId_webpageCode = "News_id" + System.currentTimeMillis();
            peoplesDailyEntity.setNews_id(newsId_webpageCode);
            peoplesDailyEntity.setCoreId("core_id" + System.currentTimeMillis());
            peoplesDailyEntity.setChannel("Top news");
            peoplesDailyEntity.setCreateDatetime(new Date());
            peoplesDailyEntity.setEntity("UEC,荣之联");
            peoplesDailyEntity.setKeywords("Keywords");
            peoplesDailyEntity.setM_url("m.google.com");
            peoplesDailyEntity.setUrl("www.google.com");
            peoplesDailyEntity.setNoTagContent("基于微信公众号开发的h5页面，使用jssdk成功分享之后，被分享的页面再次被分享的时候jssdk出错");
            peoplesDailyEntity.setSort(1);
            peoplesDailyEntity.setWebpageCode(newsId_webpageCode);
            peoplesDailyEntity.setTo_top(1);
            peoplesDailyEntity.setVidioImg("vidioImg");
            String requsetString = CommonUtil.toJson(peoplesDailyEntity);
            BeanUtils.copyProperties(peoplesDailyEntity, peoplesDailyEntityTmp);
            peoplesDailyEntityTmp.setRequestBody(requsetString);
            //peoplesDailyEntityList.add(peoplesDailyEntityTmp);
            peoplesDailyService.saveNewsForMediaTest();
        }
    }

    @Test
    public void testMdeiaTest() throws Exception{

        for (int i = 0; i < 3; i++) {
            peoplesDailyService.saveNewsForMediaTest();
        }

       /* for (int i = 0; i < 3; i++) {
            peoplesDailyService.dataToImediaTest();
        }*/
    }

}