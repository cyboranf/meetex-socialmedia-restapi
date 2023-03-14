package com.example.meetexApi.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "notification")
@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @OneToOne
    private User fromUser;
    @OneToOne
    private User toUser;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
