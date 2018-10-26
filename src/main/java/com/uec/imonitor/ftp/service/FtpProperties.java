package com.uec.imonitor.ftp.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration  
@ConfigurationProperties(prefix = "Ftp")  
@PropertySource("ftpaccount.properties") 
//@PropertySource("classpath:ftpaccount.properties") 

public class FtpProperties {
	
	private String ZGJYB_HOST;
	private Integer ZGJYB_PORT;
	private String ZGJYB_USERNAME;
	private String ZGJYB_PASSWORD;
	private String ZGJYB_WEBNAME;
	private String ZGJYB_REQUESTID;
	private String ZGJYB_SERVERPATH;
	private String ZGJYB_LOCALPATH;
	private String ZGJYB_COPYPATH;
	
	public String getZGJYB_HOST() {
		return ZGJYB_HOST;
	}
	public void setZGJYB_HOST(String zGJYB_HOST) {
		ZGJYB_HOST = zGJYB_HOST;
	}
	public Integer getZGJYB_PORT() {
		return ZGJYB_PORT;
	}
	public void setZGJYB_PORT(Integer zGJYB_PORT) {
		ZGJYB_PORT = zGJYB_PORT;
	}
	public String getZGJYB_USERNAME() {
		return ZGJYB_USERNAME;
	}
	public void setZGJYB_USERNAME(String zGJYB_USERNAME) {
		ZGJYB_USERNAME = zGJYB_USERNAME;
	}
	public String getZGJYB_PASSWORD() {
		return ZGJYB_PASSWORD;
	}
	public void setZGJYB_PASSWORD(String zGJYB_PASSWORD) {
		ZGJYB_PASSWORD = zGJYB_PASSWORD;
	}
	public String getZGJYB_WEBNAME() {
		return ZGJYB_WEBNAME;
	}
	public void setZGJYB_WEBNAME(String zGJYB_WEBNAME) {
		ZGJYB_WEBNAME = zGJYB_WEBNAME;
	}
	public String getZGJYB_REQUESTID() {
		return ZGJYB_REQUESTID;
	}
	public void setZGJYB_REQUESTID(String zGJYB_REQUESTID) {
		ZGJYB_REQUESTID = zGJYB_REQUESTID;
	}
	public String getZGJYB_SERVERPATH() {
		return ZGJYB_SERVERPATH;
	}
	public void setZGJYB_SERVERPATH(String zGJYB_SERVERPATH) {
		ZGJYB_SERVERPATH = zGJYB_SERVERPATH;
	}
	public String getZGJYB_LOCALPATH() {
		return ZGJYB_LOCALPATH;
	}
	public void setZGJYB_LOCALPATH(String zGJYB_LOCALPATH) {
		ZGJYB_LOCALPATH = zGJYB_LOCALPATH;
	}
	public String getZGJYB_COPYPATH() {
		return ZGJYB_COPYPATH;
	}
	public void setZGJYB_COPYPATH(String zGJYB_COPYPATH) {
		ZGJYB_COPYPATH = zGJYB_COPYPATH;
	}
	
	
	
	

}
