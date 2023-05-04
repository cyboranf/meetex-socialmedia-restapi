package com.example.meetexApi.service;

import com.example.meetexApi.dto.user.FriendRequestDTO;
import com.example.meetexApi.dto.user.UserRegistrationRequest;
import com.example.meetexApi.model.Role;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.RoleRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new OpenApiResourceNotFoundException("User not found with ID: " + id));
    }

    public User findFirstByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    public User registerUser(UserRegistrationRequest registrationRequest) {
        // Check if the username already exists
        if (userRepository.existsByUserName(registrationRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        // Check if the email already exists
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        // Create a new User object with the registration details
        User newUser = new User();
        newUser.setUserName(registrationRequest.getUsername());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));


        // Assign a default role to the user
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("Default user role not found."));
        newUser.setRoles(Collections.singleton(defaultRole));

        return userRepository.save(newUser);
    }

    public User updateUser(Long id, User userToUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("User not found with ID: " + id));

        user.setUserName(userToUpdate.getUserName());
        user.setUserName(userToUpdate.getUserName());
        user.setLastName(userToUpdate.getLastName());
        user.setEmail(userToUpdate.getEmail());

        return userRepository.save(user);
    }

    public void sendFriendRequest(Long userId, FriendRequestDTO friendRequestDTO) {
        User sender = getUserById(friendRequestDTO.getSenderId());
        User receiver = getUserById(friendRequestDTO.getReceiverId());

        // Check if there's already a friend request or if they are already friends
        if (sender.getSentFriendRequests().contains(receiver) || receiver.getSentFriendRequests().contains(sender) ||
                sender.getFriends().contains(receiver) || receiver.getFriends().contains(sender)) {
            throw new IllegalArgumentException("There is already a friend request or the users are already friends.");
        }

        sender.getSentFriendRequests().add(receiver);
        receiver.getReceivedFriendRequests().add(sender);

        userRepository.save(sender);
        userRepository.save(receiver);
    }


}
