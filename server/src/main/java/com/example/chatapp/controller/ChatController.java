package com.example.chatapp.controller;


import com.example.chatapp.model.Message;
import com.example.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import com.example.chatapp.model.Chat;
import com.example.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatService chatService;
@Autowired
private MessageService messageService;

// Handle sending messages via WebSocket
@MessageMapping("/sendMessage/{chatId}")  // Include chatId in the endpoint
@SendTo("/topic/{chatId}")  // Send the message to the correct chat
public Message sendMessage(@DestinationVariable UUID chatId, Message message) {
    // Associate message with the correct chat
    return messageService.sendMessage(chatId,message);
}




    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Chat createChat(@RequestBody Chat chat) {
        return chatService.createChat(chat);
    }

    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public Chat getChatById(@PathVariable UUID id) {
        return chatService.getChatById(id);
    }
}


//
//import com.example.chatapp.model.Message;
//import com.example.chatapp.service.MessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatController {
//
//    @Autowired
//    private MessageService messageService;
//
//    // Handle sending messages via WebSocket
//    @MessageMapping("/sendMessage") // WebSocket endpoint for sending messages
//    @SendTo("/topic/{chatId}") // Broadcast the message to all users in the specific chat
//    public Message sendMessage(Message message) {
//        // Save the message in the database
//        return messageService.saveMessage(message); // Returns the saved message
//    }
//
//    // Other HTTP methods for loading chat history, creating chats, etc.
//}
