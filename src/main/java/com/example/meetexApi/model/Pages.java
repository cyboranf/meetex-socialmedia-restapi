package com.example.meetexApi.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table
@Data
public class Pages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @Nullable
    private User contributors;

}
