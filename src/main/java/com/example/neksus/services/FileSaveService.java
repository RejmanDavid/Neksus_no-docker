package com.example.neksus.services;

import com.example.neksus.dao.FileSaveDAO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileSaveService {
    public String saveImage(MultipartFile file){
        return FileSaveDAO.SaveFile(file,true);
    }
    public String saveFile(MultipartFile file){
        return FileSaveDAO.SaveFile(file,false);
    }
}
