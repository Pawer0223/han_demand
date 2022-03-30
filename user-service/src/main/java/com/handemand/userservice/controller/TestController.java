package com.handemand.userservice.controller;

import com.handemand.userservice.auth.PrincipalDetails;
import com.handemand.userservice.domain.User;
import com.handemand.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.debug("principalDetails : {}", principalDetails);
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/success")
    public String success() {
        return "login success";
    }

    @GetMapping("/updateNickname")
    public String updateNickname() {
        return "updateNickname";
    }

    @GetMapping("/fail")
    public String fail() {
        return "login fail";
    }

    @GetMapping("/authentication")
    public String authentication(Authentication authentication,
                            @AuthenticationPrincipal PrincipalDetails userDetails) {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        log.debug("===== Security Context 가 관리하는 Authentication 객체를 가져오는 2가지 방법 =====");
        log.debug("[1] : {}", principalDetails.getUser());
        log.debug("[2] : {}", userDetails.getUser());
        return "2가지 방법으로 조회한 결과는 동일해야 한다. 콘솔의 log 확인.";
    }

    @GetMapping("/authentication/oauth")
    public String oauth_authentication(Authentication authentication,
                                       @AuthenticationPrincipal OAuth2User googleData) {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        log.debug("===== Oauth2를 통해 가져온 구글 프로필 정보를 조회하는 방법 =====");
        log.debug("[1] : {}", oAuth2User.getAttributes());
        log.debug("[2] : {}", googleData.getAttributes());
        return "OAuth2User 타입의 객체를 통해 조회한다.";
    }
}
