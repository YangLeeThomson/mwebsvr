package com.uec.imonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: imonitor启动类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
@ComponentScan(basePackages="com.uec.imonitor.*")
@EnableConfigurationProperties
public class ImonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImonitorApplication.class, args);
	}
	
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {    
//	    return new EmbeddedServletContainerCustomizer(){        
//	        @Override        
//	         public void customize(ConfigurableEmbeddedServletContainer container) {            
//	            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));            
//	            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));            
//	            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));        
//	        }    
//	    };
//	}
}
