package com.example.meetexApi.service;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.dto.message.MessageResponseDTO;
import com.example.meetexApi.exception.BadRequestException;
import com.example.meetexApi.model.Message;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.MessageRepository;
import com.example.meetexApi.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Message> findAllMessagesBetweenUsers(Long senderId, Long recipientId) {
        List<Message> messages = messageRepository.findAllBySender_IdAndRecipient_Id(senderId, recipientId);
        messages.addAll(messageRepository.findAllBySender_IdAndRecipient_Id(recipientId, senderId));
        messages.sort(Comparator.comparing(Message::getSendDate));
        return messages;
    }

    public List<MessageResponseDTO> toMessageResponseDTOList(List<Message> messages) {
        return messages.stream()
                .map(this::toMessageResponseDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO toMessageResponseDTO(Message message) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setId(message.getId());
        messageResponseDTO.setText(message.getText());
        messageResponseDTO.setSendDate(message.getSendDate());
        messageResponseDTO.setSenderId(message.getSender().getId());
        messageResponseDTO.setRecipientId(message.getRecipient().getId());
        return messageResponseDTO;
    }

    public void deleteMessage(Long messageId, String currentUsername) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found with id: " + messageId));

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + currentUsername));

        if (!currentUser.getId().equals(message.getSender().getId())) {
            throw new BadRequestException("You are not authorized to delete this message");
        }

        messageRepository.deleteById(messageId);
    }


}
