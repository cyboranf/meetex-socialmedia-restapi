package com.example.meetexApi.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table
@Data
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String nameOfActivity;
    @ManyToOne
    @Nullable
    private User user;
}
