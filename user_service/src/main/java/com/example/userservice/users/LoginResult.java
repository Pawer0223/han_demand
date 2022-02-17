package com.example.userservice.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ToString @Getter
public class LoginResult {

    private final String token;
    private final UserDto user;

    public LoginResult(String token, User user) {
        this.token = token;
        this.user = new UserDto(user);
    }

    public String getToken() {
        return token;
    }

    public UserDto getUser() {
        return user;
    }
}