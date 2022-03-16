package com.handemand.userservice.auth;

import com.handemand.userservice.auth.provider.GoogleUserInfo;
import com.handemand.userservice.auth.provider.Oauth2UserInfo;
import com.handemand.userservice.auth.provider.OauthClient;
import com.handemand.userservice.domain.User;
import com.handemand.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static com.handemand.userservice.auth.provider.OauthClient.*;

/**
 * [ UserRequest 정보 ]
 *  - 구글 로그인 창에서 로그인 완료 하면, code를 return 받는다. (Oauth-Client 라이브러리가 처리.)
 *  - code를 통해 AccessToken을 요청.
 *
 *  [ UserRequest 정보를 통해 회원 프로필을 받는다 ]
 *  - 이 기능이 loadUser를 통해 처리 됨.
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final String DEFAULT_PASSWORD = "default_pw";
    private final String DEFAULT_ROLE = "ROLE_USER";

    /**
     * 구글 API를 통해 프로필 정보를 받아온다. (with AccessToken)
     * 중요 포인트.
     * loadUserByUsername 가 종료될 때, @AuthenticationPrincipal 어노테이션이 만들어진다.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // oauth2UserRequestLogging(userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        Oauth2UserInfo oauth2UserInfo = null;
        if (provider.equals(GOOGLE.getValue())) {
            oauth2UserInfo = GOOGLE.getOauth2UserInfo(oAuth2User.getAttributes());
        } else {
            log.debug("지원하지 않는 로그인 !");
            return null;
        }
        User user = createUserWithOauth(oauth2UserInfo);
        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }

    // 메서드 명 마음에 안드는데.. 사실상 찾는기능. 강제로 만드는건 안좋은것 같음.. 바꾸자 !
    private User createUserWithOauth(Oauth2UserInfo oauth2UserInfo) {
        String provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String email = oauth2UserInfo.getEmail();
        String password = passwordEncoder.encode(DEFAULT_PASSWORD); // 안쓰이기 때문에 큰 의미 없음.

        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.debug("### ### ### 최초 로그인 - 자동 회원가입 진행 ### ### ###");
            user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .role(DEFAULT_ROLE)
                    .build();
            userRepository.save(user);
        } else {
            log.debug("### ### ### 로그인 이력 있음 ! ### ### ###");
        }
        return user;
    }

    private void oauth2UserRequestLogging(OAuth2UserRequest userRequest) {
        log.debug("=== === === === === === === === === === === === === === ===");
        log.debug("getClientRegistration : {}", userRequest.getClientRegistration());
        log.debug("getAccessToken : {}", userRequest.getAccessToken().getTokenValue());
        log.debug("getAttributes : {}", super.loadUser(userRequest).getAttributes());
        log.debug("=== === === === === === === === === === === === === === ===");
    }
}
