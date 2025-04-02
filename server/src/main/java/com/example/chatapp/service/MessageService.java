package com.example.chatapp.service;


import com.example.chatapp.model.Message;
import com.example.chatapp.model.Chat;
import com.example.chatapp.repository.MessageRepository;
import com.example.chatapp.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }


    // Method to save the message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);  // Persist message to DB
    }

    public Message sendMessage(UUID chatId, Message message) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        message.setChat(chat);
        return messageRepository.save(message);
    }

    public List<Message> getMessages(UUID chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        return chat.getMessages();
    }
}

