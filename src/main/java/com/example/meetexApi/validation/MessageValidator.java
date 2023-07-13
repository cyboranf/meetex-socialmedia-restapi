package com.example.meetexApi.validation;

import com.example.meetexApi.dto.message.MessageRequestDTO;
import com.example.meetexApi.exception.message.InvalidMessageException;
import com.example.meetexApi.exception.message.MessageNotFoundException;
import com.example.meetexApi.exception.user.UserNotFoundException;
import com.example.meetexApi.model.Message;
import com.example.meetexApi.repository.MessageRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class MessageValidator {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageValidator(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * @param id
     * @return validated Message to delete
     */
    public Message deleteMessageValidation(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException("Can not found message with id = " + id));
    }

    /**
     * @param senderId
     * @param messageRequestDTO
     */
    public void sendMessageValidation(Long senderId, MessageRequestDTO messageRequestDTO) {
        userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + senderId));
        userRepository.findById(messageRequestDTO.getRecipientId())
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + messageRequestDTO.getRecipientId()));
        if (messageRequestDTO.getText() == null || messageRequestDTO.getText().length() > 1000) {
            throw new InvalidMessageException("Can not send an invalid message.");
        }
    }

    /**
     * @param senderId
     * @param recipientId
     */
    public void findAllMessagesBetweenUsersValidation(Long senderId, Long recipientId) {
        userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + senderId));
        userRepository.findById(recipientId)
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + recipientId));
    }
}
