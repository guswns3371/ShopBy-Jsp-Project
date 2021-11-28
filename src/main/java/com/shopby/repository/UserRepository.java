package com.shopby.repository;

import com.shopby.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserId(String userId);

    boolean existsByUserIdAndPassword(String userId, String userPassword);

    boolean existsByUserId(String userId);
}
