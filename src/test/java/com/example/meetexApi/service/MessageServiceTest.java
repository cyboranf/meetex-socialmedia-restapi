package com.example.meetexApi.service;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.dto.message.MessageResponseDTO;
import com.example.meetexApi.mapper.MessageMapper;
import com.example.meetexApi.model.Message;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.MessageRepository;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.service.MessageService;
import com.example.meetexApi.validation.MessageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageValidator messageValidator;

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void deleteMessageTest() {
        Long messageId = 1L;
        Message message = new Message();

        when(messageValidator.deleteMessageValidation(messageId)).thenReturn(message);
        doNothing().when(messageRepository).delete(message);

        messageService.delete(messageId);

        verify(messageRepository, times(1)).delete(message);
    }

    @Test
    public void sendMessageTest() {
        Long userId = 1L;
        MessageRequestDTO requestDTO = new MessageRequestDTO();
        requestDTO.setText("Test message");

        User user = new User();
        Message message = new Message();
        message.setSender(user);
        message.setText(requestDTO.getText());

        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setSenderId(userId);
        responseDTO.setText(requestDTO.getText());

        when(userRepository.getById(userId)).thenReturn(user);
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        when(messageMapper.messageToMessageResponseDTO(message)).thenReturn(responseDTO);

        MessageResponseDTO result = messageService.sendMessage(userId, requestDTO);

        assertEquals(responseDTO, result);
    }

    @Test
    public void findAllMessagesBetweenUsersTest() {
        Long senderId = 2L;
        Long recipientId = 2L;
        Message message = new Message();
        message.setSendDate(LocalDateTime.now());
        MessageResponseDTO responseDTO = new MessageResponseDTO();

        when(messageRepository.findAllBySender_IdAndRecipient_Id(senderId, recipientId)).thenReturn(Arrays.asList(message));
        when(messageRepository.findAllBySender_IdAndRecipient_Id(recipientId, senderId)).thenReturn(Arrays.asList(message));
        when(messageMapper.messageToMessageResponseDTO(message)).thenReturn(responseDTO);

        List<MessageResponseDTO> result = messageService.findAllMessagesBetweenUsers(senderId, recipientId);

        assertEquals(2, result.size());
        assertEquals(responseDTO, result.get(0));
        assertEquals(responseDTO, result.get(1));
    }
}
