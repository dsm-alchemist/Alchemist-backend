package com.alchemist.bianca.controller.image;

import com.alchemist.bianca.dto.image.response.SaveImageResponse;
import com.alchemist.bianca.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.CREATED)
    SaveImageResponse attachment(@RequestParam("file") MultipartFile file) {
        return imageService.saveAttachment(file);
    }
}
