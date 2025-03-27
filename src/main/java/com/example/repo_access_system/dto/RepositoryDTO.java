package com.example.repo_access_system.dto;

public class RepositoryDTO {
    private Long id;
    private String name;

    public RepositoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}