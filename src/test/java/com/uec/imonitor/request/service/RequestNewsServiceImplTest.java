package com.uec.imonitor.request.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.request.bean.RequestNewsEntity;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestNewsServiceImplTest {
	@Autowired
	private IRequestNewsService requestNewsService;
	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByRequestId() throws BaseException, Exception {
		RequestNewsEntity re = requestNewsService.findById(83928);
		System.out.println("**************");
		System.out.println(re.getReprintNewsList());
		System.out.println(CommonUtil.toJson(re)); 
	}

	@Test
	public void testFindByWebpageCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindDetailByRequestNewsEntity() {
		fail("Not yet implemented");
	}

	@Test
	public void testListDetailByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testPageRequestNewsEs() {
		fail("Not yet implemented");
	}

	@Test
	public void testPageRequestNews() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertAndUpdateIndexFailureJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertRequestNewsIndexJob() {
		requestNewsService.insertRequestNewsIndexJob(500);
	}

	@Test
	public void testDeleteRequestNewsJob() {
		requestNewsService.deleteRequestNewsJob(3000);
	}

	@Test
	public void testUpdateRequestNewsIndexJob() {
		requestNewsService.updateRequestNewsIndexJob(500);
	}

}
