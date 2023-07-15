package com.example.meetexApi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import org.springframework.lang.Nullable;
import javax.persistence.*;

import java.util.HashSet;
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_friends",
            joinColumns = {@JoinColumn(name = "user_id", unique = false)},
            inverseJoinColumns = {@JoinColumn(name = "friend_id", unique = false)})
    @Nullable
    private List<User> friends;

    private int friendsCount;
    private int msgCount;
    private int notCount;
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Community> communities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @Nullable
    @JoinTable(name = "user_activity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "sent_friend_requests",
            joinColumns = @JoinColumn(name = "sender_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id"))
    private List<User> sentFriendRequests;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "received_friend_requests",
            joinColumns = @JoinColumn(name = "receiver_id"),
            inverseJoinColumns = @JoinColumn(name = "sender_id"))
    private List<User> receivedFriendRequests;
    public User() {
        roles = new HashSet<>();
    }
}