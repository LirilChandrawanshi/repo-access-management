package com.example.repo_access_system.controller;

import com.example.repo_access_system.model.UserGroup; // ✅ Fix: Change Group to UserGroup
import com.example.repo_access_system.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GroupViewController {
    private final GroupService groupService;

    public GroupViewController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public String showGroups(Model model) {
        List<UserGroup> groups = groupService.getAllGroups(); // ✅ Fix: Change Group to UserGroup
        model.addAttribute("groups", groups);
        return "groups"; // This will render groups.html
    }
}