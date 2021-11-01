package com.postnov.recipecatalog.store.impl;

import com.postnov.recipecatalog.model.Picture;
import com.postnov.recipecatalog.store.FileStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

@Service
public class PictureStoreImpl implements FileStore<Picture> {

    @Value("${application.properties.picture-path}")
    String picturePath;

    @Override
    public void saveFile(MultipartFile multipartFile, String name) {
        Objects.requireNonNull(multipartFile, "File not found: file is null");
        File file = new File(System.getProperty("user.dir") + picturePath + name);
        try (OutputStream outputStream = new FileOutputStream(file);
             BufferedOutputStream stream = new BufferedOutputStream(outputStream)) {
            byte[] bytes = multipartFile.getBytes();
            stream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Save file exception: ", e);
        }
    }
}
