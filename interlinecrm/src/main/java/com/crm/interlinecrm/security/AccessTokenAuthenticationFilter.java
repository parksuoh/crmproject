package com.crm.interlinecrm.security;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class AccessTokenAuthenticationFilter implements Filter {

    private static final String AUTHORIZATION_PREFIX = "Bearer ";


    private final AccessTokenService accessTokenService;

    public AccessTokenAuthenticationFilter(
            AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String accessToken = parseAccessToken(httpRequest);

        UsernamePasswordAuthenticationToken authentication =
                accessTokenService.authenticate(accessToken);

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        filterChain.doFilter(httpRequest, httpResponse);
    }


    private static String parseAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(i -> i.startsWith(AUTHORIZATION_PREFIX))
                .map(i -> i.substring(AUTHORIZATION_PREFIX.length()))
                .orElse("");
    }



}
