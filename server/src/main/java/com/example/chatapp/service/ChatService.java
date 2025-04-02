package com.example.chatapp.service;


import com.example.chatapp.model.Chat;
import com.example.chatapp.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Chat getChatById(UUID id) {
        return chatRepository.findById(id).orElseThrow(() -> new RuntimeException("Chat not found"));
    }
}
