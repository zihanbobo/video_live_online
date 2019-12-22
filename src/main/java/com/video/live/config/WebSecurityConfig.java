package com.video.live.config;

import com.video.live.common.properties.TokenIgnoreProperties;
import com.video.live.sucurity.UserDetailsServerImpl;
import com.video.live.sucurity.filter.JwtAuthenticationTokenFilter;
import com.video.live.sucurity.hanlder.AjaxAuthenticationFailureHandler;
import com.video.live.sucurity.hanlder.AjaxLogoutSuccessHandler;
import com.video.live.sucurity.hanlder.AjaxAuthenticationSuccessHandler;
import com.video.live.sucurity.hanlder.AjaxAuthenticationEntryPointHandler;
import com.video.live.sucurity.hanlder.AjaxAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 11:05
 */
@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter; // JWT拦截器

    @Autowired
    private AjaxAuthenticationEntryPointHandler entryPointHandler;//未登陆handler

    @Autowired
    private AjaxAccessDeniedHandler accessDeniedHandler; //无权限访问handler

    @Autowired
    private AjaxAuthenticationFailureHandler failureHandler; //登陆失败handler

    @Autowired
    private AjaxLogoutSuccessHandler logoutHandler;//退出handler

    @Autowired
    private AjaxAuthenticationSuccessHandler successHandler;//登陆成功handler

    @Autowired
    private UserDetailsServerImpl detailsServer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenIgnoreProperties tokenIgnoreProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsServer).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(tokenIgnoreProperties.getIgnoreUris());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(entryPointHandler)
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(logoutHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .access("@RBACAuthorityManager.hasPermission(request,authentication)")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //http.userDetailsService(detailsServer);
    }

    public String encoder(String pwd) {
        return passwordEncoder.encode(pwd);
    }
}
