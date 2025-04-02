package com.example.chatapp.controller;



import com.example.chatapp.model.Message;
import com.example.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages/{topic}")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/{chatId}")
    public Message sendMessage(@PathVariable UUID chatId, @RequestBody Message message) {
        return messageService.sendMessage(chatId, message);
    }

    @GetMapping("/{chatId}")
    public List<Message> getMessages(@PathVariable UUID chatId) {
        return messageService.getMessages(chatId);
    }
}

