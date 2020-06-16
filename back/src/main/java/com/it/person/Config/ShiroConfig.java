package com.it.person.Config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //创建拦截器
    /*
    * ShiroFilterFactoryBean 为Shiro过滤器工厂类
    * 配置一些过滤路径
    *
    * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getSecurityManager") SecurityManager securityManager) throws Exception{
        System.out.println("经过安全管理器");
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //必须设置安全管理器
        bean.setSecurityManager(securityManager);


        //授权 未授权跳转到未授权页面
        // 配置访问权限
        Map<String, String> filterChainDefinitionMap  = new HashMap<>();
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转由端控制，后台仅返回json数据
        //无需认证
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/email","anon");
        filterChainDefinitionMap.put("/sigon","anon");
        //配置资源受限列表
        filterChainDefinitionMap.put("/upload","perms[upload]");
        filterChainDefinitionMap.put("/playvideo","perms[playvideo]");
        filterChainDefinitionMap.put("/postblog","perms[postblog]");
        filterChainDefinitionMap.put("/authority","perms[authority]");
        // 使用该过滤器过滤所有的链接
//        filterChainDefinitionMap.put("/**","authc");
        //登出
        //filterChainDefinitionMap.put("/logout", "logout");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 过滤器链配置
        bean.setLoginUrl("/tologin");
        //设置未授权
        bean.setUnauthorizedUrl("/noauth");
        //自定义拦截器
        Map<String, Filter> filters = bean.getFilters();
        // 注意这里不要用Bean的方式，否则会报错
        filters.put("myFilter", new ShiroUserFilter());
        bean.setFilters(filters);
        return bean;
    }
    //创建安全管理器
    //SecurityManager 为shiro安全管理器，管理着所有的Subject
    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("getRealm") UserRealm userRealm){
        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        //注入realm
        SecurityManager.setRealm(userRealm);
        return SecurityManager;
    }
    //创建自定义realm
    //引入自己实现的 ShiroRealm
    @Bean
    public UserRealm getRealm(){
        UserRealm realm = new UserRealm();
//        realm.setCredentialsMatcher(matcher());
        return realm;
    }
}
