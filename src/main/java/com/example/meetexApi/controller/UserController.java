package com.example.meetexApi.controller;

import com.example.meetexApi.dto.user.UserRequestDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.security.JwtTokenProvider;
import com.example.meetexApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.example.meetexApi.dto.user.UserRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final JwtTokenProvider jwtTokenProvider;


    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    /**
     * @param registrationRequest
     * @return DTO of new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
        try {
            UserResponseDTO userResponseDTO = userService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * @param userId
     * @return DTO of user with id = @param
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        UserResponseDTO user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * @param userId
     * @param userRequestDTO
     * @return DTO of updated user
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(userId, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
