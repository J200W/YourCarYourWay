package com.example.ycyw.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ycyw.dtos.MessageDTO;
import com.example.ycyw.models.Message;
import com.example.ycyw.services.MessageService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        // Enregistrez le message dans la base de données
        System.out.println("Message envoyé: " + message);
        MessageDTO messageDto = new MessageDTO(
            message.getContenu(), 
            message.getType(),
            message.getDate(),
            message.getUtilisateur()
        );
        // Return response and object message
        return ResponseEntity.ok(messageService.sendMessage(messageDto));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = messageService.getMessages();
        return ResponseEntity.ok(messages);
    }
}
