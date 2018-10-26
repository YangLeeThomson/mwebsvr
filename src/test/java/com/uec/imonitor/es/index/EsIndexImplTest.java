package com.uec.imonitor.es.index;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uec.imonitor.es.bean.index.IndexConfig;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsIndexImplTest {
	@Autowired
	private IEsIndex esIndex;

	@Value("${imonitor.es.index.request.news.name}")
	private String requestNewsName;
	@Value("${imonitor.es.index.spreading.name}")
	private String newsSpreadingName;

	@Value("${imonitor.es.index.request.news.type}")
	private String requestNewsType;
	@Value("${imonitor.es.index.spreading.type}")
	private String newsSpreadingType;
	
	@Value("${imonitor.es.cluster.shards.num}")
	private int shardsNum;
	@Value("${imonitor.es.cluster.replicas.num}")
	private int replicasNum;
	@Test
	public void testIndexRequestNewsSetting(){
		IndexConfig iConfig = new IndexConfig();
		iConfig.setIndexName(requestNewsName);
		iConfig.setTypeName(requestNewsType);
		iConfig.setShardsNum(shardsNum);
		iConfig.setReplicasNum(replicasNum); 
		esIndex.createIndex(iConfig);
	}
	
	@Test
	public void testIndexNewsSpreadingSetting(){
		IndexConfig iConfig = new IndexConfig();
		iConfig.setIndexName(newsSpreadingName);
		iConfig.setTypeName(newsSpreadingType);
		iConfig.setShardsNum(shardsNum);
		iConfig.setReplicasNum(replicasNum); 
		esIndex.createIndex(iConfig);
	}


}
