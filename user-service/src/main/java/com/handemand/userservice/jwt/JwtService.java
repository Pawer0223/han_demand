package com.handemand.userservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final Environment env;

    public String generateToken(String userName) {
        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.valueOf(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();
        return token;
    }

    public void addTokenResHeader(HttpServletResponse response, String token) {
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("accessToken", token);
    }

    // 토큰에 대한 유효성 검사가 주 목적.
    public boolean isJwtValid(String jwt) {
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();
        } catch (Exception ex) {
            return false;
        }
        if (subject == null || subject.isEmpty())
            return false;
        return true;
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(env.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
                ;
    }
}
