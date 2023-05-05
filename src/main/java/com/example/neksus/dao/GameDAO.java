package com.example.neksus.dao;

import com.example.neksus.models.Game;

import java.util.List;

public interface GameDAO {
    List<Game> getAllGames();

    Game getGameById(long id);

    boolean insertGame(Game game);

    boolean updateGame(Game game);

    boolean deleteGame(long id);
}