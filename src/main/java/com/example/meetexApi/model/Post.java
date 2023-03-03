package com.example.meetexApi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sendDate;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToMany
    @JoinColumn(name = "addressee_id")
    private List<User> addressee;

    @Column
    private int reactions;

    @Column
    private int comCount;

}