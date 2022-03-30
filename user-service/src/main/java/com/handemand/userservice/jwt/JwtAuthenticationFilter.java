package com.handemand.userservice.jwt;

import com.handemand.userservice.auth.PrincipalDetails;
import com.handemand.userservice.domain.User;
import com.handemand.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final String REDIRECT_URL = "/needNickName";
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.debug("request Url : {}", requestURI);
        if (requestURI.equals(REDIRECT_URL)) {
            return ;
        }
        String jwt = getJwtFromRequest(request);
        if (jwtService.isJwtValid(jwt)) {
            String username = jwtService.getUsername(jwt);
            log.info("jwt 존재함: {}, username : {}", jwt, username);
            User user = userRepository.findByUsername(username);
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication auth = getAuthentication(principalDetails);
            SecurityContextHolder.getContext().setAuthentication(auth);
            if (user.getNickname() == null) {
                response.sendRedirect(REDIRECT_URL);
                return ;
            }
        }
        filterChain.doFilter(request, response);
    }


    private Authentication getAuthentication(PrincipalDetails principalDetails) {
        return new UsernamePasswordAuthenticationToken(
                principalDetails,
                null,
                principalDetails.getAuthorities());
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        // header check
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return null;
        }
        // token validate
        return authHeader.replace("Bearer ", "");
    }

}
