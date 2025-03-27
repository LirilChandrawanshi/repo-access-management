package com.example.repo_access_system.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        if (oAuth2User == null) {
            System.out.println("🚨 No user found in session! Redirecting to login...");
            return "redirect:/login"; // ✅ Fix: Prevent infinite loop
        }

        System.out.println("✅ User Found: " + oAuth2User.getAttributes());

        model.addAttribute("user", oAuth2User);
        return "dashboard"; // ✅ Render dashboard.html
    }
}