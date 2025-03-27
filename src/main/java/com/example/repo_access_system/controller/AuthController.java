package com.example.repo_access_system.controller;
import com.example.repo_access_system.model.User;
import com.example.repo_access_system.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        String role = user.getRole();

        // ✅ Default role USER if not provided
        if (role == null || role.isEmpty()) {
            role = "USER";
        }

        userService.registerUser(user.getUsername(), user.getPassword(), role);
        return ResponseEntity.ok("User registered successfully with role: " + role);
    }
}