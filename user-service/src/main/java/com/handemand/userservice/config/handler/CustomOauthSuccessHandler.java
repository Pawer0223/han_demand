package com.handemand.userservice.config.handler;

import com.handemand.userservice.auth.PrincipalDetails;
import com.handemand.userservice.domain.User;
import com.handemand.userservice.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomOauthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        log.debug("success handler");
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        log.debug("user : {}", user);
        // 여기서 토큰 발급해서 응답해더에 전달
        String token = jwtService.generateToken(user.getUsername());
        jwtService.addTokenResHeader(response, token);
        response.sendRedirect("/");
    }
}
