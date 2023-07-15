package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Message;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.MessageRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(8)
@Profile("seed")
public class MessageSeeder implements DatabaseSeeder {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageSeeder(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * Message:
     * "1": {
     * "text": "Sample message",
     * "sender": 1,
     * "recipient": 2
     * }
     */

    @Override
    public void run(String... args) throws Exception {
        if (messageRepository.count() == 0) {
            User sender = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            User recipient = userRepository.findById(2L)
                    .orElseThrow(() -> new RuntimeException("Recipient not found"));

            Message message = new Message();
            message.setText("Sample message");
            message.setSendDate(LocalDateTime.now());
            message.setSender(sender);
            message.setRecipient(recipient);

            messageRepository.save(message);
        }
    }
}
