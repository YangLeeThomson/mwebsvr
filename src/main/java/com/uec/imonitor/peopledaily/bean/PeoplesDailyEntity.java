package com.uec.imonitor.peopledaily.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p>
 * <p>Description:  </p>
 * <p>Author:xkwang/王西坤</p>
 */
@Entity
@Table(name="news_peopledaily")
public class PeoplesDailyEntity {
//    @Id//指明这个属性映射为数据库的主键
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="innerid",nullable=false)
//    private Integer innerid;
	@Id
	@Column(name="webpage_code",nullable=false)
    private String webpageCode;
    
    @Column(name="title")
    private String title;

    @Column(name="tags")
    private String tags;


    @Column(name="authors")
    private String authors;

    @Column(name="source")
    private String source;

    @Column(name="news_id")
    private String news_id;

    @Column(name="channel")
    private String channel;

    @Column(name="original")
    private Integer original;

    @Column(name="keywords")
    private String keywords;

    @Column(name="summary")
    private String summary;

    @Column(name="content")
    private String content;

    @Column(name="notag_content")
    private String noTagContent;

    @Column(name="pub_time")
    private String pub_time;

    @Column(name="m_url")
    private String m_url;

    @Column(name="url")
    private String url;

    @Column(name="source_url")
    private String source_url;

    @Column(name="entity")
    private String entity;
    
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
	
	
	@Column(name="is_core")
	private Integer isCore;
	
	@Column(name="core_id")
	private String coreId;
	
	@Column(name="request_body")
	private String requestBody;
	
	@Column(name="org")
	private String org;


	@Column(name="contenttype")
	private  Integer contenttype; // 0 是普通文章，　1为专题

	@Column(name="vidio_img")
	private  String vidioImg;

	@Column(name="to_top")
	private  Integer to_top;

	@Column(name="sort")
	private  Integer sort;

	public Integer getTo_top() {
		return to_top;
	}

	public void setTo_top(Integer to_top) {
		this.to_top = to_top;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	//	@PrePersist//在对实体数据进行数据库更新操作之前，调用实体的 @PreUpdate 回调方法。这些数据库操作可以在更新实体状态时发生，也可以在将状态刷新到数据库（可能位于事务结尾）时发生。请注意：此回调是否在持久保存实体并随后在单个事务中修改该实体时发生，均依赖于实施。
//	protected void prePersist() {
//		Date nowDate =  new Date();
//		this.createDatetime = nowDate;
//		this.updateDatetime = nowDate;
//	}
	public String getVidioImg() {
	return vidioImg;
}

	public void setVidioImg(String vidioImg) {
		this.vidioImg = vidioImg;
	}

	public Integer getContenttype() {
		return contenttype;
	}

	public void setContenttype(Integer contenttype) {
		this.contenttype = contenttype;
	}

	@PreUpdate//在对实体数据进行数据库更新操作之前。
	protected void preUpdate() {
		this.updateDatetime = new Date();
	}

	
	
	public String getOrg() {
		return org;
	}



	public void setOrg(String org) {
		this.org = org;
	}



	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public Integer getIsCore() {
		return isCore;
	}

	public void setIsCore(Integer isCore) {
		this.isCore = isCore;
	}

	

	public String getCoreId() {
		return coreId;
	}

	public void setCoreId(String coreId) {
		this.coreId = coreId;
	}

	public String getWebpageCode() {
		return webpageCode;
	}

	public void setWebpageCode(String webpageCode) {
		this.webpageCode = webpageCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getOriginal() {
		return original;
	}

	public void setOriginal(Integer original) {
		this.original = original;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNoTagContent() {
		return noTagContent;
	}

	public void setNoTagContent(String noTagContent) {
		this.noTagContent = noTagContent;
	}

	public String getPub_time() {
		return pub_time;
	}

	public void setPub_time(String pub_time) {
		this.pub_time = pub_time;
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

	public String getSource_url() {
		return source_url;
	}

	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
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
