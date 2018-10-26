
package com.uec.imonitor.portal.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;
import com.uec.imonitor.config.service.IConfigPageParamService;
import com.uec.imonitor.config.service.IConfigTenantParamService;
import com.uec.imonitor.tenant.bean.TenantEntity;
import com.uec.imonitor.tenant.service.ITenantService;
import com.uec.imonitor.user.bean.OrgEntity;
import com.uec.imonitor.user.bean.OrgUserEntity;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.service.IOrgService;
import com.uec.imonitor.user.service.IUserService;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司 http://www.ronglian.com</p>
 * <p>Description: 页面跳转controller </p>
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Scope("prototype")
@Controller
@RequestMapping(value = "")
public class PortalController extends BaseController{
	
	private Log logger = LogFactory.getLog(PortalController.class);
	
    @Autowired
	@Qualifier("userService")
	private IUserService userService;
    
    @Autowired
	@Qualifier("orgService")
	private IOrgService orgService;
    
    @Autowired
	@Qualifier("tenantService")
	private ITenantService tenantService;
    
    @Autowired
	@Qualifier("configTenantParamService")
	private IConfigTenantParamService configTenantParamService;
    
	@Autowired
	@Qualifier("configPageParamService")
	private IConfigPageParamService configPageParamService;
	
	@Value("${inews.image.server.address}")
	private String inewsImageServer;
	
	/**
	 * <br/>Description:传播监测登录页
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = {"/login","/{tenantMark}/login"},method = RequestMethod.GET)
	public String login(Model model) throws BaseException,Exception {
		//获取上次请求路径
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		if(null != savedRequest){
			String requestUrl = savedRequest.getRequestUrl();
			String ctx = request.getContextPath();
			requestUrl = requestUrl.replace(ctx, "");
			String[] split = requestUrl.split("\\/");
			if(split.length>1){
				//获取租户标记
				String tenantMark = split[1];
				if(StringUtils.isNotBlank(tenantMark)){
					Map<String,String> pageConfig = new HashedMap<String,String>();
					TenantEntity tenantEntity = tenantService.findByTenantMark(tenantMark);
					if(null != tenantEntity){
						List<ConfigTenantParamEntity> list = configTenantParamService.findByTenantId(tenantEntity.getTenantId());
						for (ConfigTenantParamEntity config : list) {
							pageConfig.put(config.getParamName(), config.getValue().replace("${inewsImageServer}",inewsImageServer));
						}
					}else{
						List<ConfigPageParamEntity> all = configPageParamService.listAll();
						for (ConfigPageParamEntity config : all) {
							pageConfig.put(config.getName(), config.getValue().replace("${inewsImageServer}",inewsImageServer));
						}
					}
					model.addAttribute("pageConfig", pageConfig);
				}
//				return "redirect:/"+tenantMark+"/login";
			}
		}
		String view = "frontEnd/login";
		return view;
	}
	
//	@RequestMapping(value = "/{tenantMark}/login",method = RequestMethod.GET)
//	public String tenantLogin(Model model) throws BaseException,Exception {
//		String view = "frontEnd/login";
//		return view;
//	}
	
	/**
	 * <br/>Description:用户登录验证
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param user
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelMap login(UserEntity user,Model model,HttpServletRequest request,HttpServletResponse response) throws BaseException,Exception{
		session.setMaxInactiveInterval( - 1); // 设置session不过期
		
		UserEntity userEntity = userService.findByUserName(user.getUserName());
		
		if(null == userEntity){ return super.getModelMap(false,null,null,"用户名不存在"); }
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),CommonUtil.encryptPassword(user.getPassword(),userEntity.getSalt()));
		Subject currentUser = SecurityUtils.getSubject();
		try{
			currentUser.login(token);
		}catch(AuthenticationException e){
			logger.error(this, e);
		};
		
		
		if(currentUser.isAuthenticated()){
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			Session session = subject.getSession();
			UserEntity userInfo = userService.findByUserName(userName);
			session.setAttribute("user",userInfo);
			
			//获取租户id
			List<OrgUserEntity> orgUserList = orgService.findOrgUserByUserId(userInfo.getInnerid());
			if(!CollectionUtils.isEmpty(orgUserList)){
				OrgUserEntity orgUserEntity = orgUserList.get(0);
				OrgEntity orgEntity = orgService.findOrgById(orgUserEntity.getOrgId());
				session.setAttribute("tenantId",orgEntity.getTenantId());
				//获取租户信息
				TenantEntity tenantEntity = tenantService.findById(orgEntity.getTenantId());
				if(null != tenantEntity){
					session.setAttribute("tenantMark",tenantEntity.getTenantMark());
				}else{
					return super.getModelMap(false,null,null,"租户不存在");
				}
			}
			
			
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			// 获取保存的URL
			if(savedRequest != null && savedRequest.getRequestUrl() != null){
				logger.info("登录后跳转路径："+savedRequest.getRequestUrl());
				return super.getModelMap(savedRequest.getRequestUrl()); 
			}
			
			return super.getModelMap(request.getContextPath()+"/"+session.getAttribute("tenantMark") + "/");
		}else{
			return super.getModelMap(false,null,null,"用户名或密码错误");
		}

	}
	
	
	/**
	 * <br/>Description:退出
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(Model model,HttpServletRequest request,HttpServletResponse response){
		String tenantMark = (String)session.getAttribute("tenantMark");
		SecurityUtils.getSubject().logout();
		if(StringUtils.isBlank(tenantMark)){
			return "redirect:/login";
		}else{
			return "redirect:/"+tenantMark+"/login";
		}
		
	}
	
	/**
	 * <br/>Description:传播监测首页
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = {"/{tenantMark}/","","/"},method = RequestMethod.GET)
	public String gotoHome(Model model) throws BaseException,Exception {
		String view = "frontEnd/index";
		return view;
	}
	
	/**
	 * <br/>Description:单个被转载分析详情
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = "/{tenantMark}/gotoReprintedDatail",method = RequestMethod.GET)
	public String gotoReprintedDatail(String webpageCode,Integer newsId,Double contentSimilarity, Model model) throws BaseException,Exception {
		String view = "frontEnd/reprinteddatail";
		model.addAttribute("webpageCode", webpageCode);
		model.addAttribute("newsId", newsId);
		model.addAttribute("contentSimilarity", contentSimilarity);
		return view;
	}
	
	/**
	 * <br/>Description:单个转载单位转载统计页
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = "/{tenantMark}/gotoUnitreProducedDetail",method = RequestMethod.GET)
	public String gotoUnitreProducedDetail(String queryStr,String sourceCrawl, String startTime, String endTime, Model model) throws BaseException,Exception {
		String view = "frontEnd/unitreproduceddetail";
		model.addAttribute("queryStr", queryStr);
		model.addAttribute("sourceCrawl", sourceCrawl);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return view;
	}
	
	/**
	 * <br/>Description:单篇稿件被转载统计页
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = "/{tenantMark}/gotoArticlerePrintedDetail",method = RequestMethod.GET)
	public String gotoArticlerePrintedDetail(String queryStr,String sourceCrawl, String startTime,Integer newsId, String endTime,Model model) throws BaseException,Exception {
		String view = "frontEnd/articlereprinteddetail";
		model.addAttribute("queryStr", queryStr);
		model.addAttribute("sourceCrawl", sourceCrawl);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("newsId", newsId);
		return view;
	}
	
	/**
	 * <br/>Description:上传页
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = "/{tenantMark}/gotoUpLoad",method = RequestMethod.GET)
	public String gotoUpLoad(Model model) throws BaseException,Exception {
		String view = "frontEnd/upload";
		return view;
	}
	
	
	
	
	
	
	/**
	 * 
	 * 传播力分析
	 * 
	 */
	
