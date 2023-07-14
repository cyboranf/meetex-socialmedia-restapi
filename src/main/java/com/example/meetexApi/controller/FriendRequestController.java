package com.example.meetexApi.controller;

import com.example.meetexApi.dto.friendRequest.FriendRequestDTO;
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

    /**
     * @param userId
     * @param friendRequestDTO
     * @return communicat or exception
     */
    @PostMapping("/users/{userId}/friend-requests")
    public ResponseEntity<?> sendFriendRequest(@PathVariable Long userId, @Valid @RequestBody FriendRequestDTO friendRequestDTO) {
        userService.sendFriendRequest(userId, friendRequestDTO);
        return new ResponseEntity<>("Friend request sent successfully.", HttpStatus.OK);
    }

    /**
     * @param userId
     * @param senderId
     * @param action
     * @return
     */
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

    /**
     * @param userId
     * @param receiverId
     * @return
     */
    @DeleteMapping("/users/{userId}/delete-friend-request/{receiverId}")
    public ResponseEntity<?> deleteFriendRequest(@PathVariable Long userId, @PathVariable Long receiverId) {
        userService.deleteFriendRequest(userId, receiverId);
        return ResponseEntity.ok().body("Friend request deleted.");
    }

    /**
     * @param userId
     * @return DTO of all user with id = {userId} friends
     */
    @GetMapping("/users/{userId}/friends")
    public ResponseEntity<List<UserResponseDTO>> getAllFriends(@PathVariable Long userId) {
        List<UserResponseDTO> friends = userService.getAllFriends(userId);
        return ResponseEntity.ok().body(friends);
    }

}
