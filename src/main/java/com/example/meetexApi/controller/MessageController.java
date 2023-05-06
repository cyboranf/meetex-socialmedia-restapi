package com.example.meetexApi.controller;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.dto.message.MessageResponseDTO;
import com.example.meetexApi.model.Message;
import com.example.meetexApi.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/users/{userId}/messages")
    public ResponseEntity<MessageResponseDTO> sendMessage(@PathVariable Long userId, @Valid @RequestBody MessageRequestDTO messageRequestDTO) {
        MessageResponseDTO messageResponseDTO = messageService.sendMessage(userId, messageRequestDTO);
        return ResponseEntity.ok(messageResponseDTO);
    }

    @GetMapping("/users/{userId}/messages")
    public ResponseEntity<List<MessageResponseDTO>> getAllMessages(@PathVariable Long userId, @RequestParam(required = false) Long recipientId) {
        List<Message> messages = messageService.findAllMessagesBetweenUsers(userId, recipientId);
        List<MessageResponseDTO> messageResponseDTOList = messageService.toMessageResponseDTOList(messages);
        return ResponseEntity.ok(messageResponseDTOList);
    }

    @DeleteMapping("/messages/{messageId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId, Authentication authentication) {
        String currentUsername = authentication.getName();
        messageService.deleteMessage(messageId, currentUsername);
        return ResponseEntity.ok().build();
    }

}
