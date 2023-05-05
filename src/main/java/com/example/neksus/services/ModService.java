package com.example.neksus.services;

import com.example.neksus.models.Mod;
import java.util.List;

public interface ModService {
    List<Mod> getAllMods();
    Mod getModById(Long id);
    boolean addMod(Mod mod);
    boolean updateMod(Mod mod);
    boolean deleteMod(Long id);
}