	/**
	 * <br/>Description:综合传播力分析
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	
	@RequestMapping(value = "/{tenantMark}/gotoIndex",method = RequestMethod.GET)
	public String gotoIndex(Model model) throws BaseException,Exception {
		String view = "propagationforce/frontEnd/index";
		return view;
	}
	
	/**
	 * <br/>Description:被转发情况分析
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	
	@RequestMapping(value = "/{tenantMark}/gotoMediaForwarding",method = RequestMethod.GET)
	public String gotoMediaForwarding(Model model) throws BaseException,Exception {
		String view = "propagationforce/frontEnd/mediaForwarding";
		return view;
	}
	
	
	/**
	 * <br/>Description:热点话题影响分析
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	
	@RequestMapping(value = "/{tenantMark}/gotoHotImpact",method = RequestMethod.GET)
	public String gotoHotImpact(Model model) throws BaseException,Exception {
		String view = "propagationforce/frontEnd/hotImpact";
		return view;
	}
	
	
	/**
	 * <br/>Description:热点话题影响分析
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	
	@RequestMapping(value = "/{tenantMark}/gotoArticleInfluence",method = RequestMethod.GET)
	public String gotoArticleInfluence(Model model) throws BaseException,Exception {
		String view = "propagationforce/frontEnd/articleInfluence";
		return view;
	}
	
	
	/**
	 * <br/>Description:人明日报临时测试
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = "/gotoList",method = RequestMethod.GET)
	public String gotoList(Model model) throws BaseException,Exception {
		String view = "frontEnd/temporary/list";
		return view;
	}
	
	/**
	 * <br/>Description:人明日报临时测试
	 * <p>Author:xlyang/杨小璐</p>
	 * 
	 */
	@RequestMapping(value = "/{tenantMark}/gotoDetail/{webpageCode}",method = RequestMethod.GET)
	public String gotoDetail(@PathVariable(name = "webpageCode") String webpageCode,Model model) throws BaseException,Exception {
		String view = "frontEnd/temporary/detail";
		model.addAttribute("webpageCode", webpageCode);
		return view;
	}
}
