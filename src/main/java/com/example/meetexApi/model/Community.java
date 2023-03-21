package com.example.meetexApi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;

}
