package com.example.neksus.services;

import com.example.neksus.models.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();

    Game getGameById(Long id);

    boolean addGame(Game game);

    boolean updateGame(Game game);

    boolean deleteGame(Long id);
}
