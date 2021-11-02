package com.postnov.recipecatalog.store;

import org.springframework.web.multipart.MultipartFile;

/**
 * File store interface.
 *
 * @param <S> - type of file.
 */
public interface FileStore<S> {
    /**
     * Save picture.
     *
     * @param file - input file to be saved in static folder (/src/main/resources/static/).
     */
    void saveFile(MultipartFile file, String name);
}