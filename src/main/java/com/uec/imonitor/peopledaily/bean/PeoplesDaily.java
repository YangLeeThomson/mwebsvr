package com.uec.imonitor.peopledaily.bean;


import java.util.List;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p>
 * <p>Description:  </p>
 * <p>Author:xkwang/王西坤</p>
 */
public class PeoplesDaily {

    private String title;
    private String tags;
    private String authors;
    private String source;
    private String news_id;
    private String channel;
    private Integer original;
    private String keywords;
    private String summary;
    private String content;
    private List<PeoplesDailyImg> image_list;

    private String pub_time;
    private String m_url;
    private String url;
    private String source_url;

    private String entity;
    
    private String requestBody;
    private String org;

    //1是审核，0是不审核
    private Integer verify;

    private  Integer contenttype;

    private  String vidioImg;

    private  Integer to_top;
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

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
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

	private List<PeoplesDailyRelated> related;

    private List<PeoplesDailyVideos> videos;

    public List<PeoplesDailyVideos> getVideos() {
        return videos;
    }

    public void setVideos(List<PeoplesDailyVideos> videos) {
        this.videos = videos;
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

    public List<PeoplesDailyImg> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<PeoplesDailyImg> image_list) {
        this.image_list = image_list;
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

    public List<PeoplesDailyRelated> getRelated() {
        return related;
    }

    public void setRelated(List<PeoplesDailyRelated> related) {
        this.related = related;
    }
}
