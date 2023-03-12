package com.example.meetexApi.service;

import com.example.meetexApi.model.Role;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.RoleRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public User save(User user) {

        user.setActive(true);
        Role userRole = roleRepository.findRoleByName("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        return userRepository.save(user);
    }

    public User findUserByName(String name) {
        return userRepository.findUserByUserName(name);
    }
}
