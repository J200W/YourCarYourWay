package com.example.ycyw.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.example.ycyw.models.Utilisateur;
import com.example.ycyw.payload.request.LoginRequest;
import com.example.ycyw.payload.response.AuthResponse;
import com.example.ycyw.security.jwt.JwtUtils;
import com.example.ycyw.security.service.UserDetailsImpl;
import com.example.ycyw.services.AuthService;
import com.example.ycyw.services.UtilisateurService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UtilisateurService utilisateurService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/login")
    public AuthResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        // Authentification de l'utilisateur avec l'email et le mot de passe
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailOrUsername(), loginRequest.getPassword()));

        // Mettre l'authentification dans le contexte de sécurité de Spring
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Récupérer les détails de l'utilisateur authentifié
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Récupérer les rôles de l'utilisateur
        List<String> roles = authService.getRoles(userDetails);

        // Retourner la réponse contenant le token JWT et les détails de l'utilisateur
        return new AuthResponse(userDetails.getUsername(), userDetails.getEmail(),
                jwtUtils.generateJwtToken(authentication), roles, HttpStatus.OK.value());
    }

    @GetMapping("/is-logged")
    public Utilisateur isLogged(HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return utilisateurService.findByEmail(userDetails.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("Erreur: Erreur rencontrée lors de la vérification de l'utilisateur !");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        try {
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok("Déconnexion réussie !");
        } catch (Exception e) {
            throw new RuntimeException("Erreur: Erreur rencontrée lors de la déconnexion de l'utilisateur !");
        }
    }
}
