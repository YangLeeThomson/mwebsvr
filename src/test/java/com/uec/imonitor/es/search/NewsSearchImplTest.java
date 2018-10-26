//package com.uec.imonitor.es.search;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.uec.inews.common.util.CommonUtil;
//import com.uec.inews.common.util.ESConstantUtil;
//import com.uec.inews.es.bean.params.MatchParams;
//import com.uec.inews.es.bean.params.QueryParams;
//import com.uec.inews.es.bean.params.RangeParams;
//import com.uec.inews.es.bean.params.SortParams;
//import com.uec.inews.es.bean.result.NewsQueryResult;
//import com.uec.inews.es.index.news.INewsIndex;
//import com.uec.inews.es.search.INewsSearch;
//import com.uec.inews.latest.bean.WebpageDetail;
//
//@SuppressWarnings("unused")
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class NewsSearchImplTest {
//	@Autowired
//	private INewsSearch newsSearch;
//	@Autowired
//	private INewsIndex newsIndex;
//	@Test
//	public void test() {
//		// 查询词
//		List<MatchParams> matchList = new ArrayList<>();
//		
////		MatchParams match3 = new MatchParams(new ArrayList<String>(){{add("title");}{add("noTagContent"); }}, new ArrayList<String>(){{add("雷霆骑士"); }}, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND,ESConstantUtil.OPT_OR, ESConstantUtil.ANALYZER_IK_MAX);
////		matchList.add(match3);
////				
//		MatchParams match4 = new MatchParams(new ArrayList<String>(){{add("cusClassificationLevelOne");add("cusClassificationLevelTwo");}}, new ArrayList<String>(){{add("金融新闻"); add("社会");}}, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND,ESConstantUtil.OPT_OR, ESConstantUtil.ANALYZER_NOT);
//		matchList.add(match4);
//				
////		@SuppressWarnings("serial")
////		MatchParams match2 = new MatchParams(new ArrayList<String>(){{add("isDeleted"); }}, new ArrayList<String>(){{add("0"); }}, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
////		matchList.add(match2);
//
//		List<RangeParams> rangeParamsList = new ArrayList<>();
//		RangeParams range = new RangeParams("releaseDatetime", CommonUtil.getTodayZero(), new Date());
//		rangeParamsList.add(range);
//
//		// 查询index
//		
////		String[] indexArray = { "latest.20160908" }; // 索引库集
////		String[] typeArray = { "test_latest" };// 类型集
//		int start = 0;// 起始条
//		int size = 700; // 条数
//
//		//排序
//		SortParams sp = new SortParams("_score", ESConstantUtil.SORT_DESC);
//		List<SortParams> spl = new ArrayList<SortParams>();
//		spl.add(sp);
//		
//		QueryParams qp = new QueryParams( matchList, rangeParamsList, null, start, size, spl);
//		String[] StringArray = {"classification","innerid"};
//		qp.setReturnFieldsArray(StringArray);
//		NewsQueryResult wr = newsSearch.textSearcher(qp);
//		System.out.println("total = " + wr.getTotal() + ", hitSize = " + wr.getWebpageList().size() + ", timeTook="
//				+ wr.getTimeTook());
//
//		List<WebpageDetail> webList = wr.getWebpageList();
//		List<WebpageDetail> updateList = new ArrayList<>();
//		for(int i = 0 ; i < webList.size(); i++ ){
//			WebpageDetail w = webList.get(i);
//			w.setSourceCrawlLevelOne("传统媒体");
//			newsIndex.updateWebpageDetail(w);
//		}
//			
//	}
//
//	@Test
//	public void testRangeQuery() {
//		
//	}
//	
//}
