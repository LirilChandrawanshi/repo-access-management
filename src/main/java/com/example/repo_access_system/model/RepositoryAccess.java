package com.example.repo_access_system.model;
import jakarta.persistence.*;

@Entity

@Table(name = "repository_access")
public class RepositoryAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "repository_id", nullable = false)
    private Repository repository;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Repository getRepository() { return repository; }
    public void setRepository(Repository repository) { this.repository = repository; }
}