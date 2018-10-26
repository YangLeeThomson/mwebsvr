package com.uec.imonitor.peopledaily.bean;

import java.util.Date;

import javax.persistence.*;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p>
 * <p>Description:  </p>
 * <p>Author:xkwang/王西坤</p>
 */
@Entity
@Table(name="news_peopledaily_relate")
public class PeoplesDailyRelatedEntity {


    @Id//指明这个属性映射为数据库的主键
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="innerid",nullable=false)
    private Integer innerid;

    @Column(name="channel")
    private  String channel;

    @Column(name="news_id")
    private  String news_id;

    @Column(name="title")
    private  String title;

    @Column(name="m_url")
    private  String m_url;

    @Column(name="url")
    private  String url;

    @Column(name="webpage_code")
    private String webpageCode;    //新闻列表id
    
    @Temporal(TemporalType.TIMESTAMP)
   	@Column(name="create_datetime")
   	private Date createDatetime;
       
       @Temporal(TemporalType.TIMESTAMP)
   	@Column(name="update_datetime")
   	private Date updateDatetime;
   	
   	@Column(name="status")
   	private Integer status;
   	
   	
   	@Column(name="is_delete")
   	private Integer isDelete;
   	
   	@PrePersist//在对实体数据进行数据库更新操作之前，调用实体的 @PreUpdate 回调方法。这些数据库操作可以在更新实体状态时发生，也可以在将状态刷新到数据库（可能位于事务结尾）时发生。请注意：此回调是否在持久保存实体并随后在单个事务中修改该实体时发生，均依赖于实施。
	protected void prePersist() {
		Date nowDate =  new Date();
		this.createDatetime = nowDate;
		this.updateDatetime = nowDate;
	}
	
	@PreUpdate//在对实体数据进行数据库更新操作之前。
	protected void preUpdate() {
		this.updateDatetime = new Date();
	}

	public Integer getInnerid() {
		return innerid;
	}

	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getM_url() {
		return m_url;
	}

	public void setM_url(String m_url) {
		this.m_url = m_url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWebpageCode() {
		return webpageCode;
	}

	public void setWebpageCode(String webpageCode) {
		this.webpageCode = webpageCode;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
    
   
  	
  	
  	
}
