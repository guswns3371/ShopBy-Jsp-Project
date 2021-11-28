package com.shopby.service;

import com.shopby.model.User;
import com.shopby.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean checkLoginData(String userId, String userPassword) {
        return userRepository.existsByUserIdAndPassword(userId, userPassword);
    }

    public boolean checkUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

}
