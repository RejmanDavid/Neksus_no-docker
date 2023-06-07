package com.example.neksus.services;

import com.example.neksus.dao.FilesDAO;
import com.example.neksus.models.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {
    private final FilesDAO filesDAO;

    @Autowired
    public FilesService(FilesDAO filesDAO) {
        this.filesDAO = filesDAO;
    }

    public List<Files> getAllFiles() {
        return filesDAO.getAllFiles();
    }

    public Files getFileById(Long fileId) {
        return filesDAO.getFileById(fileId);
    }

    public List<Files> getFilesByModId(Long modId) {
        return filesDAO.getFilesByModId(modId);
    }

    public boolean addFile(Files file) {
        if (isFileValid(file)) {
            return filesDAO.insertFile(file);
        } else {
            throw new IllegalArgumentException("Invalid file data.");
        }
    }

    public boolean updateFile(Files file) {
        if (isFileValid(file)) {
            return filesDAO.updateFile(file);
        } else {
            throw new IllegalArgumentException("Invalid file data.");
        }
    }

    public boolean deleteFile(Long fileId) {
        return filesDAO.deleteFile(fileId);
    }

    private boolean isFileValid(Files file) {
        if (file.getModId() == null || file.getModId() <= 0) {
            return false;
        }

        if (file.getFilePath() == null || file.getFilePath().trim().isEmpty()) {
            return false;
        }

        if (file.getVersion() == null || file.getVersion().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
