package com.example.meetexApi.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne
    @Column(name = "owner", nullable = false)
    private User owner;
    @ManyToOne
    @Column(name = "contributors", nullable = false)
    private User contributors;
}
