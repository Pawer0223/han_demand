package com.handemand.userservice.auth.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@Getter @RequiredArgsConstructor
public enum ProviderType {
    GOOGLE("google", attributes -> new GoogleUserInfo(attributes));
    // NAVER("naver", attributes -> new NaverUserInfo(attributes));

    private final String provider;
    private final Function<Map<String, Object>, Oauth2UserInfo> function;

    public Oauth2UserInfo getOauth2UserInfo(Map<String, Object> attributes) {
        return function.apply(attributes);
    }
}
