package com.spring.jwt.utils;


import io.jsonwebtoken.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;


@Component
public class FileStorageUtil {
    public static String saveFile(MultipartFile file, Integer userId, String prefix) throws IOException, java.io.IOException {
        String uploadDir="uploads/"+ userId+"/";
        File folder=new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs(); // Create directory if not exists
        }
        String finalPath=uploadDir +prefix+"_"+file.getOriginalFilename();

        File destination=new File(finalPath);
        file.transferTo(destination);
        return finalPath;
    }


}
