package com.example.neksus.services;

import com.example.neksus.dao.TrackedModDAO;
import com.example.neksus.models.TrackedMod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackedModService {
    private final TrackedModDAO trackedModDAO;

    @Autowired
    public TrackedModService(TrackedModDAO trackedModDAO) {
        this.trackedModDAO = trackedModDAO;
    }

    public List<TrackedMod> getAllTrackedMods() {
        return trackedModDAO.getAllTrackedMods();
    }

    public TrackedMod getTrackedModById(Long id) {
        return trackedModDAO.getTrackedModById(id);
    }

    public boolean addTrackedMod(TrackedMod trackedMod) {
        if (isTrackedModValid(trackedMod)) {
            return trackedModDAO.insertTrackedMod(trackedMod);
        } else {
            throw new IllegalArgumentException("Invalid tracked mod data.");
        }
    }

    public boolean updateTrackedMod(TrackedMod trackedMod) {
        if (isTrackedModValid(trackedMod)) {
            return trackedModDAO.updateTrackedMod(trackedMod);
        } else {
            throw new IllegalArgumentException("Invalid tracked mod data.");
        }
    }

    public boolean followMod(String userId, Long modId) {
        TrackedMod trackedMod = new TrackedMod();
        trackedMod.setUserId(userId);
        trackedMod.setModId(modId);
        // It's better to generate a new unique ID for trackedMod here
        // trackedMod.setId(generateNewId()); // pseudo code
        return addTrackedMod(trackedMod);
    }

    public boolean deleteTrackedMod(Long id) {
        return trackedModDAO.deleteTrackedMod(id);
    }

    private boolean isTrackedModValid(TrackedMod trackedMod) {

        if (trackedMod == null) {
            return false;
        }

        if (trackedMod.getUserId() == null || trackedMod.getUserId().isEmpty()) {
            return false;
        }

        if (trackedMod.getModId() == null || trackedMod.getModId() <= 0) {
            return false;
        }

        return true;
    }
}
