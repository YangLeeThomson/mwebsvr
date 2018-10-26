package com.uec.imonitor.task.service;

import java.util.Date;
import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.task.bean.JobFailureEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 失败任务处理服务层 </p> 
 * <p>Author:cjl/陈金梁</p>
 */
public interface IJobFailureService {
	public Boolean add(JobFailureEntity jobStatus) throws RequestParamException;
	
	/**
	 * <br/>Description: 保存
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName 任务名
	 * @param tableName 表名
	 * @param idList 失败任务ids
	 * @param updateTime 更新时间
	 * @param num 执行次数
	 * @return
	 * @throws RequestParamException 
	 */
	public boolean saveFailureJob(String jobName, String tableName,List<Integer> idList,Date updateTime, int num) throws RequestParamException;

	/**
	 * <br/>Description:获取所有的任务列表
	 * <p>Author:jlchen/陈金梁</p>
	 * @return
	 */
	public List<JobFailureEntity> listAll();
	
	/**
	 * <br/>Description:通过任务名获取任务列表
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName
	 * @return
	 * @throws RequestParamException 
	 */
	public List<JobFailureEntity> listByJobName(String jobName) throws RequestParamException;

	/**
	 * <br/>Description: 通过任务名和表名获取任务列表
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 */
	public List<JobFailureEntity> listByJobAndTableName(String jobName, String tableName) throws RequestParamException;
	
	/**
	 * <br/>Description:通过任务名和表名获取前topN条任务列表
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobName
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	public List<JobFailureEntity> listTopNByJobAndTableName(String jobName, String tableName,Integer topN) throws RequestParamException;

	/**
	 * <br/>Description: 根据innerid删除任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @param innerid
	 * @return
	 * @throws RequestParamException 
	 */
	public Boolean deleteJobFailure(int innerid) throws RequestParamException;
	
	/**
	 * <br/>Description:删除任务集
	 * <p>Author:jlchen/陈金梁</p>
	 * @param jobStatusList
	 * @return
	 * @throws RequestParamException 
	 */
	public Boolean deleteJobFailureList(List<JobFailureEntity> jobStatusList) throws RequestParamException;
	
	/**
	 * <br/>Description:通过id获取JobFailureEntity
	 * <p>Author:sq/孙强</p>
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public JobFailureEntity findJobById(int id) throws BaseException,Exception;
}
