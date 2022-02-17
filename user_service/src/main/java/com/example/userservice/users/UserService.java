package com.example.userservice.users;

import com.example.userservice.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service @AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User login(Email email, String password) {
        checkNotNull(password, "password must be provided");
        User user = findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Could not found user for " + email));
        user.login(passwordEncoder, password);
        user.afterLoginSuccess();
        userRepository.update(user);
        return user;
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long userId) {
        checkNotNull(userId, "userId must be provided");
        return userRepository.findById(userId);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(Email email) {
        checkNotNull(email, "email must be provided");
        return userRepository.findByEmail(email);
    }

}