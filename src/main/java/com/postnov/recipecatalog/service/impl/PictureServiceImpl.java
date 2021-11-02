package com.postnov.recipecatalog.service.impl;

import com.postnov.recipecatalog.model.Picture;
import com.postnov.recipecatalog.repository.PictureRepository;
import com.postnov.recipecatalog.service.PictureService;
import com.postnov.recipecatalog.store.FileStore;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    @Value("${application.properties.random-string-size}")
    int randomStringSize;

    private final PictureRepository repository;
    private final FileStore<Picture> pictureStore;

    @Autowired private PictureServiceImpl self;

    @Override
    @Transactional
    public void savePicture(MultipartFile file) {
        String[] nameWithExtension = checkFileFormatAndReturnNameWithExtension(file);
        String name = nameWithExtension[0].concat(RandomStringUtils.random(randomStringSize));
        String extension = nameWithExtension[1];
        Optional<Picture> pictureOpt = repository.findById(name);
        if (pictureOpt.isEmpty()) {
            Picture picture = Picture
                    .builder()
                    .name(name)
                    .extension(extension)
                    .build();
            repository.save(picture);
            pictureStore.saveFile(file, name + "." + extension);
        } else {
            self.savePicture(file);
        }
    }

    private String[] checkFileFormatAndReturnNameWithExtension(MultipartFile file) {
        Objects.requireNonNull(file, "File not found: file is null");
        Objects.requireNonNull(file.getOriginalFilename(), "file.getOriginalFilename() is null");
        String[] nameWithExtension = file.getOriginalFilename().split("\\.");
        if (nameWithExtension.length != 2) {
            throw new RuntimeException("Invalid input file format");
        }
        return nameWithExtension;
    }
}
