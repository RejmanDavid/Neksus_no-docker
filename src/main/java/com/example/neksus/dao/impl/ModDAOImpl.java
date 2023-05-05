package com.example.neksus.dao.impl;

import com.example.neksus.dao.ModDAO;
import com.example.neksus.models.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ModDAOImpl implements ModDAO {

    @Autowired
    private Connection connection;

    @Override
    public List<Mod> getAllMods() {
        List<Mod> mods = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM MOD");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mod mod = new Mod();
                mod.setId(resultSet.getLong("id"));
                mod.setName(resultSet.getString("name"));
                mod.setDescription(resultSet.getString("description"));
                mod.setAuthorId(resultSet.getLong("author_id"));
                mod.setGameId(resultSet.getLong("game_id"));
                mod.setDateCreated(resultSet.getDate("creation_date"));
                mods.add(mod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mods;
    }

    @Override
    public Mod getModById(long id) {
        Mod mod = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM MOD WHERE MOD_ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mod = new Mod();
                mod.setId(resultSet.getLong("id"));
                mod.setName(resultSet.getString("name"));
                mod.setDescription(resultSet.getString("description"));
                mod.setAuthorId(resultSet.getLong("author_id"));
                mod.setGameId(resultSet.getLong("game_id"));
                mod.setDateCreated(resultSet.getDate("creation_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mod;
    }

    @Override
    public boolean insertMod(Mod mod) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO MOD (MOD_NAME, description, AUTHOR, GAME_ID, DATE_PUBLISHED) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, mod.getName());
            statement.setString(2, mod.getDescription());
            statement.setLong(3, mod.getAuthorId());
            statement.setLong(4, mod.getGameId());
            statement.setDate(5, new java.sql.Date(mod.getDateCreated().getTime()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMod(Mod mod) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE MOD SET MOD_NAME = ?, DESCRIPTION = ?, AUTHOR = ?, GAME_ID = ?, DATE_PUBLISHED = ? WHERE MOD_ID = ?");
            statement.setString(1, mod.getName());
            statement.setString(2, mod.getDescription());
            statement.setLong(3, mod.getAuthorId());
            statement.setLong(4, mod.getGameId());
            statement.setDate(5, new java.sql.Date(mod.getDateCreated().getTime()));
            statement.setLong(6, mod.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMod(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM MOD WHERE MOD_ID = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}