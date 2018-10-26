package com.uec.imonitor.auth;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class MyWebSessionManager extends DefaultWebSessionManager {

	public MyWebSessionManager() {
		super();
		this.getSessionIdCookie().setName("JSESSIONID_SHIRO");
	}

	
}
