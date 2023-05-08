package com.example.neksus.dao;

import com.example.neksus.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<News> newsRowMapper = (resultSet, rowNum) -> {
        News news = new News();
        news.setNewsId(resultSet.getLong("NEWS_ID"));
        news.setDescription(resultSet.getString("DESCRIPTION"));
        news.setHeadline(resultSet.getString("HEADLINE"));
        news.setImagePath(resultSet.getString("IMAGE_PATH"));
        news.setPinned(resultSet.getString("IS_PINNED").equals("Y"));
        news.setReleaseDate(resultSet.getDate("RELEASE_DATE").toLocalDate());
        news.setGameId(resultSet.getLong("GAME_ID"));
        news.setModId(resultSet.getLong("MOD_ID"));
        news.setAuthor(resultSet.getString("AUTHOR"));
        return news;
    };

    @Autowired
    public NewsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<News> getAllNews() {
        String sql = "SELECT * FROM N_NEWS";
        return jdbcTemplate.query(sql, newsRowMapper);
    }

    public News getNewsById(Long id) {
        String sql = "SELECT * FROM N_NEWS WHERE NEWS_ID = ?";
        return jdbcTemplate.queryForObject(sql, newsRowMapper, id);
    }

    public boolean insertNews(News news) {
        String sql = "INSERT INTO N_NEWS (DESCRIPTION, HEADLINE, IMAGE_PATH, IS_PINNED, RELEASE_DATE, NEWS_ID, GAME_ID, MOD_ID, AUTHOR) VALUES (?, ?, ?, ?, ?, SEQ_N_NEWS_ID.nextval, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, news.getDescription(), news.getHeadline(), news.getImagePath(),
                news.isPinned() ? "Y" : "N", news.getReleaseDate(), news.getGameId(), news.getModId(), news.getAuthor());
        return rowsAffected > 0;
    }

    public boolean updateNews(News news) {
        String sql = "UPDATE N_NEWS SET DESCRIPTION = ?, HEADLINE = ?, IMAGE_PATH = ?, IS_PINNED = ?, RELEASE_DATE = ?, GAME_ID = ?, MOD_ID = ?, AUTHOR = ? WHERE NEWS_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, news.getDescription(), news.getHeadline(), news.getImagePath(),
                news.isPinned() ? "Y" : "N", news.getReleaseDate(), news.getGameId(), news.getModId(), news.getAuthor(), news.getNewsId());
        return rowsAffected > 0;
    }

    public boolean deleteNews(Long id) {
        String sql = "DELETE FROM N_NEWS WHERE NEWS_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
