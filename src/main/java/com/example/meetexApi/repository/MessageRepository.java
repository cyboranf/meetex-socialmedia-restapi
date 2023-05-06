package com.example.meetexApi.repository;

import com.example.meetexApi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySender_IdAndRecipient_Id(Long senderId, Long recipientId);

}
