package com.example.neksus.services;

import com.example.neksus.dao.ModDAO;
import com.example.neksus.models.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModService {
    private final ModDAO modDAO;

    @Autowired
    public ModService(ModDAO modDAO) {
        this.modDAO = modDAO;
    }

    public List<Mod> getAllMods() {
        return modDAO.getAllMods();
    }

    public Mod getModById(Long modId) {
        return modDAO.getModById(modId);
    }

    public boolean addMod(Mod mod) {
        return modDAO.insertMod(mod);
    }

    public boolean updateMod(Mod mod) {
        return modDAO.updateMod(mod);
    }

    public boolean deleteMod(Long modId) {
        return modDAO.deleteMod(modId);
    }

    public List<Mod> getModsByGameId(Long gameId) {
        return modDAO.getModsByGameId(gameId);
    }
}