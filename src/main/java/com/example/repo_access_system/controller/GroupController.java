package com.example.repo_access_system.controller;

import com.example.repo_access_system.model.UserGroup; // ✅ Fix: Change Group to UserGroup
import com.example.repo_access_system.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<UserGroup> getAllGroups() { // ✅ Fix: Change Group to UserGroup
        return groupService.getAllGroups();
    }

    @PostMapping
    public UserGroup createGroup(@RequestBody UserGroup group) { // ✅ Fix: Change Group to UserGroup
        return groupService.createGroup(group);
    }
}