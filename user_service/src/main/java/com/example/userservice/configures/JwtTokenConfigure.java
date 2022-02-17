package com.example.userservice.configures;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component @Getter @Setter @ToString
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenConfigure {
    private String header;
    private String issuer;
    private String clientSecret;
    private int expirySeconds;
}