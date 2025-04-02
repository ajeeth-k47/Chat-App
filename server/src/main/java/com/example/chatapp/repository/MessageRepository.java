package com.example.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.chatapp.model.Message;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}

