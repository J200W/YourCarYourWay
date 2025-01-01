package com.example.ycyw.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ycyw.dtos.MessageDTO;
import com.example.ycyw.models.Message;
import com.example.ycyw.models.Utilisateur;
import com.example.ycyw.security.service.UserDetailsImpl;
import com.example.ycyw.services.MessageService;
import com.example.ycyw.services.UtilisateurService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/api/messages")
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;

    private final UtilisateurService utilisateurService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String email = utilisateurService.findByEmail(userDetails.getEmail()).getEmail();
        Utilisateur utilisateur = utilisateurService.findByEmail(email);

        MessageDTO messageDto = new MessageDTO(
                message.getContenu(),
                message.getDate(),
                utilisateur);
        // Return response and object message
        return ResponseEntity.ok(messageService.sendMessage(messageDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Message>> getMessages(HttpServletResponse response) {
        List<Message> messages = messageService.getMessages();
        return ResponseEntity.ok(messages);
    }
}
