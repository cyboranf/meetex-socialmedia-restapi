package com.example.meetexApi.controller;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.dto.message.MessageResponseDTO;
import com.example.meetexApi.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * @param userId
     * @param messageRequestDTO
     * @return DTO of sended(US) message
     */
    @PostMapping("/users/{userId}/messages")
    public ResponseEntity<MessageResponseDTO> sendMessage(@PathVariable Long userId, @Valid @RequestBody MessageRequestDTO messageRequestDTO) {
        MessageResponseDTO messageResponseDTO = messageService.sendMessage(userId, messageRequestDTO);
        return ResponseEntity.ok(messageResponseDTO);
    }

    /**
     * @param userId
     * @param recipientId
     * @return List of Message DTO between users with id = {userId} and id = recipientId
     */
    @GetMapping("/users/{userId}/messages")
    public ResponseEntity<List<MessageResponseDTO>> getAllMessages(@PathVariable Long userId, @RequestParam(required = false) Long recipientId) {
        List<MessageResponseDTO> messages = messageService.findAllMessagesBetweenUsers(userId, recipientId);
        return ResponseEntity.ok(messages);
    }

    /**
     * @param messageId
     * @param authentication
     * @return
     */
    @DeleteMapping("/messages/{messageId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId, Authentication authentication) {
        messageService.delete(messageId);
        return ResponseEntity.ok().build();
    }
}
