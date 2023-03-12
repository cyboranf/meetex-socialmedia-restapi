package com.example.meetexApi.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoggedUser extends org.springframework.security.core.userdetails.User {
    private final User user;

    public LoggedUser(String username, String password, Collection<? extends GrantedAuthority> authorities, User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
