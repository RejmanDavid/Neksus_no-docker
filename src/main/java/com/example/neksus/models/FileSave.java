package com.example.neksus.models;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class FileSave {

    public static String SaveFile(MultipartFile file){
        Random random = new Random();
        int number;
        String basePath = new File("").getAbsolutePath()+"/target/classes/static/img/userContent/Images";
        try {
            Files.createDirectories(Path.of(basePath));
        } catch (IOException e) {
            return null;
        }

        File savedFile;
        do {
            number = random.nextInt(0,1000000000);
            savedFile = new File(basePath +"/"+ number +file.getOriginalFilename());
        } while(savedFile.exists());

        try {
            //file.transferTo(savedFile);
            file.transferTo(new File(basePath +"/"+ number + file.getOriginalFilename()));
        } catch (IOException e) {
            return null;
        }
        return "/img/userContent/Images/" + number + file.getOriginalFilename();
    }
}
