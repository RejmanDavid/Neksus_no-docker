package com.example.neksus.services;

import com.example.neksus.dao.ChangelogsDAO;
import com.example.neksus.models.Changelogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangelogsService {

    private final ChangelogsDAO changelogsDAO;

    @Autowired
    public ChangelogsService(ChangelogsDAO changelogsDAO) {
        this.changelogsDAO = changelogsDAO;
    }

    public List<Changelogs> getAllChangelogs() {
        return changelogsDAO.getAllChangelogs();
    }

    public Changelogs getChangelogById(Long id) {
        return changelogsDAO.getChangelogById(id);
    }

    public List<Changelogs> getChangelogsByModId(Long modId) {
        return changelogsDAO.getChangelogsByModId(modId);
    }

    public boolean addChangelog(Changelogs changelog) {
        validateChangelogData(changelog);
        return changelogsDAO.insertChangelog(changelog);
    }

    public boolean updateChangelog(Changelogs changelog) {
        validateChangelogData(changelog);
        return changelogsDAO.updateChangelog(changelog);
    }

    public boolean deleteChangelog(Long id) {
        return changelogsDAO.deleteChangelog(id);
    }

    private void validateChangelogData(Changelogs changelog) {
        if (changelog.getDescription() == null || changelog.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Changelog description cannot be empty.");
        }

        if (changelog.getVersion() == null || changelog.getVersion().trim().isEmpty()) {
            throw new IllegalArgumentException("Changelog version cannot be empty.");
        }

        if (changelog.getModId() == null || changelog.getModId() <= 0) {
            throw new IllegalArgumentException("Invalid mod ID.");
        }
    }
}
