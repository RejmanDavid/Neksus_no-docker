package com.example.neksus.services.impl;

import com.example.neksus.dao.ModDAO;
import com.example.neksus.dao.impl.ModDAOImpl;
import com.example.neksus.models.Mod;
import com.example.neksus.services.ModService;

import java.util.List;

public class ModServiceImpl implements ModService {
    private ModDAO modDAO;

    public ModServiceImpl() {
        modDAO = new ModDAOImpl();
    }

    @Override
    public List<Mod> getAllMods() {
        return modDAO.getAllMods();
    }

    @Override
    public Mod getModById(Long id) {
        return modDAO.getModById(id);
    }

    @Override
    public boolean addMod(Mod mod) {
        return modDAO.insertMod(mod);
    }

    @Override
    public boolean updateMod(Mod mod) {
        return modDAO.updateMod(mod);
    }

    @Override
    public boolean deleteMod(Long id) {
        return modDAO.deleteMod(id);
    }
}