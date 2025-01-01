package com.example.ycyw.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ycyw.models.Utilisateur;
import com.example.ycyw.repository.UtilisateurRepository;

/**
 * Le service UserDetailsServiceImpl est utilisé pour charger les détails de
 * l'utilisateur
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /*
     * UserRepository est utilisé pour accéder aux utilisateur de la base de données
     */
    @Autowired
    UtilisateurRepository userRepository;

    /**
     * Cette méthode est utilisée pour charger les informations de l'utilisateur
     * avec son email à partir de la base de données lors de l'authentification
     *
     * @param email est l'email de l'utilisateur
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByEmail(email);
        if (user == null) {
            user = userRepository.findByEmail(email);
        }
        return UserDetailsImpl.build(user);
    }
}