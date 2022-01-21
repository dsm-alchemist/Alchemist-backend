package com.alchemist.bianca.dto.image.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveImageResponse {
    private final Integer id;

    private final String fileName;

    private final String originalFileName;
}
