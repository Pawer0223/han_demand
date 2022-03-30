package com.handemand.userservice.auth;

import com.handemand.userservice.auth.provider.Oauth2UserInfo;
import com.handemand.userservice.auth.provider.ProviderType;
import com.handemand.userservice.domain.User;
import com.handemand.userservice.repository.UserRepository;
import com.handemand.userservice.utils.ProviderTypeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


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
     *
     * 구글 로그인 폼에서 로그인을 시도했을 때 타는 함수.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        oauth2UserRequestLogging(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        ProviderType providerType = ProviderTypeUtil.support(provider);

        if (providerType != null) {
            OAuth2User oAuth2User = super.loadUser(userRequest);
            Oauth2UserInfo oauth2UserInfo = providerType.getOauth2UserInfo(oAuth2User.getAttributes());
            User user = createUserWithOauth(oauth2UserInfo);
            return new PrincipalDetails(user, oAuth2User.getAttributes());
        } else {
            String msg = provider + "는 로그인할 수 없습니다.";
            log.error(msg);
            OAuth2Error oAuth2Error = new OAuth2Error("Invalid Provider", msg, "");
            throw new OAuth2AuthenticationException(oAuth2Error);
        }
    }

    private User createUserWithOauth(Oauth2UserInfo oauth2UserInfo) {

        String provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String email = oauth2UserInfo.getEmail();
        String password = passwordEncoder.encode(DEFAULT_PASSWORD); // 안쓰이기 때문에 큰 의미 없음.

        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.debug("### ### ### 자동 회원가입. ### ### ###");
            user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .roles(DEFAULT_ROLE)
                    .build();
            userRepository.save(user);
        }
        log.debug("### ### ### 가입 된 사용자는 필수 정보 입력 후, 사용 가능 ### ### ###");
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
