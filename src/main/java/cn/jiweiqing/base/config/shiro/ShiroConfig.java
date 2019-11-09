package cn.jiweiqing.base.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yb.zhang
 * @Description shiro 配置
 */
@Configuration
public class ShiroConfig {

    @Bean
    @ConfigurationProperties(prefix = "shiro-props")
    public ShiroProps shiroProps() {
        ShiroProps shiroProps = new ShiroProps();
        return shiroProps;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager
    ) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置未登录要跳转的路径
        shiroFilterFactoryBean.setLoginUrl("/api/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(
                shiroProps().getFilterChainDefinitionMap());
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
//        securityManager.setSessionManager(sessionManager(sessionDAO));
        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    /*@Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }*/

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    /*@Bean
    public DefaultWebSessionManager sessionManager(RedisSessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(3600000);
        sessionManager.setCacheManager(redisCacheManager());
        return sessionManager;
    }*/

    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
