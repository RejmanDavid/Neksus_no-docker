package com.example.neksus.controllers;

import com.example.neksus.models.Mod;
import com.example.neksus.models.News;
import com.example.neksus.models.User;
import com.example.neksus.services.ModService;
import com.example.neksus.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    //shows view "news" with all the news
    @GetMapping("/news")
    public String getAllNews(Model model) {
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("news", newsList);
        return "news";
    }

    //shows page "createNews" for creating new news
    @RequestMapping("/news/create")
    public String getNewsCreatePage() {
        return "createNews";
    }

    //receives news information from user and redirects them to the news page
    @PostMapping("/news/create")
    public String postNews(String author, String headline, String description,String imagePath) {
        News news = new News();
        news.setAuthor(author);
        news.setHeadline(headline);
        news.setDescription(description);
        news.setImagePath(imagePath);
        news.setPinned(false);
        newsService.addNews(news);

        return "redirect:/news";
    }
}
