package com.flybirds.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author flybirds
 * @version 1.0
 **/
@Configuration
@EnableResourceServer // 开启资源服务器
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@Order(30) // 认证之后开始授权
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "res1";

    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        DefaultTokenServices tonkenService = new DefaultTokenServices();
        tonkenService.setTokenStore(tokenStore);
        resources
                .resourceId(RESOURCE_ID)//资源 id
                .tokenServices(tonkenService) //token的验证服务
                .stateless(true);// 无状态存储
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated() // 所有请求都需要认证
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //关闭session共享
    }
}
