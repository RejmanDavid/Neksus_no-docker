package com.example.neksus.dao;

import com.example.neksus.models.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Game> gameRowMapper = (rs, rowNum) -> {
        Game game = new Game();
        game.setId(rs.getLong("GAME_ID"));
        game.setName(rs.getString("GAME_NAME"));
        game.setReleaseDate(rs.getDate("RELEASE_DATE").toLocalDate());
        game.setDescription(rs.getString("DESCRIPTION"));
        game.setDeveloper(rs.getString("DEVELOPER"));
        game.setImagePath(rs.getString("IMAGE_PATH"));
        return game;
    };

    public GameDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Game> getAllGames() {
        String sql = "SELECT * FROM N_GAME";
        return jdbcTemplate.query(sql, gameRowMapper);
    }

    public Game getGameById(long id) {
        String sql = "SELECT * FROM N_GAME WHERE GAME_ID = ?";
        return jdbcTemplate.queryForObject(sql, gameRowMapper, id);
    }

    public boolean insertGame(Game game) {
        String sql = "INSERT INTO N_GAME (GAME_ID, GAME_NAME, RELEASE_DATE, DESCRIPTION, DEVELOPER, IMAGE_PATH) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, game.getId(), game.getName(), game.getReleaseDate(), game.getDescription(), game.getDeveloper(), game.getImagePath());
        return rowsAffected > 0;
    }

    public boolean updateGame(Game game) {
        String sql = "UPDATE N_GAME SET GAME_NAME = ?, RELEASE_DATE = ?, DESCRIPTION = ?, DEVELOPER = ?, IMAGE_PATH = ? WHERE GAME_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, game.getName(), game.getReleaseDate(), game.getDescription(), game.getDeveloper(), game.getImagePath(), game.getId());
        return rowsAffected > 0;
    }

    public boolean deleteGame(long id) {
        String sql = "DELETE FROM N_GAME WHERE GAME_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}

