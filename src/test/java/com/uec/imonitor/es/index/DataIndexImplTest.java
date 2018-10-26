package com.uec.imonitor.es.index;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataIndexImplTest {
	@Autowired
	private IDataIndex dataIndex;
	
	@Value("${imonitor.es.index.request.news.name}")
	private String requestNewsName;
	@Value("${imonitor.es.index.spreading.name}")
	private String newsSpreadingName;

	@Value("${imonitor.es.index.request.news.type}")
	private String requestNewsType;
	@Value("${imonitor.es.index.spreading.type}")
	private String newsSpreadingType;
	
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStringStringBaseEntity() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testBulkAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStringStringStringMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testBulkUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testBulkDelete() {
		List<String> primaryKeyList = new ArrayList<>();
		primaryKeyList.add("73004"); 
		dataIndex.bulkDelete(requestNewsName, requestNewsType, primaryKeyList);
	}

}
