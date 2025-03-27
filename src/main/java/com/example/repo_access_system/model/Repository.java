package com.example.repo_access_system.model;

import jakarta.persistence.*;
@Entity
@Table(name = "repositories")
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "repo_name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private UserGroup group;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UserGroup getGroup() { return group; }
    public void setGroup(UserGroup group) { this.group = group; }
}