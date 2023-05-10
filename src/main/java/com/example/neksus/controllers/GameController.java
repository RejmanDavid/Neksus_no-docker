package com.example.neksus.controllers;

import com.example.neksus.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String listGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "index";
    }
}
