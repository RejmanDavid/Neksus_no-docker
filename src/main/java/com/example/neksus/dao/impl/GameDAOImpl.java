package com.example.neksus.dao.impl;

import com.example.neksus.dao.GameDAO;
import com.example.neksus.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDAOImpl implements GameDAO {

    @Autowired
    private Connection connection;

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM GAME");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Game game = new Game();
                game.setId(resultSet.getLong("id"));
                game.setName(resultSet.getString("name"));
                games.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    @Override
    public Game getGameById(long id) {
        Game game = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM GAME WHERE GAME_ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                game = new Game();
                game.setId(resultSet.getLong("GAME_ID"));
                game.setName(resultSet.getString("GAME_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }

    @Override
    public boolean insertGame(Game game) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO GAME (GAME_NAME) VALUES (?)");
            statement.setString(1, game.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateGame(Game game) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE GAME SET GAME_NAME = ? WHERE GAME_ID = ?");
            statement.setString(1, game.getName());
            statement.setLong(2, game.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteGame(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM GAME WHERE GAME_ID = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
