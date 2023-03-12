package com.example.meetexApi.repository;

import com.example.meetexApi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
