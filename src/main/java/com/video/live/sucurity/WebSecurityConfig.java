package com.video.live.sucurity;

import com.video.live.sucurity.filter.JwtAuthenticationFilter;
import com.video.live.sucurity.hanlder.AuthFailureHandler;
import com.video.live.sucurity.hanlder.AuthLogoutHandler;
import com.video.live.sucurity.hanlder.AuthSuccessHandler;
import com.video.live.sucurity.hanlder.AuthenticationEntryPointHandler;
import com.video.live.sucurity.hanlder.AuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 11:05
 */
@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPointHandler entryPointHandler;

    @Autowired
    private AuthenticationHandler accessDeniedHandler;

    @Autowired
    private AuthFailureHandler failureHandler;

    @Autowired
    private AuthLogoutHandler logoutHandler;

    @Autowired
    private AuthSuccessHandler successHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServerImpl detailsServer;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsServer).passwordEncoder(passwordEncoder);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(entryPointHandler)
                .and()
                .authorizeRequests()
                .anyRequest()
                .access("@rbacAuthorityManager.hasPermission(request,authentication)")
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)

                .and()
                .logout()
                .logoutSuccessHandler(logoutHandler)
                .permitAll();
            http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


    }

    public String encoder(String pwd) {
        return passwordEncoder.encode(pwd);
    }
}
