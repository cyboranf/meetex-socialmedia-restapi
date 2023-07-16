package com.example.meetexApi.controller;

import com.example.meetexApi.dto.user.UserRequestDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;


    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void getUserByIdTest() {
        Long userId = 1L;
        UserResponseDTO responseDTO = new UserResponseDTO();

        when(userService.findById(any(Long.class))).thenReturn(responseDTO);

        ResponseEntity<UserResponseDTO> result = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseDTO, result.getBody());
    }

    @Test
    public void updateUserTest() {
        Long userId = 1L;
        UserRequestDTO request = new UserRequestDTO();
        UserResponseDTO responseDTO = new UserResponseDTO();

        when(userService.updateUser(any(Long.class), any(UserRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<UserResponseDTO> result = userController.updateUser(userId, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseDTO, result.getBody());
    }
}
