package com.example.meetexApi.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(length = 60, unique = true)
    private String email;

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true, length = 30)
    private String userName;

    @Column(length = 60)
    private String lastName;

    @NotBlank
    @Size(min = 6, max = 100)
    @Column(length = 100)
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_friends", joinColumns = {@JoinColumn(name = "user_id", unique = false)}, inverseJoinColumns = {@JoinColumn(name = "friend_id", unique = false)})
    @Nullable
    private List<User> friends;

    private int friendsCount;
    private int msgCount;
    private int notCount;
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(mappedBy = "members")
    private Set<Community> communities;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_activities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activities_id"))
    private Set<Activity> interests;
}