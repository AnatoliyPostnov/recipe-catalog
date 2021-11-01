package com.postnov.recipecatalog.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    /**
     * Save picture.
     *
     * @param file - input file to be saved in static folder (/src/main/resources/static/)
     * and meta data to database.
     */
    void savePicture(MultipartFile file);
}
