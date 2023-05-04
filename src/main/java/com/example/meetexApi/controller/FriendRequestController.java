package com.example.meetexApi.controller;

import com.example.meetexApi.dto.user.FriendRequestDTO;
import com.example.meetexApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api")
public class FriendRequestController {

    private final UserService userService;

    public FriendRequestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{userId}/friend-requests")
    public ResponseEntity<?> sendFriendRequest(@PathVariable Long userId, @Valid @RequestBody FriendRequestDTO friendRequestDTO) {
        if (!userId.equals(friendRequestDTO.getSenderId())) {
            return new ResponseEntity<>("User ID in the path and sender ID in the request body do not match.", HttpStatus.BAD_REQUEST);
        }

        userService.sendFriendRequest(userId, friendRequestDTO);
        return new ResponseEntity<>("Friend request sent successfully.", HttpStatus.OK);
    }
}
