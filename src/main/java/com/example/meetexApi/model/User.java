package com.example.meetexApi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String userName;

    @Column(length = 60)
    private String lastName;

    @Column(length = 100)
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_friends",
            joinColumns = {@JoinColumn(name = "user_id", unique = false)},
            inverseJoinColumns = {@JoinColumn(name = "friend_id", unique = false)}
    )
    @Nullable
    private List<User> friends;

    private int friendsCount;
    private int msgCount;
    private int notCount;

    @Column
    private String matchingPassword;

    private boolean logged;
    private boolean active;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
