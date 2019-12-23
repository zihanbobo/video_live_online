package com.video.live.sucurity.filter;

import cn.hutool.core.util.StrUtil;
import com.video.live.common.response.ResponseEnum;
import com.video.live.common.response.ResponseResult;
import com.video.live.common.util.JWTUtils;
import com.video.live.sucurity.UserDetailsServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.video.live.common.constant.EntityConstant.AUTHORIZATION;
import static com.video.live.common.constant.EntityConstant.TOKEN_BEARER;

/**
 * Jwt token过滤器，验证token的有效性
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 14:06
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private UserDetailsServerImpl userDetailsServer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (StrUtil.isBlank(token) || !StrUtil.startWith(token, TOKEN_BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.substring(TOKEN_BEARER.length());
        String userName = JWTUtils.getUserName(token);
        if (StrUtil.isBlank(userName)){
            ResponseResult.out(response,ResponseResult.failed(ResponseEnum.ACCESS_DENIED));
            return;
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsServer.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
