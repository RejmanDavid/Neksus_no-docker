package com.example.neksus.models;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

public class FileSave {

    public static boolean SaveFile(MultipartFile file){
        Random random = new Random();
        String basePath = new File("").getAbsolutePath();
        File savedFile;
        do {
            savedFile = new File(basePath+"/src/main/resources/userContent/Images/"+ random.nextInt(0,1000000000) +file.getOriginalFilename());
        } while(savedFile.exists());

        try {
            file.transferTo(savedFile);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
