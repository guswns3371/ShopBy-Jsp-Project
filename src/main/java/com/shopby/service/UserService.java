package com.shopby.service;

import com.shopby.model.User;
import com.shopby.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    public boolean checkLoginData(String userId, String userPassword) {
        return userRepository.existsByUserIdAndPassword(userId, userPassword);
    }

    public boolean checkUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

}
