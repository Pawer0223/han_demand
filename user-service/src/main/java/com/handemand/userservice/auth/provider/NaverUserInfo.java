package com.handemand.userservice.auth.provider;

import java.util.Map;

/**
 *  로그아웃이 생각보다 번가로와서 일단 사용하지 않음.
 */
public class NaverUserInfo implements Oauth2UserInfo {

    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map)attributes.get("response");
    }

    @Override
    public String getProviderId() {
        return (String)attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
        //return ProviderType.NAVER.getProvider();
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}
