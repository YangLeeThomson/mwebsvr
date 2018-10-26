package com.uec.imonitor.es.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.NewsStatusEntity;
import com.uec.imonitor.es.dao.INewsStatusJPARepository;
import com.uec.imonitor.es.service.INewsStatusService;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司 http://www.ronglian.com</p>
 * <p>Description: 记录状态服务层 </p>
 * <p>Author:cjl/陈金梁</p>
 */
@Service("newsStatusService")
@Transactional(value = "transactionManager")
public class NewsStatusServiceImpl implements INewsStatusService{

	@Autowired
	private INewsStatusJPARepository newsStatusJPARepository;

	/**
	 * <p>Description:新增</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: add
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.es.service.IEsStatusService#add(com.uec.inews.es.bean.EsStatusEntity)
	 */
	@Override
	public NewsStatusEntity add(NewsStatusEntity entity) throws BaseException,Exception{
		if(null == entity){ throw new RequestParamException(new String[]{"entity"}); }
		NewsStatusEntity statusEntity = newsStatusJPARepository.save(entity);
		return statusEntity;
	}

	/**
	 * <p>Description:更新</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: update
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.es.service.IEsStatusService#addOrUpdate(com.uec.inews.es.bean.EsStatusEntity)
	 */
	@Override
	public NewsStatusEntity addOrUpdate(NewsStatusEntity entity) throws BaseException,Exception{
		if(null == entity){ throw new RequestParamException(new String[]{"entity"}); }
		List<NewsStatusEntity> tempList = newsStatusJPARepository.listByTableNameAndStatusAndWebpageCode(entity.getTableName(),entity.getStatus(),entity.getWebpageCode());
		NewsStatusEntity statusEntity = new NewsStatusEntity();
		if(CollectionUtils.isEmpty(tempList)){
			statusEntity = newsStatusJPARepository.save(entity);
		}else{
			statusEntity = tempList.get(0);
			statusEntity.setLastModifyTime(entity.getLastModifyTime());
			statusEntity.setRecordId(entity.getRecordId());
			statusEntity.setWebpageCode(entity.getWebpageCode());
			statusEntity = newsStatusJPARepository.save(statusEntity);
		}
		return statusEntity;
	}

	/**
	 * <p>Description:通过表名获取所有变化的数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: listByTableName
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 * @see com.uec.inews.es.service.IEsStatusService#listByTableName(java.lang.String)
	 */
	@Override
	public List<NewsStatusEntity> listByTableName(String tableName) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableName(tableName);
		return esStatusList;
	}

	/**
	 * <p>Description:通过表名获取TopN数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: listByTableName
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 * @see com.uec.inews.es.service.IEsStatusService#listByTableName(java.lang.String,
	 *      int)
	 */
	@Override
	public List<NewsStatusEntity> listByTableName(String tableName,int topN) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		if(topN < 0){ throw new RequestParamException(new String[]{"topN"}); }
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameTopN(tableName,topN);
		return esStatusList;
	}

	/**
	 * <p>Description:通过表名获取插入的数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: listInsertRecordsByTableName
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 * @see com.uec.inews.es.service.IEsStatusService#listInsertRecordsByTableName(java.lang.String)
	 */
	@Override
	public List<NewsStatusEntity> listInsertRecordsByTableName(String tableName) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatus(tableName,0);
		return esStatusList;
	}

	/**
	 * <br/>Description:通过表名获取topN的插入数据
	 * <p>Author:jlchen/陈金梁</p>
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	@Override
	public List<NewsStatusEntity> listTopNInsertRecordsByTableName(String tableName,int topN) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		if(topN < 0){ throw new RequestParamException(new String[]{"topN"}); }
		List<Integer> statusList = new ArrayList<>();
		statusList.add(0);
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName,statusList,topN);
		return esStatusList;
	}

	/**
	 * <p>Description:通过表名获取topN的新增和更新的数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: listTopInsertAndUpdateByTable
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 * @see com.uec.inews.es.service.IEsStatusService#listTopInsertAndUpdateByTable(java.lang.String,
	 *      int)
	 */
	@Override
	public List<NewsStatusEntity> listTopInsertAndUpdateByTable(String tableName,int topN) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		if(topN < 0){ throw new RequestParamException(new String[]{"topN"}); }
		List<Integer> statusList = new ArrayList<>();
		statusList.add(0);
		statusList.add(2);
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName,statusList,topN);
		return esStatusList;
	}

	/**
	 * <p>Description:通过表名获取所有的update的数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: listUpdateRecordsByTableName
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 * @see com.uec.inews.es.service.IEsStatusService#listUpdateRecordsByTableName(java.lang.String)
	 */
	@Override
	public List<NewsStatusEntity> listUpdateRecordsByTableName(String tableName) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatus(tableName,2);
		return esStatusList;
	}

	/**
	 * <br/>Description:通过表名获取topN的update的数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * 
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	@Override
	public List<NewsStatusEntity> listTopNUpdateRecordsByTableName(String tableName,int topN) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		if(topN < 0){ throw new RequestParamException(new String[]{"topN"}); }
		List<Integer> statusList = new ArrayList<>();
		statusList.add(2);
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName,statusList,topN);
		return esStatusList;
	}
	


	/**
	 * <br/>Description:通过表名获取topN的deleted的数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * 
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	@Override
	public List<NewsStatusEntity> listTopNDeletedRecordsByTableName(String tableName,int topN) throws RequestParamException{
		if(null == tableName){ throw new RequestParamException(new String[]{"tableName"}); }
		if(topN < 0){ throw new RequestParamException(new String[]{"topN"}); }
		List<Integer> statusList = new ArrayList<>();
		statusList.add(1);
		List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName,statusList,topN);
		return esStatusList;
	}

	/**
	 * <p>Description:通过id删除数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: deleteEsStatus
	 * @param innerid
	 * @return
	 * @see com.uec.inews.es.service.IEsStatusService#deleteEsStatus(int)
	 */
	@Override
	public Boolean deleteEsStatus(int innerid){
		newsStatusJPARepository.delete(innerid);
		return true;
	}

	/**
	 * <p>Description:通过ids删除数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * 
	 * @Title: deleteEsStatusList
	 * @param esStatusList
	 * @return
	 * @throws RequestParamException 
	 * @see com.uec.inews.es.service.IEsStatusService#deleteEsStatusList(java.util.List)
	 */
	@Override
	public Boolean deleteEsStatusList(List<NewsStatusEntity> esStatusList) throws RequestParamException{
		if(null == esStatusList){ throw new RequestParamException(new String[]{"esStatusList"}); }
		newsStatusJPARepository.deleteInBatch(esStatusList);
		return true;
	}

}
