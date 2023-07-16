package com.example.meetexApi.controller;

import com.example.meetexApi.dto.login.LoginRequest;
import com.example.meetexApi.dto.login.LoginResponse;
import com.example.meetexApi.dto.user.UserRegistrationRequest;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.security.JwtTokenProvider;
import com.example.meetexApi.service.EmailService;
import com.example.meetexApi.service.UserService;
import com.example.meetexApi.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        // Setup
    }

    @Test
    public void authenticateUserTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName("testuser");
        loginRequest.setPassword("testpassword");
        String mockJwt = "mockToken";

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtTokenProvider.generateToken(any())).thenReturn(mockJwt);

        ResponseEntity<?> result = loginController.authenticateUser(loginRequest);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockJwt, ((LoginResponse) result.getBody()).getAccessToken());
    }

    @Test
    public void registerUserTest() {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
        registrationRequest.setEmail("test@test.com");
        registrationRequest.setPassword("password");
        registrationRequest.setUsername("Test");

        User user = new User();
        user.setId(1L);
        user.setUserName("testuser");
        user.setPassword("password");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUserName("testuser");

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.userToUserResponseDTO(any(User.class))).thenReturn(userResponseDTO);

        ResponseEntity<?> result = loginController.register(registrationRequest);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(userResponseDTO, result.getBody());
    }
}
