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
@Table(name="news_peopledaily_video")
public class PeoplesDailyVideosEntity {
    @Id//指明这个属性映射为数据库的主键
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="innerid",nullable=false)
    private Integer innerid;

    @Column(name="title")
    private  String title;

    @Column(name="description")
    private  String description;

    @Column(name="coverpic")
    private  String coverpic;

    @Column(name="video_size")
    private  String videoSize;

    @Column(name="duration")
    private  String duration;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCoverpic() {
		return coverpic;
	}

	public void setCoverpic(String coverpic) {
		this.coverpic = coverpic;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideoSize() {
		return videoSize;
	}

	public void setVideoSize(String videoSize) {
		this.videoSize = videoSize;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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


