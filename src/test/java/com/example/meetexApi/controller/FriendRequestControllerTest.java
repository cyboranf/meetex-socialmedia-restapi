package com.example.meetexApi.controller;

import com.example.meetexApi.dto.friendRequest.FriendRequestDTO;
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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendRequestControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private FriendRequestController friendRequestController;

    private FriendRequestDTO friendRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    public void setUp() {
        friendRequestDTO = new FriendRequestDTO();
        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUserName("testuser");
    }

    @Test
    public void sendFriendRequestTest() {
        doNothing().when(userService).sendFriendRequest(anyLong(), any(FriendRequestDTO.class));

        ResponseEntity<?> result = friendRequestController.sendFriendRequest(1L, friendRequestDTO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Friend request sent successfully.", result.getBody());
    }

    @Test
    public void acceptFriendRequestTest() {
        doNothing().when(userService).acceptFriendRequest(anyLong(), anyLong());

        ResponseEntity<?> result = friendRequestController.acceptOrDeclineFriendRequest(1L, 1L, "accept");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Friend request accepted.", result.getBody());
    }

    @Test
    public void declineFriendRequestTest() {
        doNothing().when(userService).declineFriendRequest(anyLong(), anyLong());

        ResponseEntity<?> result = friendRequestController.acceptOrDeclineFriendRequest(1L, 1L, "decline");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Friend request declined.", result.getBody());
    }

    @Test
    public void invalidActionFriendRequestTest() {
        ResponseEntity<?> result = friendRequestController.acceptOrDeclineFriendRequest(1L, 1L, "invalidAction");

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid action. Please use 'accept' or 'decline'.", result.getBody());
    }

    @Test
    public void deleteFriendRequestTest() {
        doNothing().when(userService).deleteFriendRequest(anyLong(), anyLong());

        ResponseEntity<?> result = friendRequestController.deleteFriendRequest(1L, 1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Friend request deleted.", result.getBody());
    }

    @Test
    public void getAllFriendsTest() {
        when(userService.getAllFriends(anyLong())).thenReturn(Collections.singletonList(userResponseDTO));

        ResponseEntity<List<UserResponseDTO>> result = friendRequestController.getAllFriends(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
        assertEquals(userResponseDTO, result.getBody().get(0));
    }
}
