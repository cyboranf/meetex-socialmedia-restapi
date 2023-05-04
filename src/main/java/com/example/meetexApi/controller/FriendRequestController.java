package com.example.meetexApi.controller;

import com.example.meetexApi.dto.friendRequest.FriendRequestDTO;
import com.example.meetexApi.dto.friendRequest.FriendRequestResponseDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/users/{userId}/friend-requests")
    public ResponseEntity<List<FriendRequestResponseDTO>> getAllFriendRequests(@PathVariable Long userId) {
        List<FriendRequestResponseDTO> friendRequests = userService.getAllFriendRequests(userId);
        return ResponseEntity.ok(friendRequests);
    }

    @PutMapping("/users/{userId}/accept-friend-request/{senderId}")
    public ResponseEntity<?> acceptOrDeclineFriendRequest(@PathVariable Long userId, @PathVariable Long senderId,
                                                          @RequestParam(value = "action") String action) {
        if ("accept".equalsIgnoreCase(action)) {
            userService.acceptFriendRequest(userId, senderId);
            return ResponseEntity.ok().body("Friend request accepted.");
        } else if ("decline".equalsIgnoreCase(action)) {
            userService.declineFriendRequest(userId, senderId);
            return ResponseEntity.ok().body("Friend request declined.");
        } else {
            return ResponseEntity.badRequest().body("Invalid action. Please use 'accept' or 'decline'.");
        }
    }

    @DeleteMapping("/users/{userId}/delete-friend-request/{receiverId}")
    public ResponseEntity<?> deleteFriendRequest(@PathVariable Long userId, @PathVariable Long receiverId) {
        userService.deleteFriendRequest(userId, receiverId);
        return ResponseEntity.ok().body("Friend request deleted.");
    }

    @GetMapping("/users/{userId}/friends")
    public ResponseEntity<List<UserResponseDTO>> getAllFriends(@PathVariable Long userId) {
        List<UserResponseDTO> friends = userService.getAllFriends(userId);
        return ResponseEntity.ok().body(friends);
    }

}
