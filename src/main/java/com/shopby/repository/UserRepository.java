package com.shopby.repository;

import com.shopby.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserIdAndPassword(String userId, String userPassword);

    boolean existsByUserId(String userId);
}
