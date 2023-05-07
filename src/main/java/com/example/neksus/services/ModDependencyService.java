package com.example.neksus.services;

import com.example.neksus.dao.ModDependencyDAO;
import com.example.neksus.models.ModDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModDependencyService {
    private final ModDependencyDAO modDependencyDAO;

    @Autowired
    public ModDependencyService(ModDependencyDAO modDependencyDAO) {
        this.modDependencyDAO = modDependencyDAO;
    }

    public boolean addModDependency(ModDependency modDependency) {
        if (isValidModDependency(modDependency)) {
            return modDependencyDAO.insertModDependency(modDependency);
        } else {
            throw new IllegalArgumentException("Invalid mod dependency data.");
        }
    }

    public boolean deleteModDependency(Long modDependencyId) {
        return modDependencyDAO.deleteModDependency(modDependencyId);
    }

    private boolean isValidModDependency(ModDependency modDependency) {
        if (modDependency.getChildModId() == null || modDependency.getChildModId() <= 0) {
            return false;
        }

        if (modDependency.getParentModId() == null || modDependency.getParentModId() <= 0) {
            return false;
        }

        return true;
    }
}
