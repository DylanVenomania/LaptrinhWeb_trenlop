package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.model.ChatMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController 
{
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private static final List<ChatMessage> chatLogs = new ArrayList<>(); // In-memory log cho admin

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        logger.info("Received message from {}: {}", chatMessage.getSender(), chatMessage.getContent()); // Log
        chatLogs.add(chatMessage); // Lưu log
        return chatMessage;
    }

    // Getter cho log (dùng ở admin controller nếu cần)
    public static List<ChatMessage> getChatLogs() 
    {
        return new ArrayList<>(chatLogs); // Trả copy để an toàn
    }
    
}