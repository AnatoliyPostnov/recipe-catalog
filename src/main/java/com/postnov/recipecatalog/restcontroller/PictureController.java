package com.postnov.recipecatalog.restcontroller;

import com.postnov.recipecatalog.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void savePicture(@RequestPart("file") MultipartFile file) {
        pictureService.savePicture(file);
    }
}
