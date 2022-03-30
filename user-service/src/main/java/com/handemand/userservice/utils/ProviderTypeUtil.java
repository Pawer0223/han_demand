package com.handemand.userservice.utils;

import com.handemand.userservice.auth.provider.ProviderType;

public class ProviderTypeUtil {
    public static ProviderType support(String provider) {
        ProviderType[] values = ProviderType.values();
        for (ProviderType value : values) {
            if (value.getProvider().equals(provider))
                return value;
        }
        return null;
    }
}
