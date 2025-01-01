package com.example.ycyw.security.service;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ycyw.models.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * UserDetailsImpl est utilisé pour implémenter UserDetails et pour charger les
 * informations de l'utilisateur
 */
public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    /*
     * id de l'utilisateur
     */
    private final Integer id;

    /*
     * email de l'utilisateur
     */
    private final String email;

    /*
     * nom de l'utilisateur
     */
    private final String nom;

    /*
     * mot de passe de l'utilisateur
     */
    @JsonIgnore
    private final String mot_de_passe;

    /*
     * les roles de l'utilisateur
     */
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor
     * 
     * @param id
     * @param username
     * @param email
     * @param password
     * @param authorities
     */

    public UserDetailsImpl(Long id, String nom, String email, String mot_de_passe,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id.intValue();
        this.nom = nom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.authorities = authorities;
    }

    /**
     * build qui permet de construire un UserDetailsImpl à partir d'un utilisateur
     * 
     * @param user
     * @return UserDetailsImpl
     */
    public static UserDetailsImpl build(Utilisateur user) {
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getNom(),
                user.getEmail(),
                user.getMot_de_passe(),
                authorities);
    }

    /*
     * Cette méthode retourne les roles de l'utilisateur
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String getPassword() {
        return mot_de_passe;
    }

    @Override
    public String getUsername() {
        return nom;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}