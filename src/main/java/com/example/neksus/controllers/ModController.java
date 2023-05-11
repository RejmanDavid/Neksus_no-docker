package com.example.neksus.controllers;

import com.example.neksus.models.Mod;
import com.example.neksus.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModController {

    private final ModService modService;
    private final GameService gameService;
    private final ChangelogsService changelogsService;
    private final FilesService filesService;
    private final NewsService newsService;
    private final VideoService videoService;
    private final CommentService commentService;
    private final TrackedModService trackedModService;
    private final UserService userService;

    @Autowired
    public ModController(ModService modService, GameService gameService, ChangelogsService changelogsService, FilesService filesService, NewsService newsService, VideoService videoService, CommentService commentService, TrackedModService trackedModService, UserService userService) {
        this.modService = modService;
        this.gameService = gameService;
        this.changelogsService = changelogsService;
        this.filesService = filesService;
        this.newsService = newsService;
        this.videoService = videoService;
        this.commentService = commentService;
        this.trackedModService = trackedModService;
        this.userService = userService;
    }

    @RequestMapping("/games/{gameId}")
    public String getModsByGameId(@PathVariable("gameId") Long gameId, Model model) {
        model.addAttribute("mods", modService.getModsByGameId(gameId));
        return "mods";
    }

    @RequestMapping("/games/{gameId}/{modId}")
    public String getModById(@PathVariable("modId") Long modId, Model model) {
        model.addAttribute("mod", modService.getModById(modId));
        model.addAttribute("changelogs", changelogsService.getChangelogsByModId(modId));
        model.addAttribute("files", filesService.getFilesByModId(modId));
        model.addAttribute("news", newsService.getNewsByModId(modId));
        model.addAttribute("videos", videoService.getVideosByModId(modId));
        model.addAttribute("comments", commentService.getCommentsByModId(modId));
        return "modDetails";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("mod", new Mod());
        model.addAttribute("games", gameService.getAllGames());
        return "create";
    }

    @PostMapping("/mods/create")
    public String createMod(@ModelAttribute Mod mod, @RequestParam("gameId") Long gameId, Model model) {
        mod.setGameId(gameId); // set the gameId to the mod
        boolean isModAdded = modService.addMod(mod);
        if (isModAdded) {
            model.addAttribute("message", "Mod created successfully!");
        } else {
            model.addAttribute("message", "Error creating mod. Please try again.");
        }
        return "redirect:/";
    }


}