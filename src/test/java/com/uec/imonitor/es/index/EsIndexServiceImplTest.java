package com.uec.imonitor.es.index;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.es.service.IEsIndexService;

import junit.framework.Assert;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsIndexServiceImplTest {

	@Autowired
	private IEsIndexService esIndexService;
	
	@Test
	public void testIndexNewsSpreadingSetting() {
		esIndexService.indexNewsSpreadingSetting();
	}

	@Test
	public void testIndexRequsetNewsSetting() {
		esIndexService.indexRequsetNewsSetting();
	}

	@Test
	public void testEsIndexSetting() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitAllRequestNews() throws BaseException, Exception {
		Assert.assertEquals(true, esIndexService.initAllRequestNews());
	}

	@Test
	public void testInitAllNewsSpreading() throws BaseException, Exception {
		Assert.assertEquals(true, esIndexService.initAllNewsSpreading());
	}

	@Test
	public void testSetIndexAliase() {
		fail("Not yet implemented");
	}

}
