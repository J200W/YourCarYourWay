package com.example.ycyw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ycyw.models.Utilisateur;
import com.example.ycyw.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
