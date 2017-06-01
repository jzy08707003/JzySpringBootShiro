/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.configuration.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.apopo.springbootshiro.settings.ShiroSettings;
/**
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * Created by zhenyujin on 2017/05/24.
 */

@Configuration
public class ShiroConfiguration{
	
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
//    public static void main(String args[]) {  
//        ResourceBundle bundle = ResourceBundle.getBundle("shiro");  
//        String cancel = bundle.getString("shiroRealm.cachingEnabled");  
//        System.out.println(cancel);  
//    }  
	
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在初始化ShiroFilterFactoryBean的时候需要注入SecurityManager
     *
     *  Filter Chain定义说明
     *  1、一个URL可以配置多个Filter，使用逗号分隔
     *  2、当设置多个过滤器时，全部验证通过，才视为通过
     *  3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        logger.info("ShiroConfiguration.shirFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置SecuritManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 配置退出过滤器,其中的具体代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //过滤特定url
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        //静态文件过滤
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        //过滤Spring ico
        filterChainDefinitionMap.put("/**/favicon.ico", "anon");

        filterChainDefinitionMap.put("/**", "authc");
        
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的链接
        //不设置默认/index
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(shiroRealm());
        //使用缓存管理器
        securityManager.setCacheManager(ehCacheManager());
//      securityManager.setSessionManager();
        //注入记住我管理器;  
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 身份认证realm;
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        ShiroSettings shiroSettings = getShiroSettings();
        shiroRealm.setCachingEnabled(shiroSettings.isCachingEnabled());
        shiroRealm.setAuthenticationCacheName(shiroSettings.getAuthenticationCacheName());
        shiroRealm.setAuthenticationCachingEnabled(shiroSettings.isAuthenticationCachingEnabled());
        shiroRealm.setAuthorizationCacheName(shiroSettings.getAuthorizationCacheName());
        shiroRealm.setAuthorizationCachingEnabled(shiroSettings.isAuthorizationCachingEnabled());
        return shiroRealm;
    }

    /**
     * 获取Shiro配置属性
     * @return
     */
    private ShiroSettings getShiroSettings() {
    	ShiroSettings shiroSettings = new ShiroSettings();
        ResourceBundle shiroCacheBundle = ResourceBundle.getBundle("shiro");  
        String cachingEnabled = shiroCacheBundle.getString("shiroRealm.cachingEnabled");
        String authenticationCacheName = shiroCacheBundle.getString("shiroRealm.authenticationCacheName");  
        String authenticationCachingEnabled = shiroCacheBundle.getString("shiroRealm.authenticationCachingEnabled");  
        String authorizationCacheName = shiroCacheBundle.getString("shiroRealm.authorizationCacheName");  
        String authorizationCachingEnabled = shiroCacheBundle.getString("shiroRealm.authorizationCachingEnabled");
        shiroSettings.setCachingEnabled(Boolean.parseBoolean(cachingEnabled));
        shiroSettings.setAuthenticationCacheName(authenticationCacheName);
        shiroSettings.setAuthenticationCachingEnabled(Boolean.parseBoolean(authenticationCachingEnabled));
        shiroSettings.setAuthorizationCacheName(authorizationCacheName);
        shiroSettings.setAuthorizationCachingEnabled(Boolean.parseBoolean(authorizationCachingEnabled));
		return shiroSettings;
	}

	/**
     * LifecycleBeanPostProcessor用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调，
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调。如UserRealm就实现了Initializable，
     * 而DefaultSecurityManager实现了Destroyable。
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 创建代理
     *
     * @return
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true); // it's false by default
        return creator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     * 
     * 
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 处理异常跳转页面
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties exceptionMappers = new Properties();
        exceptionMappers.setProperty("org.apache.shiro.authz.UnauthorizedException", "403");
        exceptionMappers.setProperty("org.apache.shiro.authz.UnauthenticatedException", "403");
        simpleMappingExceptionResolver.setExceptionMappings(exceptionMappers);
        return simpleMappingExceptionResolver;
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }
    
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;)
     * @return
     */
    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher() {  
        HashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());  
                //new HashedCredentialsMatcher();  
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;  
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于  
                                                        // md5(md5(""));  
        return hashedCredentialsMatcher;  
    }  
    
    @Bean  
    public SimpleCookie rememberMeCookie(){  
       logger.info("ShiroConfiguration.rememberMeCookie()");  
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe  
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");  
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->  
       simpleCookie.setMaxAge(259200);  
       return simpleCookie;  
    } 
    
    /**  
      * cookie管理对象;  
      * @return  
      */  
    @Bean  
    public CookieRememberMeManager rememberMeManager(){  
       logger.info("ShiroConfiguration.rememberMeManager()");  
       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();  
       cookieRememberMeManager.setCookie(rememberMeCookie());  
       return cookieRememberMeManager;  
    }
    
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {  
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();  
        aasa.setSecurityManager(securityManager());  
        return aasa;  
    }  
    
    /**  
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean  
     * @return  
     */  
    @Bean  
    public ShiroDialect shiroDialect(){  
    	return new ShiroDialect(); 
    }

}
