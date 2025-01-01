package com.example.ycyw.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.ycyw.security.service.UserDetailsImpl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
/**
 * La classe AuthService est le service pour l'authentification.
 * @see IAuthService
 */
public class AuthService {

    private final UtilisateurService utilisateurService;

    public List<String> getRoles(UserDetailsImpl userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public Integer getUserId(UserDetailsImpl userDetails) {
        return userDetails.getId();
    }

}