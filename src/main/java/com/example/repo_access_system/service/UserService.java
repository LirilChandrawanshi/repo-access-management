package com.example.repo_access_system.service;
import com.example.repo_access_system.model.UserGroup; // ✅ Fix: Replace Group with UserGroup
import com.example.repo_access_system.model.User;
import com.example.repo_access_system.repository.UserGroupRepository; // ✅ Fix: Rename Repository
import com.example.repo_access_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserGroupRepository groupRepository; // ✅ Fix: Use Correct Repository Name
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserGroupRepository groupRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // ✅ Ensure role is either USER or ADMIN
        if (role == null || role.isEmpty()) {
            role = "USER";
        } else if (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("USER")) {
            role = "USER";
        }

        user.setRole(role);
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ✅ Fix: Add User to UserGroup Method
    public void addUserToGroup(Long userId, Long groupId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<UserGroup> groupOpt = groupRepository.findById(groupId); // ✅ Fix: Use UserGroup

        if (userOpt.isPresent() && groupOpt.isPresent()) {
            User user = userOpt.get();
            UserGroup group = groupOpt.get(); // ✅ Fix: Change Group to UserGroup

            user.getGroups().add(group);
            group.getUsers().add(user);

            userRepository.save(user);
            groupRepository.save(group);
        } else {
            throw new RuntimeException("User or Group not found");
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}