package com.example.repo_access_system.service;

import com.example.repo_access_system.model.User;
import com.example.repo_access_system.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // ✅ Load user from OAuth2 provider
        OAuth2User oAuth2User = new org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService().loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        // ✅ Check if user exists, if not save
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setUsername((String) attributes.get("name")); // Ensure correct attribute key
            newUser.setEmail(email);
            newUser.setPassword(""); // OAuth users don't need passwords
            newUser.setRole("USER"); // Default role
            return userRepository.save(newUser);
        });

        // ✅ Set authorities for the user
        Set<OAuth2UserAuthority> authorities = Collections.singleton(new OAuth2UserAuthority(attributes));
        return new DefaultOAuth2User(authorities, attributes, "email");
    }
}