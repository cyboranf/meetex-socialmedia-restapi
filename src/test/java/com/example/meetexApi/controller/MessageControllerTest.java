package com.example.meetexApi.controller;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.dto.message.MessageResponseDTO;
import com.example.meetexApi.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    public void setUp() {
        // Intentionally left blank
    }

    @Test
    public void sendMessageTest() {
        MessageRequestDTO request = new MessageRequestDTO();
        MessageResponseDTO response = new MessageResponseDTO();

        when(messageService.sendMessage(anyLong(), any(MessageRequestDTO.class))).thenReturn(response);

        ResponseEntity<MessageResponseDTO> result = messageController.sendMessage(1L, request);

        assertEquals(response, result.getBody());
    }

    @Test
    public void getAllMessagesTest() {
        MessageResponseDTO response = new MessageResponseDTO();
        List<MessageResponseDTO> responseList = Collections.singletonList(response);

        when(messageService.findAllMessagesBetweenUsers(anyLong(), anyLong())).thenReturn(responseList);

        ResponseEntity<List<MessageResponseDTO>> result = messageController.getAllMessages(1L, 2L);

        assertEquals(responseList, result.getBody());
    }

    @Test
    public void deleteMessageTest() {
        ResponseEntity<?> result = messageController.deleteMessage(1L, null);

        assertEquals(ResponseEntity.ok().build(), result);
    }
}
