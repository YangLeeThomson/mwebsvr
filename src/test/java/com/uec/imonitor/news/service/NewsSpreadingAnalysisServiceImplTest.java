package com.uec.imonitor.news.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsSpreadingAnalysisServiceImplTest {
	@Autowired
	private INewsSpreadingAnalysisService newsSpreadingAnalysisService;
	@Test
	public void testFindByWebpageCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByNewsId() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByNewsIdAndNotMarked() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindDetailByNewsSpreadingAnalysisEntity() {
		fail("Not yet implemented");
	}

	@Test
	public void testListNewsSpreadingAnalysisDetailByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testPageReprintNews() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByNewsIdAndCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertAndUpdateIndexFailureJob() {
		newsSpreadingAnalysisService.insertAndUpdateIndexFailureJob();
	}

	@Test
	public void testInsertSpreadingAnalysisIndexJob() {
		newsSpreadingAnalysisService.insertSpreadingAnalysisIndexJob(500);
	}

	@Test
	public void testDeletedSpreadingAnalysisIndexJob() {
		newsSpreadingAnalysisService.deletedSpreadingAnalysisIndexJob(500);
	}

	@Test
	public void testUpdateSpreadingAnalysisIndexJob() {
		newsSpreadingAnalysisService.updateSpreadingAnalysisIndexJob(500);
	}

}
