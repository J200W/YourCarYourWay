package com.example.ycyw.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ycyw.dtos.MessageDTO;
import com.example.ycyw.models.Message;
import com.example.ycyw.repository.MessageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {
    
    @Autowired
    private final MessageRepository messageRepository;

    public Message sendMessage(MessageDTO messageDto) {
        Message message = new Message();
        message.setContenu(messageDto.getContenu());
        message.setUtilisateur(messageDto.getUtilisateur());
        return messageRepository.save(message);
    }

    public List<Message> getMessages() {
        return messageRepository.findAllByOrderByDateAsc();
    }
}
