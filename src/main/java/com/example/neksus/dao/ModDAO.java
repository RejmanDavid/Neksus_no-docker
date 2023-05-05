package com.example.neksus.dao;

import com.example.neksus.models.Mod;

import java.util.List;

public interface ModDAO {
    List<Mod> getAllMods();

    Mod getModById(long id);

    boolean insertMod(Mod mod);

    boolean updateMod(Mod mod);

    boolean deleteMod(long id);
}