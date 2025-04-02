package com.example.chatapp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String sender;
    private String content;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    @JsonBackReference  // Avoid recursive loop for the "many" side
    private Chat chat;

    // Constructors
    public Message() {}

    public Message(String sender, String content, Chat chat) {
        this.sender = sender;
        this.content = content;
        this.chat = chat;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Chat getChat() { return chat; }
    public void setChat(Chat chat) { this.chat = chat; }
}

