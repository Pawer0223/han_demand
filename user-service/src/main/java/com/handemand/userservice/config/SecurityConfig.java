package com.handemand.userservice.config;

import com.handemand.userservice.auth.PrincipalDetails;
import com.handemand.userservice.auth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *  Oauth 2.0
 *  1. 코드를 받는다.(인증)
 *  2. 1을 통해 엑세스 토큰을 발급 받음.
 *      - 구글이 가지고 있는 사용자 정보에 접근 가능.
 *  3. 사용자 구글 프로필 정보를 받는다.
 *  4. 인증하여 받은 정보를 기반으로 내 Application 의 정보 참조(저장 등등..)
 *
 *  Oauth 2.0을 쓰면 코드를 받지 않고, 한번에 엑세스 토큰 + 사용자 프로필 정보를 받을 수 있다.
 *
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated()
            .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginForm")
            .loginProcessingUrl("/login") // login 호출 시 시큐리티가 처리
            .defaultSuccessUrl("/")
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(principalOauth2UserService)
        ;
    }
}
