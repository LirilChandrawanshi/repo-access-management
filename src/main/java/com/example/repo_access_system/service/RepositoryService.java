package com.example.repo_access_system.service;

import com.example.repo_access_system.model.Repository;
import com.example.repo_access_system.model.User;
import com.example.repo_access_system.repository.RepositoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryService(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    public List<Repository> getAllRepositories() {
        return repositoryRepository.findAll();
    }

    public Repository createRepository(Repository repo) {
        return repositoryRepository.save(repo);
    }

    // ✅ FIX: Get repositories for a specific user
    public List<Repository> getRepositoriesByUser(User user) {
        Set<Long> userGroupIds = user.getGroups().stream()
                .map(group -> group.getId())
                .collect(java.util.stream.Collectors.toSet());

        return repositoryRepository.findAll().stream()
                .filter(repo -> userGroupIds.contains(repo.getGroup().getId()))
                .toList();
    }
}