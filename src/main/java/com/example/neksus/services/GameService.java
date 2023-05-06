package com.example.neksus.services;

import com.example.neksus.dao.GameDAO;
import com.example.neksus.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameDAO gameDAO;

    @Autowired
    public GameService(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public List<Game> getAllGames() {
        return gameDAO.getAllGames();
    }

    public Game getGameById(Long id) {
        return gameDAO.getGameById(id);
    }

    public boolean addGame(Game game) {
        return gameDAO.insertGame(game);
    }

    public boolean updateGame(Game game) {
        return gameDAO.updateGame(game);
    }

    public boolean deleteGame(Long id) {
        return gameDAO.deleteGame(id);
    }
}
