package com.example.neksus.services;

import com.example.neksus.models.FileSave;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileSaveService {
    public String saveFile(MultipartFile file){
        return FileSave.SaveFile(file);
    }
}
