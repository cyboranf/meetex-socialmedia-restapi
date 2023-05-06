package com.example.meetexApi.controller;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.dto.message.MessageResponseDTO;
import com.example.meetexApi.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
