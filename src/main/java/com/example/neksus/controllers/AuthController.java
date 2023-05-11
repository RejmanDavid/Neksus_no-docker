package com.example.neksus.controllers;

import com.example.neksus.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.neksus.services.UserService;

import java.util.Date;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to handle GET requests to the "/login" URL. It returns the name of the login view.
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Method to handle GET requests to the "/register" URL. It initializes a new User object and returns the name of the registration view.
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Method to handle POST requests to the "/register" URL. It validates the password confirmation, sets the user's properties,
    // and saves the new User object to the database.
    @PostMapping("/register")
    public String register(User user, @RequestParam("confirmPassword") String confirmPassword) {
        // Check if the password and its confirmation are the same.
        if (!user.getPassword().equals(confirmPassword)) {
            return "redirect:/register?error=passwordMismatch";
        }

        // Set default values for the new user. The user isn't banned or an admin, and the registration date is set to the current time.
        user.setIsBanned('N');
        user.setIsAdmin('N');
        user.setRegisterDate(new Date());
        // Encode the password and set it to the user.
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the database.
        userService.create(user); // The password is already encoded
        // Redirect the user to the login page.
        return "redirect:/login";
    }
}
