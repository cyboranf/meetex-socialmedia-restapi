package com.example.meetexApi.service;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.dto.message.MessageResponseDTO;
import com.example.meetexApi.model.Message;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.MessageRepository;
import com.example.meetexApi.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    public MessageResponseDTO sendMessage(Long userId, MessageRequestDTO messageRequestDTO) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        User recipient = userRepository.findById(messageRequestDTO.getRecipientId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + messageRequestDTO.getRecipientId()));

        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setText(messageRequestDTO.getText());
        message.setSendDate(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setId(savedMessage.getId());
        messageResponseDTO.setText(savedMessage.getText());
        messageResponseDTO.setSendDate(savedMessage.getSendDate());
        messageResponseDTO.setSenderId(savedMessage.getSender().getId());
        messageResponseDTO.setRecipientId(savedMessage.getRecipient().getId());

        return messageResponseDTO;
    }
}
