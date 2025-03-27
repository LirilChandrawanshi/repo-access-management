package com.example.repo_access_system.service;

import com.example.repo_access_system.model.UserGroup; // ✅ Fix: Use UserGroup
import com.example.repo_access_system.repository.UserGroupRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {
    private final UserGroupRepository groupRepository;

    public GroupService(UserGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<UserGroup> getAllGroups() { // ✅ Fix: Use UserGroup
        return groupRepository.findAll();
    }

    public UserGroup createGroup(UserGroup group) { // ✅ Fix: Use UserGroup
        return groupRepository.save(group);
    }
}