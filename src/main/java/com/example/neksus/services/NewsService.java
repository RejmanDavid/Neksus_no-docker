package com.example.neksus.services;

import com.example.neksus.dao.NewsDAO;
import com.example.neksus.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsDAO newsDAO;

    @Autowired
    public NewsService(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    public List<News> getAllNews() {
        return newsDAO.getAllNews();
    }

    public News getNewsById(Long id) {
        return newsDAO.getNewsById(id);
    }

    public boolean addNews(News news) {
        validateNews(news);
        return newsDAO.insertNews(news);
    }

    public boolean updateNews(News news) {
        validateNews(news);
        return newsDAO.updateNews(news);
    }

    public boolean deleteNews(Long id) {
        return newsDAO.deleteNews(id);
    }

    public List<News> getNewsByModId(Long modId) {
        return newsDAO.getNewsByModId(modId);
    }


    private void validateNews(News news) {
        if (news.getDescription() == null || news.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("News description is required");
        }

        if (news.getHeadline() == null || news.getHeadline().trim().isEmpty()) {
            throw new IllegalArgumentException("News headline is required");
        }

        if (news.getImagePath() == null || news.getImagePath().trim().isEmpty()) {
            throw new IllegalArgumentException("News image path is required");
        }

        if (news.getReleaseDate() == null) {
            throw new IllegalArgumentException("News release date is required");
        }

        if (news.getAuthor() == null || news.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("News author is required");
        }
    }
}
