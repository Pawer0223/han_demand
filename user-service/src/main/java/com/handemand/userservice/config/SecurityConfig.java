package com.handemand.userservice.config;

import com.handemand.userservice.auth.PrincipalOauth2UserService;
import com.handemand.userservice.config.handler.CustomOauthSuccessHandler;
import com.handemand.userservice.jwt.JwtAuthenticationFilter;
import com.handemand.userservice.jwt.JwtService;
import com.handemand.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;

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
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.addFilterBefore(new JwtAuthenticationFilter(userRepository, jwtService)
                , OAuth2AuthorizationRequestRedirectFilter.class)
        ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .formLogin().disable()
        ;
        // http.addFilterAfter(new JwtAuthenticationFilter(), LogoutFilter.class);
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
        ;
        // oath2
        http.oauth2Login()
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                .and()
                .successHandler(new CustomOauthSuccessHandler(jwtService))
        ;

    }
}
