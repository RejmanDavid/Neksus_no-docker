package com.example.neksus.controllers;

import com.example.neksus.models.News;
import com.example.neksus.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String getAllNews(Model model) {
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("news", newsList);
        return "news";
    }
}
