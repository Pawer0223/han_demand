package com.handemand.userservice.auth;

import com.handemand.userservice.domain.User;
import com.handemand.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *  security config 에서 loginProcessingUrl("/login"); 설정에 의해
 *  login 요청 시 UserDetailsService 타입을 가진 빈의 loadUserByUsername 호출
 *  form 의 이름이 반드시 username 이어야 함. 아니면 설정을 바꿔야 함.
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    /**
    *  중요 포인트.
    *  loadUserByUsername 가 종료될 때, @AuthenticationPrincipal 어노테이션이 만들어진다.
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            log.debug("{}", user);
            return new PrincipalDetails(user);
        }
        // 여기서 throw ?
        return null;
    }
}
