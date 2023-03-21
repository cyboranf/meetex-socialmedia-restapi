package com.example.meetexApi.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activities_id;
    @Column
    private String name;

}
