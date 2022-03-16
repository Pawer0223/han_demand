package com.handemand.userservice.controller;

import com.handemand.userservice.domain.User;
import com.handemand.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm.html";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm.html";
    }

    @PostMapping("join")
    public String join(User user) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        String encPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encPw);
        userRepository.save(user);
        return "redirect:/loginForm";
    }
}
