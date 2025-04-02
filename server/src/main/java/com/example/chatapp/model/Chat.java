package com.example.chatapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String topic;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Avoid recursive loop for the "one" side
    private List<Message> messages;

    // Constructors
    public Chat() {}

    public Chat(String topic) {
        this.topic = topic;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }
}

