package com.example.ycyw.dtos;

import java.time.LocalDateTime;

import com.example.ycyw.models.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String contenu;
    public LocalDateTime date = LocalDateTime.now();
    private Utilisateur utilisateur;
}
