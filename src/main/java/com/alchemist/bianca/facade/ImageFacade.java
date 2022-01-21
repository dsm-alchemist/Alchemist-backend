package com.alchemist.bianca.facade;

import com.alchemist.bianca.able.Savable;
import com.alchemist.bianca.entity.image.Image;
import com.alchemist.bianca.entity.image.ImageRepository;
import com.alchemist.bianca.exception.FileSaveFailedException;
import com.alchemist.bianca.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
public class ImageFacade {

    private final S3Util s3Util;

    private final ImageRepository imageRepository;

    public Image saveAttachment(MultipartFile file) {
        Optional<String> savedFile = saveFileToStorage(file, "attachment");

        if (savedFile.isEmpty()) {
            throw new FileSaveFailedException();
        }

        return saveFileToDatabase(savedFile.get(), file.getOriginalFilename());
    }

    public <T extends Savable> void saveAttachment(T entity, MultipartFile file, Consumer<Image> consumer) {
        Optional<String> savedFile = saveFileToStorage(file, entity.getDirectoryName() + "/" + entity.getId());
        if (savedFile.isPresent()) {
            Image image = saveFileToDatabase(savedFile.get(), file.getOriginalFilename());
            consumer.accept(image);
        }
    }

    private Optional<String> saveFileToStorage(MultipartFile file, String directoryName) {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
            return Optional.empty();
        }
        return Optional.of(s3Util.saveFile(file, directoryName));
    }

    private Image saveFileToDatabase(String fileName, String originalFileName) {
        return imageRepository.save(Image.builder()
                .fileName(fileName)
                .originalFileName(originalFileName)
                .build());
    }
}
