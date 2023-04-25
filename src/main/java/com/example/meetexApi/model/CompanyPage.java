package com.example.meetexApi.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Data
public class CompanyPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String logo;
    private String website;
    private Date creationDate;

    @ManyToMany
    @JoinTable(name = "company_page_admins", joinColumns = @JoinColumn(name = "company_page_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Nullable
    private Set<User> admins;
}