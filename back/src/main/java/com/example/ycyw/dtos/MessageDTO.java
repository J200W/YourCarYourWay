package com.example.ycyw.dtos;

import com.example.ycyw.models.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String contenu;
    private String type;
    private String date;
    private Utilisateur utilisateur;
}
