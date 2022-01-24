package com.alchemist.bianca.service.image;

import com.alchemist.bianca.dto.image.response.SaveImageResponse;
import com.alchemist.bianca.entity.image.Image;
import com.alchemist.bianca.entity.user.User;
import com.alchemist.bianca.entity.user.UserRepository;
import com.alchemist.bianca.exception.UserNotFoundException;
import com.alchemist.bianca.facade.ImageFacade;
import com.alchemist.bianca.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageFacade attachmentFacade;
    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public SaveImageResponse saveAttachment(MultipartFile file) {
        Image image = attachmentFacade.saveAttachment(file);

        User user = userRepository.findById(userFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        user.setImage(image);

        return SaveImageResponse.builder()
                .id(image.getId())
                .fileName(image.getFileName())
                .originalFileName(image.getOriginalFileName())
                .build();
    }
}
