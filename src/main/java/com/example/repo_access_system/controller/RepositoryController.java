package com.example.repo_access_system.controller;

import com.example.repo_access_system.model.Repository;
import com.example.repo_access_system.service.RepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/repositories")
public class RepositoryController {
    private final RepositoryService repositoryService;

    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping
    public String getRepositories(Model model) {
        List<Repository> repositories = repositoryService.getAllRepositories();
        model.addAttribute("repositories", repositories);
        return "repositories";
    }
}