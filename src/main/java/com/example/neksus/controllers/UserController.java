package com.example.neksus.controllers;

import com.example.neksus.models.User;
import com.example.neksus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String showUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        User user = userService.findByEmail(currentUsername).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/username")
    public String updateUsername(@RequestParam("username") String newUsername) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = auth.getName();

        User user = userService.findByEmail(currentEmail).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        user.setUsername(newUsername);

        userService.update(user);
        return "redirect:/profile";
    }

    @PostMapping("/profile/password")
    public String updatePassword(@RequestParam("password") String newPassword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = auth.getName();

        User user = userService.findByEmail(currentEmail).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        user.setPassword(passwordEncoder.encode(newPassword));

        userService.update(user);
        return "redirect:/profile";
    }
}
