package com.example.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.chatapp.model.Chat;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}

