package com.example.neksus.dao;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class FileSaveDAO {

    public static String SaveFile(MultipartFile file,boolean isImage){
        Random random = new Random();
        int number;
        String basePath;

        if (isImage){basePath = new File("").getAbsolutePath()+"/target/classes/static/img/userContent/Images";}
        else {basePath = new File("").getAbsolutePath()+"/target/classes/static/img/userContent/Files";}

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
        if (isImage){
            return "/img/userContent/Images/" + number + file.getOriginalFilename();
        }
        return "/img/userContent/Files/" + number + file.getOriginalFilename();
    }
}
