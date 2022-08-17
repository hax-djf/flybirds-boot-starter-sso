package com.flybirds.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author flybirds
 * @version 1.0
 **/
@Configuration
@EnableWebSecurity
@Order(20) // 设置过滤器加载顺序
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //认证管理器
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                /**
                 CSRF通过伪造用户请求访问受信任站点的非法请求访问。
                 跨域：只要网络协议，ip 地址，端口中任何一个不相同就是跨域请求。
                 客户端与服务进行交互时，由于 http 协议本身是无状态协议，
                 所以引入了cookie进行记录客户端身份。在cookie中会存放session id用来识别客户端身份的。
                 在跨域的情况下，session id 可能被第三方恶意劫持，通过这个 session id 向服务端发起请求时，
                 服务端会认为这个请求是合法的，可能发生很多意想不到的事情
                 */
                .csrf() // 关闭 csrf跨域 防护
                .disable()
                .httpBasic() //开启httpBasic
                .and()
                .formLogin() //允许表单提交
                .and()
                .authorizeRequests()
                .antMatchers("/login*").permitAll() //将login登录入口放开
                .anyRequest().fullyAuthenticated() // 其他请求全部都要进行认证
                /**
                 表示该页面不允许在 frame 中展示，即便是在相同域名的页面中嵌套也不允许 DENY
                 表示该页面可以在相同域名页面的 frame 中展示 SAMEORIGIN
                 表示该页面可以在指定来源的 frame 中展示 ALLOW-FROM
                 */
                .and().headers().frameOptions().disable(); //完全允许iframe

    }
}
