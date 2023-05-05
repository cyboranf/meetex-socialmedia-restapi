package com.example.meetexApi.repository;

import com.example.meetexApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByEmail(String email);
    Optional<User> findByUsername(String username);


    User findUserByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
