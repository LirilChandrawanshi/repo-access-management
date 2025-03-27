package com.example.repo_access_system.repository;

import com.example.repo_access_system.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
}