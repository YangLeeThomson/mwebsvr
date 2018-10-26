package com.uec.imonitor.es.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.common.util.ESConstantUtil;
import com.uec.imonitor.es.bean.params.MatchParams;
import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.params.RangeParams;
import com.uec.imonitor.es.bean.params.SortParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.request.bean.RequestNewsDetail;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsSearchImplTest {
	@Autowired
	private IDataSearch dataSearch;
	@Value("${imonitor.es.index.request.news.name.alias}")
	private String requestNewsName;
	@Value("${imonitor.es.index.spreading.name.alias}")
	private String newsSpreadingName;

	@Value("${imonitor.es.index.request.news.type}")
	private String requestNewsType;
	@Value("${imonitor.es.index.spreading.type}")
	private String newsSpreadingType;
	@Test
	public void testTextSearcher() {
		// 查询词
		List<MatchParams> matchList = new ArrayList<>();
		
		MatchParams match3 = new MatchParams(new ArrayList<String>(){{add("title");}{add("content"); }}, new ArrayList<String>(){{add("仕途被前妻终结"); }}, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND,ESConstantUtil.OPT_OR, ESConstantUtil.ANALYZER_IK_MAX);
		matchList.add(match3);
//		MatchParams match3 = new MatchParams(new ArrayList<String>(){{add("title");}{add("content"); }}, new ArrayList<String>(){{add("现金贷自查摸底"); }}, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND,ESConstantUtil.OPT_OR, ESConstantUtil.ANALYZER_IK_MAX);
//		matchList.add(match3);
//				
//		MatchParams match4 = new MatchParams(new ArrayList<String>(){{add("cusClassificationLevelOne");add("cusClassificationLevelTwo");}}, new ArrayList<String>(){{add("金融新闻"); add("社会");}}, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND,ESConstantUtil.OPT_OR, ESConstantUtil.ANALYZER_NOT);
//		matchList.add(match4);
				
//		@SuppressWarnings("serial")
//		MatchParams match2 = new MatchParams(new ArrayList<String>(){{add("isDeleted"); }}, new ArrayList<String>(){{add("0"); }}, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
//		matchList.add(match2);

		List<RangeParams> rangeParamsList = new ArrayList<>();
		RangeParams range = new RangeParams("createDatetime", DateUtils.addDays(new Date(), -12), new Date());
		rangeParamsList.add(range);

		// 查询index
		
		String[] indexArray = { requestNewsName }; // 索引库集
		String[] typeArray = { requestNewsType };// 类型集
//		String[] indexArray = { newsSpreadingName }; // 索引库集
//		String[] typeArray = { newsSpreadingType };// 类型集
		int start = 0;// 起始条
		int size = 700; // 条数

		//排序
		SortParams sp = new SortParams("_score", ESConstantUtil.SORT_DESC);
		List<SortParams> spl = new ArrayList<SortParams>();
		spl.add(sp);
//		QueryParams<RequestNewsDetail>  qp = new QueryParams<RequestNewsDetail>(RequestNewsDetail.class);
		QueryParams<RequestNewsDetail>  qp= new QueryParams<RequestNewsDetail>( matchList, rangeParamsList, null, start, size, spl,RequestNewsDetail.class);
		qp.setIndexArray(indexArray);
		qp.setTypeArray(typeArray);
		BaseQueryResult<RequestNewsDetail> wr = dataSearch.textSearcher(qp);
		System.out.println("total = " + wr.getTotal() + ", hitSize = " + wr.getResultList().size() + ", timeTook="
				+ wr.getTimeTook());

		List<RequestNewsDetail> webList = wr.getResultList();
		List<RequestNewsDetail> updateList = new ArrayList<>();
		for(int i = 0 ; i < webList.size(); i++ ){
			RequestNewsDetail w = webList.get(i);
			System.out.println(CommonUtil.toJson(w) +"\n"); 
		}
	}

	@Test
	public void testPhraseSearcher() {
		fail("Not yet implemented");
	}

	@Test
	public void testTextHistogramSearcher() {
		fail("Not yet implemented");
	}

	@Test
	public void testMatchAllSearch() {
		fail("Not yet implemented");
	}

}
