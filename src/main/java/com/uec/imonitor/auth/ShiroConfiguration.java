package com.uec.imonitor.auth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.TemplateResolver;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration(value="shiroCasConfiguration")
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
    
    private SessionManager sessionManager;

    
    @Bean(name = "shiroCache")
    public EhCacheManager getEhCacheManager() {  
    	logger.info("init EhCacheManager ......");
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml"); 
        
        return em;  
    }  

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {
    	logger.info("init MyShiroCasRealm ......");
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCacheManager(cacheManager);
        return realm;
    } 

//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//    	logger.info("1.init LifecycleBeanPostProcessor ......");
//        return new LifecycleBeanPostProcessor();
//    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
    	logger.info("init DefaultAdvisorAutoProxyCreator ......");
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
    	logger.info("init securityManager ......");
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 --> 
        dwsm.setCacheManager(getEhCacheManager());
        // 指定 SubjectFactory
//        dwsm.setSubjectFactory(new CasSubjectFactory());
        dwsm.setSessionManager(new MyWebSessionManager());
        //获取默认的sessionManager
        sessionManager = dwsm.getSessionManager();
        
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    	logger.info("init AuthorizationAttributeSourceAdvisor ......");
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }
    
    /**
     * <br/>Description:限制同一账号登录同时登录人数控制
     * <p>Author:xpguo/郭晓鹏</p>
     * @return
     */
    @Bean(name="kickoutFilter")
    public KickoutSessionControlFilter getKickoutSessionControlFilter(){
    	logger.info("init kickoutFilter");
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
        kickoutSessionControlFilter.setCacheManager(getEhCacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager);
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(10);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/kickout");
        
        kickoutSessionControlFilter.setName("kickoutFilter");
        kickoutSessionControlFilter.setEnabled(true);
        return kickoutSessionControlFilter;
     }
    
    
    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
    	logger.info("excute method: loadShiroFilterChain ......");
        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/*/login","anon");
//        filterChainDefinitionMap.put("/common/dic/*","anon");
        filterChainDefinitionMap.put("/common/**","anon");
        filterChainDefinitionMap.put("/frontEnd/**","anon");
        filterChainDefinitionMap.put("/backEnd/**","anon");
        filterChainDefinitionMap.put("/assets/**","anon");
        filterChainDefinitionMap.put("/propagation/**","anon");
        filterChainDefinitionMap.put("/propagationforce/**","anon");
        filterChainDefinitionMap.put("/api/**","anon");
        filterChainDefinitionMap.put("/error/**","anon");
        filterChainDefinitionMap.put("/kickout","anon");
        filterChainDefinitionMap.put("/peoplesdaily","anon");
        filterChainDefinitionMap.put("/transDataToCloud","anon");
        
//        filterChainDefinitionMap.put("/back","authc,kickoutFilter");
        
//        filterChainDefinitionMap.put("/**","anon");
        filterChainDefinitionMap.put("/**","authc,kickoutFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * ShiroFilter
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,KickoutSessionControlFilter kickoutFilter) {
    	logger.info("init ShiroFilterFactoryBean ......");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager  
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        // 添加casFilter到shiroFilter中
        Map<String, Filter> filters = new HashMap<String,Filter>();
        
        //限制同一帐号同时在线的个数。
        filters.put("kickoutFilter", kickoutFilter);
        shiroFilterFactoryBean.setFilters(filters);

        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    /**
     * 注册DelegatingFilterProxy（Shiro）
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
    	logger.info("init FilterRegistrationBean ......");
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
    
    
    
    
	/**
	 * <br/>Description:重写spring的模板引擎，将Shiro加入到模板引擎管理
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param templateResolver
	 * @return
	 */
	@Bean
	public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {  
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();  
	    templateEngine.setTemplateResolver(templateResolver);  
	    Set<IDialect> additionalDialects = new HashSet<IDialect>();  
	    additionalDialects.add(new ShiroDialect());  
	    templateEngine.setAdditionalDialects(additionalDialects);  
	    return templateEngine;  
	}  
}
