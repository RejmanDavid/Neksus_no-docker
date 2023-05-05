package com.example.neksus.services.impl;

import com.example.neksus.dao.GameDAO;
import com.example.neksus.dao.impl.GameDAOImpl;
import com.example.neksus.models.Game;
import com.example.neksus.services.GameService;

import java.util.List;

public class GameServiceImpl implements GameService {
    private GameDAO gameDAO;

    public GameServiceImpl() {
        gameDAO = new GameDAOImpl();
    }

    @Override
    public List<Game> getAllGames() {
        return gameDAO.getAllGames();
    }

    @Override
    public Game getGameById(Long id) {
        return gameDAO.getGameById(id);
    }

    @Override
    public boolean addGame(Game game) {
        return gameDAO.insertGame(game);
    }

    @Override

    public boolean updateGame(Game game) {
        return gameDAO.updateGame(game);
    }

    @Override
    public boolean deleteGame(Long id) {
        return gameDAO.deleteGame(id);
    }
}
