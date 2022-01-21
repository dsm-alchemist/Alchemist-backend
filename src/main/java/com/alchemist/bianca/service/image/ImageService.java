package com.alchemist.bianca.service.image;

import com.alchemist.bianca.dto.image.response.SaveImageResponse;
import com.alchemist.bianca.entity.image.Image;
import com.alchemist.bianca.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageFacade attachmentFacade;

    public SaveImageResponse saveAttachment(MultipartFile file) {
        Image image = attachmentFacade.saveAttachment(file);

        return SaveImageResponse.builder()
                .id(image.getId())
                .fileName(image.getFileName())
                .originalFileName(image.getOriginalFileName())
                .build();
    }
}
