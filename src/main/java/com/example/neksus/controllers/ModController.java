package com.example.neksus.controllers;

import com.example.neksus.models.Image;
import com.example.neksus.models.Mod;
import com.example.neksus.models.User;
import com.example.neksus.services.*;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final ImageService imageService;
    private final FileSaveService fileSaveService;

    @Autowired
    public ModController(ModService modService, GameService gameService, ChangelogsService changelogsService, FilesService filesService, NewsService newsService, VideoService videoService, CommentService commentService, TrackedModService trackedModService, UserService userService, ImageService imageService, FileSaveService fileSaveService) {
        this.modService = modService;
        this.gameService = gameService;
        this.changelogsService = changelogsService;
        this.filesService = filesService;
        this.newsService = newsService;
        this.videoService = videoService;
        this.commentService = commentService;
        this.trackedModService = trackedModService;
        this.userService = userService;
        this.imageService = imageService;
        this.fileSaveService = fileSaveService;
    }

    //prepares mods, their thumbnails and authors and returns view "mods"
    @RequestMapping("/games/{gameId}")
    public String getModsByGameId(@PathVariable("gameId") Long gameId, Model model) {
        List<Mod> mods = modService.getModsByGameId(gameId);
        model.addAttribute("mods", mods);
        List<Image> images = new ArrayList<>();
        List<User> authors = new ArrayList<>();
        for (Mod mod: mods) {
            Optional<User> optional = userService.findByEmail(mod.getAuthor());
            if (optional.isPresent()){
                authors.add(optional.get());
            }else continue;
            images.add(imageService.getImageById(Long.parseLong(mod.getThumbnailImageId())));
        }
        model.addAttribute("images", images);
        model.addAttribute("authors",authors);
        return "mods";
    }

    //prepares all detailed mod information and returns view "modDetails"
    @RequestMapping("/games/{gameId}/{modId}")
    public String getModById(@PathVariable("modId") Long modId, Model model) {
        Mod mod = modService.getModById(modId);
        model.addAttribute("mod",mod);
        model.addAttribute("changelogs", changelogsService.getChangelogsByModId(modId));
        model.addAttribute("files", filesService.getFilesByModId(modId));
        model.addAttribute("news", newsService.getNewsByModId(modId));
        model.addAttribute("videos", videoService.getVideosByModId(modId));
        model.addAttribute("comments", commentService.getCommentsByModId(modId));
        model.addAttribute("images",imageService.getImagesByModId(modId));
        Optional<User> optional = userService.findByEmail(mod.getAuthor());
        if (optional.isPresent()){
            model.addAttribute("author",optional.get());
        }
        return "modDetails";
    }

    //returns the "create" view for creating new mods
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("mod", new Mod());
        model.addAttribute("games", gameService.getAllGames());
        return "create";
    }

    //recieves mod information from user and recirects them back to index
    @PostMapping("/mods/create")
    public String createMod(@ModelAttribute Mod mod, @RequestParam("gameId") Long gameId, @RequestPart("thumbnail") MultipartFile file, Model model) {
        mod.setGameId(gameId); // set the gameId to the mod

        if (fileSaveService.saveFile(file)){
            /*todo re-add when fixed
        boolean isModAdded = modService.addMod(mod);
        if (isModAdded) {
            model.addAttribute("message", "Mod created successfully!");
        } else {
            model.addAttribute("message", "Error creating mod. Please try again.");
        }*/
        }


        return "redirect:/";
    }


}