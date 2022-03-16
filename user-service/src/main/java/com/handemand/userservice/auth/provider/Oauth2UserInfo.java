package com.handemand.userservice.auth.provider;

import java.util.Map;

public interface Oauth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
